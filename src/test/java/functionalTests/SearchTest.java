package functionalTests;

import baseForTests.TestBase;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sections.Searchs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import java.util.ArrayList;


@Epic("Тесты поиска")
  public class SearchTest extends TestBase {
    private By searchToDesigner;

    @BeforeEach
    public void setUp() {
        mainSetUp();
        search = new Searchs(driver);
    }

    /*
     *Вспомогательный метод для тестов:<p>
     *Нажатие на поиск
     */
    public void ClickSearch() {
        search.clickToSearch();
        search.clickInputToSearch();
    }

    /*
     * Поиск товара по наименованию дизайнера "Moonka" + проверяем что на странице все товары дизайнера Moonka и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByDesigner() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Moonka" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> Moonka = driver.findElements(By.xpath("//a[text()='Moonka']"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=Moonka", url));
                      assertEquals(48, Moonka.size());
    }

    /*
     * Поиск товара по коду "41997" + проверяем что такой товар только один и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByCode() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("41977" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> dataId = driver.findElements(By.xpath("//div[@data-id]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=41977", url));
                      assertEquals(1, dataId.size());
    }

    /*
     * Поиск товара по названию "Колье цепь из серебра" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByName() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Колье цепь из серебра" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> Колье = driver.findElements(By.xpath("//a[contains(text(), 'цепь')or contains(text(), 'серебра')]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%9A%D0%BE%D0%BB%D1%8C%D0%B5%20%D1%86%D0%B5%D0%BF%D1%8C%20%D0%B8%D0%B7%20%D1%81%D0%B5%D1%80%D0%B5%D0%B1%D1%80%D0%B0", url));
                      assertEquals(48, Колье.size());
    }

    /*
     * Поиск товара по части имени "Медальон" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByPartOfTheName() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Медальон" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> Медальон = driver.findElements(By.xpath("//a[contains(text(), 'медальон')or contains(text(), 'Медальон')]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%9C%D0%B5%D0%B4%D0%B0%D0%BB%D1%8C%D0%BE%D0%BD", url));
                      assertEquals(46, Медальон.size());
    }

    /*
     * Поиск товара по категории "Серьги" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByProductCategory() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Серьги" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> Серьги = driver.findElements(By.xpath("//a[contains(text(), 'серьги')or contains(text(), 'Серьги')]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A1%D0%B5%D1%80%D1%8C%D0%B3%D0%B8", url));
                      assertEquals(48, Серьги.size());
    }

    /*
     * Поиск товара по цвету "Розовый" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByColor() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Розовый" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> Розовый = driver.findElements(By.xpath("//a[contains(text(), 'роз')or contains(text(), 'Роз')]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A0%D0%BE%D0%B7%D0%BE%D0%B2%D1%8B%D0%B9", url));
                      assertEquals(44, Розовый.size());
    }

    /*
     * Поиск товара по вставке "Текстиль" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByInsertion() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Текстиль" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> Текстиль = driver.findElements(By.xpath("//a[contains(text(), 'Текстиль')or contains(text(), 'текстил')]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A2%D0%B5%D0%BA%D1%81%D1%82%D0%B8%D0%BB%D1%8C", url));
                      assertEquals(9,Текстиль.size());
    }

    /*
     * Поиск товара по покрытию "Черный родий" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByCoverage() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Черный родий" + "\n");
        List<WebElement> родий = driver.findElements(By.xpath("//a[contains(text(), 'черным')or contains(text(), 'родием')or contains(text(), 'серебра')]"));
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A7%D0%B5%D1%80%D0%BD%D1%8B%D0%B9%20%D1%80%D0%BE%D0%B4%D0%B8%D0%B9", url));
                      assertSame(8, родий.size());
    }

    /*
     * Поиск товара по материалу "Фарфор" + проверяем что товары соответствуют поиску и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByMaterial() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Фарфор" + "\n");
        String url = driver.getCurrentUrl();
        List<WebElement> фарфор = driver.findElements(By.xpath("//a[contains(text(), 'фарфор')]"));
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A4%D0%B0%D1%80%D1%84%D0%BE%D1%80",url));
                      assertEquals(25, фарфор.size());
    }
    /*
     * Поиск товара по коду,котого нет в наличии "29424" + проверка на соответствующую надпись "по вашему запросу ничего не найдено" и ссылка соответствует
     */
    @Test()
    public void searchForAnItemByCodeIsNotOutOfStock() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("29424" + "\n");
        String successfulSearchText = search.getSearchText();
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=29424",url));
                      assertEquals("по вашему запросу ничего не найдено", successfulSearchText);
    }
}
