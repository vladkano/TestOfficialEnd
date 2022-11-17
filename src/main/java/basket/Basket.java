package basket;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket extends Base {

    private final By itemInBasketButton = By.xpath("//button[@class='product-actions__add-to-cart button-fill']/span");
    private final By plusBasketButton = By.xpath("//button[@class='counter__button counter__button_plus']");
    private final By minusBasketButton = By.xpath("//button[@class='counter__button counter__button_minus']");
    private final By basketButton = By.xpath("//a[@href='/cart']/span");
    private final By catalogButton = By.xpath("//a[@href='/catalog/']");
    private final By newCatalogButton = By.xpath("//a[@href='/catalog/new/']");
    private final By cartCountButton = By.xpath("//a[@href='/cart/']");
    private final By setItemInBasketButton = By.xpath("(//span[text()='В корзину'])[2]");
    private final By goToBasketButton = By.xpath("//a[@class='product-actions__add-to-cart button-go-to-cart']/span");

    private final By plus2 = By.xpath("//input[@name='quantity']");
    private final By max = By.xpath("//div[@class='counter']");
    private final By cartCount = By.xpath("//a[@href='/cart/']/span[@class='icon-with-counter__counter _with-offset']");

    private final By inBasket = By.xpath("//span[text()='в корзине']");
    private final By noBasketHeader = By.xpath("//p[@class='product-actions__notification']");

    public Basket(WebDriver driver) {
        super(driver);
    }


    public String getNoBasketHeader() {
        return driver.findElement(noBasketHeader).getText();
    }

    public String getGoToBasketButtonHeader() {
        return driver.findElement(goToBasketButton).getText();
    }

    public void clickToItemButton() {
        String firstItem = findFirstItemMoreThan5000();
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[contains(text()," + "'" + firstItem.substring(0,20) + "')]")));
    }


    public void clickToRingButton() {
        String firstRing = findFirstRing();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstRing + "']")));
    }

    public void clickToAnotherItemButton() {
        String anotherItem = findFirstItemLessThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + anotherItem + "']")));
    }

    public void clickToItemInBasketButton() {
        click(itemInBasketButton);
    }

    public void clickToSetItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(setItemInBasketButton));
    }

    public void clickToPlusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(plusBasketButton));
        sleep(1000);
//        click(plusBasketButton);
    }

    public String getBasketNumber() {
        return driver.findElement(plus2).getAttribute("value");
    }

    public void clickToMinusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(minusBasketButton));
//        sleep(2000);
//        click(minusBasketButton);
    }

    public Integer getDataMax() {
        return Integer.valueOf(driver.findElement(max).getAttribute("data-max"));
    }

    public void clickToBasketButton() {
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", driver.findElement(basketButton));
        click(basketButton);
    }

    public void clickToGoToBasketButton() {
        click(goToBasketButton);
    }

    public void clickToCatalogButton() {
        click(catalogButton);
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getAttribute("textContent");
    }

    public void clickToCart() {
        click(cartCountButton);
    }

    public void clickToCartFromNew() {
        click(newCatalogButton);
    }

    public String getInBasketHeader() {
        return driver.findElement(inBasket).getAttribute("textContent");
    }


    //SQL
    public static String findFirstItemMoreThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_translations.name, sum(storage_stock.balance) AS count from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN catalog_translation ON catalog_translation.catalog_id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_translation.locale = 'ru' and is_archive = 0 and item_sku_price.price > 5000 and filter_id = 155 " +
                "and storage_id !=1006 and storage_id !=1007 and designer.show = 1 and item_translations.locale = 'ru' and currency_id = 1 " +
                "group by item_catalog_position.position " +
                "HAVING count > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Название товара с остатком более 1 штуки и ценой не менее 5000: " + list.get(1));
        return list.get(1);
    }

    public static String findFirstRing() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_translations.name, SUM(balance) from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog_translation ON catalog_translation.catalog_id = item.catalog_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_translation.catalog_id = 5 and catalog_translation.locale = 'ru' and is_archive = 0 " +
                "and storage_id !=1006 and storage_id !=1007 and item_sku_price.price != 0 and filter_id = 155 " +
                "and designer.show = 1 and item_translations.locale = 'ru' and sku_characteristic_value.characteristic_value = 'Universal' " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(1);
    }

    public static String findFirstItemLessThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_translations.name, sum(storage_stock.balance) AS count from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price < 5000 and item_sku_price.price > 0 and filter_id = 155 " +
                "and storage_id !=1006 and storage_id !=1007 and designer.show = 1 and item_translations.locale = 'ru' and currency_id = 1 " +
                "group by item_catalog_position.position " +
                "HAVING count > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }


    public static Integer findFirstItemIdMoreThan5000() {
        int id;
        String coun;
        List<Integer> list = new ArrayList<>();
        String query = "SELECT item.id, SUM(balance) as c from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price > 5000 and filter_id = 155 " +
                "and storage_id !=1006 and storage_id !=1007 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                coun = resultSet.getString("c");
                list.add(id);
//                System.out.println(coun);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list.get(0));
        return list.get(1);
    }

    public Integer getBalance() {
        int id;
        Map<Integer, Integer> hashMap = new HashMap<>();
        String query = "SELECT item.id, item_translations.name, sum(storage_stock.balance) AS count FROM item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price > 5000 and filter_id = 155 " +
                "and storage_id !=1006 and storage_id !=1007 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position " +
                "HAVING count>1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                int summa = resultSet.getInt("count");
                hashMap.put(id, summa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer firstItem = findFirstItemIdMoreThan5000();
//        System.out.println(hashMap.get(firstItem));
        return hashMap.get(firstItem);
    }

    //Вытаскиваем ссылки на кольца, которые входят в коллекции
    public String getFirstLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT catalog_translation.url, item_collection_translation.url, item_collection_characteristic_translation.url," +
                " item_collection_characteristic_value_translation.url, SUM(balance) from catalog " +
                "JOIN catalog_translation ON catalog.id = catalog_translation.catalog_id " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic_value_translation ON item_collection_characteristic_value_translation.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection_characteristic_translation ON item_collection_characteristic_translation.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "JOIN item_collection_translation ON item_collection_translation.item_collection_id = item_collection.id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                " and item_sku.url is not null and filter_id = 149" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0 " +
                " and item_collection_consist.item_collection_id != 0 and catalog_translation.locale = 'ru' and item_collection_translation.locale = 'ru' " +
                " and item_collection_characteristic_translation.locale = 'ru' and item_collection_characteristic_value_translation.locale = 'ru' " +
                " group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection_translation.url");
                name3 = resultSet.getString("item_collection_characteristic_translation.url");
                name4 = resultSet.getString("item_collection_characteristic_value_translation.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
//                System.out.println(name + name2 + name3 + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        return list.get(0);
    }

    //Вытаскиваем ссылки на браслеты, которые входят в коллекции
    public String getSecondLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT catalog_translation.url, item_collection_translation.url, item_collection_characteristic_translation.url," +
                " item_collection_characteristic_value_translation.url, SUM(balance) from catalog " +
                "JOIN catalog_translation ON catalog.id = catalog_translation.catalog_id " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic_value_translation ON item_collection_characteristic_value_translation.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection_characteristic_translation ON item_collection_characteristic_translation.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "JOIN item_collection_translation ON item_collection_translation.item_collection_id = item_collection.id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                " and item_sku.url is not null and filter_id = 149" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0 " +
                " and item_collection_consist.item_collection_id != 0 and catalog_translation.locale = 'ru' and item_collection_translation.locale = 'ru' " +
                " and item_collection_characteristic_translation.locale = 'ru' and item_collection_characteristic_value_translation.locale = 'ru' " +
                " group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection_translation.url");
                name3 = resultSet.getString("item_collection_characteristic_translation.url");
                name4 = resultSet.getString("item_collection_characteristic_value_translation.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
//                System.out.println(name + name2 + name3 + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        int id;
        Map<Integer, Integer> hashMap = new HashMap<>();
//        String query = "SELECT i.id, item_translations.name, sum(ss.balance) AS count FROM item AS i " +
//                "JOIN item_translations ON i.id = item_translations.item_id " +
//                "JOIN item_sku AS si ON i.id=si.item_id " +
//                "JOIN storage_stock AS ss ON ss.sku_id=si.id " +
//                "GROUP BY i.id, item_translations.name, si.id " +
//                "HAVING count>1";
        String query = "SELECT item.id, item_translations.name, sum(storage_stock.balance) AS count FROM item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=3 and is_archive = 0 and item_sku_price.price != 0 and filter_id = 148 " +
                "and storage_id !=1006 and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item_catalog_position.position " +
                "HAVING count>1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                int summa = resultSet.getInt("count");
                hashMap.put(id, summa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer firstItem = findFirstItemIdMoreThan5000();
        System.out.println(hashMap.get(firstItem));
        worker.getSession().disconnect();
    }


}
