/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.news.setup;

import static de.hybris.news.constants.NewsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.news.constants.NewsConstants;
import de.hybris.news.service.NewsService;


@SystemSetup(extension = NewsConstants.EXTENSIONNAME)
public class NewsSystemSetup
{
	private final NewsService newsService;

	public NewsSystemSetup(final NewsService newsService)
	{
		this.newsService = newsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		newsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return NewsSystemSetup.class.getResourceAsStream("/news/sap-hybris-platform.png");
	}
}
