package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import mainPage.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import personal.PersonalData;

import javax.annotation.concurrent.NotThreadSafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Epic("Тесты личного кабинета пользователя")
@ResourceLock("Code")
@NotThreadSafe
public class PersonalAreaTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        basket = new Basket(driver);
        mainPage = new MainPage(driver);
        personalData = new PersonalData(driver);
        driver.get(getUrl);
        mainPage.sigInWithPhone(phoneForAuthorization);
        String codeToLogin = mainPage.getPhonePasswordForLC();
        mainPage.sigInWithPassword(codeToLogin);
        personalData.clickOnPersonalDataButton();
    }

    /**
     * Позитивные тесты <p>
     * Отображение элементов и полей на странице личного кабинета
     */
    @Test
    @Description("Отображение элементов и полей на странице личного кабинета")
    public void visibilityOfElements() {
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
                () -> assertEquals("личные данные", personalDataHeader),
                () -> assertEquals("о вас", aboutYouHeader),
                () -> assertEquals("имя, можно с фамилией", nameHeader),
                () -> assertEquals("дата рождения", birthdayHeader),
                () -> assertEquals("вход на сайт", signInHeader),
                () -> assertEquals("телефон", phoneHeader),
                () -> assertEquals("email", emailHeader),
                () -> assertEquals("адрес доставки", addressHeader),
                () -> assertEquals("адрес", deliveryAddressHeader),
                () -> assertEquals("квартира, офис", apartmentsHeader),
                () -> assertEquals("подъезд", entranceHeader),
                () -> assertEquals("этаж", floorHeader),
                () -> assertEquals("домофон", intercomHeader));
    }

    /**
     * Получение и изменения значений в полях<p>
     * Изменение имени пользователя
     */
    @Test
    @Description("Изменение имени пользователя")
    public void changeName() {
        String firstName = personalData.getName();
        personalData.clickOnName();
        personalData.typeName("1");
        personalData.clickOnSaveButton();
        String secondName = personalData.getName();
        personalData.clickOnName();
        personalData.typeName("Тестовое Имя");
        personalData.clickOnSaveButton();
        String finalName = personalData.getName();
        Assertions.assertAll(
                () -> assertNotEquals(firstName, secondName),
                () -> assertNotEquals(secondName, finalName),
                () -> assertEquals(firstName, finalName));
    }

    /**
     * Проверяем что адрес доставки можно изменить
     */
    @Test
    @Description("Проверяем что адрес доставки можно изменить")
    public void changeDeliveryAddress() {
        String first = personalData.getDeliveryCity();
        personalData.addAddress("Санкт-Петербург, пр-кт Просвещения, д 20");
        personalData.clickOnSaveAddressButton();
        String second = personalData.getDeliveryCity();
        sleep(1000);
        personalData.addAddress("Санкт-Петербург, пр-кт Просвещения, д 10");
        personalData.clickOnSaveAddressButton();
        String saveAddressHeader = personalData.getSaveAddressHeader();
        String last = personalData.getDeliveryCity();
        Assertions.assertAll(
                () -> assertNotEquals(first, second),
                () -> assertNotEquals(second, last),
                () -> assertEquals(first, last),
                () -> assertEquals("сохранено", saveAddressHeader));
    }

    /**
     * Проверяем что изменения по полям: квартира, подъезд, этаж и домофон можно сохранить
     */
    @Test
    @Description("Проверяем что изменения по полям: квартира, подъезд, этаж и домофон можно сохранить")
    public void changeDeliveryApartmentAndFloor() {
        String first = personalData.getApartmentsDeliveryAddress();
        personalData.addApartments("1");
        String second = personalData.getApartmentsDeliveryAddress();
        personalData.addApartments("2");
        String saveAddressHeader = personalData.getSaveAddressHeader();
        String last = personalData.getApartmentsDeliveryAddress();

        String firstEntrance = personalData.getEntranceDeliveryAddress();
        personalData.addEntrance("3");
        String secondEntrance = personalData.getEntranceDeliveryAddress();
        personalData.addEntrance("2");
        String saveAddressHeader2 = personalData.getSaveAddressHeader();
        String lastEntrance = personalData.getApartmentsDeliveryAddress();

        String firstFloor = personalData.getFloorDeliveryAddress();
        personalData.addFloor("4");
        String secondFloor = personalData.getFloorDeliveryAddress();
        personalData.addFloor("2");
        String saveAddressHeader3 = personalData.getSaveAddressHeader();
        String lastFloor = personalData.getFloorDeliveryAddress();

        String firstIntercom = personalData.getIntercomDeliveryAddress();
        personalData.addIntercom("да");
        String secondIntercom = personalData.getIntercomDeliveryAddress();
        personalData.addIntercom("нет");
        String saveAddressHeader4 = personalData.getSaveAddressHeader();
        String lastIntercom = personalData.getIntercomDeliveryAddress();

        Assertions.assertAll(
                () -> assertNotEquals(first, second),
                () -> assertNotEquals(second, last),
                () -> assertEquals(first, last),
                () -> assertEquals("сохранено", saveAddressHeader),
                () -> assertNotEquals(firstEntrance, secondEntrance),
                () -> assertNotEquals(secondEntrance, lastEntrance),
                () -> assertEquals(firstEntrance, lastEntrance),
                () -> assertEquals("сохранено", saveAddressHeader2),
                () -> assertNotEquals(firstFloor, secondFloor),
                () -> assertNotEquals(secondFloor, lastFloor),
                () -> assertEquals(firstFloor, lastFloor),
                () -> assertEquals("сохранено", saveAddressHeader3),
                () -> assertNotEquals(firstIntercom, secondIntercom),
                () -> assertNotEquals(secondIntercom, lastIntercom),
                () -> assertEquals(firstIntercom, lastIntercom),
                () -> assertEquals("сохранено", saveAddressHeader4));

    }

    /**
     * Изменять поля телефон и email в ЛК запрещено(поэтому просто проверяем значения)
     */
    @Test
    @Description("Изменять поля телефон и email в ЛК запрещено(поэтому просто проверяем значения)")
    public void getPhoneNumberAndMail() {
        String phone = personalData.getPhone();
        String email = personalData.getEmail();
        Assertions.assertAll(
                () -> assertEquals("+7 950 197 89 05", phone),
                () -> assertEquals("ran.owen@rambler.ru", email));
    }

    /**
     * Негативные тесты <p>
     * Не заполнено поле "Имя"
     */
    @Test
    @Description("Не заполнено поле 'Имя'")
    public void emptyFieldName() {
        personalData.clickOnName();
        String header = personalData.getEmptyNameHeader();
        assertEquals("необходимо указать имя", header);
    }

    /**
     * Не заполнено поле "Дата рождения"
     */
    @Test
    @Description("Не заполнено поле 'Дата рождения'")
    public void emptyFieldBirthday() {
        personalData.clickOnBirthday();
        String header = personalData.getEmptyBirthdayHeader();
        assertEquals("ошибка! день рождения должен быть указан в формате дд.мм", header);
    }

    /**
     * Нельзя изменить поле "Дата рождения"
     */
    @Test
    @Description("Проверяем, что нельзя изменить поле 'Дата рождения'")
    public void changeFieldBirthday() {
        personalData.typeBirthday("12.12");
        String birthdayError = personalData.getBirthdayError();
        assertEquals("Повторное изменение дня рождения из личного кабинета невозможно. Свяжитесь с оператором.", birthdayError);
    }


   //валидация поля отключена в таске: https://tracker.yandex.ru/PD-2568
//    /**
//     * Некорректно заполнено поле "Адрес"
//     */
//    @Test
//    @Description("Некорректно заполнено поле 'Адрес'")
//    public void incorrectAddressField() {
//        personalData.addIncorrectAddress("Тестовая аллея");
//        String profileDeliveryAddressError = personalData.getProfileDeliveryAddressError();
//        assertEquals("Адрес указан не полностью", profileDeliveryAddressError);
//    }


    /**
     * Тесты отображения заказов в ЛК(номер, статус, дата, адрес, получатель, состав, доставка, итого) <p>
     * Проверка отображения заголовков в заказе
     */
    @Test
    @Description("Проверка отображения заголовков в заказе")
    public void checkingOrderHeaders() {
        String orderStatus = personalData.clickOnOrdersButton()
                .getOrderStatus();
        String orderDataHeader = personalData.getOrderDataHeader();
        String orderAddressHeader = personalData.getOrderAddressHeader();
        String orderRecipientHeader = personalData.getOrderRecipientHeader();
        String orderYouOrderedHeader = personalData.getOrderYouOrderedHeader();
        Assertions.assertAll(
                () -> assertEquals("тестовый заказ", orderStatus),
                () -> assertEquals("дата заказа", orderDataHeader),
                () -> assertEquals("адрес доставки", orderAddressHeader),
                () -> assertEquals("получатель", orderRecipientHeader),
                () -> assertEquals("вы заказали", orderYouOrderedHeader));
    }

    /**
     * Проверка отображения всех данных последнего заказа(сверка с БД)
     */
    @Test
    @Description("Проверка отображения всех данных последнего заказа(сверка с БД)")
    public void checkingLastOrder() {
        //site
        String orderNumber = personalData.clickOnOrdersButton()
                .getOrderNumber();
        String orderData = personalData.getOrderData();
        String orderAddress = personalData.getOrderAddress();
        String orderRecipient = personalData.getOrderRecipient();
        String orderContent = personalData.getOrderContent();
        String orderPrice = personalData.getOrderPrice();
        String resultPrice = orderPrice.replaceAll("[^A-Za-z0-9]", "");
        String orderFinalPrice = personalData.getOrderFinalPrice();
        String finalSum = orderFinalPrice.replaceAll("[^A-Za-z0-9]", "");
        //sql
        String lastOrderNumber = personalData.getLastOrderNumber();
        String lastOrderData = personalData.getLastOrderData();
        String lastOrderAddress = personalData.getLastOrderAddress();
        String lastOrderRecipient = personalData.getLastOrderRecipient();
        String lastOrderContent = personalData.getLastOrderContent();
        String lastOrderPrice = String.valueOf(personalData.getLastOrderPrice());
        String lastOrderFinalSum = String.valueOf(personalData.getLastOrderFinalPrice());
        Assertions.assertAll(
                () -> assertEquals("заказ №" + lastOrderNumber, orderNumber),
                () -> assertEquals(lastOrderData, orderData),
                () -> assertEquals(lastOrderAddress, orderAddress),
                () -> assertEquals(lastOrderRecipient, orderRecipient),
                () -> assertEquals(lastOrderContent.substring(0, 20), orderContent.substring(0, 20)),
                () -> assertEquals(lastOrderPrice, resultPrice),
                () -> assertEquals(lastOrderFinalSum, finalSum));
    }

    /**
     * Сверяем кол-во заказов у пользователя
     */
    @Test
    @Description("Сверяем кол-во заказов у пользователя")
    public void checkingOrderList() {
        Integer numberOfOrdersSql = personalData.getNumberOfOrdersSql();
        Integer numberOfOrders = personalData.clickOnOrdersButton()
                .getNumberOfOrders();
        assertEquals(numberOfOrdersSql, numberOfOrders);
    }

}
