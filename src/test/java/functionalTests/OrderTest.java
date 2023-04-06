package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Epic("Тесты оформления заказов")
public class OrderTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.manage().window().maximize();
        driver.get(TestConfig.SITE_URL + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket = new Basket(driver);
        order = new Order(driver);
    }

    /**
     * Вспомогательные методы для тестов: <p>
     * Запрос кода подтверждения при оплате онлайн и переход на экран ввода реквизитов карты + проверка заголовка и стоимости заказа на странице ввода реквизитов.
     */
    public void payConfirmAndHeaderCheck() {
        int cartPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        int cloudPrice = parseInt(order.getCloudPrice().replaceAll("[^A-Za-z0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals("Оплата заказа", header.substring(0, 13)),
                () -> assertEquals(cartPrice, cloudPrice));
    }

    /**
     * Запрос кода подтверждения, оплата при получении + переход на экран подтверждения оформления заказа + проверка заголовка на
     * странице успешного оформления заказа.
     */
    public void noPayConfirmAndHeaderCheck() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    /**
     * Положить в корзину товар стоимостью менее 5000 рублей.
     */
    public void putItemLessThan5000InBasket() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
    }

    /**
     * Тесты: <p>
     * Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000: <p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000, Способ связи: Звонок по телефону.")
    public void courierDeliveryAndPhone() {
        putItemInBasket();
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000, Способ связи: Сообщение в WhatsApp.")
    public void courierDeliveryAndWA() {
        putItemInBasket();
        order.orderWithWhatsApp(phoneForOrder, email, testNameForOrder,
                "Санкт", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    /**
     * Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка): <p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка), Способ связи: Звонок по телефону.")
    public void courierDeliveryAndPhoneLessThan5000() {
        putItemLessThan5000InBasket();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка), Способ связи: Сообщение в WhatsApp.")
    public void courierDeliveryAndWALessThan5000() {
        putItemLessThan5000InBasket();
        order.orderWithWhatsApp(phoneForOrder, email, testNameForOrder,
                "Санкт", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }


    /**
     * Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине:<p>
     * Универмаг «Цветной»:<p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Цветной), Способ связи: Звонок по телефону.")
    public void tsvetnoyAndPhone() {
        putItemInBasket();
        order.orderWithCompanyStoreTsvetnoy(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Цветной), Способ связи: Сообщение в WhatsApp.")
    public void tsvetnoyAndWA() {
        putItemInBasket();
        order.orderWithCompanyStoreTsvetnoyWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: СМС о статусе заказа
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Цветной), Способ связи: СМС о статусе заказа.")
    public void tsvetnoyAndSms() {
        putItemInBasket();
        order.orderWithCompanyStoreTsvetnoySms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * ТЦ «Метрополис»: <p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Метрополис), Способ связи: Звонок по телефону.")
    public void metropolisAndPhone() {
        putItemInBasket();
        order.orderWithCompanyStoreMetropolisPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Метрополис), Способ связи: Сообщение в WhatsApp.")
    public void metropolisAndWA() {
        putItemInBasket();
        order.orderWithCompanyStoreMetropolisWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: СМС о статусе заказа
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Метрополис), Способ связи: СМС о статусе заказа.")
    public void metropolisAndSms() {
        putItemInBasket();
        order.orderWithCompanyStoreMetropolisSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * ТЦ «Атриум»:<p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Атриум), Способ связи: Звонок по телефону.")
    public void atriumAndPhone() {
        putItemInBasket();
        order.orderWithCompanyStoreAtriumPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Атриум), Способ связи: Сообщение в WhatsApp.")
    public void atriumAndWA() {
        putItemInBasket();
        order.orderWithCompanyStoreAtriumWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: СМС о статусе заказа
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Атриум), Способ связи: СМС о статусе заказа.")
    public void atriumAndSms() {
        putItemInBasket();
        order.orderWithCompanyStoreAtriumSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * ТЦ «Афимолл»:<p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Афимолл), Способ связи: Звонок по телефону.")
    public void afimollAndPhone() {
        putItemInBasket();
        order.orderWithAfimollPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Афимолл), Способ связи: Сообщение в WhatsApp.")
    public void afimollAndWA() {
        putItemInBasket();
        order.orderWithAfimollWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: СМС о статусе заказа
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Афимолл), Способ связи: СМС о статусе заказа.")
    public void afimollAndSms() {
        putItemInBasket();
        order.orderWithAfimollSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * ТРЦ «Павелецкая плаза»:<p>
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Павелецкая плаза), Способ связи: Сообщение в WhatsApp.")
    public void paveletskayaAndPhone() {
        putItemInBasket();
        order.paveletskayaWithPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Универмаг «Au Pont Rouge. У Красного моста»:<p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(У Красного моста), Способ связи: Звонок по телефону.")
    public void redBridgeAndPhone() {
        putItemInBasket();
        order.orderWithRedBridgePhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(У Красного моста), Способ связи: Сообщение в WhatsApp.")
    public void redBridgeAndWA() {
        putItemInBasket();
        order.orderWithRedBridgeWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Способ связи: СМС о статусе заказа
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(У Красного моста), Способ связи: СМС о статусе заказа.")
    public void redBridgeAndSms() {
        putItemInBasket();
        order.orderWithRedBridgeSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * ТРЦ «Галерея Краснодар»<p>
     * Способ связи: Звонок по телефону
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(Галерея Краснодар), Способ связи: Звонок по телефону.")
    public void galleryKrasnodarAndPhone() {
        putItemInBasket();
        order.galleryKrasnodarWithPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * ТЦ «KazanMall» <p>
     * Способ связи: Сообщение в WhatsApp
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине(KazanMall), Способ связи: Сообщение в WhatsApp.")
    public void kazanMallAndWA() {
        putItemInBasket();
        order.kazanMallWithWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Международная доставка доступна только для ряда стран(Белоруссия, Казахстан, Азербайджан, Армения, Грузия, Израиль, Сербия, Турция) <p>
     * Проверяем оформление доставки в Белоруссию.
     */
    @Test()
    @Description("Международная доставка. Проверяем оформление доставки в Белоруссию.")
    public void internationalAndPhone() {
        putItemInBasket();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Минск", "улица Пушкина 12", "Test");
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        System.out.println(price);
        System.out.println(finalPrice);
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    /**
     * Проверяем, что при оформлении доставки в Италию пользователя переносит на сайт https://poisondrop.com/cart/
     */
    @Test()
    public void internationalDeliveryIsNotPossible() {
        putItemInBasket();
        order.deliveryIsNotPossible(phoneForOrder, email, testNameForOrder, "Рим");
        sleep(1000);
        String comUrl = driver.getCurrentUrl();
        assertEquals(TestConfig.COM_URL + "cart", comUrl);
    }

//    /**
//     * Доставка до постамата:<p>
//     * Проверка перехода к оплате заказа на сайте, способ доставки: Доставка до постамата(Россия), Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Доставка до постамата. Проверка перехода к оплате заказа на сайте, способ доставки: Доставка до постамата(Россия), Способ связи: Звонок по телефону.")
//    public void postomatAndRussian() {
//        putItemInBasket();
//        order.orderWithPickPointPhone(phoneForOrder, email, testNameForOrder, "Россия", "Екатеринбург", "Шварца");
//        payConfirmAndHeaderCheck();
//    }


    //Доставка в Белоруссию более не доступна(Инфа от Юры)
//    /**
//     * Проверка перехода к оплате заказа на сайте, способ доставки: Доставка до постамата(Белоруссия), СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Доставка до постамата. Проверка перехода к оплате заказа на сайте, способ доставки: Доставка до постамата(Белоруссия), Способ связи: СМС о статусе заказа.")
//    public void postomatAndBelarus() {
//        putItemInBasket();
//        order.orderWithPickPointSMS(phoneForOrder, email, testNameForOrder, "Беларусь\n", "Минск\n", "Ленина");
//        payConfirmAndHeaderCheck();
//    }

    /**
     * Проверка перехода к оплате заказа с применением промокода.
     */
    @Test()
    @Description("Проверка перехода к оплате заказа с применением промокода..")
    public void orderWithPromo() {
        putItemInBasket();
        order.usePromocode("iampoisoned");
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Екатеринбург", "ул Родонитовая, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }


    //____________________________________________________________________________________________________________

//    /**
//     * Проверка оформления заказов с попаданием в 1с и ЦРМ: <p>
//     * Способ оплаты: При получении наличными или картой: <p>
//     * Способ доставки: доставка курьером товар дороже 5000 рублей: <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: доставка курьером. Товар дороже 5000. Способ связи: Звонок по телефону.")
//    public void noPayCourierDeliveryAndPhone() {
//        putItemInBasket();
//        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: доставка курьером. Товар дороже 5000. Способ связи: Сообщение в WhatsApp.")
//    public void noPayCourierDeliveryAndWA() {
//        putItemInBasket();
//        order.orderWithDvdNoPayAndWA(phoneForOrder, email, testNameForOrder,
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ доставки: доставка курьером товар дешевле 5000 рублей(платная доставка): <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: доставка курьером. Товар дешевле 5000 рублей(платная доставка). Способ связи: Звонок по телефону.")
//    public void noPayCourierDeliveryAndPhoneLessThan5000() {
//        putItemLessThan5000InBasket();
//        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
//        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
//        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        assertTrue(finalPrice > price);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: доставка курьером. Товар дешевле 5000 рублей(платная доставка). Способ связи: Сообщение в WhatsApp.")
//    public void noPayCourierDeliveryAndWALessThan5000() {
//        putItemLessThan5000InBasket();
//        order.orderWithDvdNoPayAndWA(phoneForOrder, email, testNameForOrder,
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//
//    /**
//     * Способ доставки: Забрать в фирменном магазине: <p>
//     * Универмаг «Цветной»: <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Цветной). Способ связи: Звонок по телефону.")
//    public void noPayTsvetnoyAndPhone() {
//        putItemInBasket();
//        order.tsvetnoyWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Цветной). Способ связи: Сообщение в WhatsApp.")
//    public void noPayTsvetnoyAndWA() {
//        putItemInBasket();
//        order.tsvetnoyWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Цветной). Способ связи: СМС о статусе заказа.")
//    public void noPayTsvetnoyAndSms() {
//        putItemInBasket();
//        order.tsvetnoyWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//
//    /**
//     * ТЦ «Метрополис»: <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Метрополис). Способ связи: Звонок по телефону.")
//    public void noPayMetropolisAndPhone() {
//        putItemInBasket();
//        order.metropolisWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Метрополис). Способ связи: Сообщение в WhatsApp.")
//    public void noPayMetropolisAndWA() {
//        putItemInBasket();
//        order.metropolisWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Метрополис). Способ связи: СМС о статусе заказа.")
//    public void noPayMetropolisAndSms() {
//        putItemInBasket();
//        order.metropolisWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//
//    /**
//     * ТЦ «Атриум»: <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Атриум). Способ связи: Звонок по телефону.")
//    public void noPayAtriumAndPhone() {
//        putItemInBasket();
//        order.atriumWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Атриум). Способ связи: Сообщение в WhatsApp.")
//    public void noPayAtriumAndWA() {
//        putItemInBasket();
//        order.atriumWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Атриум). Способ связи: СМС о статусе заказа.")
//    public void noPayAtriumAndSMS() {
//        putItemInBasket();
//        order.atriumWithNoPayAndSMS(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * ТЦ «Афимолл»: <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Афимолл). Способ связи: Звонок по телефону.")
//    public void noPayAfimollAndPhone() {
//        putItemInBasket();
//        order.afimollWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Афимолл). Способ связи: Сообщение в WhatsApp.")
//    public void noPayAfimollAndWA() {
//        putItemInBasket();
//        order.afimollWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Афимолл). Способ связи: СМС о статусе заказа.")
//    public void noPayAfimollAndSMS() {
//        putItemInBasket();
//        order.afimollWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * ТРЦ «Павелецкая плаза»: <p>
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Павелецкая плаза). Способ связи: Сообщение в WhatsApp.")
//    public void noPayPaveletskayaAndWA() {
//        putItemInBasket();
//        order.paveletskayaWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Универмаг «Au Pont Rouge. У Красного моста»: <p>
//     * Способ связи: Звонок по телефону.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(У Красного моста). Способ связи: Звонок по телефону.")
//    public void noPayRedBridgeAndPhone() {
//        putItemInBasket();
//        order.redBridgeWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(У Красного моста). Способ связи: Сообщение в WhatsApp.")
//    public void noPayRedBridgeAndWA() {
//        putItemInBasket();
//        order.redBridgeWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Способ связи: СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(У Красного моста). Способ связи: СМС о статусе заказа.")
//    public void noPayRedBridgeAndSms() {
//        putItemInBasket();
//        order.redBridgeWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * ТРЦ «Галерея Краснодар» <p>
//     * Способ связи: Сообщение в WhatsApp.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(Галерея Краснодар). Способ связи: Сообщение в WhatsApp.")
//    public void noPayGalleryKrasnodarAndWA() {
//        putItemInBasket();
//        order.galleryKrasnodarWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * ТЦ «KazanMall» <p>
//     * Способ связи: СМС о статусе заказа.
//     */
//    @Test()
//    @Description("Проверка оформления заказа на сайте, способ доставки: забрать в фирменном магазине(KazanMall). Способ связи: СМС о статусе заказа.")
//    public void noPayKazanMallAndSMS() {
//        putItemInBasket();
//        order.kazanMallWithNoPayAndSMS(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }

}
