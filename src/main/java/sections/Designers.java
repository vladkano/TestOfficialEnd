package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Designers extends Base {

    private final String url = mainPageUrl + "designers/";

    private final By designersButton = By.xpath("//a[@href='/designers/']");
    private final By designersFirstHref = By.xpath("//div[@class='new-designers__wrapper']/a");
    private final By designersSecondHref = By.xpath("//div[@class='new-designers__wrapper']/a[2]");
    private final By designersThirdHref = By.xpath("//div[@class='new-designers__wrapper']/a[3]");
    private final By firstPopularHref = By.xpath("//div[@class='popular-designers__item']/a");
    private final By lastPopularHref = By.xpath("//div[@class='popular-designers__item'][last()]/a");
    private final By designer = By.xpath("//li[@class='index-designers__name']/a");

    //карточка товара
    private final By designerPhoto = By.xpath("//span[@class='picture designer-block__picture']/picture/img");
    private final By designerName = By.xpath("//a[@class='designer-block__link']");
    private final By designerText = By.xpath("//p[@class='designer-block__description']");

    public Designers(WebDriver driver) {
        super(driver);
    }


    public String getDescriptions() {
        List<WebElement> designersName = driver.findElements(designerLink);
        return designersName.get(3).getText();
    }

    public void clickOnItemName() {
        List<WebElement> itemsName = driver.findElements(nameLink);
        itemsName.get(3).click();
    }

    public void clickToDesignerButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerName));
    }

    public String getDesignerText() {
        return driver.findElement(designerText).getText();
    }

    public String getDesignerName() {
        return driver.findElement(designerName).getText();
    }

    public String getDesignerPhotoAlt() {
        return driver.findElement(designerPhoto).getAttribute("alt");
    }

    public String getFirstDesignerHref() {
        List<WebElement> designersList = driver.findElements(designer);
        return designersList.get(0).getAttribute("href");
    }

    public String get10DesignerHref() {
        List<WebElement> designersList = driver.findElements(designer);
        return designersList.get(9).getAttribute("href");
    }

    public String get20dDesignerHref() {
        List<WebElement> designersList = driver.findElements(designer);
        return designersList.get(19).getAttribute("href");
    }

    public void clickToFirstDesignerLink() {
        List<WebElement> designersList = driver.findElements(designer);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", designersList.get(0));
    }

    public void clickTo10DesignerLink() {
        List<WebElement> designersList = driver.findElements(designer);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", designersList.get(9));
    }

    public void clickTo20DesignerLink() {
        List<WebElement> designersList = driver.findElements(designer);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", designersList.get(19));
    }


    public String getFirstPopularHref() {
        return driver.findElement(firstPopularHref).getAttribute("href");
    }

    public String getLastPopularHref() {
        return driver.findElement(lastPopularHref).getAttribute("href");
    }

    public void clickToDesignersButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designersButton));
    }

    public String getDesignersFirstHref() {
        return driver.findElement(designersFirstHref).getAttribute("href");
    }

    public String getDesignersSecondHref() {
        return driver.findElement(designersSecondHref).getAttribute("href");
    }

    public String getDesignersThirdHref() {
        return driver.findElement(designersThirdHref).getAttribute("href");
    }


    public void clickToFirstDesignerHref() {
        driver.get(url + getUrls().get(0) + "/");
    }

    public void clickToSecondDesignerHref() {
        driver.get(url + getUrls().get(1) + "/");
    }

    public void clickToThirdDesignerHref() {
        driver.get(url + getUrls().get(2) + "/");
    }

    public void clickToFirstPopularHref() {
        driver.get(url + getPopularUrls().get(0) + "/");
    }

    public void clickToSecondPopularHref() {
        driver.get(url + getPopularUrls().get(getPopularUrls().size() - 1) + "/");
    }

    public String getDesignerDescription(String text) {
        String description = "";
        String query = "select designer_translation.description from designer_translation " +
                "JOIN designer ON designer_translation.designer_id = designer.id " +
                "where designer_translation.locale = 'ru' and `show` = 1 and designer_translation.name = " + "'" + text + "'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                description = resultSet.getString("description");
//                System.out.println(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

    public static List<String> getUrls() {
        String url;
        List<String> text = new ArrayList<>();
        String query = "select designer_translation.url from designer " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "where designer.show = 1 and designer_translation.locale = 'ru' " +
                "group by created_at desc limit 3";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                url = resultSet.getString("url");
//                System.out.println(url);
                text.add(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static List<String> getDesignerUrlForFilter() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "select designer_translation.url from designer " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "where designer.show = 1 and designer_translation.locale = 'ru' " +
                "group by designer.created_at desc limit 3";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("url");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(text);
        return text;
    }

    public static Integer getFirstFilterID() {
        int filter = 0;
        String query = "select item_catalog_position_filter.id from item_catalog_position_filter " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlForFilter().get(0) + "%' " +
                "order by id desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filter = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(filter);
        return filter;
    }

    public static Integer getSecondFilterID() {
        int filter = 0;
        String query = "select item_catalog_position_filter.id from item_catalog_position_filter " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlForFilter().get(1) + "%' " +
                "order by id desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filter = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filter;
    }

    public static Integer getThirdFilterID() {
        int filter = 0;
        String query = "select item_catalog_position_filter.id from designer " +
                "JOIN item on item.designer_id = designer.id " +
                "JOIN item_catalog_position on item.id = item_catalog_position.item_id " +
                "JOIN item_catalog_position_filter on item_catalog_position.filter_id = item_catalog_position_filter.id " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlForFilter().get(2) + "%'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filter = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filter;
    }

    public static List<String> getPopularUrls() {
        String url;
        List<String> text = new ArrayList<>();
        String query = "select designer_translation.url from designer " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "where designer.show = 1 and designer_translation.locale = 'ru' and is_popular = 1 " +
                "group by designer.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                url = resultSet.getString("url");
//                System.out.println(url);
                text.add(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static List<String> getDesignerUrlsForPopular() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "select url from designer " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "where designer.show = 1 and designer_translation.locale = 'ru' and is_popular = 1 " +
                "group by designer.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("url");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static Integer getFirstFilterIDForPopular() {
        int filter = 0;
        String query = "select item_catalog_position_filter.id from item_catalog_position_filter " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlsForPopular().get(0) + "%' " +
                "order by id desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filter = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(filter);
        return filter;
    }


    public static Integer getLastFilterIDForPopular() {
        int filter = 0;
        String query = "select item_catalog_position_filter.id from item_catalog_position_filter " +
                "where item_catalog_position_filter.filters like" +
                " " + "'%" + getDesignerUrlsForPopular().get(getDesignerUrlsForPopular().size() - 1) + "%'" +
                "order by id desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filter = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(filter);
        return filter;
    }

    public List<String> getFirstLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0  and filter_id = " + "" + getFirstFilterID() + " " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.toLowerCase());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getSecondLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0  and filter_id = "  + "" + getSecondFilterID() + " " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.toLowerCase());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getThirdLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0  and filter_id = " + "" + getThirdFilterID() + " " +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.toLowerCase());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static List<String> getListOfDesigners() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "select designer_translation.name from designer " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and storage_id not in " + unavailableStorages + " and balance > 0 and designer.show = 1 and item_sku_price.price != 0 " +
                "and designer_translation.locale = 'ru' " +
                "group by designer_translation.name";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(url);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }


    public static List<String> getDesignerUrlForList() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "select designer_translation.url from designer " +
                "JOIN designer_translation ON designer.id = designer_translation.designer_id " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 " +
                "and balance > 0 and designer.show = 1 and designer_translation.locale = 'ru' " +
                "group by designer_translation.name";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("url");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(text.get(0));
        return text;
    }


    //вытаскиваем filterID для первого дизайнера из списка
    public static Integer getFirstFilterIDForList() {
        int filterID = 0;
        String query = "select item_catalog_position_filter.id from designer " +
                "JOIN item on item.designer_id = designer.id " +
                "JOIN item_catalog_position on item.id = item_catalog_position.item_id " +
                "JOIN item_catalog_position_filter on item_catalog_position.filter_id = item_catalog_position_filter.id " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlForList().get(0) + "%'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filterID = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(filterID);
        return filterID;
    }

    public static Integer getTenFilterIDForList() {
        int filterID = 0;
        String query = "select item_catalog_position_filter.id from designer " +
                "JOIN item on item.designer_id = designer.id " +
                "JOIN item_catalog_position on item.id = item_catalog_position.item_id " +
                "JOIN item_catalog_position_filter on item_catalog_position.filter_id = item_catalog_position_filter.id " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlForList().get(9) + "%'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filterID = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(filterID);
        return filterID;
    }

    public static Integer getTwentyFilterIDForList() {
        int filterID = 0;
        String query = "select item_catalog_position_filter.id from designer " +
                "JOIN item on item.designer_id = designer.id " +
                "JOIN item_catalog_position on item.id = item_catalog_position.item_id " +
                "JOIN item_catalog_position_filter on item_catalog_position.filter_id = item_catalog_position_filter.id " +
                "where item_catalog_position_filter.filters like " + "'%" + getDesignerUrlForList().get(19) + "%'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                filterID = resultSet.getInt("item_catalog_position_filter.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(filterID);
        return filterID;
    }

    //вытаскиваем название товаров по первому дизайнеру из списка
    public List<String> getFirstDesignerNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = " + "'" + getFirstFilterIDForList() + "'" +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.substring(0,15).toLowerCase());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> get10DesignerItemNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = " + "'" + getTenFilterIDForList() + "'" +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
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

    public List<String> get20DesignerItemNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = " + "'" + getTwentyFilterIDForList() + "'" +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.toLowerCase());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getFirstPopularLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = " + "'" + getFirstFilterIDForPopular() + "'" +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.toLowerCase().substring(0,20));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public List<String> getLastPopularLinkNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item " +
                "JOIN item_translations ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = " + "'" + getLastFilterIDForPopular() + "'" +
                "and balance > 0 and designer.show = 1 and item_translations.locale = 'ru' " +
                "group by item.id, item_translations.name, designer.id, designer.name " +
                "order by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name.toLowerCase().substring(0,20));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public static void main(String[] args) {
        String description = "";
        String query = "select designer_translation.description from designer_translation " +
                "JOIN designer ON designer_translation.designer_id = designer.id " +
                "where locale = ru and `show` = 1 ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                description = resultSet.getString("description");
                System.out.println(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        worker.getSession().disconnect();

    }
}
