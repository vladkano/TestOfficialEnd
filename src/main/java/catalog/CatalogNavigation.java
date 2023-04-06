package catalog;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CatalogNavigation extends Base {

    By showMoreButton = By.xpath("//div[@class='catalog__more']/button/span");
    By showMoreTrendsButton = By.xpath("//span[contains(text(), 'показать ещё')]");
    By numberOfPages = By.xpath("//button[@class='pagination__link'][6]");


    public CatalogNavigation(WebDriver driver) {
        super(driver);
    }

    public String getNumberOfPages() {
        return driver.findElement(numberOfPages).getText();
    }

    public void clickOnShowMoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(showMoreButton));
    }

    public void clickOnShowMoreTrendsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(showMoreTrendsButton));
    }

    public int countRings() {
        int id = 0;
        String query = "SELECT COUNT(distinct item_translations.item_id) as count from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN catalog_translation ON catalog_translation.catalog_id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_translation.locale = 'ru' and is_archive = 0 and catalog_translation.catalog_id = 5 " +
                "and storage_id not in "+ unavailableStorages + " and item_sku_price.price != 0 and balance > 0 " +
                "and filter_id in (155) and designer.show = 1 and item_translations.locale = 'ru'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public static void main(String[] args) {
        int id = 0;
        String query = "SELECT COUNT(distinct item_catalog_position.item_id) as count from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and item_sku_price.price != 0 and balance > 0 " +
                "and filter_id = 155 and designer.show = 1 and item_translations.locale = 'ru'"
                ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(id);
        worker.getSession().disconnect();


    }


}
