package functionalTests;

import baseForTests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import sections.Subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты окна с подпиской")
public class SubscriptionTests extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        subscription = new Subscription(driver);
    }

    /**
     * Вспомогательный метод для тестов:<p>
     * Проверка того, что блок с подпиской отображается на странице
     */
    public void checkSubscription() {
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    /**
     * Проверяем что блок с подпиской отображается на разных страницах сайта:<p>
     * Главная страница
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на главной странице")
    public void checkSubscriptionTextMainPage() {
        driver.get(getUrl);
        checkSubscription();
    }

    /**
     * Новинки
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице новинок")
    public void checkSubscriptionTextNewItems() {
        driver.get(getUrl + "catalog/new/");
        checkSubscription();
    }

    /**
     * Каталог
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице основного каталога")
    public void checkSubscriptionTextCatalog() {
        driver.get(getUrl + "catalog/");
        checkSubscription();
    }

    /**
     * Страница золото и серебро
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице 'золото и серебро'")
    public void checkSubscriptionTextJewelry() {
        driver.get(getUrl + "jewelry/");
        checkSubscription();
    }

    /**
     * Для мужчин
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице 'для мужчин'")
    public void checkSubscriptionTextMen() {
        driver.get(getUrl + "catalog/men/");
        checkSubscription();
    }

    /**
     * Тренды
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице 'тренды'")
    public void checkSubscriptionTextTrend() {
        driver.get(getUrl + "trend/");
        checkSubscription();
    }

    /**
     * Дизайнеры
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице 'дизайнеры'")
    public void checkSubscriptionTextDesigners() {
        driver.get(getUrl + "designers/");
        checkSubscription();
    }

    /**
     * Сертификаты
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице 'сертификаты'")
    public void checkSubscriptionTextCertificates() {
        driver.get(getUrl + "certificate/");
        checkSubscription();
    }

    /**
     * Sale
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается на странице 'sale'")
    public void checkSubscriptionTextSale() {
        driver.get(getUrl + "catalog/sale/");
        checkSubscription();
    }


    /**
     * Проверяем что блок с подпиской отображается в карточке товара.
     */
    @Test()
    @Description("Проверяем что блок с подпиской отображается в карточке товара.")
    public void checkSubscriptionTextProductCard() {
        driver.get(getUrl + "catalog/");
        subscription.clickOnNameLink();
        checkSubscription();
    }

    /**
     * Проверяем что подписка прошла успешно.
     */
    @Test()
    @Description("Проверяем что подписка прошла успешно.")
    public void successfulSubscription() {
        driver.get(getUrl);
        subscription.subscriptionWithEmail("ran.owen@rambler.ru");
        String successfulSubscriptionText = subscription.getSuccessfulSubscriptionText();
        assertEquals("наш почтовый голубь уже вылетел.", successfulSubscriptionText);
    }

    /**
     * Вводим некорректный адрес эл.почты и смотри что подписка не прошла успешно.
     */
    @Test()
    @Description("Вводим некорректный адрес эл.почты и смотри что подписка не прошла успешно.")
    public void failedSubscription() {
        driver.get(getUrl);
        subscription.subscriptionWithEmail("ran.owen@");
        String failedSubscriptionText = subscription.getSubscriptionText();
        assertEquals("подписчики нашей рассылки выбирают самые классные украшения.", failedSubscriptionText);
    }
    
}
