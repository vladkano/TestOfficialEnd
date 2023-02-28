package functionalTests;

import baseForTests.TestBase;
import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import mainPage.MainPageBanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты отображения баннеров на главной странице")
public class MainPageBannerTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(TestConfig.SITE_URL);
        banner = new MainPageBanner(driver);
    }

    /**
     * Поверяем отображение баннеров на главной странице <p>
     * Видимость главного баннера
     */
    @Test
    @Description("Поверяем отображение главного баннера")
    public void mainBannerIsVisible() {
        List<WebElement> mainBanner = driver.findElements(By.xpath("//div[@class='main-banner']"));
        assertEquals(1, mainBanner.size());
    }

    /**
     * Отображаются все 12 баннеров
     */
    @Test
    @Description("Поверяем, что отображаются все 12 баннеров")
    public void bannersIsVisible() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        assertEquals(12, banners.size());
    }

    /**
     * Проверяем, что блок бестселлеров отображается на главной странице в правильном порядке <p>
     * Проверка по наименованию изделия
     */
    @Test
    @Description("Проверяем, что блок бестселлеров отображается на главной странице в правильном порядке(По наименованию изделия)")
    public void bestsellersNameIsVisible() {
        List<String> bestsellers = new ArrayList<>();
        List<WebElement> best = driver.findElements(By.xpath("//h3[@class='catalog-card__name']/a"));
        for (WebElement name : best) {
            String textBanner = name.getAttribute("textContent");
            bestsellers.add(textBanner);
        }
        List<String> sqlList = banner.listOfBests();
        assertEquals(sqlList, bestsellers);
    }

    /**
     * Проверка по наименованию дизайнера
     */
    @Test
    @Description("Проверяем, что блок бестселлеров отображается на главной странице в правильном порядке(По наименованию дизайнера)")
    public void bestsellersDesigners() {
        List<String> bestsellers = new ArrayList<>();
        List<WebElement> best = driver.findElements(By.xpath("//div[@class='catalog-card__designer']//a"));
        for (WebElement name : best) {
            String textBanner = name.getAttribute("textContent");
            bestsellers.add(textBanner);
        }
        List<String> sqlList = banner.listOfDesigners();
        assertEquals(sqlList, bestsellers);
    }

    /**
     * Проверка по стоимости изделия
     */
    @Test
    @Description("Проверяем, что блок бестселлеров отображается на главной странице в правильном порядке(По стоимости изделия)")
    public void bestsellersPrice() {
        List<Integer> bestsellers = new ArrayList<>();
        List<WebElement> best = driver.findElements(By.xpath("//div[@class='price-block__main']/span[1]"));
        for (WebElement name : best) {
            String s = name.getAttribute("textContent");
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            bestsellers.add(price);
        }
        List<Integer> sqlList = banner.listPriceOfBests();
        assertEquals(sqlList, bestsellers);
    }

    /**
     * Отображение блока Shop The Look <p>
     * Раздел отключен 14.03.2022(поэтому проверяем, что раздел недоступен)
     */
    @Test
    @Description("Проверяем, что блок Shop The Look не отображается на главной странице")
    public void shopTheLookIsNotVisible() {
        int banners = driver.findElements(By.xpath("//div[@class='frisbuy-post-provider-instagram']")).size();
        assertTrue(banners <= 0);
    }


    /**
     * Проверяем ссылки: переход на верную страницу <p>
     * Главный баннер
     */
    @Test
    @Description("Проверяем ссылки: переход на верную страницу(главный баннер)")
    public void mainBannerLink() {
        String href = banner.getMainCatalogHref();
        banner.clickToMainCatalogHref();
        String header = banner.getFirstCatalogHeader().replaceAll("\\s+","");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals("фильтр", header.substring(0, 6)));
    }

    /**
     * Первый баннер
     */
    @Test
    @Description("Проверяем ссылки: переход на верную страницу(первый баннер)")
    public void firstBannerLink() {
        String href = banner.getCatalogHref();
        banner.clickToCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getFirstCatalogHeader().replaceAll("\\s+","");
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals("фильтр", header.substring(0, 6)));
    }

    /**
     * Шестой баннер
     */
    @Test
    @Description("Проверяем ссылки: переход на верную страницу(шестой баннер)")
    public void sixBannerLink() {
        String href = banner.getSixCatalogHref();
        banner.clickToSixCatalogHref();
        String url = driver.getCurrentUrl();
        String header = banner.getFirstCatalogHeader().replaceAll("\\s+","");
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals("фильтр", header.substring(0,6)));
    }

    /**
     * Блок бестселлеров <p>
     * Переход по наименованию дизайнера
     */
    @Test
    @Description("Проверяем ссылки: переход на верную страницу(Блок бестселлеров: по наименованию дизайнера)")
    public void bestsellersDesignerLink() {
        banner.clickToCarouselButton();
        String designerName = banner.getDesignerName();
        banner.clickToDesignerButton();
        String header = banner.getDesignerHeader();
        assertEquals(designerName, header);
    }

    /**
     * Переход по наименованию изделия
     */
    @Test
    @Description("Проверяем ссылки: переход на верную страницу(Блок бестселлеров: по наименованию изделия)")
    public void bestsellersNameLink() {
        String name = banner.getName();
        banner.clickToBestsellerNameButton();
        String header = banner.getBestsellerNameHeader();
        assertEquals(name, header);
    }

}
