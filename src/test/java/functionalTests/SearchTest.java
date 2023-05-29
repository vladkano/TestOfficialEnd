package functionalTests;

import baseForTests.TestBase;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sections.Searchs;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Epic("Тесты поиска")
  public class SearchTest extends TestBase {
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
     * Поиск товара по наименованию дизайнера "Moonka"
     */
    @Test()
    public void searchForAnItemByDesigner() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Moonka" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=Moonka", url));
    }

    /*
     * Поиск товара по коду "41997"
     */
    @Test()
    public void searchForAnItemByCode() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("41977" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=41977", url));
    }

    /*
     * Поиск товара по названию "Колье цепь из серебра"
     */
    @Test()
    public void searchForAnItemByName() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Колье цепь из серебра" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%9A%D0%BE%D0%BB%D1%8C%D0%B5%20%D1%86%D0%B5%D0%BF%D1%8C%20%D0%B8%D0%B7%20%D1%81%D0%B5%D1%80%D0%B5%D0%B1%D1%80%D0%B0", url));
    }

    /*
     * Поиск товара по части имени "Медальон"
     */
    @Test()
    public void searchForAnItemByPartOfTheName() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Медальон" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%9C%D0%B5%D0%B4%D0%B0%D0%BB%D1%8C%D0%BE%D0%BD", url));
    }

    /*
     * Поиск товара по категории "Серьги"
     */
    @Test()
    public void searchForAnItemByProductCategory() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Серьги" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A1%D0%B5%D1%80%D1%8C%D0%B3%D0%B8", url));
    }

    /*
     * Поиск товара по цвету "Розовый"
     */
    @Test()
    public void searchForAnItemByColor() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Розовый" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A0%D0%BE%D0%B7%D0%BE%D0%B2%D1%8B%D0%B9", url));
    }

    /*
     * Поиск товара по вставке "Текстиль"
     */
    @Test()
    public void searchForAnItemByInsertion() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Текстиль" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A2%D0%B5%D0%BA%D1%81%D1%82%D0%B8%D0%BB%D1%8C", url));
    }

    /*
     * Поиск товара по покрытию "Черный родий"
     */
    @Test()
    public void searchForAnItemByCoverage() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Черный родий" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A7%D0%B5%D1%80%D0%BD%D1%8B%D0%B9%20%D1%80%D0%BE%D0%B4%D0%B8%D0%B9", url));
    }

    /*
     * Поиск товара по материалу "Фарфор"
     */
    @Test()
    public void searchForAnItemByMaterial() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("Фарфор" + "\n");
        String url = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=%D0%A4%D0%B0%D1%80%D1%84%D0%BE%D1%80",url));
    }
    /*
     * Поиск товара по коду,котого нет в наличии "29424" + проверка на соответствующую надпись "по вашему запросу ничего не найдено"
     */
    @Test()
    public void searchForAnItemByCodeIsNotOutOfStock() {
        driver.get(TestConfig.SITE_URL);
        ClickSearch();
        search.InputToSearchWeb("29424" + "\n");
        String successfulSearchText = search.getSearchText();
        String url = driver.getCurrentUrl();
        assertEquals("по вашему запросу ничего не найдено", successfulSearchText);
        Assertions.assertAll(
                () -> assertEquals(TestConfig.SITE_URL + "search?search=29424",url));
    }
}
