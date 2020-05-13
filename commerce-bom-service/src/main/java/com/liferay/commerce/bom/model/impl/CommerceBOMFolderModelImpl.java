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

package com.liferay.commerce.bom.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.model.CommerceBOMFolderModel;
import com.liferay.commerce.bom.model.CommerceBOMFolderSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CommerceBOMFolder service. Represents a row in the &quot;CommerceBOMFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceBOMFolderModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceBOMFolderImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderImpl
 * @see CommerceBOMFolder
 * @see CommerceBOMFolderModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceBOMFolderModelImpl extends BaseModelImpl<CommerceBOMFolder>
	implements CommerceBOMFolderModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce bom folder model instance should use the {@link CommerceBOMFolder} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceBOMFolder";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceBOMFolderId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "parentCommerceBOMFolderId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "logoId", Types.BIGINT },
			{ "treePath", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceBOMFolderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentCommerceBOMFolderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("treePath", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceBOMFolder (commerceBOMFolderId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentCommerceBOMFolderId LONG,name VARCHAR(75) null,logoId LONG,treePath VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceBOMFolder";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceBOMFolder.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceBOMFolder.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.bom.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.bom.model.CommerceBOMFolder"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.bom.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.bom.model.CommerceBOMFolder"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.bom.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.bom.model.CommerceBOMFolder"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long PARENTCOMMERCEBOMFOLDERID_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceBOMFolder toModel(CommerceBOMFolderSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceBOMFolder model = new CommerceBOMFolderImpl();

		model.setCommerceBOMFolderId(soapModel.getCommerceBOMFolderId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentCommerceBOMFolderId(soapModel.getParentCommerceBOMFolderId());
		model.setName(soapModel.getName());
		model.setLogoId(soapModel.getLogoId());
		model.setTreePath(soapModel.getTreePath());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceBOMFolder> toModels(
		CommerceBOMFolderSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceBOMFolder> models = new ArrayList<CommerceBOMFolder>(soapModels.length);

		for (CommerceBOMFolderSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.bom.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.bom.model.CommerceBOMFolder"));

	public CommerceBOMFolderModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceBOMFolderId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceBOMFolderId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBOMFolderId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBOMFolder.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBOMFolder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceBOMFolderId", getCommerceBOMFolderId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentCommerceBOMFolderId",
			getParentCommerceBOMFolderId());
		attributes.put("name", getName());
		attributes.put("logoId", getLogoId());
		attributes.put("treePath", getTreePath());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceBOMFolderId = (Long)attributes.get("commerceBOMFolderId");

		if (commerceBOMFolderId != null) {
			setCommerceBOMFolderId(commerceBOMFolderId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long parentCommerceBOMFolderId = (Long)attributes.get(
				"parentCommerceBOMFolderId");

		if (parentCommerceBOMFolderId != null) {
			setParentCommerceBOMFolderId(parentCommerceBOMFolderId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		String treePath = (String)attributes.get("treePath");

		if (treePath != null) {
			setTreePath(treePath);
		}
	}

	@JSON
	@Override
	public long getCommerceBOMFolderId() {
		return _commerceBOMFolderId;
	}

	@Override
	public void setCommerceBOMFolderId(long commerceBOMFolderId) {
		_commerceBOMFolderId = commerceBOMFolderId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getParentCommerceBOMFolderId() {
		return _parentCommerceBOMFolderId;
	}

	@Override
	public void setParentCommerceBOMFolderId(long parentCommerceBOMFolderId) {
		_columnBitmask |= PARENTCOMMERCEBOMFOLDERID_COLUMN_BITMASK;

		if (!_setOriginalParentCommerceBOMFolderId) {
			_setOriginalParentCommerceBOMFolderId = true;

			_originalParentCommerceBOMFolderId = _parentCommerceBOMFolderId;
		}

		_parentCommerceBOMFolderId = parentCommerceBOMFolderId;
	}

	public long getOriginalParentCommerceBOMFolderId() {
		return _originalParentCommerceBOMFolderId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	@JSON
	@Override
	public String getTreePath() {
		if (_treePath == null) {
			return "";
		}
		else {
			return _treePath;
		}
	}

	@Override
	public void setTreePath(String treePath) {
		_treePath = treePath;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceBOMFolder.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceBOMFolder toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceBOMFolder)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceBOMFolderImpl commerceBOMFolderImpl = new CommerceBOMFolderImpl();

		commerceBOMFolderImpl.setCommerceBOMFolderId(getCommerceBOMFolderId());
		commerceBOMFolderImpl.setCompanyId(getCompanyId());
		commerceBOMFolderImpl.setUserId(getUserId());
		commerceBOMFolderImpl.setUserName(getUserName());
		commerceBOMFolderImpl.setCreateDate(getCreateDate());
		commerceBOMFolderImpl.setModifiedDate(getModifiedDate());
		commerceBOMFolderImpl.setParentCommerceBOMFolderId(getParentCommerceBOMFolderId());
		commerceBOMFolderImpl.setName(getName());
		commerceBOMFolderImpl.setLogoId(getLogoId());
		commerceBOMFolderImpl.setTreePath(getTreePath());

		commerceBOMFolderImpl.resetOriginalValues();

		return commerceBOMFolderImpl;
	}

	@Override
	public int compareTo(CommerceBOMFolder commerceBOMFolder) {
		int value = 0;

		value = getName().compareTo(commerceBOMFolder.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceBOMFolder)) {
			return false;
		}

		CommerceBOMFolder commerceBOMFolder = (CommerceBOMFolder)obj;

		long primaryKey = commerceBOMFolder.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceBOMFolderModelImpl commerceBOMFolderModelImpl = this;

		commerceBOMFolderModelImpl._originalCompanyId = commerceBOMFolderModelImpl._companyId;

		commerceBOMFolderModelImpl._setOriginalCompanyId = false;

		commerceBOMFolderModelImpl._setModifiedDate = false;

		commerceBOMFolderModelImpl._originalParentCommerceBOMFolderId = commerceBOMFolderModelImpl._parentCommerceBOMFolderId;

		commerceBOMFolderModelImpl._setOriginalParentCommerceBOMFolderId = false;

		commerceBOMFolderModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceBOMFolder> toCacheModel() {
		CommerceBOMFolderCacheModel commerceBOMFolderCacheModel = new CommerceBOMFolderCacheModel();

		commerceBOMFolderCacheModel.commerceBOMFolderId = getCommerceBOMFolderId();

		commerceBOMFolderCacheModel.companyId = getCompanyId();

		commerceBOMFolderCacheModel.userId = getUserId();

		commerceBOMFolderCacheModel.userName = getUserName();

		String userName = commerceBOMFolderCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceBOMFolderCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceBOMFolderCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceBOMFolderCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceBOMFolderCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceBOMFolderCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceBOMFolderCacheModel.parentCommerceBOMFolderId = getParentCommerceBOMFolderId();

		commerceBOMFolderCacheModel.name = getName();

		String name = commerceBOMFolderCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceBOMFolderCacheModel.name = null;
		}

		commerceBOMFolderCacheModel.logoId = getLogoId();

		commerceBOMFolderCacheModel.treePath = getTreePath();

		String treePath = commerceBOMFolderCacheModel.treePath;

		if ((treePath != null) && (treePath.length() == 0)) {
			commerceBOMFolderCacheModel.treePath = null;
		}

		return commerceBOMFolderCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{commerceBOMFolderId=");
		sb.append(getCommerceBOMFolderId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", parentCommerceBOMFolderId=");
		sb.append(getParentCommerceBOMFolderId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", logoId=");
		sb.append(getLogoId());
		sb.append(", treePath=");
		sb.append(getTreePath());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.bom.model.CommerceBOMFolder");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceBOMFolderId</column-name><column-value><![CDATA[");
		sb.append(getCommerceBOMFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentCommerceBOMFolderId</column-name><column-value><![CDATA[");
		sb.append(getParentCommerceBOMFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logoId</column-name><column-value><![CDATA[");
		sb.append(getLogoId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>treePath</column-name><column-value><![CDATA[");
		sb.append(getTreePath());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceBOMFolder.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceBOMFolder.class, ModelWrapper.class
		};
	private long _commerceBOMFolderId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentCommerceBOMFolderId;
	private long _originalParentCommerceBOMFolderId;
	private boolean _setOriginalParentCommerceBOMFolderId;
	private String _name;
	private long _logoId;
	private String _treePath;
	private long _columnBitmask;
	private CommerceBOMFolder _escapedModel;
}