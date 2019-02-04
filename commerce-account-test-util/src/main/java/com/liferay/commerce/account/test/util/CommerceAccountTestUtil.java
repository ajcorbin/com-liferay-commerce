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

package com.liferay.commerce.account.test.util;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountTestUtil {

	public static CommerceAccount addBusinessCommerceAccount(
			long userId, String name, String email, long[] userIds,
			long[] organizationIds, ServiceContext serviceContext)
		throws Exception {

		// Commerce account

		CommerceAccount commerceAccount =
			CommerceAccountLocalServiceUtil.addBusinessCommerceAccount(
				name, CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, email,
				StringPool.BLANK, true, StringPool.BLANK, new long[] {userId},
				null, serviceContext);

		// Commerce account user rels

		addCommerceAccountUserRels(
			commerceAccount.getCommerceAccountId(), userIds, serviceContext);

		// Commerce account organization rels

		addCommerceAccountOrganizationRels(
			commerceAccount.getCommerceAccountId(), organizationIds,
			serviceContext);

		return commerceAccount;
	}

	public static CommerceAccount addBusinessCommerceAccount(
			long userId, String name, String email,
			ServiceContext serviceContext)
		throws Exception {

		return addBusinessCommerceAccount(
			userId, name, email, null, null, serviceContext);
	}

	public static void addCommerceAccountOrganizationRels(
			long commerceAccountId, long[] organizationIds,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceAccountOrganizationRelLocalServiceUtil.
			addCommerceAccountOrganizationRels(
				commerceAccountId, organizationIds, serviceContext);
	}

	public static void addCommerceAccountUserRels(
			long commerceAccountId, long[] userIds,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceAccountUserRelLocalServiceUtil.addCommerceAccountUserRels(
			commerceAccountId, userIds, null, null, serviceContext);
	}

	public static CommerceAccount addPersonalCommerceAccount(
			long userId, ServiceContext serviceContext)
		throws Exception {

		return CommerceAccountLocalServiceUtil.addPersonalCommerceAccount(
			userId, StringPool.BLANK, StringPool.BLANK, serviceContext);
	}

}