package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewItems extends Base {

    private final By newItemsButton = By.xpath("//a[@href='/catalog/new/']");

    public NewItems(WebDriver driver) {
        super(driver);
    }

    public void clickToNewItemsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(newItemsButton));
    }

    public List<String> getNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN catalog_translation ON catalog_translation.catalog_id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and item_translations.locale = 'ru' and catalog_translation.locale = 'ru' and filter_id = 155 and is_archive = 0 " +
                "and storage_id not in (1006, 1007) and balance > 0 and designer.show = 1 and item_sku_price.price != 0 " +
//                "group by item.id, item.name, designer.id, designer.name, catalog.id, catalog.name, catalog.url " +
//                "group by item_catalog_position.position " +
                "order by item.created_at desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                String name2 = name.trim().replaceAll(" +", " ");
                text.add(name2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getDesigners() {
        String designer;
        List<String> text = new ArrayList<>();
        String query = "SELECT designer_translation.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog_translation ON catalog_translation.catalog_id = item.catalog_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_translation.catalog_id in (1,19) and catalog_translation.locale = 'ru' and is_archive = 0 and filter_id = 155 " +
                "and storage_id !=1006 and storage_id !=1007 and balance > 0 and designer.show = 1 and item_sku_price.price != 0 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position " +
                "order by item.created_at desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                designer = resultSet.getString("name");
//                System.out.println(designer);
                text.add(designer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getDesigner: " + text);
        return text;
    }


    public List<Integer> getPrice() {
        int price, discount;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku_price.price, (item_sku_price.price * discount/100) as discount from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and storage_id !=1006 and storage_id !=1007 and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position " +
                "order by item.created_at desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price = resultSet.getInt("price");
                discount = resultSet.getInt("discount");
                int priceNew = price - discount;
//                System.out.println(discount);
                text.add(priceNew);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 " +
                "and item_sku.url is not null and balance > 0 and catalog.show = 1 " +
                "group by item.id, item.name, designer.id, designer.name, catalog.id, catalog.name, catalog.url " +
                "order by item_sku.created_at desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }

}
