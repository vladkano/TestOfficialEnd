package functionalTests;

import baseForTests.TestBase;
import catalog.CatalogNavigation;
import config.TestConfig;
import filters.Filters;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import mainPage.MainPageBanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import sections.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты разделов и футеров")
public class SectionsTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(TestConfig.SITE_URL);
        footer = new Footer(driver);
        trends = new Trends(driver);
        designers = new Designers(driver);
        filters = new Filters(driver);
        jewelry = new Jewelry(driver);
        certificate = new Certificate(driver);
        navigation = new CatalogNavigation(driver);
        newItems = new NewItems(driver);
        man = new Man(driver);
        sale = new Sale(driver);
        shops = new Shops(driver);
        wishlist = new Wishlist(driver);
        errorPage = new ErrorPage(driver);
        banner = new MainPageBanner(driver);
    }

    /**
     * Проверка кнопок разделов на главной странице. <p>
     * Новинки
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Новинки.")
    public void newItemsButton() {
        newItems.clickToNewItemsButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "catalog/new/", url);
    }

    /**
     * Золото и серебро
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Золото и серебро.")
    public void jewelryButton() {
        jewelry.clickToJewelryButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "jewelry/", url);
    }

    /**
     * Для мужчин
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Для мужчин.")
    public void forManButton() {
        man.clickToManButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "catalog/men/", url);
    }

    /**
     * Тренды
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Тренды.")
    public void trendsButton() {
        trends.clickToTrendsButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "trend/", url);
    }

    /**
     * Дизайнеры
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Дизайнеры.")
    public void designersButton() {
        designers.clickToDesignersButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "designers/", url);
    }

    /**
     * Сертификаты
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Сертификаты.")
    public void certificatesButton() {
        certificate.clickToCertificateButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "certificate", url);
    }

    /**
     * Sale
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Sale.")
    public void saleButton() {
        sale.clickToSaleButton();
        String url = driver.getCurrentUrl();
        assertEquals(TestConfig.SITE_URL + "catalog/sale/", url);
    }

    /**
     * Магазины
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Магазины.")
    public void shopsButton() {
        shops.clickToShopsButton();
        String url = driver.getCurrentUrl();
        String shopsHeader = shops.getShopsHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "shops/", url),
                () -> assertEquals("МАГАЗИНЫ", shopsHeader));
    }

    /**
     * Избранное
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. Избранное.")
    public void wishListButton() {
        wishlist.clickToWishListButton();
        String url = driver.getCurrentUrl();
        String header = wishlist.getWishListHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "wishlist/", url),
                () -> assertEquals("избранное", header));
    }

    /**
     * Проверка разделов футера. <p>
     * О нас
     */
    @Test()
    @Description("Проверка разделов футера. О нас.")
    public void aboutButton() {
        footer.clickToAboutButton();
        String url = driver.getCurrentUrl();
        String header = footer.getAboutHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "about/", url),
                () -> assertEquals("ПРИВЕТ\n" +
                        "И ДОБРО ПОЖАЛОВАТЬ\n" +
                        "В POISON DROP", header));
    }

    /**
     * Контакты
     */
    @Test()
    @Description("Проверка разделов футера. Контакты.")
    public void contactsButton() {
        footer.clickToContactsButton();
        String url = driver.getCurrentUrl();
        String header = footer.getContactsHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "contacts/", url),
                () -> assertEquals("Контакты", header));
    }

    /**
     * Адреса магазинов
     */
    @Test()
    @Description("Проверка разделов футера. Адреса магазинов.")
    public void footerShopsButton() {
        footer.clickToShopsButton();
        String url = driver.getCurrentUrl();
        String header = footer.getShopsHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "shops/", url),
                () -> assertEquals("МАГАЗИНЫ", header));
    }

    /**
     * Вакансии
     */
    @Test()
    @Description("Проверка разделов футера. Вакансии.")
    public void vacancyButton() {
        footer.clickToVacancyButton();
        String url = driver.getCurrentUrl();
        String header = footer.getVacancyHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "career/", url),
                () -> assertEquals("карьера в Poison Drop", header));
    }

    /**
     * Пользовательское соглашение/Акции
     */
    @Test()
//    @Description("Проверка разделов футера. Пользовательское соглашение.")
    @Description("Проверка разделов футера. Акции.")
    public void soglashenieButton() {
        footer.clickToSoglashenieButton();
        String url = driver.getCurrentUrl();
        String header = footer.getSoglashenieHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "promotion/", url),
                () -> assertEquals("Правила проведения и участия в Акции «при покупке от 20 000 рублей скидка 20%».", header));
    }

    /**
     * Доставка и оплата
     */
    @Test()
    @Description("Проверка разделов футера. Доставка и оплата.")
    public void dostavkaButton() {
        footer.clickToDostavkaButton();
        String url = driver.getCurrentUrl();
        String header = footer.getDostavkaHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "dostavka-i-oplata/", url),
                () -> assertEquals("доставка и оплата", header));
    }

    /**
     * Обмен и возврат
     */
    @Test()
    @Description("Проверка разделов футера. Обмен и возврат.")
    public void obmenButton() {
        footer.clickToObmenButton();
        String url = driver.getCurrentUrl();
        String header = footer.getObmenHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "obmen-i-vozvrat/", url),
                () -> assertEquals("Обмен и возврат", header));
    }

    /**
     * Гарантии
     */
    @Test()
    @Description("Проверка разделов футера. Гарантии.")
    public void garantiiButton() {
        footer.clickToGarantiiButton();
        String url = driver.getCurrentUrl();
        String header = footer.getGarantiiHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "garantii/", url),
                () -> assertEquals("Гарантийный сервис", header));
    }

    /**
     * Оферта
     */
    @Test()
    @Description("Проверка разделов футера. Оферта.")
    public void ofertaButton() {
        footer.clickToOfertaButton();
        String url = driver.getCurrentUrl();
        String header = footer.getOfertaHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "oferta/", url),
                () -> assertEquals("Оферта", header));
    }

    /**
     * Персональные данные
     */
    @Test()
    @Description("Проверка разделов футера. Персональные данные.")
    public void personalnyeDannyeButton() {
        footer.clickToPersonalnyeDannyeButton();
        String url = driver.getCurrentUrl();
        String header = footer.getPersonalnyeDannyeHeader();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "polozhenie-ob-obrabotke-i-zashchite-personalnykh-dannykh/", url),
                () -> assertEquals("Положение об обработке и защите персональных данных", header));
    }

    /**
     * Вконтакте
     */
    @Test()
    @Description("Проверка разделов футера. Вконтакте.")
    public void vkButton() {
        footer.clickToVkButton();
        //переключения между окнами
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url = driver.getCurrentUrl();
        assertEquals("https://vk.com/poisondrop", url);
    }

    /**
     * Телеграм
     */
    @Test()
    @Description("Проверка разделов футера. Телеграм.")
    public void telegaButton() {
        footer.clickToTelegaButton();
        //переключения между вкладками
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
//        driver.close();
//        driver.switchTo().window(tabs2.get(0));
        String url = driver.getCurrentUrl();
        String header = footer.getTelegaHeader();
        Assertions.assertAll(
                () -> assertEquals("https://t.me/impoisoned", url),
                () -> assertEquals("@poisonedstaff", header));
    }

    /**
     * Тик Ток
     */
    @Test()
    @Description("Проверка разделов футера. Тик Ток.")
    public void tikTokButton() {
        footer.clickToTikTokButton();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url = driver.getCurrentUrl();
        assertEquals("https://www.tiktok.com/@poisondropru?lang=ru-RU", url);
    }

    /**
     * Ютуб
     */
    @Test()
    @Description("Проверка разделов футера. Ютуб.")
    public void youtubeButton() {
        footer.clickToYoutubeButton();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url = driver.getCurrentUrl();
        String header = footer.getYoutubeHeader();
        Assertions.assertAll(
                () -> assertEquals("https://www.youtube.com/channel/UCrZvQ1xKouTkYU_MxC6Hv4g", url),
                () -> assertEquals("Poison Drop", header));
    }

    /**
     * Вотсап
     */
    @Test()
    @Description("Проверка разделов футера. Вотсап.")
    public void whatsAppButton() {
        footer.clickToWhatsAppButton();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url = driver.getCurrentUrl();
        String header = footer.getWhatsAppHeader();
        Assertions.assertAll(
                () -> assertEquals("https://api.whatsapp.com/send/?phone=74952551533&text&type=phone_number&app_absent=0", url),
                () -> assertEquals("Poison Drop | универмаг украшений", header));
    }


    /**
     * Проверка раздела: Золото и серебро.<p>
     * Количество наименований в базе и на странице, выборочная проверка по наименованию изделия.
     */
    @Test
    @Description("Золото и серебро(проверка по наименованию изделия)")
    public void jewelryNames() {
        driver.get(TestConfig.SITE_URL + "jewelry/");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = jewelry.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0,7));
        }
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Проверка по наименованию дизайнера
     */
    @Test
    @Description("Золото и серебро(проверка по наименованию дизайнера)")
    public void jewelryDesigners() {
        driver.get(TestConfig.SITE_URL + "jewelry/");
        List<String> sqlList = jewelry.getDesigners();
        List<WebElement> elements = driver.findElements(designerName);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    /**
     * Проверка на соответствие цены на сайте цене в базе.
     */
    @Test
    @Description("Золото и серебро(проверка на соответствие цены на сайте цене в базе)")
    public void priceOfJewelry() {
        List<Integer> priceList = new ArrayList<>();
        driver.get(TestConfig.SITE_URL + "jewelry/");
        List<Integer> sqlList = jewelry.getPrice();
        List<WebElement> elements = driver.findElements(price);
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    /**
     * Проверяем отображения картинок и их количество.
     */
    @Test
    @Description("Золото и серебро(проверка отображения картинок и их количество)")
    public void pictureOfJewelry() {
        driver.get(TestConfig.SITE_URL + "jewelry/");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    /**
     * Тренды:<p>
     * Отображение баннеров, работа кнопки "Показать еще", наименование и порядок их отображения
     */
    @Test
    @Description("Тренды(отображение баннеров, работа кнопки 'Показать еще', наименование и порядок их отображения)")
    public void bannersIsVisibleInTrends() {
        driver.get(TestConfig.SITE_URL + "trend/");
        navigation.clickOnShowMoreTrendsButton();
        List<WebElement> banners = driver.findElements(trendBanners);
        List<String> sqlList = trends.getNames();
        List<WebElement> elements = driver.findElements(namesOfTrends);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(trends.listOfBanners(), banners.size()),
                () -> assertEquals(sqlList.subList(0, trends.listOfBanners() - 1), siteList.subList(0, trends.listOfBanners() - 1)));
    }

    /**
     * Ссылки: переход на верную страницу
     */
    @Test
    @Description("Тренды(ссылки: переход на верную страницу)")
    public void mainBannerLink() {
        driver.get(TestConfig.SITE_URL + "trend/");
        String href = trends.getMainHref();
        trends.clickToMainHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(0);
        String s = description.replaceAll("<[^>]*>", "");
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(s, header));
    }

    /**
     * Проверка перехода по первому баннеру
     */
    @Test
    @Description("Тренды(проверка перехода по первому баннеру)")
    public void firstBannerLinkTrends() {
        driver.get(TestConfig.SITE_URL + "trend/");
        String href = trends.getFirstHref();
        trends.clickToFirstHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(1);
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(description, header));
    }

    /**
     * Проверка перехода по второму баннеру
     */
    @Test
    @Description("Тренды(проверка перехода по второму баннеру)")
    public void secondBannerLink() {
        driver.get(TestConfig.SITE_URL + "trend/");
        String href = trends.getSecondHref();
        trends.clickToSecondHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(2);
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(description, header));
    }

    /**
     * Проверка перехода по третьему баннеру
     */
    @Test
    @Description("Тренды(проверка перехода по третьему баннеру)")
    public void thirdBannerLink() {
        driver.get(TestConfig.SITE_URL + "trend/");
        String href = trends.getThirdHref();
        trends.clickToThirdHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(9);
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(description, header));
    }

    /**
     * Проверка перехода по четвертому баннеру
     */
    @Test
    @Description("Тренды(проверка перехода по четвертому баннеру)")
    public void fourBannerLink() {
        driver.get(TestConfig.SITE_URL + "trend/");
        String href = trends.getFourthFineHref();
        trends.clickToFourthFineHref();
        String header = trends.linkHeader();
        String url = driver.getCurrentUrl();
        String description = trends.listOfDescription().get(4);
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(description, header));
    }


    /**
     * Дизайнеры:<p>
     * Отображение баннеров, наименование и порядок их отображения.<p>
     * Новые:<p>
     * Первый баннер новинок
     */
    @Test
    @Description("Новые дизайнеры(Отображение баннеров, наименование и порядок их отображения. Первый баннер 'Новые')")
    public void firstDesignersBannerLink() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.getDesignersFirstHref();
        designers.clickToFirstDesignerHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.getFirstLinkNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20)),
                () -> assertEquals(sqlList.get(4).substring(0, 20), siteList.get(4).substring(0, 20)));
    }

    /**
     * Второй баннер новинок
     */
    @Test
    @Description("Новые дизайнеры(Отображение баннеров, наименование и порядок их отображения. Второй баннер 'Новые')")
    public void secondDesignersBannerLink() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.getDesignersSecondHref();
        designers.clickToSecondDesignerHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.getSecondLinkNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(sqlList.size() - 1), siteList.get(numberOnly - 1)));
    }


    /**
     * Третий баннер новинок
     */
    @Test
    @Description("Новые дизайнеры(Отображение баннеров, наименование и порядок их отображения. Третий баннер 'Новые')")
    public void thirdDesignersBannerLink() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.getDesignersThirdHref();
        designers.clickToThirdDesignerHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.getThirdLinkNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, numberOnly)));
    }

    /**
     * Популярные:<p>
     * Первый баннер из популярных дизайнеров
     */
    @Test
    @Description("Популярные дизайнеры(Отображение баннеров, наименование и порядок их отображения. Первый баннер 'Популярные')")
    public void firstPopularBannerLink() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.getFirstPopularHref();
        designers.clickToFirstPopularHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.getFirstPopularLinkNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0,20));
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, numberOfFoto), siteList.subList(0, numberOfFoto)));
    }

    /**
     * Последний баннер из популярных дизайнеров
     */
    @Test
    @Description("Популярные дизайнеры(Отображение баннеров, наименование и порядок их отображения. Последний баннер 'Популярные')")
    public void lastPopularBannerLink() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.getLastPopularHref();
        designers.clickToSecondPopularHref();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.getLastPopularLinkNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0,20));
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, numberOfFoto), siteList.subList(0, numberOfFoto)));
    }

    //Создана задача по багу https://tracker.yandex.ru/PD-1390

    /**
     * Отображение корректного списка дизайнеров на сайте.
     */
    @Test
    @Description("Отображение корректного списка дизайнеров на сайте")
    public void listOfDesigners() {
        driver.get(TestConfig.SITE_URL + "designers/");
        List<String> sqlList = designers.getListOfDesigners();
        List<WebElement> elements = driver.findElements(numberOfDesigners);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size()));
    }

    /**
     * Выборочная проверка ссылок и товаров из таблицы на странице дизайнеров:<p>
     * Первый дизайнер из списка
     */
    @Test
    @Description("Выборочная проверка ссылок и товаров из таблицы на странице дизайнеров. Первый дизайнер из списка.")
    public void firstDesigner() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.getFirstDesignerHref();
        designers.clickToFirstDesignerLink();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.getFirstDesignerNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0,15));
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, numberOnly)));
    }

    /**
     * Десятый дизайнер из списка
     */
    @Test
    @Description("Выборочная проверка ссылок и товаров из таблицы на странице дизайнеров. Десятый дизайнер из списка.")
    public void secondDesigner() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.get10DesignerHref();
        designers.clickTo10DesignerLink();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.get10DesignerItemNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, siteSize), siteList.subList(0, siteSize)));
    }

    //Создана задача по багу https://tracker.yandex.ru/PD-1390

    /**
     * Двадцатый дизайнер из списка
     */
    @Test
    @Description("Выборочная проверка ссылок и товаров из таблицы на странице дизайнеров. Двадцатый дизайнер из списка.")
    public void thirdDesigner() {
        driver.get(TestConfig.SITE_URL + "designers/");
        String href = designers.get20dDesignerHref();
        designers.clickTo20DesignerLink();
        String url = driver.getCurrentUrl();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = designers.get20DesignerItemNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(href, url),
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlSize), siteList.subList(0, numberOnly)));
    }

    //создана задача https://tracker.yandex.ru/PD-3243
    /**
     * Блок тестов по 404 странице: <p>
     * Проверка отображения надписи об 404 ошибке и раздела бестселлеров на странице https://poisondrop.ru/404
     */
    @Test
    @Description("Проверка отображения надписи об 404 ошибке и раздела бестселлеров на странице https://poisondrop.ru/404")
    public void errorPage() {
        driver.get(TestConfig.SITE_URL + "404");
        String errorHeader = errorPage.getErrorHeader();
        String bestsellersHeader = errorPage.getBestsellersHeader();
        Assertions.assertAll(
                () -> assertEquals("Ошибка 404", errorHeader),
                () -> assertEquals("бестселлеры", bestsellersHeader));
    }


    /**
     * Раздел бестселлеров. Переход по наименованию изделия на 404 странице
     */
    @Test
    @Description("Проверка отображения надписи об 404 ошибке и раздела бестселлеров на странице https://poisondrop.ru/404")
    public void bestsellersOnErrorPage() {
        driver.get(TestConfig.SITE_URL + "404");
        String name = banner.getName();
        banner.clickToBestsellerNameButton();
        String header = banner.getBestsellerNameHeader();
        assertEquals(name, header);
    }

}
