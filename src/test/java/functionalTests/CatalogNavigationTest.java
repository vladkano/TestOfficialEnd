package functionalTests;

import baseForTests.TestBase;
import catalog.CatalogNavigation;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты навигации в каталоге")
public class CatalogNavigationTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        navigation = new CatalogNavigation(driver);
    }

    /**
     * Кнопка "Показать ещё":<p>
     * Проверяем, что после нажатия кнопки "Показать ещё" количество единиц товара на странице равно 96
     */
    @Test()
    @Description("Проверяем, что после нажатия кнопки 'Показать ещё' количество единиц товара на странице равно 96")
    public void numberOfItem() {
        driver.get(getUrl + "catalog/koltsa");
        navigation.clickOnShowMoreButton();
        sleep(2000);
        List<WebElement> numbers = driver.findElements(numberOfItem);
        navigation.clickOnNameLink();
        assertEquals(96, numbers.size());
    }

    /**
     * Проверка отсутствия кнопки 'Показать ещё', кнопка должна отображается только, если в разделе больше 48 продуктов
     */
    @Test
    @Description("Проверка отсутствия кнопки 'Показать ещё', кнопка должна отображается только, если в разделе больше 48 продуктов")
    public void showMoreNotVisible() {
        driver.get(getUrl + "catalog/koltsa/?type_product=nabory-kolets");
        int numbers = driver.findElements(showMore).size();
        assertEquals(0, numbers);
    }

    /**
     * Проверка отсутствия кнопки 'Показать ещё' при переходе на последнюю страницу каталога
     */
    @Test
    @Description("Проверка отсутствия кнопки 'Показать ещё' при переходе на последнюю страницу каталога")
    public void showMoreLastPage() {
        driver.get(getUrl + "catalog/koltsa/?type_product=pechatki");
//        navigation.clickOnShowMoreButton();
        int numbers = driver.findElements(showMore).size();
        assertEquals(0, numbers);
    }


    /**
     * Постраничная навигация:<p>
     * Проверяем, что кол-во страниц в каталоге колец равняется количеству товаров в БД деленное на количество товаров отображаемых на странице каталога(48)
     */
    @Description("Проверяем, что кол-во страниц в каталоге колец равняется количеству товаров в БД деленное на количество товаров отображаемых на странице каталога(48)")
    @Test
    public void numberOfPagesKoltsa() {
        driver.get(getUrl + "catalog/koltsa");
        double count = Math.ceil((double) navigation.countRings()/numberOfFoto);
        int countOfRings = (int) count;
        int numberOfPages = Integer.parseInt(navigation.getNumberOfPages());
        assertEquals(countOfRings, numberOfPages);
    }

    /**
     * Проверяем, что в каталоге на странице колец отображается 48 изделий
     */
    @Test()
    @Description("Проверяем, что в каталоге на странице колец отображается 48 изделий")
    public void pageNumber48Koltsa() {
        driver.get(getUrl + "catalog/koltsa");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    /**
     * Проверяем, что в каталоге на странице серёг отображается 48 изделий
     */
    @Test()
    @Description("Проверяем, что в каталоге на странице серёг отображается 48 изделий")
    public void pageNumber48Sergi() {
        driver.get(getUrl + "catalog/sergi");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    /**
     * Проверяем, что в каталоге на странице колье отображается 48 изделий
     */
    @Test()
    @Description("Проверяем, что в каталоге на странице колье отображается 48 изделий")
    public void pageNumber48Kole() {
        driver.get(getUrl + "catalog/kole");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

    /**
     * Проверяем, что в каталоге на странице браслетов отображается 48 изделий
     */
    @Test()
    @Description("Проверяем, что в каталоге на странице браслетов отображается 48 изделий")
    public void pageNumber48Braslety() {
        driver.get(getUrl + "catalog/braslety");
        List<WebElement> numbers = driver.findElements(numberOfItem);
        assertEquals(numberOfFoto, numbers.size());
    }

}
