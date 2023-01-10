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

public class Size extends Base {


    private final By sizeButton = By.xpath("(//div[@class='filters__list-tab'])[4]");
    private final By firstSizeButton = By.xpath("(//div[@class='filters__item-left']/div[contains(text(), '14')])[2]");
    private final By secondSizeButton = By.xpath("(//div[@class='filters__item-left']/div[contains(text(), '15.5')])[2]");
    private final By thirdSizeButton = By.xpath("(//div[@class='filters__item-left']/div[contains(text(), '16')])[3]");
    private final By universalSizeButton = By.xpath("//div[contains(text(), 'Universal')]");
    private final By currentSize = By.xpath("//li[@class='product-variant product-variant_size product-variant_active']/span");
    private final By firstCurrentSizeButton = By.xpath("(//span[@class='product-variant__variant product-variant__variant_size'])[2]");
    private final By secondCurrentSizeButton = By.xpath("//ul/li[3]/label/span[@class='product-variant__variant product-variant__variant_size']");
    private final By sizeHeader = By.xpath("//span[@class='cart-item__additional-params']");
    private final By plateHeader = By.xpath("//span[@class='notification__text']");

    public Size(WebDriver driver) {
        super(driver);
    }


    public String getPlateHeader() {
        return driver.findElement(plateHeader).getAttribute("textContent");
    }

    public String getSizeHeader() {
        return driver.findElement(sizeHeader).getAttribute("textContent");
    }

    public void clickToSecondCurrentSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondCurrentSizeButton));
    }

    public void clickOnImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(getImageLink()));
    }

    public void clickToFirstCurrentSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstCurrentSizeButton));
    }

    public String getCurrentSize() {
        return driver.findElement(currentSize).getAttribute("textContent");
    }

    public void clickToSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(sizeButton));
    }

    public void clickToFirstSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstSizeButton));
    }

    public void clickToThirdSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdSizeButton));
    }

    public void clickToSecondSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondSizeButton));
    }

    public void clickToUniversalSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(universalSizeButton));
    }

    //SQL
    //Находим товар с плашкой 3-5 дней
    public String findItem35days() {
        String name = "";
        String query = "select item_sku.name from item_sku " +
                "where id = " + findFirstItem() + "";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    //Находим айдишники товаров с плашкой 3-5 дней
    public static String findFirstItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "WITH cte_a AS ( " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id where storage_id=5 and designer.name not like 'LAV%' group by sku_id " +
                "),cte_b AS (select sku_id, balance from storage_stock where storage_id=2 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=3 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=4 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=7 group by sku_id " +
                ")select t1.*,t2.b2 from (select sku_id,sum(balance) as b1 from cte_b group by sku_id HAVING(sum(balance))=0 " +
                ") as t1 " +
                "JOIN (select sku_id,sum(balance) as b2 from cte_a group by sku_id HAVING(sum(balance))>0 " +
                ") as t2 " +
                "ON t1.sku_id=t2.sku_id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("sku_id");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    //Находим товар с плашкой от 7 дней
    public String findItem7days() {
        String name = "";
        String query = "select item_sku.name from item_sku " +
                "where id = " + findSecondItem() + "";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    //Находим айдишники товаров с плашкой 7 дней
    public static String findSecondItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "WITH cte_a AS ( " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "where storage_id=1 and designer.name not like 'LAV%' group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id where storage_id=5 and designer.name not like 'LAV%' group by sku_id " +
                "),cte_b AS (select sku_id, balance from storage_stock where storage_id=2 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=3 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=4 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=7 group by sku_id " +
                ")select t1.*,t2.b2 from (select sku_id,sum(balance) as b1 from cte_b group by sku_id HAVING(sum(balance))=0 " +
                ") as t1 " +
                "JOIN (select sku_id,sum(balance) as b2 from cte_a group by sku_id HAVING(sum(balance))>0 " +
                ") as t2 " +
                "ON t1.sku_id=t2.sku_id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("sku_id");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(1);
    }


    public List<String> getListOfFirstSize() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '14' " +
                "and storage_id not in "+ unavailableStorages + " and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position ";
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

    public List<String> getListOfSecondSize() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '15.5' " +
                "and storage_id not in "+ unavailableStorages + " and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position ";
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

    public List<String> getListOfUniversalSize() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and storage_id not in "+ unavailableStorages + " and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 and balance > 0 " +
                "and designer.show = 1 and item_translations.locale = 'ru' and sku_characteristic_value.characteristic_value = 'Universal' " +
                " group by item_catalog_position.position";
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

    public static void main(String[] args) {
        String name;
        List<String> list = new ArrayList<>();
        String query = "WITH cte_a AS ( " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id where storage_id=5 and designer.name not like 'LAV%' group by sku_id " +
                "),cte_b AS (select sku_id, balance from storage_stock where storage_id=2 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=3 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=4 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=7 group by sku_id " +
                ")select t1.*,t2.b2 from (select sku_id,sum(balance) as b1 from cte_b group by sku_id HAVING(sum(balance))=0 " +
                ") as t1 " +
                "JOIN (select sku_id,sum(balance) as b2 from cte_a group by sku_id HAVING(sum(balance))>0 " +
                ") as t2 " +
                "ON t1.sku_id=t2.sku_id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("sku_id");
                list.add(name);
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }
}
