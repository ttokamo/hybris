package de.hybris.news.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.news.dao.NewsDAO;
import de.hybris.platform.core.model.NewsModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

@IntegrationTest
public class DefaultNewsDAOImplIntegrationTest extends ServicelayerTransactionalTest {

    private final Logger LOG = LoggerFactory.getLogger(DefaultNewsDAOImplIntegrationTest.class);
    private final String NEWS_CODE = "Product news";
    private final String NEWS_TEXT = "Hello";
    private final String NEWS_TITLE = "News";

    @Resource
    private ModelService modelService;
    @Resource
    private NewsDAO newsDAO;

    @Test
    public void findAllNews_withExistentData() {
        NewsModel newsModel = modelService.create(NewsModel.class);
        NewsModel newsModel2 = modelService.create(NewsModel.class);
        newsModel.setCode(NEWS_CODE);
        newsModel.setText(NEWS_TEXT);
        newsModel.setTitle(NEWS_TITLE);
        modelService.save(newsModel);
        newsModel2.setCode(NEWS_CODE + "_");
        newsModel2.setText(NEWS_TEXT + "_");
        newsModel2.setTitle(NEWS_TITLE + "_");
        modelService.save(newsModel2);

        List<NewsModel> newsList = newsDAO.findAllNews();
        getInfoAboutNewsListSize("findAllNews_withExistentData", newsList.size(), 2);

        Assert.assertEquals(2, newsList.size());
    }

    @Test
    public void findAllNews_withNonExistentData() {
        List<NewsModel> newsList = newsDAO.findAllNews();
        getInfoAboutNewsListSize("findAllNews_withNonExistentData", newsList.size(), 0);

        Assert.assertEquals(0, newsList.size());
    }

    @Test
    public void findNewsByCode() {
        NewsModel newsModel = modelService.create(NewsModel.class);
        newsModel.setCode(NEWS_CODE);
        newsModel.setText(NEWS_TEXT);
        newsModel.setTitle(NEWS_TITLE);
        modelService.save(newsModel);

        List<NewsModel> newsList = newsDAO.findNewsByCode(NEWS_CODE);
        getInfoAboutNewsListSize("findNewsByCode", newsList.size(), 1);

        Assert.assertEquals(1, newsList.size());
        Assert.assertEquals(NEWS_CODE, newsList.get(0).getCode());
        Assert.assertEquals(NEWS_TEXT, newsList.get(0).getText());
        Assert.assertEquals(NEWS_TITLE, newsList.get(0).getTitle());

        newsList = newsDAO.findNewsByCode(NEWS_CODE + "_");
        getInfoAboutNewsListSize("findNewsByCode", newsList.size(), 0);

        Assert.assertEquals(0, newsList.size());
    }

    private void getInfoAboutNewsListSize(final String methodName, final int actualSize, final int expectedSize) {
        LOG.info(methodName + " The size of newsList is {} of {}", actualSize, expectedSize);
    }
}