package filters;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Material extends Base {

    private final By materialButton = By.xpath("//div[text()='материал']");
    private final By insertButton = By.xpath("//div[text()='вставки']");
    private final By zemcugButton = By.xpath("//div[text()='Жемчуг']");
    private final By kristallyButton = By.xpath("//div[text()='Кристалл']");
    private final By kamenButton = By.xpath("//div[text()='Натуральный камень']");
    private final By stekloButton = By.xpath("//div[text()='Стекло']");
    private final By bronzeButton = By.xpath("//div[contains(text(), 'бронза')]");
    private final By silverButton = By.xpath("//div[contains(text(), 'Серебро')]");
    private final By jewelryAlloyButton = By.xpath("//div[@class='filters__section filters__section_material']//div[contains(text(), 'ювелирный сплав')]");

    public Material(WebDriver driver) {
        super(driver);
    }


    public void clickToJewelryAlloyButton() {
//        click(jewelryAlloyButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(jewelryAlloyButton));
    }

    public void clickToInsertButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(insertButton));
    }

    public void clickToMaterialButton() {
        click(materialButton);
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", driver.findElement(materialButton));
    }

    public void clickToZemcugButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(zemcugButton));
    }

    public void clickToKristallyButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(kristallyButton));
    }

    public void clickToKamenButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(kamenButton));
    }

    public void clickToStekloButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(stekloButton));
    }

    public void clickToBronzeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(bronzeButton));
    }

    public void clickToSilverButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(silverButton));
    }


    //SQL
    public List<String> getListOfZemcug() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_item_gem ON item.id = item_item_gem.item_id " +
                "JOIN item_gem_name ON item_item_gem.item_gem_id = item_gem_name.id " +
                "JOIN item_gem_name_translation ON item_gem_name.id = item_gem_name_translation.item_gem_name_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_gem_name_translation.item_gem_name_id = 2 " +
                "and item_gem_name_translation.locale = 'ru' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfKristally() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_gem_name_translation ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_gem_name_translation.name = 'Кристалл' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfKamen() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_gem_name_translation ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_gem_name_translation.name = 'Натуральный камень' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }


    public List<String> getListOfSteklo() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_gem_name_translation ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_gem_name_translation.name = 'Стекло' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfBronze() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN currency ON item_sku_price.currency_id = currency.id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and storage_id not in "+ unavailableStorages + " and filter_id = 155 and is_archive = 0 and item_sku_price.price != 0 " +
                "and currency.code='RUB' and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_base_metal_group.id = 63 "
//                +
//                "group by item.id"
//                "group by item_catalog_position.position"
                ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getBronze() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN currency ON item_sku_price.currency_id = currency.id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and storage_id in (1,2,3,4,5,6,7,1001,1002,1003,1004,1005) and filter_id = 155 and is_archive = 0 and item_sku_price.price != 0 " +
                "and currency.code='RUB' and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_base_metal_group.id = 63 "
//                +
//                "group by item.id"
//                "group by item_catalog_position.position"
                ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfSilver() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN item_base_metal_group_translation ON item.base_metal_group_id = item_base_metal_group_translation.item_base_metal_group_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 217 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_base_metal_group.name = 'Серебро' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN item_base_metal_group_translation ON item.base_metal_group_id = item_base_metal_group_translation.item_base_metal_group_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_base_metal_group.id = 63 " +
                "group by item_catalog_position.position, item_sku.id "
//                "order by item_sku.updated_at desc "
//                "group by item_catalog_position.position"
                ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println(text.size());
        }

        worker.getSession().disconnect();
    }
}
