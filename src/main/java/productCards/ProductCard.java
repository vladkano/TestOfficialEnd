package productCards;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCard extends Base {

    private final By structureButton = By.xpath("//div[@class='accordion-vue']");
    private final By specification = By.xpath("//p[@class='product-info__specification']");

    private final By availabilityButton = By.xpath("//div[@class='accordion-vue product-info__storage-quantities']/h3");
    private final By tsvetnoy = By.xpath("//span[text()='Цветной']");
    private final By metropolis = By.xpath("//span[text()='Метрополис']");
    private final By afimoll = By.xpath("//span[text()='Афимолл']");
    private final By atrium = By.xpath("//span[text()='Атриум']");
    private final By redBridge = By.xpath("//span[text()='APR Санкт-Петербург']");
    private final By paveletskaya = By.xpath("//span[text()='Павелецкая плаза']");
    private final By krasnodar = By.xpath("//span[text()='Галерея Краснодар']");
    private final By kazan = By.xpath("//span[text()='KazanMall']");
    private final By availabilityText = By.xpath("//p[@class='storages-list__description']");

    private final By jewelryCareButton = By.xpath("//div[@class='accordion-vue product-info__maintenance']/h3");
    private final By jewelryCareHeader = By.xpath("//div[@class='accordion-vue product-info__maintenance accordion-vue_opened']//h4");
    private final By jewelryCareText = By.xpath("//div[@id='maintenance-accordion']/p[@class='product-info__text']");
    private final By bijouterieCareHeader = By.xpath("(//div[@class='accordion-vue product-info__maintenance accordion-vue_opened']//h4)[2]");
    private final By bijouterieCareText = By.xpath("(//div[@id='maintenance-accordion']/p[@class='product-info__text'])[2]");

    private final By payInfoButton = By.xpath("//div[@class='accordion-vue product-info__payment-return']/h3");
    private final By payInfoText = By.xpath("//div[@id='payment-accordion']/p[@class='product-info__text']");

    private final By guaranteeButton = By.xpath("//div[@class='accordion-vue product-info__warranty']/h3");
    private final By guaranteeText = By.xpath("//div[@id='warranty-accordion']/p[@class='product-info__text']");
    private final By code = By.xpath("//p[@class='product-main-info__code']");

    private final By receivingText = By.xpath("//span[@class='delivery-accordion__title-text']");
    private final By receivingCity = By.xpath("//h3[@class='delivery-accordion__title']/span[2]");


    private final By recentlyViewedProductsHeader = By.xpath("//section[@class='products-slider viewed-products product-card__products-slider']/h2");
    private final By designerNameFromRecentlyViewedProducts = By.xpath("//div[@class='catalog-card__designer']/a");
    private final By priceFromRecentlyViewedProducts = By.xpath("//div[@class='price-block catalog-card__price']//span[1]");


    public ProductCard(WebDriver driver) {
        super(driver);
    }


    public String getPriceFromRecentlyViewedProducts() {
        return driver.findElement(priceFromRecentlyViewedProducts).getText();
    }

    public String getDesignerNameFromRecentlyViewedProducts() {
        return driver.findElement(designerNameFromRecentlyViewedProducts).getText();
    }

    public String getRecentlyViewedProductsHeader() {
        return driver.findElement(recentlyViewedProductsHeader).getAttribute("textContent");
    }

    public void clickToStructureButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(structureButton));
    }

    public String getSpecification() {
        return driver.findElement(specification).getAttribute("textContent");
    }

    public ProductCard clickToAvailabilityButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(availabilityButton));
        return this;
    }

    public String getTsvetnoy() {
        return driver.findElement(tsvetnoy).getAttribute("textContent");
    }

    public String getMetropolis() {
        return driver.findElement(metropolis).getAttribute("textContent");
    }

    public String getAfimoll() {
        return driver.findElement(afimoll).getAttribute("textContent");
    }

    public String getAtrium() {
        return driver.findElement(atrium).getAttribute("textContent");
    }

    public String getRedBridge() {
        return driver.findElement(redBridge).getAttribute("textContent");
    }

    public String getPaveletskaya() {
        return driver.findElement(paveletskaya).getAttribute("textContent");
    }

    public String getKrasnodar() {
        return driver.findElement(krasnodar).getAttribute("textContent");
    }

    public String getKazan() {
        return driver.findElement(kazan).getAttribute("textContent");
    }

    public String getAvailabilityText() {
        return driver.findElement(availabilityText).getAttribute("textContent");
    }

    public ProductCard clickToJewelryCareButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(jewelryCareButton));
//        driver.findElement(jewelryCareButton).click();
        return this;
    }

    public String getJewelryCareHeader() {
        return driver.findElement(jewelryCareHeader).getAttribute("textContent");
    }

    public String getJewelryCareText() {
        return driver.findElement(jewelryCareText).getAttribute("textContent");
    }

    public String getBijouterieCareHeader() {
        return driver.findElement(bijouterieCareHeader).getAttribute("textContent");
    }

    public String getBijouterieCareText() {
        return driver.findElement(bijouterieCareText).getAttribute("textContent");
    }

    public ProductCard clickToDeliveryButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(payInfoButton));
        return this;
    }

    public String getDeliveryText() {
        return driver.findElement(payInfoText).getAttribute("textContent");
    }

    public ProductCard clickToGuaranteeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(guaranteeButton));
        return this;
    }

    public String getGuaranteeText() {
        return driver.findElement(guaranteeText).getAttribute("textContent");
    }

    public String getReceivingText() {
        return driver.findElement(receivingText).getAttribute("textContent");
    }

    public String getReceivingCity() {
        return driver.findElement(receivingCity).getAttribute("textContent");
    }

    public String getLocation() {
        return driver.findElement(location).getAttribute("textContent");
    }



    public String getItemCode() {
        return driver.findElement(code).getText();
    }


    public static void main(String[] args) {
        String code;
        List<String> text = new ArrayList<>();
        String query = "SELECT code from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'sergi' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
                System.out.println(code);
                text.add(code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }


}
