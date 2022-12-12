package functionalTests;

import base.Base;
import baseForTests.TestBase;
import basket.Basket;
import catalog.Bracelets;
import catalog.Earrings;
import filters.Filters;
import filters.Size;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import mainPage.MainPage;
import order.Order;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import personal.PersonalData;
import productCards.ProductCard;
import sections.*;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты com сайта")
public class ComTests extends TestBase {


    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getComUrl + "catalog");
        basket = new Basket(driver);
        order = new Order(driver);
        filters = new Filters(driver);
        base = new Base(driver);
        mainPage = new MainPage(driver);
        personalData = new PersonalData(driver);
        productCard = new ProductCard(driver);
        size = new Size(driver);
        newItems = new NewItems(driver);
        jewelry = new Jewelry(driver);
        trends = new Trends(driver);
        designers = new Designers(driver);
        sale = new Sale(driver);
        wishlist = new Wishlist(driver);
        earrings = new Earrings(driver);
    }

    /**
     * Вспомогательные методы для тестов: <p>
     * Запрос кода подтверждения при оплате онлайн и переход на экран ввода реквизитов карты + проверка заголовка и стоимости заказа на странице ввода реквизитов.
     */
    public void payConfirmAndHeaderCheck() {
        double cartPrice = Double.parseDouble(order.getFinalPrice().replaceAll("[^\\d.]", ""));
//        System.out.println(cartPrice);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayComHeader();
//        String cloudPrice = order.getCheckoutPrice().replaceAll(",", "");
//        System.out.println(cloudPrice);
        double finalCloudPrice = Double.parseDouble(order.getCheckoutPrice().replaceAll("[^\\d.]", ""));
//        System.out.println(finalCloudPrice);
        Assertions.assertAll(
                () -> assertEquals("Pay Poison Drop LLC", header.substring(0, 19)),
                () -> assertEquals(cartPrice, finalCloudPrice));
    }

    /**
     * Положить в корзину товар.
     */
    public void putItemInBasket() {
        basket.clickOnSecondImageLink();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
    }

    /**
     * Получить список изделий с сайта
     */
    public void getProductsListFromPage() {
        sleep(1000);
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s.substring(0, 10));
            siteSize = siteList.size();
        }
    }

    /**
     * Вход в личный кабинет
     */
    public void login() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        String codeToLogin = mainPage.getPhonePasswordForLC();
        mainPage.sigInWithPassword(codeToLogin);
        personalData.clickOnPersonalDataButton();
    }

    /**
     * Тесты:<p>
     * Проверяем работу корзины(положить товар в корзину, перейти в корзину, кнопки '+' и '-'). <p>
     * Обычный товар без размера
     */
    @Test
    @Description("Проверяем работу корзины(положить товар в корзину, перейти в корзину, кнопки '+' и '-')." +
            " Обычный товар без размера")
    @DisplayName("basketTestCom")
    public void basketTestCom() {
        basket.clickOnImageLink();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    /**
     * Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером в Dubai: <p>
     */
    @Test()
    public void internationalDeliveryToDubai() {
        basket.chooseDubai();
        putItemInBasket();
        order.deliveryFromDubai("+7" + phoneForOrder, email, testNameForOrder, "Dubai", "Test");
        payConfirmAndHeaderCheck();
    }

    /**
     * Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером в New York: <p>
     */
    @Test()
    public void internationalDeliveryToNewYork() {
        basket.chooseNewYork();
        putItemInBasket();
        order.deliveryFromDubaiToNY("+7" + phoneForOrder, email, testNameForOrder, "NewYork", "Test");
        payConfirmAndHeaderCheck();
    }

    /**
     * Сравниваем количество наименований в базе и каталоге(размеры и содержание списков):<p>
     * Весь каталог(проверка по наименованию изделия)
     */
    @Test
    @Description("Весь каталог(проверка по наименованию изделия)")
    public void productNamesInCatalogCom() {
        String countHeader = filters.getCountHeader();
        int numberOnly = Integer.valueOf(countHeader.replaceAll("\\D", ""));
        List<String> sqlList = base.getComNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Проверяем работу фильтров: <p>
     * По типу изделия: <p>
     * Серьги
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Серьги)")
    public void earringFilterCom() {
        basket.chooseNewYork();
        filters.clickToFilterButton();
        filters.clickToAllEarringsComButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getEarringNamesForFiltersFromCom();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Кольца
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Кольца)")
    public void ringsFilterCom() {
        basket.chooseNewYork();
        filters.clickToFilterButton();
        filters.clickToAllRingsButtonCom();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getRingNamesForFiltersFromCom();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Колье
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Колье)")
    public void necklacesFilterCom() {
        basket.chooseNewYork();
        filters.clickToFilterButton();
        filters.clickToAllNecklacesComButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getNecklacesNamesForFiltersFromCom();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Авторизация по номеру телефона + проверка, что отображаются надписи 'Вход или регистрация', 'Вход'
     */
    @Test
    @Description("Поверяем возможность авторизации по номеру телефона и отображения надписи 'Вход или регистрация', 'Вход'")
    public void signInComWithPhoneNumber() {
        basket.chooseNewYork();
        mainPage.sigInToComWithPhone("+7" + phoneForAuthorization);
        String heading = mainPage.getSigOutHeader();
        String code2 = mainPage.getPhonePassword();
        String sigInHeader = mainPage.getSigInHeader();
        mainPage.sigInWithPassword(code2);
        Assertions.assertAll(
                () -> assertEquals("sign in or sign up", heading),
                () -> assertEquals("login", sigInHeader));
    }

    /**
     * Тесты личного кабинета пользователя <p>
     * Позитивные тесты <p>
     * Отображение элементов и полей на странице личного кабинета
     */
    @Test
    @Description("Отображение элементов и полей на странице личного кабинета")
    public void visibilityOfElementsCom() {
        login();
        String personalDataHeader = personalData.getPersonalDataHeader();
        String aboutYouHeader = personalData.getAboutYouHeader();
        String nameHeader = personalData.getProfileFullNameHeader();
        String birthdayHeader = personalData.getProfileBirthdayHeader();
        String signInHeader = personalData.getSignInHeader();
        String phoneHeader = personalData.getProfilePhoneHeader();
        String emailHeader = personalData.getProfileEmailHeader();
        String addressHeader = personalData.getDeliveryAddressHeader();
        String deliveryAddressHeader = personalData.getProfileDeliveryAddressHeader();
        String apartmentsHeader = personalData.getApartmentsHeader();
        String entranceHeader = personalData.getEntranceHeader();
        String floorHeader = personalData.getFloorHeader();
        String intercomHeader = personalData.getIntercomHeader();

        Assertions.assertAll(
                () -> assertEquals("personal data", personalDataHeader),
                () -> assertEquals("about you", aboutYouHeader),
                () -> assertEquals("name", nameHeader),
                () -> assertEquals("date of Birth", birthdayHeader),
                () -> assertEquals("contact details", signInHeader),
                () -> assertEquals("phone", phoneHeader),
                () -> assertEquals("email", emailHeader),
                () -> assertEquals("delivery details", addressHeader),
                () -> assertEquals("address", deliveryAddressHeader),
                () -> assertEquals("apartment, office", apartmentsHeader),
                () -> assertEquals("entrance", entranceHeader),
                () -> assertEquals("floor", floorHeader),
                () -> assertEquals("intercom", intercomHeader));
    }

    /**
     * Тесты карточки товара <p>
     * Отображение блоков: <p>
     * -delivery <p>
     * -materials and features <p>
     * -jewelry care <p>
     * -payments and returns <p>
     * -6 months guarantee <p>
     * Проверка по разделам:
     */
    @Test
    @Description("Проверяем отображение 5 блоков(delivery, materials and features, jewelry care, " +
            "payments and returns, 6 months guarantee). Проверка по разделам: Серьги")
    public void checkingBlocksSergiCom() {
        driver.get(getComUrl + "catalog/earrings/");
        size.clickOnImageLink();
        String receivingText = productCard.getReceivingText();
        String receivingCity = productCard.getReceivingCity();
        String location = productCard.getLocation();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();
        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();
        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();
        Assertions.assertAll(
                () -> assertEquals("Product type", specification.substring(0, 12)),
                () -> assertEquals("delivery", receivingText),
                () -> assertEquals("Russia", location),
                () -> assertEquals("jewelry", jewelryCareHeader),
                () -> assertEquals("you may never want to take it off, but remember that alcohol in perfume can harm natural stones.", jewelryCareText),
                () -> assertEquals("fashion jewelry", bijouterieCareHeader),
                () -> assertEquals("jewelry made of alloy metals can be damaged by water (especially salt water), cream and perfume - take it off before taking a shower or applying lotion.", bijouterieCareText),
                () -> assertEquals("if you do not like the purchased item or the size does not fit, we will always", deliveryText.substring(0, 78)),
                () -> assertEquals("a warranty covers manufacturing defects for up to 6 months from date of purchase in stores or on the Poison Drop website the defects that may fall under warranty:", guaranteeText.substring(0, 162)));
    }

    /**
     * Тесты разделов и футеров <p>
     * Проверка кнопок разделов на главной странице. <p>
     * new in
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. new in.")
    public void newItemsComButton() {
        newItems.clickToNewItemsButton();
        String url = driver.getCurrentUrl();
        assertEquals(getComUrl + "catalog/new/", url);
    }

    /**
     * earrings
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. earrings.")
    public void earringsComButton() {
        earrings.clickToEarringsButton();
        String url = driver.getCurrentUrl();
        assertEquals(getComUrl + "catalog/earrings/", url);
    }

    /**
     * trending
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. trending.")
    public void trendsComButton() {
        trends.clickToTrendsButton();
        String url = driver.getCurrentUrl();
        assertEquals(getComUrl + "trend/", url);
    }

    /**
     * designers
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. designers.")
    public void designersComButton() {
        designers.clickToDesignersButton();
        String url = driver.getCurrentUrl();
        assertEquals(getComUrl + "designers/", url);
    }

    /**
     * sale
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. sale.")
    public void saleComButton() {
        sale.clickToSaleButton();
        String url = driver.getCurrentUrl();
        assertEquals(getComUrl + "catalog/sale/", url);
    }

    /**
     * favorites
     */
    @Test()
    @Description("Проверка кнопок разделов на главной странице. favorites.")
    public void wishListComButton() {
        wishlist.clickToWishListButton();
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(getComUrl + "wishlist/", url));
    }


    /**
     * Тесты избранного <p>
     * Проверка того, что товар добавился в избранное из карточки товара + урл и заголовок корректны.
     */
    @Test()
    @Description("Проверка того, что товар добавился в избранное из карточки товара + урл и заголовок корректны")
    public void wishListCheckComButton() {
        wishlist.clickOnImageLink();
        wishlist.clickToWishListInCardListButton();
        wishlist.clickToWishListButton();
        int numbers = driver.findElements(By.xpath("//h3/a")).size();
        assertTrue(numbers > 0);
        String url = driver.getCurrentUrl();
        String header = wishlist.getWishListHeader();
        Assertions.assertAll(
                () -> assertEquals(getComUrl + "wishlist/", url),
                () -> assertEquals("favorites", header));
    }

    /**
     * Блок тестов по добавлению в избранное со страниц каталога:<p>
     * Основной каталог
     */
    @Test()
    @Description("Блок тестов по добавлению в избранное со страниц каталога: Основной каталог")
    public void addToWishlistFromCatalogCom() {
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemNameFromFavorites();
        assertEquals(itemName.substring(0, 10), itemNameFromWishlist.substring(0, 10).toLowerCase());
    }

    /**
     * Блок тестов по переносу товара из избранного в корзину:<p>
     * Добавление в избранное из каталога:<p>
     * Товар без размера.
     */
    @Test()
    @Description("Блок тестов по переносу товара из избранного в корзину. Добавление в избранное из каталога: Товар без размера.")
    public void favoritesToBasketCom() {
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemNameFromFavorites().toLowerCase();
        wishlist.clickToTransferToBasketButton();
        wishlist.clickToMoveToBasketButton();
        String basketProductName = wishlist.getBasketProductName().toLowerCase();
        Assertions.assertAll(
                () -> assertEquals(itemName.substring(0, 20), itemNameFromWishlist.substring(0, 20)),
                () -> assertEquals(itemNameFromWishlist.substring(0, 20), basketProductName.substring(0, 20)));
    }

}
