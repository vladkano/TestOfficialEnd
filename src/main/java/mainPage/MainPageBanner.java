package mainPage;

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

public class MainPageBanner extends Base {

    private final By carouselButton = By.xpath("//button[@aria-label='Carousel Page 2']");
    private final By designerButton = By.xpath("//div[@class='catalog-card__designer']/a");
    private final By mainCatalogHref = By.xpath("//div[@class='main-banner']/a");
    private final By countOfBanners = By.xpath("//div[@class='banner index-page__banner']/a");

    private final By designerButtonHeader = By.xpath("//div[@class='catalog-card__designer']/a");
    private final By nameButtonHeader = By.xpath("//h3[@class='catalog-card__name']/a");
    private final By designerHeader = By.xpath("//a[@class='product-main-info__designer-link']");
    private final By firstCatalogHeader = By.xpath("//span[@class='filters__label-text']");
    private final By mainCatalogHeader = By.xpath("//h1[text()='Самой сверкающей']");
    private final By bestsellerNameHeader = By.xpath("//h1[@class='product-main-info__product-name']");


    public MainPageBanner(WebDriver driver) {
        super(driver);
    }

    //находит элемент на странице перекрытый другими элементами и кликает на него
    public void clickToCarouselButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(carouselButton));
    }

    public void clickToDesignerButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerButton));
    }

    public String getDesignerName() {
        return driver.findElement(designerButtonHeader).getAttribute("textContent");
    }

    public String getName() {
        return driver.findElement(nameButtonHeader).getAttribute("textContent");
    }

    public String getDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }

    public String getMainCatalogHref() {
        return driver.findElement(mainCatalogHref).getAttribute("href");
    }

    public void clickToMainCatalogHref() {
        click(mainCatalogHref);
    }

    public String getMainCatalogHeader() {
        return driver.findElement(mainCatalogHeader).getAttribute("textContent");
    }

    public String getFirstCatalogHeader() {
        return driver.findElement(firstCatalogHeader).getAttribute("textContent");
    }

    public String getCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement first = banners.get(0);
        return first.getAttribute("href");
    }

    public void clickToCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement first = banners.get(0);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", first);

    }

    public String getSixCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement six = banners.get(5);
        return six.getAttribute("href");
    }

    public void clickToSixCatalogHref() {
        List<WebElement> banners = driver.findElements(countOfBanners);
        WebElement six = banners.get(5);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", six);
    }

    public void clickToBestsellerNameButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(nameButtonHeader));
    }

    public String getBestsellerNameHeader() {
        return driver.findElement(bestsellerNameHeader).getAttribute("textContent");
    }


    public List<String> listOfBests() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "where item_translations.locale = 'ru' and bestsellers.locale = 'ru' " +
                "order by bestsellers.id ASC LIMIT 5";
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
//        System.out.println(list);
        return list;
    }

    public List<String> listOfDesigners() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT designer_translation.name from designer_translation " +
                "JOIN designer ON designer_translation.designer_id = designer.id " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "where bestsellers.locale = 'ru' and designer_translation.locale = 'ru' " +
                "order by bestsellers.id LIMIT 5";
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
//        System.out.println(list);
        return list;
    }


    public List<Integer> listPriceOfBests() {
        int price;
        double discount;
        List<Integer> list = new ArrayList<>();
        String query = "SELECT item_sku_price.price, (item_sku_price.price * item_sku_price.discount/100) as discount from item_sku_price " +
                "JOIN item_sku ON item_sku_price.item_sku_id = item_sku.id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "where locale = 'ru' " +
                "group by bestsellers.id LIMIT 5";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                price = resultSet.getInt("price");
                discount = resultSet.getDouble("discount");
                int priceNew = (int) Math.round(price - discount);
                list.add(priceNew);
//                System.out.println(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        return list;
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT designer.name from designer " +
                "JOIN item ON item.designer_id = designer.id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN bestsellers ON bestsellers.sku_id = item_sku.id " +
                "order by bestsellers.id desc";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }
}
