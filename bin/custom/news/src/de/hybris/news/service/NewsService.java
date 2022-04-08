/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.news.service;

public interface NewsService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
