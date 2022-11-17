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


public class Jewelry extends Base {


    private final By jewelryButton = By.xpath("//a[@href='/jewelry/']");

    public Jewelry(WebDriver driver) {
        super(driver);
    }

    public void clickToJewelryButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(jewelryButton));
    }

    public List<String> getNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and (base_metal_group_id = 2 or base_metal_group_id = 11) " +
                "and balance > 0 and item_translations.locale = 'ru' and filter_id = 337 and designer.show = 1 " +
                "group by item.id " +
                "order by item_catalog_position.position ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
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
        String query = "SELECT designer_translation.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN designer_translation ON designer_translation.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and (base_metal_group_id = 2 or base_metal_group_id = 11) " +
                "and balance > 0 and designer_translation.locale = 'ru' and filter_id = 337 and designer.show = 1 " +
                "and storage_id !=1006 and storage_id !=1007 " +
                "group by item.id " +
                "order by item_catalog_position.position ";
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
        String query = "SELECT item_sku_price.price, (item_sku.price * discount/100) as discount from item_sku_price " +
                "JOIN item_sku ON item_sku_price.item_sku_id = item_sku.id " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and (base_metal_group_id = 2 or base_metal_group_id = 11) " +
                "and storage_id !=1006 and storage_id !=1007 and balance > 0 and filter_id = 337 and designer.show = 1 " +
                "group by item.id " +
                "order by item_catalog_position.position ";
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
//        System.out.println("метод getPrice: " + text);
        return text;
    }


    public static void main(String[] args) {
        int price, discount;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku_price.price, (item_sku.price * discount/100) as discount from item_sku_price " +
                "JOIN item_sku ON item_sku_price.item_sku_id = item_sku.id " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and (base_metal_group_id = 2 or base_metal_group_id = 11) " +
                "and balance > 0 and filter_id = 337 and designer.show = 1 " +
                "order by item_catalog_position.position ";
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
        System.out.println("метод getPrice: " + text);
        worker.getSession().disconnect();
    }

}
