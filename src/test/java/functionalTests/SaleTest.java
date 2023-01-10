package functionalTests;

import baseForTests.TestBase;
import filters.Filters;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sections.Sale;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты раздела Sale")
public class SaleTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl + "catalog/sale/");
        sale = new Sale(driver);
        filters = new Filters(driver);
    }

    /**
     * Проверяем количество наименований товаров в базе и на странице раздела 'sale'. <p>
     * Проверка по наименованию изделия.
     */
    @Test
    @Description("Проверяем количество наименований товаров в базе и на странице раздела 'sale'. Проверка по наименованию изделия.")
    public void saleCheckingByProductName() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = sale.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 6));
        }
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Проверка по наименованию дизайнера.
     */
    @Test
    @Description("Проверяем наименования дизайнеров в базе и на странице раздела 'sale'.")
    public void saleCheckingByDesignerName() {
        List<String> sqlList = sale.getDesigners();
        List<WebElement> elements = driver.findElements(designerName);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    /**
     * Проверка по цене со скидкой.
     */
    @Test
    @Description("Проверяем цены с учетом скидок в базе и на странице раздела 'sale'.")
    public void saleCheckingByFinalPrice() {
        List<Integer> sqlList = sale.getFinalPrice();
        List<WebElement> elements = driver.findElements(price);
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    //Проверка по цене без скидки. База и на сайте
    @Test
    public void saleCheckingByPrice() {
        List<Integer> sqlList = sale.getOldPrice();
        //site:
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='price-block__price price-block__price_old']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    //Проверка отображения размера скидки.
    @Test
    public void saleCheckingDisplayOfDiscountAmount() {
        List<Integer> sqlList = sale.getSale();
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='price-block__discount']"));
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer sale = parseInt(result);
            priceList.add(sale);
        }
        //сравниваем содержание списков
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }
}
