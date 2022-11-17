package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Base {

    protected static DBWorker worker = new DBWorker();
    protected WebDriver driver;
    //Бой
    protected static String mainPageUrl = "https://poisondrop.ru/";

    //Тест(Сталинград)
//    protected static String mainPageUrl = "https://ru.stalingrad.poisontestdrop.ru/";
    //Тест(Севастополь)
//    protected static String mainPageUrl = "https://ru.sevastopol.poisontestdrop.ru/";
    //Тест(Курск)
//    protected static String mainPageUrl = "https://ru.kursk.poisontestdrop.ru/";
    //Тест(Тула)
//    protected static String mainPageUrl = "https://ru.tula.poisontestdrop.ru/";

    protected static String getUrl = mainPageUrl + "catalog/";


    private By imageLink = By.xpath("//picture/img");
    protected By secondImageLink = By.xpath("(//picture/img)[3]");
    protected By nameLink = By.xpath("//h3[@class='catalog-card__name']/a");
    protected By designerLink = By.xpath("//div[@class='catalog-card__designer']/a");
    protected By catalogButton = By.xpath("//a[@href='/catalog/']");

    protected By nameHeader = By.xpath("//h1[@class='product-main-info__product-name']");
    protected By designerHeader = By.xpath("//a[@class='product-main-info__designer-link']");
    protected By priceFromProductCard = By.xpath("//div[@class='price-block catalog-card__price']//span[1]");
    protected By okButton = By.xpath("//button[@class='location-detect__button _yes']");
    protected By noButton = By.xpath("//button[@class='location-detect__button _no']");
    protected By goodButton = By.xpath("//button[@class='button-default button-default--black']");
    protected By catalogLocationButton = By.xpath("//li[@class='location-choose__variant _initial']/p");

    protected By cartLocationButtonNY = By.xpath("//p[text()='New York']");
    protected By catalogLocationButtonUSA = By.xpath("//p[text()='United States']");

    public Base(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

//    public static void setDriver (WebDriver webDriver) {
//        driver = webDriver;
//    }

    public WebDriverWait wait;

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).sendKeys(text);
    }

    private void waitFor(ExpectedCondition<WebElement> conditions, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != 0 ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(conditions);
    }

    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public void clickToOkButton() {
        click(okButton);
    }

    public void chooseDubai() {
        click(noButton);
        click(catalogLocationButton);
        sleep(1000);
    }

    public void chooseNewYork() {
        click(noButton);
        click(catalogLocationButtonUSA);
        sleep(1000);
    }

    public void clickToGoodButton() {
        click(goodButton);
    }


    public By getImageLink() {
        return imageLink;
    }

    public String getPriceFromProductCard() {
        return driver.findElement(priceFromProductCard).getText();
    }

    public void clickOnCatalogButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(catalogButton));
    }

    public void clickOnImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(imageLink));
//        click(imageLink);
    }

    public void clickOnSecondImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondImageLink));
    }

    public void clickOnNameLink() {
        List<WebElement> elements = driver.findElements(nameLink);
        elements.get(0).click();
    }

    public void clickOnDesignerLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerLink));
    }

    public String getImageHeader() {
        List<WebElement> elements = driver.findElements(imageLink);
        return elements.get(0).getAttribute("alt");
    }

    public String getNameHeader() {
        List<WebElement> elements = driver.findElements(nameLink);
        return elements.get(0).getAttribute("textContent");
    }

    public String getDesignerLinkHeader() {
        List<WebElement> elements = driver.findElements(designerLink);
        return elements.get(0).getAttribute("textContent");
    }

    public String getHeader() {
        return driver.findElement(nameHeader).getText();
    }


    public String getNextDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getComNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and storage_id in (1006,1007) and balance > 0 and designer.show = 1 and item_translations.locale = 'en' " +
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
        return text;
    }

    public static void main(String[] args) {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_translations.name from item_translations " +
                "JOIN item ON item.id = item_translations.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_sku_price ON item_sku.id = item_sku_price.item_sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and item_sku_price.price != 0 and filter_id = 155 " +
                "and storage_id in (1006,1007) and balance > 0 and designer.show = 1 and item_translations.locale = 'en' " +
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
