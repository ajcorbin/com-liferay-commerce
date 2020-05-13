/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.initializer.util;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordSetSettings;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.io.*;
import com.liferay.dynamic.data.mapping.model.*;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDMFormFactoryHelper;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.ArrayUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Steven Smith
 */
@Component(service = DDMFormImporter.class)
public class DDMFormImporter {

	public void importDDMForms(
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws Exception {

		User user = _userLocalService.fetchUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			_addDDMFormInstance(
				jsonArray.getJSONObject(i), userId, scopeGroupId,
				serviceContext);
		}
	}

	protected void updatePermissions(
			long companyId, String name, String primKey, JSONArray jsonArray)
		throws PortalException {

		if (jsonArray == null) {
			jsonArray = JSONFactoryUtil.createJSONArray(
				"[{\"actionIds\": [\"VIEW\", \"ADD_FORM_INSTANCE_RECORD\"]," +
					"\"roleName\": \"Site Member\", \"scope\": 4}]");
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			int scope = jsonObject.getInt("scope");

			String roleName = jsonObject.getString("roleName");

			Role role = _roleLocalService.getRole(companyId, roleName);

			String[] actionIds = new String[0];

			JSONArray actionIdsJSONArray = jsonObject.getJSONArray("actionIds");

			if (actionIdsJSONArray != null) {
				for (int j = 0; j < actionIdsJSONArray.length(); j++) {
					actionIds = ArrayUtil.append(
						actionIds, actionIdsJSONArray.getString(j));
				}
			}

			_resourcePermissionLocalService.setResourcePermissions(
				companyId, name, scope, primKey, role.getRoleId(), actionIds);
		}
	}

	private void _addDDMFormInstance(
			JSONObject jsonObject, long userId, long scopeGroupId,
			ServiceContext serviceContext)
		throws Exception {

		//DDM Form

		String description = jsonObject.getString("description");
		String name = jsonObject.getString("name");

		Locale locale = serviceContext.getLocale();

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(locale, name);

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(locale, description);

		String jsonForm = jsonObject.getString("ddmStructure");

		String jsonFormSettings = jsonObject.getString("ddmFormSettings");

		DDMStructure ddmStructure = _createDDMStructure(
			nameMap, descriptionMap, jsonForm, jsonFormSettings,
			serviceContext);

		String jsonFormLayout = jsonObject.getString("ddmStructureLayout");

		_updateDDMStructureLayout(
			ddmStructure, ddmStructure.getDDMForm(), jsonFormLayout,
			serviceContext);

		DDMFormValuesDeserializerDeserializeRequest.Builder builder = DDMFormValuesDeserializerDeserializeRequest.Builder.newBuilder(jsonFormLayout, ddmStructure.getDDMForm());
		DDMFormValues settingsDDMFormValues =_ddmFormValuesJSONDeserializer.deserialize(builder.build()).getDDMFormValues();

		DDMFormInstance ddmFormInstance =
			_ddmFormInstanceLocalService.addFormInstance(
				userId, scopeGroupId, ddmStructure.getStructureId(), nameMap,
				descriptionMap, settingsDDMFormValues, serviceContext);

		JSONArray permissionsJSONArray = jsonObject.getJSONArray("permissions");

		if ((permissionsJSONArray != null) &&
			(permissionsJSONArray.length() > 0)) {

			updatePermissions(
				ddmFormInstance.getCompanyId(),
				ddmFormInstance.getModelClassName(),
				String.valueOf(ddmFormInstance.getPrimaryKey()),
				permissionsJSONArray);
		}
		else {

			// Give site members view and add form instance permissions

			updatePermissions(
				ddmFormInstance.getCompanyId(),
				ddmFormInstance.getModelClassName(),
				String.valueOf(ddmFormInstance.getPrimaryKey()), null);
		}
	}

	private List<DDMFormFieldValue> _createDDMFormFieldValues(
			DDMForm ddmForm, String jsonFormSettings)
		throws Exception {

		final List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			jsonFormSettings);

		final Stream<DDMFormField> ddmFormFieldsStream = ddmFormFields.stream();

		return ddmFormFieldsStream.map(
			formField -> {
				DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

				ddmFormFieldValue.setName(formField.getName());

				UnlocalizedValue unlocalizedValue = new UnlocalizedValue(
					jsonObject.getString(formField.getName()));

				ddmFormFieldValue.setValue(unlocalizedValue);

				return ddmFormFieldValue;
			}
		).collect(
			Collectors.toList()
		);
	}

	private DDMStructure _createDDMStructure(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String jsonForm, String jsonFormSettings,
			ServiceContext serviceContext)
		throws Exception {

		DDMFormDeserializerDeserializeRequest.Builder builder = DDMFormDeserializerDeserializeRequest.Builder.newBuilder(jsonForm);
		DDMForm ddmForm = _ddmFormJSONDeserializer.deserialize(builder.build()).getDDMForm();

		DDMFormLayout defaultDDMFormLayout = DDMUtil.getDefaultDDMFormLayout(
			ddmForm);

		defaultDDMFormLayout.setPaginationMode(StringPool.BLANK);

		long classNameId = _classNameLocalService.getClassNameId(
			DDLRecordSet.class);
		long userId = serviceContext.getUserId();
		long groupId = serviceContext.getScopeGroupId();

		DDMStructure ddmStructure = _ddmStructureLocalService.addStructure(
			userId, groupId, DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
			classNameId, StringPool.BLANK, nameMap, descriptionMap, ddmForm,
			defaultDDMFormLayout, StorageType.JSON.toString(),
			DDMStructureConstants.TYPE_AUTO, serviceContext);

		DDLRecordSet ddlRecordSet = _ddlRecordSetLocalService.addRecordSet(
			userId, groupId, ddmStructure.getStructureId(), null, nameMap,
			descriptionMap, DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT,
			DDLRecordSetConstants.SCOPE_FORMS, serviceContext);

		DDMForm settingsDDMForm = new DDMFormFactoryHelper(
			DDLRecordSetSettings.class
		).createDDMForm();

		DDMFormValues ddmFormValues = new DDMFormValues(settingsDDMForm);

		ddmFormValues.setDefaultLocale(serviceContext.getLocale());
		ddmFormValues.addAvailableLocale(serviceContext.getLocale());
		ddmFormValues.setDDMFormFieldValues(
			_createDDMFormFieldValues(settingsDDMForm, jsonFormSettings));

		_ddlRecordSetLocalService.updateRecordSet(
			ddlRecordSet.getRecordSetId(), ddmFormValues);

		return ddmStructure;
	}

	private void _updateDDMStructureLayout(
			DDMStructure structure, DDMForm ddmForm, String jsonFormLayout,
			ServiceContext serviceContext)
		throws PortalException {

		long userId = serviceContext.getUserId();

		DDMFormLayoutDeserializerDeserializeRequest.Builder builder = DDMFormLayoutDeserializerDeserializeRequest.Builder.newBuilder(jsonFormLayout);

		DDMFormLayout formLayout = _ddmFormLayoutJSONDeserializer.deserialize(
				builder.build()).getDDMFormLayout();

		_ddmStructureLocalService.updateStructure(
			userId, structure.getStructureId(), ddmForm, formLayout,
			serviceContext);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDLRecordSetLocalService _ddlRecordSetLocalService;

	@Reference
	private DDMFormInstanceLocalService _ddmFormInstanceLocalService;

	@Reference(target = "(ddm.form.deserializer.type=json)")
	private DDMFormDeserializer _ddmFormJSONDeserializer;

	@Reference(target = "(ddm.form.deserializer.type=json)")
	private DDMFormLayoutDeserializer _ddmFormLayoutJSONDeserializer;

	@Reference(target = "(ddm.form.deserializer.type=json)")
	private DDMFormValuesDeserializer _ddmFormValuesJSONDeserializer;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}