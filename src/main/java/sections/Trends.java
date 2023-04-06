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


public class Trends extends Base {

    private final By trendsButton = By.xpath("//a[@href='/trend/']");
    private final By mainHref = By.xpath("//a[@class='trend-item js-trend-item trend-item--big']");
    private final By firstHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[2]");
    private final By secondHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[3]");
    private final By thirdHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[10]");
    private final By fourthFineHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[5]");

    private final By linkHeader = By.xpath("//div[@class='trend-page__content']");
//    private final By linkHeader = By.xpath("//div[@class='catalog-banner__image']");

    public Trends(WebDriver driver) {
        super(driver);
    }

    public String getFourthFineHref() {
        return driver.findElement(fourthFineHref).getAttribute("href");
    }

    public void clickToFourthFineHref() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(fourthFineHref));
    }

    public String getThirdHref() {
        return driver.findElement(thirdHref).getAttribute("href");
    }

    public void clickToThirdHref() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdHref));
    }

    public String getSecondHref() {
        return driver.findElement(secondHref).getAttribute("href");
    }

    public void clickToSecondHref() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondHref));
    }

    public String getFirstHref() {
        return driver.findElement(firstHref).getAttribute("href");
    }

    public String linkHeader() {
        return driver.findElement(linkHeader).getAttribute("textContent");
    }

    public void clickToFirstHref() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstHref));
    }

    public void clickToTrendsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(trendsButton));
    }

    public String getMainHref() {
        return driver.findElement(mainHref).getAttribute("href");
    }

    public void clickToMainHref() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(mainHref));
    }


    //SQL
    public Integer listOfBanners() {
        int count = 0;
        String query = "SELECT count(url) as countURL from trend_translations " +
                "JOIN trend ON trend.id = trend_translations.trend_id " +
                "where `show` = 1 and locale ='ru' and trend.description is not null and trend.description not like '' ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt("countURL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(count);
        return count;
    }

    public List<String> getNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT trend_translations.name from trend_translations " +
                "JOIN trend ON trend.id = trend_translations.trend_id " +
                "where `show` = 1 and locale = 'ru' and trend.description is not null " +
                "group by position, trend.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name").replaceAll("<br>", "");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("метод getNames: " + text);
//        System.out.println(text.size());
        return text;
    }

    public List<String> listOfDescription() {
        String description;
        List<String> text = new ArrayList<>();
        String query = "SELECT trend_translations.description from trend_translations " +
                "JOIN trend ON trend.id = trend_translations.trend_id " +
                "where `show` = 1 and locale = 'ru' " +
                "group by position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                description = resultSet.getString("description").replaceAll("<br>", "");
//                System.out.println(description);
                text.add(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getNamesOfTop() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'trend' and subsection = 'top100-2020' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
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

    public static void main(String[] args) {
        String description;
        List<String> text = new ArrayList<>();
        String query = "SELECT description from trend " +
                "where `show` = 1 " +
                "group by position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                description = resultSet.getString("description");
                System.out.println(description);
                text.add(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
//        System.out.println("метод getNames: " + text);

    }


}
