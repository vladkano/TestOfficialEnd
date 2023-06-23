package tags;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Tags extends Base {

    private static String tags;
    private final By tag = By.xpath("//b[@class='tag-list__tag']");

    public Tags(WebDriver driver) {
        super(driver);
    }

    public String getTag() {
        return driver.findElement(tag).getText();
    }

    public String getRingsTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getEarringsTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String nameEarringsTags() {
        String query = "SELECT item_tag.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and item_sku_price.price != 0 and filter_id = 147 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_tag.id, item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return tags;
    }

    public String braceletTags() {
        String query = "SELECT item_tag.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN catalog_translation ON catalog_translation.catalog_id = item.catalog_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_translation.catalog_id in (3,18) and is_archive = 0 and item_sku_price.price != 0 and filter_id = 148 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_tag.id, item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(tags);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return tags;
    }

    public String nameOfRingTags() {
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and item_sku_price.price != 0 and filter_id = 149 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return tags;
    }


    public static void main(String[] args) {
        String name = "";
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and item_sku_price.price != 0 and filter_id = 147 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }

}
