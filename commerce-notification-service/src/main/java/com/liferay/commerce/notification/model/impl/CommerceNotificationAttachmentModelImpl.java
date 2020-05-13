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

package com.liferay.commerce.notification.model.impl;

import com.liferay.commerce.notification.model.CommerceNotificationAttachment;
import com.liferay.commerce.notification.model.CommerceNotificationAttachmentModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceNotificationAttachment service. Represents a row in the &quot;CNotificationAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommerceNotificationAttachmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceNotificationAttachmentImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationAttachmentImpl
 * @generated
 */
public class CommerceNotificationAttachmentModelImpl
	extends BaseModelImpl<CommerceNotificationAttachment>
	implements CommerceNotificationAttachmentModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce notification attachment model instance should use the <code>CommerceNotificationAttachment</code> interface instead.
	 */
	public static final String TABLE_NAME = "CNotificationAttachment";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"CNotificationAttachmentId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CNotificationQueueEntryId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT}, {"deleteOnSend", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CNotificationAttachmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CNotificationQueueEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("deleteOnSend", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CNotificationAttachment (uuid_ VARCHAR(75) null,CNotificationAttachmentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CNotificationQueueEntryId LONG,fileEntryId LONG,deleteOnSend BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table CNotificationAttachment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceNotificationAttachment.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CNotificationAttachment.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.notification.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.notification.model.CommerceNotificationAttachment"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.notification.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.notification.model.CommerceNotificationAttachment"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.notification.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.notification.model.CommerceNotificationAttachment"),
		true);

	public static final long COMMERCENOTIFICATIONQUEUEENTRYID_COLUMN_BITMASK =
		1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.notification.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.notification.model.CommerceNotificationAttachment"));

	public CommerceNotificationAttachmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceNotificationAttachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceNotificationAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceNotificationAttachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceNotificationAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceNotificationAttachment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceNotificationAttachment, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceNotificationAttachment, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceNotificationAttachment)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceNotificationAttachment, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceNotificationAttachment, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceNotificationAttachment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceNotificationAttachment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceNotificationAttachment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceNotificationAttachment>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceNotificationAttachment.class.getClassLoader(),
			CommerceNotificationAttachment.class, ModelWrapper.class);

		try {
			Constructor<CommerceNotificationAttachment> constructor =
				(Constructor<CommerceNotificationAttachment>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map
		<String, Function<CommerceNotificationAttachment, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceNotificationAttachment, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceNotificationAttachment, Object>>();
		Map<String, BiConsumer<CommerceNotificationAttachment, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceNotificationAttachment, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object uuidObject) {

					commerceNotificationAttachment.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceNotificationAttachmentId",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.
						getCommerceNotificationAttachmentId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceNotificationAttachmentId",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object commerceNotificationAttachmentIdObject) {

					commerceNotificationAttachment.
						setCommerceNotificationAttachmentId(
							(Long)commerceNotificationAttachmentIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object groupIdObject) {

					commerceNotificationAttachment.setGroupId(
						(Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object companyIdObject) {

					commerceNotificationAttachment.setCompanyId(
						(Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object userIdObject) {

					commerceNotificationAttachment.setUserId(
						(Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object userNameObject) {

					commerceNotificationAttachment.setUserName(
						(String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object createDateObject) {

					commerceNotificationAttachment.setCreateDate(
						(Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object modifiedDateObject) {

					commerceNotificationAttachment.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceNotificationQueueEntryId",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.
						getCommerceNotificationQueueEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceNotificationQueueEntryId",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object commerceNotificationQueueEntryIdObject) {

					commerceNotificationAttachment.
						setCommerceNotificationQueueEntryId(
							(Long)commerceNotificationQueueEntryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"fileEntryId",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getFileEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"fileEntryId",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object fileEntryIdObject) {

					commerceNotificationAttachment.setFileEntryId(
						(Long)fileEntryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"deleteOnSend",
			new Function<CommerceNotificationAttachment, Object>() {

				@Override
				public Object apply(
					CommerceNotificationAttachment
						commerceNotificationAttachment) {

					return commerceNotificationAttachment.getDeleteOnSend();
				}

			});
		attributeSetterBiConsumers.put(
			"deleteOnSend",
			new BiConsumer<CommerceNotificationAttachment, Object>() {

				@Override
				public void accept(
					CommerceNotificationAttachment
						commerceNotificationAttachment,
					Object deleteOnSendObject) {

					commerceNotificationAttachment.setDeleteOnSend(
						(Boolean)deleteOnSendObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getCommerceNotificationAttachmentId() {
		return _commerceNotificationAttachmentId;
	}

	@Override
	public void setCommerceNotificationAttachmentId(
		long commerceNotificationAttachmentId) {

		_commerceNotificationAttachmentId = commerceNotificationAttachmentId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

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

	@Override
	public long getCommerceNotificationQueueEntryId() {
		return _commerceNotificationQueueEntryId;
	}

	@Override
	public void setCommerceNotificationQueueEntryId(
		long commerceNotificationQueueEntryId) {

		_columnBitmask |= COMMERCENOTIFICATIONQUEUEENTRYID_COLUMN_BITMASK;

		if (!_setOriginalCommerceNotificationQueueEntryId) {
			_setOriginalCommerceNotificationQueueEntryId = true;

			_originalCommerceNotificationQueueEntryId =
				_commerceNotificationQueueEntryId;
		}

		_commerceNotificationQueueEntryId = commerceNotificationQueueEntryId;
	}

	public long getOriginalCommerceNotificationQueueEntryId() {
		return _originalCommerceNotificationQueueEntryId;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	@Override
	public boolean getDeleteOnSend() {
		return _deleteOnSend;
	}

	@Override
	public boolean isDeleteOnSend() {
		return _deleteOnSend;
	}

	@Override
	public void setDeleteOnSend(boolean deleteOnSend) {
		_deleteOnSend = deleteOnSend;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CommerceNotificationAttachment.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceNotificationAttachment.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceNotificationAttachment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceNotificationAttachment>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceNotificationAttachmentImpl commerceNotificationAttachmentImpl =
			new CommerceNotificationAttachmentImpl();

		commerceNotificationAttachmentImpl.setUuid(getUuid());
		commerceNotificationAttachmentImpl.setCommerceNotificationAttachmentId(
			getCommerceNotificationAttachmentId());
		commerceNotificationAttachmentImpl.setGroupId(getGroupId());
		commerceNotificationAttachmentImpl.setCompanyId(getCompanyId());
		commerceNotificationAttachmentImpl.setUserId(getUserId());
		commerceNotificationAttachmentImpl.setUserName(getUserName());
		commerceNotificationAttachmentImpl.setCreateDate(getCreateDate());
		commerceNotificationAttachmentImpl.setModifiedDate(getModifiedDate());
		commerceNotificationAttachmentImpl.setCommerceNotificationQueueEntryId(
			getCommerceNotificationQueueEntryId());
		commerceNotificationAttachmentImpl.setFileEntryId(getFileEntryId());
		commerceNotificationAttachmentImpl.setDeleteOnSend(isDeleteOnSend());

		commerceNotificationAttachmentImpl.resetOriginalValues();

		return commerceNotificationAttachmentImpl;
	}

	@Override
	public int compareTo(
		CommerceNotificationAttachment commerceNotificationAttachment) {

		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceNotificationAttachment.getCreateDate());

		value = value * -1;

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

		if (!(obj instanceof CommerceNotificationAttachment)) {
			return false;
		}

		CommerceNotificationAttachment commerceNotificationAttachment =
			(CommerceNotificationAttachment)obj;

		long primaryKey = commerceNotificationAttachment.getPrimaryKey();

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
		CommerceNotificationAttachmentModelImpl
			commerceNotificationAttachmentModelImpl = this;

		commerceNotificationAttachmentModelImpl._originalUuid =
			commerceNotificationAttachmentModelImpl._uuid;

		commerceNotificationAttachmentModelImpl._originalGroupId =
			commerceNotificationAttachmentModelImpl._groupId;

		commerceNotificationAttachmentModelImpl._setOriginalGroupId = false;

		commerceNotificationAttachmentModelImpl._originalCompanyId =
			commerceNotificationAttachmentModelImpl._companyId;

		commerceNotificationAttachmentModelImpl._setOriginalCompanyId = false;

		commerceNotificationAttachmentModelImpl._setModifiedDate = false;

		commerceNotificationAttachmentModelImpl.
			_originalCommerceNotificationQueueEntryId =
				commerceNotificationAttachmentModelImpl.
					_commerceNotificationQueueEntryId;

		commerceNotificationAttachmentModelImpl.
			_setOriginalCommerceNotificationQueueEntryId = false;

		commerceNotificationAttachmentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceNotificationAttachment> toCacheModel() {
		CommerceNotificationAttachmentCacheModel
			commerceNotificationAttachmentCacheModel =
				new CommerceNotificationAttachmentCacheModel();

		commerceNotificationAttachmentCacheModel.uuid = getUuid();

		String uuid = commerceNotificationAttachmentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceNotificationAttachmentCacheModel.uuid = null;
		}

		commerceNotificationAttachmentCacheModel.
			commerceNotificationAttachmentId =
				getCommerceNotificationAttachmentId();

		commerceNotificationAttachmentCacheModel.groupId = getGroupId();

		commerceNotificationAttachmentCacheModel.companyId = getCompanyId();

		commerceNotificationAttachmentCacheModel.userId = getUserId();

		commerceNotificationAttachmentCacheModel.userName = getUserName();

		String userName = commerceNotificationAttachmentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceNotificationAttachmentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceNotificationAttachmentCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceNotificationAttachmentCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceNotificationAttachmentCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceNotificationAttachmentCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceNotificationAttachmentCacheModel.
			commerceNotificationQueueEntryId =
				getCommerceNotificationQueueEntryId();

		commerceNotificationAttachmentCacheModel.fileEntryId = getFileEntryId();

		commerceNotificationAttachmentCacheModel.deleteOnSend =
			isDeleteOnSend();

		return commerceNotificationAttachmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceNotificationAttachment, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceNotificationAttachment, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceNotificationAttachment)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceNotificationAttachment, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceNotificationAttachment, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceNotificationAttachment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceNotificationAttachment>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _commerceNotificationAttachmentId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceNotificationQueueEntryId;
	private long _originalCommerceNotificationQueueEntryId;
	private boolean _setOriginalCommerceNotificationQueueEntryId;
	private long _fileEntryId;
	private boolean _deleteOnSend;
	private long _columnBitmask;
	private CommerceNotificationAttachment _escapedModel;

}