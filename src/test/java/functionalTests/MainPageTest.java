package functionalTests;

import baseForTests.TestBase;
import config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import mainPage.MainPage;
import order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Одноразовый телефон: <p>
 * https://onlinesim.ru/ <p>
 * https://ru.temporary-phone-number.com/Russia-Phone-Number/
 * <p>
 * Одноразовая почта:<p>
 * https://temp-mail.org/ru/
 */

@Epic("Тесты регистрации и авторизации")
@ResourceLock("Code")
public class MainPageTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        FirefoxOptions options = new FirefoxOptions();
//        WebDriverManager.firefoxdriver().setup();
//        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        options.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
//        driver = new FirefoxDriver(options);
        driver = new ChromeDriver(options);
        driver.get(TestConfig.SITE_URL);
//        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        order = new Order(driver);
//        basket = new Basket(driver);
//        basket.clickToOkButton();
    }

    /**
     * Позитивные Тесты <p>
     * Регистрация по номеру телефона
     */

    //Тест неактуален, ибо на одноразовые номера более не отправляется смс
//    @Test
//    @Description("Поверяем возможность зарегистрироваться по номеру телефона")
//    public void registrationWithPhoneNumber() {
//        personalData = new PersonalData(driver);
//        int random_number = a + (int) (Math.random() * b);
//        System.out.println("Случайное число: " + random_number);
//        //телефон
//        driver.navigate().to("https://ru.temporary-phone-number.com/Russia-Phone-Number/");
//        String s = mainPage.getPhoneFromSite();
//        String phoneFromSite = s.replace(" ", "");
//        System.out.println("phone: " + phoneFromSite);
//        //почта
//        driver.navigate().to("https://temp-mail.org/ru/");
//        sleep(5000);
//        String mailFromSite = mainPage.getMailFromSite();
//        System.out.println("mail: " + mailFromSite);
//        //заполняем форму
//        driver.get(getUrl);
////        ((JavascriptExecutor) driver).executeScript(
////                "arguments[0].click();", driver.findElement(By.xpath("//span[text()='Закрыть и больше не показывать']")));
//        sleep(2000);
//        mainPage.sigInWithPhone(phoneFromSite);
//        String code = mainPage.getPhonePassword();
//        mainPage.registerWithPhoneNumber(code, mailFromSite, "Test Phone" + random_number);
//        personalData.clickOnPersonalDataButton();
//        sleep(2000);
//        String name = personalData.getName();
//        assertEquals("Test Phone" + random_number, name);
//    }

    /**
     * Авторизация по номеру телефона + проверка, что отображаются надписи 'Вход или регистрация', 'Вход'
     */
    @Test
    @Description("Поверяем возможность авторизации по номеру телефона и отображения надписи 'Вход или регистрация', 'Вход'")
    public void signInWithPhoneNumber() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        String heading = mainPage.getSigOutHeader();
        String code2 = mainPage.getPhonePasswordForLC();
        mainPage.sigInWithPassword(code2);
//        sleep(1000);
        String sigInHeader = mainPage.getSigInHeader();
        Assertions.assertAll(
                () -> assertEquals("вход или регистрация", heading),
                () -> assertEquals("вход", sigInHeader));
    }

    
    /**
     * Авторизация по почте + проверка, что отображается подпись во время процесса авторизации
     */
    @Test
    @Description("Поверяем возможность по почте и отображение информационной подписи во время процесса авторизации")
    public void signInWithEmail() {
        mainPage.sigInWithEmail(email);
        String code2 = mainPage.getEmailPassword();
        String sigInCodeHeader = mainPage.getSigInEmailHeader();
        mainPage.sigInWithPassword(code2);
        assertEquals("если письма нет, проверьте спам или отправьте код ещё раз, в работе почтового сервиса бывают сбои", sigInCodeHeader);
    }


    /**
     * Негативные Тесты: <p>
     * Регистрация по номеру телефона: <p>
     * Неправильный код подтверждения + проверка кнопки закрытия окна регистрации(нажатие на крестик)
     */
    @Test
    @Description("Поверяем что нельзя зарегистрироваться при вводе неверного кода подтверждения " +
            "+ проверка кнопки закрытия окна регистрации(нажатие на крестик)")
    public void registrationWithWrongCode() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        mainPage.sigInWithPassword("2222");
        String incorrectSigInCodeHeader = mainPage.getIncorrectSigInCodeHeader();
        mainPage.clickOnCloseButton();
        Assertions.assertAll(
                () -> assertEquals("неверный код подтверждения", incorrectSigInCodeHeader));
    }

    /**
     * Проверяем, что кнопка 'Зарегистрироваться' неактивна, если не заполнено поле 'Электронная почта'
     */
    @Test
    @Description("Проверяем, что кнопка 'Зарегистрироваться' неактивна, если не заполнено поле 'Электронная почта'")
    public void registrationWithoutEmail() {
        mainPage.sigInWithPhone(phoneForRegistration);
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, "", "Test Name");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    /**
     * Проверяем, что кнопка 'Зарегистрироваться' неактивна, если не заполнено поле 'Имя, можно с фамилией'
     */
    @Test
    @Description("Проверяем, что кнопка 'Зарегистрироваться' неактивна, если не заполнено поле 'Имя, можно с фамилией'")
    public void registrationWithoutName() {
        mainPage.sigInWithPhone(phoneForRegistration);
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, "test13@mail.com", "");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    /**
     * Проверяем, что кнопка 'Зарегистрироваться' неактивна, если не проставлена галочка напротив поля: 'даю согласие на обработку персональных данных'
     */
    @Test
    @Description("Проверяем, что кнопка 'Зарегистрироваться' неактивна, если не проставлена галочка напротив поля: " +
            "'даю согласие на обработку персональных данных'")
    public void registrationWithoutConsent() {
        mainPage.sigInWithPhone(phoneForRegistration);
        String code = mainPage.getPhonePassword();
        mainPage.registerWithoutConsent(code, "test13@mail.com", "Test Name");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    /**
     * Авторизация по номеру телефона: <p>
     * Неправильно введен номер телефона + проверка отображения подсказки
     */
    @Test
    @Description("Поверяем что нельзя авторизоваться при вводе неверного номера телефона + проверка отображения подсказки")
    public void signInWithIncorrectPhoneNumber() {
        MainPage head = mainPage.sigInWithPhone("912645932d");
        String heading = head.getIncorrectSigInHeader();
        assertEquals("телефон указан неверно", heading);
    }

    /**
     * Проверка при вводе почты в окно для ввода телефона + проверка отображения подсказки
     */
    @Test
    @Description("Поверяем что нельзя авторизоваться при вводе почты в окно для ввода телефона + проверка отображения подсказки")
    public void signInWithEmailInPhoneWindow() {
        MainPage head = mainPage.sigInWithPhone("test13@mail.com");
        String heading = head.getIncorrectSigInHeader();
        assertEquals("телефон указан неверно", heading);
    }

    /**
     * Авторизация по электронной почте: <p>
     * Ввод почты, которой нет в базе данных + проверка отображения подсказки
     */
    @Test
    @Description("Поверяем что нельзя авторизоваться при вводе почты, которой нет в базе данных + проверка отображения подсказки")
    public void signInWithWrongEmail() {
        mainPage.sigInWithEmail("test13test@mail.com");
        String heading = mainPage.getIncorrectSigInHeader();
        assertEquals("пользователь с данным email не найден. попробуйте войти по номеру телефона, либо зарегистрируйтесь", heading);
    }

    /**
     * Проверяем, что кнопка 'получить код' неактивна, если некорректно введена электронная почта
     */
    @Test
    @Description("Проверяем, что кнопка 'получить код' неактивна, если некорректно введена электронная почта")
    public void signInWithIncorrectEmail() {
        mainPage.sigInWithEmail("owenkvist1@outlook");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }


    /**
     * Выход из аккаунта через личный кабинет <p>
     * Проверяем, что при наведении на значок личного кабинета и нажатии на кнопку 'выйти' происходит выход из личного кабинета
     */
    @Test
    @Description("Проверяем, что при наведении на значок личного кабинета и нажатии на кнопку 'выйти' происходит выход из ЛК")
    public void signOut() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        String code2 = mainPage.getPhonePasswordForLC();
//        sleep(1000);
        mainPage.sigInWithPassword(code2);
        mainPage.clickOnLcInButton();
        mainPage.clickOnExitButton();
        mainPage.clickOnSigInButton();
        String heading = mainPage.getSigOutHeader();
        assertEquals("вход или регистрация", heading);
    }

    /**
     * Оформление заказа через авторизацию в ЛК <p>
     * Проверяем, что при оформлении заказа через авторизацию в ЛК заказ успешно оформляется и осуществляется переход к оплате
     */
    @Test
    @Description("Проверяем, что при оформлении заказа через авторизацию в ЛК заказ успешно оформляется и осуществляется переход к оплате")
    public void signInWithOrder() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        String code2 = mainPage.getPhonePasswordForLC();
        mainPage.sigInWithPassword(code2);
        sleep(1000);
        mainPage.clickOnCatalogButton();
        putItemInBasket();
        order.orderWithSignIn();
        int cartPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        String header = order.getPayHeader();
        int cloudPrice = parseInt(order.getCloudPrice().replaceAll("[^A-Za-z0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals("Оплата заказа", header.substring(0, 13)),
                () -> assertEquals(cartPrice, cloudPrice));
    }


}
