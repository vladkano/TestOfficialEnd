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

public class ColorsAndCoverage extends Base {

    private final By colorButton = By.xpath("//div[text()='цвет']");
    private final By greenButton = By.xpath("//div[contains(text(), 'Зеленый')]");
    private final By blueButton = By.xpath("//div[contains(text(), 'Синий')]");
    private final By mixButton = By.xpath("//div[contains(text(), 'Мульти')]");
    private final By coveringButton = By.xpath("//div[text()='покрытие']");
    private final By rodiiButton = By.xpath("(//div[contains(text(), 'родий')])[3]");
    private final By goldButton = By.xpath("//div[contains(text(), 'золото')]");
    private final By whiteButton = By.xpath("//div[contains(text(), 'Белый')]");

    public ColorsAndCoverage(WebDriver driver) {
        super(driver);
    }


    public void clickToWhiteButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whiteButton));
    }

    public void clickToCoveringButton() {
        click(coveringButton);
    }

    public void clickToColorButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(colorButton));
    }

    public void clickToGreenButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(greenButton));
    }

    public void clickToBlueButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(blueButton));
    }

    public void clickToMixButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(mixButton));
    }

    public void clickToRodiiButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(rodiiButton));
    }

    public void clickToGoldButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(goldButton));
    }

    //SQL
    public List<String> getListOfGreenColor() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_color_list ON item.id = item_color_list.item_id " +
                "JOIN item_color_value ON item_color_list.color_value_id = item_color_value.id " +
                "JOIN item_color_value_translation ON item_color_value.id = item_color_value_translation.item_color_value_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "and item_color_value_translation.locale = 'ru' and item_color_value_translation.name = 'Зеленый' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(id);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfBlueColor() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_color_list ON item.id = item_color_list.item_id " +
                "JOIN item_color_value ON item_color_list.color_value_id = item_color_value.id " +
                "JOIN item_color_value_translation ON item_color_value.id = item_color_value_translation.item_color_value_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "and item_color_value_translation.locale = 'ru' and item_color_value_translation.name = 'Синий' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(id);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfMixColor() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_color_list ON item.id = item_color_list.item_id " +
                "JOIN item_color_value ON item_color_list.color_value_id = item_color_value.id " +
                "JOIN item_color_value_translation ON item_color_value.id = item_color_value_translation.item_color_value_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "and item_color_value_translation.locale = 'ru' and item_color_value_translation.name = 'Мультицвет' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(id);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListOfRodii() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_coverage_list ON item.id = item_coverage_list.item_id " +
                "JOIN item_coverage_value ON item_coverage_list.coverage_value_id = item_coverage_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_coverage_value.name = 'Родий' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(id);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getListGold() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN designer_translation ON designer_translation.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_coverage_list ON item.id = item_coverage_list.item_id " +
                "JOIN item_coverage_value ON item_coverage_list.coverage_value_id = item_coverage_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer_translation.locale = 'ru' and item_translations.locale = 'ru' and item_coverage_value.name = 'золото' " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(id);
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
        String query = "SELECT item.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_coverage_list ON item.id = item_coverage_list.item_id " +
                "JOIN item_coverage_value ON item_coverage_list.coverage_value_id = item_coverage_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' and item_coverage_value.name = 'Розовое золото' " +
                "group by item_catalog_position.position";
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
