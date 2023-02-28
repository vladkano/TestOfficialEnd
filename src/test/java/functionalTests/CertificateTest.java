package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sections.Certificate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты раздела Сертификаты")
public class CertificateTest extends TestBase {

    String number;

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(TestConfig.SITE_URL + "certificate/?utm_source=test&utm_medium=test&utm_campaign=test");
        certificate = new Certificate(driver);
        order = new Order(driver);
        basket = new Basket(driver);
    }

    /**
     * Вспомогательные методы для тестов:<p>
     * Кладём сертификат в корзину
     */
    public void putCertificateInBasket() {
        certificate.typeEmail(email);
        certificate.clickToFirstSectionOrderButton();
        basket.clickToCart();
        number = certificate.getBasketNumber();
    }

    /**
     * Получаем код для оплаты и переходим к оплате заказа(проверка того, что в корзине один сертификат и осуществлён переход к оплате заказа)
     */
    public void getCodeAndCheckOrder() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        Assertions.assertAll(
                () -> assertEquals("1", number),
                () -> assertEquals("Оплата заказа", header.substring(0, 13)));
    }

    /**
     * Получаем код для оплаты и оформляем заказ(проверка того, что в корзине один сертификат и заказ оформлен)
     */
    public void getCodeAndCheckOrderWithNoPay() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        Assertions.assertAll(
                () -> assertEquals("1", number),
                () -> assertEquals("Мы приняли ваш заказ", header));
    }

    /**
     * Проверяем отображение заголовка и текста на странице сертификатов
     */
    @Test
    @Description("Проверяем отображение заголовка и текста на странице сертификатов")
    public void checkHeaderAndText() {
        String mainHeader = certificate.getMainCertHeader();
        String certAmount = certificate.getCertificateAmount();
        String certText = certificate.getCertificateText();
        String applicationSection = certificate.getApplicationSection();
        String mailSection = certificate.getMailSection();
        assertEquals("электронный сертификат", mainHeader);
        assertEquals("на 10 000 ₽", certAmount);
        assertEquals("Подарите то, что точно понравится, без долгих поисков. " +
                "Сертификат можно купить в любом нашем магазине или оформить онлайн, не выходя из дома.", certText);
        assertEquals("как применить сертификат", applicationSection);
        assertEquals("Отправим на почту получателю с вашими пожеланиями", mailSection);
    }


    /**
     * Проверяем работают ли кнопки +- и заказать:<p>
     * Отправка сертификата себе <p>
     * Кнопка '+'
     */

    @Test
    @Description("Проверяем работу кнопки '+' Отправка сертификата себе")
    public void firstSectionPlus() {
        certificate.clickToFirstSectionPlusButton();
        String value = certificate.getCertificateAmount();
        assertEquals("на " + "15 000 ₽", value);
    }

    /**
     * Кнопка '-'
     */
    @Test
    @Description("Проверяем работу кнопки '-' Отправка сертификата себе")
    public void firstSectionMinus() {
        certificate.clickToFirstSectionMinusButton();
        String value = certificate.getCertificateAmount();
        assertEquals("на " + "9 000 ₽", value);
    }

    /**
     * Кнопка 'заказать'
     */
    @Test
    @Description("Проверяем работу кнопки 'заказать' Отправка сертификата себе")
    public void firstSectionOrder() {
        putCertificateInBasket();
        assertEquals("1", number);
    }

    /**
     * Отправка сертификата другу<p>
     * Кнопка '+'
     */
    @Test
    @Description("Проверяем работу кнопки '+' Отправка сертификата другу")
    public void secondSectionPlus() {
        certificate.clickToFriendButton();
        certificate.clickToFirstSectionPlusButton();
        String value = certificate.getCertificateAmount();
        assertEquals("на " + "15 000 ₽", value);
    }

    /**
     * Кнопка '-'
     */
    @Test
    @Description("Проверяем работу кнопки '-' Отправка сертификата другу")
    public void secondSectionMinus() {
        certificate.clickToFriendButton();
        certificate.clickToFirstSectionMinusButton();
        String value = certificate.getCertificateAmount();
        assertEquals("на " + "9 000 ₽", value);
    }

    /**
     * Кнопка 'заказать'
     */
    @Test
    @Description("Проверяем работу кнопки 'заказать' Отправка сертификата другу")
    public void secondSectionOrder() {
        certificate.clickToFriendButton();
        certificate.secondSectionOrder("Вася", "Петя", email, "Всего всего!");
        basket.clickToCart();
        number = certificate.getBasketNumber();
        assertEquals("1", number);
    }


    // Пока третий раздел отключен на сайте(https://poisondrop.atlassian.net/browse/PD-1535)

//    /**
//     * Третий раздел<p>
//     * Кнопка '+'
//     */
//    @Test
//    @Description("Проверяем работу кнопки '+' в третьем разделе")
//    public void thirdSectionPlus() {
//        certificate.clickToThirdSectionPlusButton();
//        String value = certificate.getThirdSectionValue();
//        assertEquals("7000 \u20BD", value);
//    }
//
//    /**
//     * Кнопка '-'
//     */
//    @Test
//    @Description("Проверяем работу кнопки '-' в третьем разделе")
//    public void thirdSectionMinus() {
//        certificate.clickToThirdSectionMinusButton();
//        String value = certificate.getThirdSectionValue();
//        assertEquals("5000 \u20BD", value);
//    }
//
//    /**
//     * Кнопка 'заказать'
//     */
//    @Test
//    @Description("Проверяем работу кнопки 'заказать' в третьем разделе")
//    public void thirdSectionOrder() {
//        certificate.clickToThirdSectionOrderButton();
//        String number = certificate.getBasketNumber();
//        assertEquals("1", number);
//    }


    /**
     * Проверка перехода к оплате заказа на сайте <p>
     * Электронный сертификат себе
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, электронный сертификат себе, способ связи: телефон")
    public void orderWithElCertificateAndPhone() {
        putCertificateInBasket();
        order.elCertificateWithPhone(phoneForOrder, email, testNameForOrder, "TEST");
        getCodeAndCheckOrder();
    }

    @Test()
    @Description("Проверка перехода к оплате заказа на сайте, электронный сертификат себе, способ связи: WA")
    public void orderWithElCertificateAndWA() {
        putCertificateInBasket();
        order.elCertificateWithWA(phoneForOrder, email, testNameForOrder, "TEST");
        getCodeAndCheckOrder();
    }


    //Функционал покупки бумажного сертификата пока отключен
//    /**
//     * Проверка перехода к оплате заказа на сайте, сертификат тип 1 <p>
//     * Бумажный сертификат
//     */
//
//    /**
//     * Способ доставки: Доставка курьером
//     */
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: доставка курьером. Способ связи: телефон")
//    public void orderWithCertificateAndPhone() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.paperCertificateWithPhone(phoneForOrder, email, testNameForOrder,
//                "Калининград", "ул Пушкина, д 4", "2",
//                "2", "2", "2", "Test Comment", "Test");
//        getCodeAndCheckOrder();
//    }
//
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: доставка курьером. Способ связи: WA")
//    public void orderWithCertificateAndWA() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.certificateWithWA(phoneForOrder, email, testNameForOrder,
//                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2", "2", "Test Comment", "Test");
//        getCodeAndCheckOrder();
//    }
//
//    /**
//     * Способ доставки: Цветной + телефон
//     */
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: самовывоз Цветной. Способ связи: телефон")
//    public void orderWithCertificateTsvetnoyAndPhone() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.certificateWithTsvetnoyAndPhone(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrder();
//    }
//
//    /**
//     * Способ доставки: Метрополис + WA
//     */
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: самовывоз Метрополис. Способ связи: WA")
//    public void orderWithCertificateMetropolisAndWA() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.certificateWithMetropolisAndWA(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrder();
//    }
//
//    //Пока не понятно если способ связи смс при покупке сертификата
////    /**
////     * Способ доставки: Атриум + СМС
////     */
////    @Test()
////    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: самовывоз Атриум. Способ связи: СМС")
////    public void orderWithCertificateAtriumAndSMS() {
////        certificate.clickToFirstSectionOrderButton();
////        putCertificateInBasket();
////        order.certificateWithAtriumAndSMS(phoneForOrder, email, testNameForOrder, "Тест");
////        getCodeAndCheckOrder();
////    }
//
//    /**
//     * Способ доставки: Афимолл + телефон
//     */
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: самовывоз Афимолл. Способ связи: телефон")
//    public void orderWithCertificateAfimallAndPhone() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.certificateWithAfimallAndPhone(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrder();
//    }
//
//    /**
//     * Способ доставки: Павелецкая плаза + WA
//     */
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: самовывоз Павелецкая плаза. Способ связи: WA")
//    public void orderWithCertificatePaveletskayaAndWA() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.certificateWithPaveletskayaAndWA(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrder();
//    }
//
//    /**
//     * Способ доставки: У Красного моста + телефон
//     */
//    @Test()
//    @Description("Проверка перехода к оплате заказа на сайте. Бумажный сертификат тип 1. Способ доставки: самовывоз У Красного моста. Способ связи: телефон")
//    public void orderWithCertificateRedBridgeAndPhone() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        order.certificateWithRedBridgeAndPhone(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrder();
//    }
//
//
//    /**
//     * Способ доставки: Доставить в другую страну + WA <p>
//     * Проверяем изменение цены с учетом доставки, количество сертификатов в корзине и оформление заказа
//     */
//    @Test()
//    @Description("Проверяем изменение цены с учетом доставки, количество сертификатов в корзине и оформление заказа. Бумажный сертификат тип 1." +
//            "Способ доставки: Доставить в другую страну + WA")
//    public void orderWithCertificateInternationalAndWA() {
//        certificate.clickToFirstSectionOrderButton();
//        putCertificateInBasket();
//        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
//        order.certificateWithInternationalAndWA(phoneForOrder, email, testNameForOrder,
//                "Нью-Йорк", "США Нью-Йорк Трамп стрит 11", "Test");
//        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
//        String code2 = order.getPhonePassword();
//        order.confirmWithPassword(code2);
//        String header = order.getPayHeader();
//        Assertions.assertAll(
//                () -> assertTrue(finalPrice > price),
//                () -> assertEquals("1", number),
//                () -> assertEquals("Оплата заказа", header.substring(0, 13)));
//    }


    /**
     * Проверка перехода к оплате заказа на сайте, сертификат другу <p>
     * Электронный сертификат другу
     */
    @Test()
    @Description("Проверка перехода к оплате заказа на сайте. Заполняем поля: кому, от кого и эл почту на которую отправится сертификат. " +
            "Электронный сертификат другу, способ связи: телефон")
    public void orderWithElCertificateEmailAndPhone() {
        certificate.clickToFriendButton();
        certificate.secondSectionOrder("Вася", "Петя", email, "Всего всего!");
        basket.clickToCart();
        number = certificate.getBasketNumber();
        order.elCertificateWithPhone(phoneForOrder, email, testNameForOrder, "TEST");
        getCodeAndCheckOrder();
    }

    @Test()
    @Description("Проверка перехода к оплате заказа на сайте. Заполняем поля: кому, от кого и эл почту на которую отправится сертификат. " +
            "Электронный сертификат другу, способ связи: WA")
    public void orderWithElCertificateEmailAndWA() {
        certificate.clickToFriendButton();
        certificate.secondSectionOrder("Вася", "Петя", email, "Всего всего!");
        basket.clickToCart();
        number = certificate.getBasketNumber();
        order.elCertificateWithWA(phoneForOrder, email, testNameForOrder, "TEST");
        getCodeAndCheckOrder();
    }


    //____________________________________________________________________________________________________________

    //Функционал покупки бумажного сертификата пока отключен
    //2 тип сертификата без заполненного поля "пожелания"(https://poisondrop.atlassian.net/browse/PD-812)

//    /**
//     * Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 2 <p>
//     * Бумажный сертификат
//     */
//    @Test()
//    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 2. Заполняем поле: эл почта на которую отправится сертификат. " +
//            "Бумажный сертификат тип 2, способ связи: телефон")
//    public void noPayOrderWithCertificateEmailWithoutWishes() {
//        certificate.secondSectionOrder("", "", email, "");
//        putCertificateInBasket();
//        order.certificateWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Казань", "ул Узорная, д 15", "2", "2", "2", "2", "Test Comment", "Тест");
//        getCodeAndCheckOrderWithNoPay();
//    }
//
//    //Третий тип сертификата пока отключен на сайте
//    /**
//     * Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3 <p>
//     * Бумажный сертификат
//     */
//
//    /**
//     * Доставка курьером. Номинал 6000 рублей
//     */
//    @Test()
//    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3. Заполняем поле: пожелания. " +
//            "Бумажный сертификат тип 3. Способ доставки: Доставка курьером(бесплатная доставка). Способ связи: телефон.")
//    public void noPayOrderWithCertificateAndPhone() {
//        certificate.thirdSectionOrder("Всего всего!");
//        putCertificateInBasket();
//        order.certificateWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Казань", "ул Узорная, д 15", "2", "2", "2", "2", "Test Comment", "Тест");
//        getCodeAndCheckOrderWithNoPay();
//    }
//
//    /**
//     * Доставка курьером. Номинал 4000 рублей(платная доставка)<p>
//     * Проверяем изменение цены с учетом доставки, кол-во сертификатов в корзине и оформление заказа
//     */
//    @Test()
//    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3. Заполняем поле: пожелания. " +
//            "Бумажный сертификат тип 3. Способ доставки: Доставка курьером(платная доставка). Способ связи: телефон. ")
//    public void noPayOrderWithCertificateAndPhonePaidDelivery() {
//        certificate.clickToFirstSectionMinusButton();
//        certificate.clickToFirstSectionMinusButton();
//        certificate.thirdSectionOrder("Всего всего!");
//        putCertificateInBasket();
//        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
//        order.certificateWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Казань", "ул Узорная, д 15", "2", "2", "2", "2",
//                "Test Comment123", "Тест 123");
//        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
//        String code2 = order.getPhonePassword();
//        order.confirmWithPassword(code2);
//        String header = order.getOrderHeader();
//        Assertions.assertAll(
//                () -> assertTrue(finalPrice > price),
//                () -> assertEquals("1", number),
//                () -> assertEquals("Мы приняли ваш заказ", header));
//    }
//
//    /**
//     * Способ доставки: Цветной+WA
//     */
//    @Test()
//    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3. Заполняем поле: пожелания. " +
//            "Бумажный сертификат тип 3. Способ доставки: самовывоз Цветной. Способ связи: WA.")
//    public void noPayOrderTsvetnoyWithCertificateAndWA() {
//        certificate.thirdSectionOrder("Всего всего!");
//        putCertificateInBasket();
//        order.certificateWithNoPayTsvetnoyAndWA(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrderWithNoPay();
//    }
//
//    //Пока не понятно если способ связи смс при покупке сертификата
//
////    /**
////     * Способ доставки: Метрополис+SMS
////     */
////    @Test()
////    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3. Заполняем поле: пожелания. " +
////            "Бумажный сертификат тип 3. Способ доставки: самовывоз Метрополис. Способ связи: SMS.")
////    public void noPayOrderMetropolisWithCertificateAndSMS() {
////        certificate.thirdSectionOrder("Всего всего!");
////        putCertificateInBasket();
////        order.certificateWithNoPayMetropolisAndSMS(phoneForOrder, email, testNameForOrder, "Тест");
////        getCodeAndCheckOrderWithNoPay();
////    }
//
//    /**
//     * Способ доставки: Атриум+телефон
//     */
//    @Test()
//    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3. Заполняем поле: пожелания. " +
//            "Бумажный сертификат тип 3. Способ доставки: самовывоз Атриум. Способ связи: телефон.")
//    public void noPayOrderAtriumWithCertificateAndPhone() {
//        certificate.thirdSectionOrder("Всего всего!");
//        putCertificateInBasket();
//        order.certificateWithNoPayAtriumAndPhone(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrderWithNoPay();
//    }
//
//    /**
//     * Способ доставки: У Красного моста+WA
//     */
//    @Test()
//    @Description("Тестовый заказ без оплаты, оформление заказа с попаданием в 1с и ЦРМ, сертификат тип 3. Заполняем поле: пожелания. " +
//            "Бумажный сертификат тип 3. Способ доставки: самовывоз У Красного моста. Способ связи: WA.")
//    public void noPayOrderRedBridgeWithCertificateAndWA() {
//        certificate.thirdSectionOrder("Всего всего!");
//        putCertificateInBasket();
//        order.certificateWithNoPayRedBridgeAndWA(phoneForOrder, email, testNameForOrder, "Тест");
//        getCodeAndCheckOrderWithNoPay();
//    }

}
