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

package com.liferay.commerce.application.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.model.CommerceApplicationModelCProductRel;
import com.liferay.commerce.application.model.CommerceApplicationModelCProductRelModel;
import com.liferay.commerce.application.model.CommerceApplicationModelCProductRelSoap;

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
 * The base model implementation for the CommerceApplicationModelCProductRel service. Represents a row in the &quot;CAModelCProductRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceApplicationModelCProductRelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceApplicationModelCProductRelImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelImpl
 * @see CommerceApplicationModelCProductRel
 * @see CommerceApplicationModelCProductRelModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceApplicationModelCProductRelModelImpl extends BaseModelImpl<CommerceApplicationModelCProductRel>
	implements CommerceApplicationModelCProductRelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce application model c product rel model instance should use the {@link CommerceApplicationModelCProductRel} interface instead.
	 */
	public static final String TABLE_NAME = "CAModelCProductRel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CAModelCProductRelId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "commerceApplicationModelId", Types.BIGINT },
			{ "CProductId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CAModelCProductRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceApplicationModelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CProductId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table CAModelCProductRel (CAModelCProductRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceApplicationModelId LONG,CProductId LONG)";
	public static final String TABLE_SQL_DROP = "drop table CAModelCProductRel";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceApplicationModelCProductRel.commerceApplicationModelCProductRelId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CAModelCProductRel.CAModelCProductRelId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.application.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.application.model.CommerceApplicationModelCProductRel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.application.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.application.model.CommerceApplicationModelCProductRel"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.application.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.application.model.CommerceApplicationModelCProductRel"),
			true);
	public static final long CPRODUCTID_COLUMN_BITMASK = 1L;
	public static final long COMMERCEAPPLICATIONMODELID_COLUMN_BITMASK = 2L;
	public static final long COMMERCEAPPLICATIONMODELCPRODUCTRELID_COLUMN_BITMASK =
		4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceApplicationModelCProductRel toModel(
		CommerceApplicationModelCProductRelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceApplicationModelCProductRel model = new CommerceApplicationModelCProductRelImpl();

		model.setCommerceApplicationModelCProductRelId(soapModel.getCommerceApplicationModelCProductRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceApplicationModelId(soapModel.getCommerceApplicationModelId());
		model.setCProductId(soapModel.getCProductId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceApplicationModelCProductRel> toModels(
		CommerceApplicationModelCProductRelSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceApplicationModelCProductRel> models = new ArrayList<CommerceApplicationModelCProductRel>(soapModels.length);

		for (CommerceApplicationModelCProductRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.application.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.application.model.CommerceApplicationModelCProductRel"));

	public CommerceApplicationModelCProductRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceApplicationModelCProductRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceApplicationModelCProductRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceApplicationModelCProductRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceApplicationModelCProductRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceApplicationModelCProductRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceApplicationModelCProductRelId",
			getCommerceApplicationModelCProductRelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceApplicationModelId",
			getCommerceApplicationModelId());
		attributes.put("CProductId", getCProductId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceApplicationModelCProductRelId = (Long)attributes.get(
				"commerceApplicationModelCProductRelId");

		if (commerceApplicationModelCProductRelId != null) {
			setCommerceApplicationModelCProductRelId(commerceApplicationModelCProductRelId);
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

		Long commerceApplicationModelId = (Long)attributes.get(
				"commerceApplicationModelId");

		if (commerceApplicationModelId != null) {
			setCommerceApplicationModelId(commerceApplicationModelId);
		}

		Long CProductId = (Long)attributes.get("CProductId");

		if (CProductId != null) {
			setCProductId(CProductId);
		}
	}

	@JSON
	@Override
	public long getCommerceApplicationModelCProductRelId() {
		return _commerceApplicationModelCProductRelId;
	}

	@Override
	public void setCommerceApplicationModelCProductRelId(
		long commerceApplicationModelCProductRelId) {
		_commerceApplicationModelCProductRelId = commerceApplicationModelCProductRelId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public long getCommerceApplicationModelId() {
		return _commerceApplicationModelId;
	}

	@Override
	public void setCommerceApplicationModelId(long commerceApplicationModelId) {
		_columnBitmask |= COMMERCEAPPLICATIONMODELID_COLUMN_BITMASK;

		if (!_setOriginalCommerceApplicationModelId) {
			_setOriginalCommerceApplicationModelId = true;

			_originalCommerceApplicationModelId = _commerceApplicationModelId;
		}

		_commerceApplicationModelId = commerceApplicationModelId;
	}

	public long getOriginalCommerceApplicationModelId() {
		return _originalCommerceApplicationModelId;
	}

	@JSON
	@Override
	public long getCProductId() {
		return _CProductId;
	}

	@Override
	public void setCProductId(long CProductId) {
		_columnBitmask |= CPRODUCTID_COLUMN_BITMASK;

		if (!_setOriginalCProductId) {
			_setOriginalCProductId = true;

			_originalCProductId = _CProductId;
		}

		_CProductId = CProductId;
	}

	public long getOriginalCProductId() {
		return _originalCProductId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceApplicationModelCProductRel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceApplicationModelCProductRel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceApplicationModelCProductRel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceApplicationModelCProductRelImpl commerceApplicationModelCProductRelImpl =
			new CommerceApplicationModelCProductRelImpl();

		commerceApplicationModelCProductRelImpl.setCommerceApplicationModelCProductRelId(getCommerceApplicationModelCProductRelId());
		commerceApplicationModelCProductRelImpl.setCompanyId(getCompanyId());
		commerceApplicationModelCProductRelImpl.setUserId(getUserId());
		commerceApplicationModelCProductRelImpl.setUserName(getUserName());
		commerceApplicationModelCProductRelImpl.setCreateDate(getCreateDate());
		commerceApplicationModelCProductRelImpl.setModifiedDate(getModifiedDate());
		commerceApplicationModelCProductRelImpl.setCommerceApplicationModelId(getCommerceApplicationModelId());
		commerceApplicationModelCProductRelImpl.setCProductId(getCProductId());

		commerceApplicationModelCProductRelImpl.resetOriginalValues();

		return commerceApplicationModelCProductRelImpl;
	}

	@Override
	public int compareTo(
		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel) {
		long primaryKey = commerceApplicationModelCProductRel.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceApplicationModelCProductRel)) {
			return false;
		}

		CommerceApplicationModelCProductRel commerceApplicationModelCProductRel = (CommerceApplicationModelCProductRel)obj;

		long primaryKey = commerceApplicationModelCProductRel.getPrimaryKey();

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
		CommerceApplicationModelCProductRelModelImpl commerceApplicationModelCProductRelModelImpl =
			this;

		commerceApplicationModelCProductRelModelImpl._setModifiedDate = false;

		commerceApplicationModelCProductRelModelImpl._originalCommerceApplicationModelId = commerceApplicationModelCProductRelModelImpl._commerceApplicationModelId;

		commerceApplicationModelCProductRelModelImpl._setOriginalCommerceApplicationModelId = false;

		commerceApplicationModelCProductRelModelImpl._originalCProductId = commerceApplicationModelCProductRelModelImpl._CProductId;

		commerceApplicationModelCProductRelModelImpl._setOriginalCProductId = false;

		commerceApplicationModelCProductRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceApplicationModelCProductRel> toCacheModel() {
		CommerceApplicationModelCProductRelCacheModel commerceApplicationModelCProductRelCacheModel =
			new CommerceApplicationModelCProductRelCacheModel();

		commerceApplicationModelCProductRelCacheModel.commerceApplicationModelCProductRelId = getCommerceApplicationModelCProductRelId();

		commerceApplicationModelCProductRelCacheModel.companyId = getCompanyId();

		commerceApplicationModelCProductRelCacheModel.userId = getUserId();

		commerceApplicationModelCProductRelCacheModel.userName = getUserName();

		String userName = commerceApplicationModelCProductRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceApplicationModelCProductRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceApplicationModelCProductRelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceApplicationModelCProductRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceApplicationModelCProductRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceApplicationModelCProductRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceApplicationModelCProductRelCacheModel.commerceApplicationModelId = getCommerceApplicationModelId();

		commerceApplicationModelCProductRelCacheModel.CProductId = getCProductId();

		return commerceApplicationModelCProductRelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{commerceApplicationModelCProductRelId=");
		sb.append(getCommerceApplicationModelCProductRelId());
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
		sb.append(", commerceApplicationModelId=");
		sb.append(getCommerceApplicationModelId());
		sb.append(", CProductId=");
		sb.append(getCProductId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.commerce.application.model.CommerceApplicationModelCProductRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceApplicationModelCProductRelId</column-name><column-value><![CDATA[");
		sb.append(getCommerceApplicationModelCProductRelId());
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
			"<column><column-name>commerceApplicationModelId</column-name><column-value><![CDATA[");
		sb.append(getCommerceApplicationModelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CProductId</column-name><column-value><![CDATA[");
		sb.append(getCProductId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceApplicationModelCProductRel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceApplicationModelCProductRel.class, ModelWrapper.class
		};
	private long _commerceApplicationModelCProductRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceApplicationModelId;
	private long _originalCommerceApplicationModelId;
	private boolean _setOriginalCommerceApplicationModelId;
	private long _CProductId;
	private long _originalCProductId;
	private boolean _setOriginalCProductId;
	private long _columnBitmask;
	private CommerceApplicationModelCProductRel _escapedModel;
}