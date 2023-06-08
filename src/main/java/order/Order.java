package order;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Order extends Base {

    private final By changeCityButton = By.xpath("//span[@class='order-delivery__location-city-output']");
    private final By locationSearch = By.id("locationSearch");
    private final By locationButton = By.xpath("//p[@class='location-choose__variant-city']");
    private final By confirmButton = By.xpath("//button[@class='confirm-phone__button']");


    private final By orderPhone = By.xpath("//input[@id='orderPhone']");
    private final By orderEmail = By.xpath("//input[@id='orderEmail']");
    private final By orderFio = By.xpath("//input[@id='orderName']");
    private final By orderAddressButton = By.xpath("//textarea[@id='deliveryAddress']");
    private final By orderAddress = By.xpath("//input[@id='addressAutocomplete']");
    private final By orderAddressCloseButton = By.xpath("//button[contains(text(), 'закрыть')]");
    private final By chooseAddressButton = By.xpath("//li[@class='address-autocomplete__variant']/b");
    private final By orderApartment = By.xpath("//input[@id='deliveryApartment']");
    private final By orderFrontDoor = By.xpath("//input[@id='deliveryFrontDoor']");
    private final By orderFloor = By.xpath("//input[@id='deliveryFloor']");
    private final By orderHouseCode = By.xpath("//input[@id='deliveryHouseCode']");
    private final By orderCommentForCourier = By.xpath("//textarea[@id='courierComment']");
    private final By orderComment = By.xpath("//textarea[@name='comment']");

    private final By deliveryCity = By.id("deliveryCity");


    private final By payButton = By.xpath("//button[@class='submit-block__button button-fill']/span");
    private final By orderButton = By.xpath("//span[text()='Оформить заказ']");
    private final By addAddressButton = By.xpath("//div[@class='dropdown courier-delivery__additional']//span");
    private final By searchBox = By.xpath("//input[@id='searchbox']");
    private final By countrySearchBox = By.xpath("//input[@class='reg']");
    private final By citySearchBox = By.xpath("(//input[@class='reg'])[2]");
    private final By addCommentButton = By.xpath("//button[@class='comment__trigger']");

    private final By authPassword = By.xpath("//input[@id='verificationCode']");
    private final By whatsAppButton = By.xpath("//label[@for='communicationWhatsapp']/span");
    private final By smsButton = By.xpath("//span[contains(text(), 'СМС о статусе заказа')]");
    private final By companyStoreButton = By.xpath("//span[contains(text(), 'забрать в фирменном магазине')]");
    private final By metropolisStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Метрополис»']");
    private final By redBridgeStoreButton = By.xpath("//span[text()='Poison Drop в Универмаге «Au Pont Rouge. У Красного моста»']");
    private final By atriumStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Атриум»']");
    private final By afimollStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Афимолл»']");
    private final By paveletskayaStoreButton = By.xpath("//span[text()='Poison Drop в ТРЦ «Павелецкая плаза»']");
    private final By galleryKrasnodarStoreButton = By.xpath("//span[text()='Poison Drop в ТРЦ «Галерея Краснодар»']");
    private final By kazanMallStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «KazanMall»']");
    private final By noPayButton = By.xpath("//label[@for='offlinePayment']/span");
    private final By pickPointButton = By.xpath("//b[contains(text(), 'постамат')]");
    private final By selectPostomatButton = By.xpath("//span[contains(text(), 'выбрать постамат')]");
    private final By searchboxButton = By.xpath("//div[@class='combobox searchbox']/span");
    private final By rodonitButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"6601-003\"); return false;']");
    private final By belarusButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"9001-009\"); return false;']");
    private final By selectButton = By.xpath("//div[text()='ВЫБРАТЬ']");
    private final By certificateButton = By.xpath("//label[@for='onlineCertificate']/span");
    private final By paperButton = By.xpath("//span[text()='Бумажный']");
    private final By firstPrice = By.xpath("//b[@class='cart-price__total']");
    private final By finalPrice = By.xpath("//div[@class='order-summary__row order-summary__row_total']/span[2]");
    private final By cloudPrice = By.xpath("//div[@class='text p0 cost-value']");
    private final By checkoutPrice = By.xpath("//h1[@id='payment-methods']/span");
    private final By frame = By.xpath("//iframe[@src='https://pickpoint.ru/select/?&ikn=9990653812']");
    private final By payFrame = By.xpath("//iframe[@class=' with-appled']");
    private final By ordinaryDeliveryButton = By.xpath("//label[@for='ordinaryDelivery']/span[@class='order-delivery__courier-type-variant']");
    private final By promoButton = By.xpath("//button[@class='cart-promo-code__trigger']/span");
    private final By orderPromocode = By.xpath("//input[@id='promoCodeInput']");
    private final By enterPromoCodeButton = By.xpath("//button[@aria-label='Применить промокод']/span");


    //headers
    private final By payHeader = By.xpath("//div[@class='text p2']");
    private final By orderHeader = By.xpath("//span[text()='Мы приняли ваш заказ']");
    private final By interHeader = By.xpath("//p[@class='order-delivery__tab-text']");
    private final By payComHeader = By.id("payment-methods");

    public Order(WebDriver driver) {
        super(driver);
    }

    public void usePromocode(String promo) {
        click(promoButton);
        type(promo, orderPromocode);
        click(enterPromoCodeButton);
    }


    public void clickOnOrdinaryDeliveryButton() {
        click(ordinaryDeliveryButton);
    }


    public void clickOnLocationButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(locationButton));
//        click(locationButton);
    }

    public void clickOnLocationButtonNY() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(cartLocationButtonNY));
    }

    public void clickOnConfirmButton() {
        click(confirmButton);
    }

    public void typeLocationSearch(String searchCity) {
        type(searchCity, locationSearch);
    }

    public void clickOnChangeCityButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(changeCityButton));
    }

    public String getFirstPrice() {
        return driver.findElement(firstPrice).getText();
    }

    public String getFinalPrice() {
        return driver.findElement(finalPrice).getText();
    }

    public String getPayHeader() {
        waitForVisibilityOf(payFrame, 10);
        WebElement payWindow = wait.until(ExpectedConditions.presenceOfElementLocated(payFrame));
        driver.switchTo().frame(payWindow);
        waitForVisibilityOf(payHeader, 10);
        return driver.findElement(payHeader).getText();
    }

    public String getPayComHeader() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(payComHeader));
        waitForVisibilityOf(payComHeader, 10);
        return driver.findElement(payComHeader).getText();
    }

    public String getCloudPrice() {
        waitForVisibilityOf(cloudPrice, 10);
        return driver.findElement(cloudPrice).getText();
    }

    public String getCheckoutPrice() {
        wait.until(ExpectedConditions.presenceOfElementLocated(checkoutPrice));
        return driver.findElement(checkoutPrice).getText();
    }

    public String getOrderHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
        return driver.findElement(orderHeader).getAttribute("textContent");
    }

    public String getInterHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(interHeader));
        return driver.findElement(interHeader).getText();
    }

    public void typePhone(String phone) {
        type(phone, orderPhone);
    }

    public void typeEmail(String email) {
        type(email, orderEmail);
    }

    public void typeFio(String fio) {
        type(fio, orderFio);
    }

    public void addAddress(String address) {
//        ((JavascriptExecutor) driver).executeScript(
//        "arguments[0].click();", driver.findElement(By.xpath("//span[text()='Закрыть и больше не показывать']")));
        //скрол вниз страницы
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, 900)");
        click(orderAddressButton);
        type(address, orderAddress);
        click(chooseAddressButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(orderAddressCloseButton));
    }

    public void typeApartment(String apartment) {
        type(apartment, orderApartment);
    }

    public void typeFrontDoor(String frontDoor) {
        type(frontDoor, orderFrontDoor);
    }

    public void typeFloor(String floor) {
        type(floor, orderFloor);
    }

    public void typeHouseCode(String houseCode) {
        type(houseCode, orderHouseCode);
    }

    public void typeCommentForCourier(String commentForCourier) {
        type(commentForCourier, orderCommentForCourier);
    }

    public void typeComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment);
    }

    public void typeDeliveryCity(String city) {
        driver.findElement(deliveryCity).sendKeys(city);
    }

    public void clickOnPayButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(payButton));
    }

    public void clickOnOrderButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(orderButton));
    }

    public void clickOnAddAdresButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(addAddressButton));
    }

    public void clickOnAddCommentButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(addCommentButton));
    }

    public void clickOnWhatsAppButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whatsAppButton));
    }

    public void clickOnSmsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(smsButton));
    }

    public void clickOnCompanyStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(companyStoreButton));
    }

    public void clickOnPickPointButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(pickPointButton));
    }

    public void clickOnSelectPostomatButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(selectPostomatButton));
    }

    public void clickOnSearchboxButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(searchboxButton));
    }

    public void clickOnMetropolisStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(metropolisStoreButton));
    }

    public void clickOnRedBridgeStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(redBridgeStoreButton));
    }

    public void clickOnAtriumStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(atriumStoreButton));
    }

    public void clickOnAfimollStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(afimollStoreButton));
    }

    public void clickOnPaveletskayaStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(paveletskayaStoreButton));
    }

    public void clickOnGalleryKrasnodarStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(galleryKrasnodarStoreButton));
    }

    public void clickOnKazanMallStoreButton() {
        clickOnCompanyStoreButton();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(kazanMallStoreButton));
    }

    public void clickOnNoPayButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(noPayButton));
    }

    public void confirmWithPassword(String password) {
        type(password, authPassword);
        sleep(1000);
        clickOnPayButton();
    }

    public void typeSearchBox(String search) {
        type(search, searchBox);
    }

    public void typeCountrySearchBox(String searchCountry) {
        type(searchCountry, countrySearchBox);
    }

    public void typeCitySearchBox(String searchCity) {
        type(searchCity, citySearchBox);
    }

    public void clickOnRodonitButton() {
        click(rodonitButton);
    }

    public void clickOnBelarusButton() {
        click(belarusButton);
    }


    public void clickOnSelectButton() {
        click(selectButton);
    }

    public void clickOnPaperButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(paperButton));
    }

    public void clickOnCertificateButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(certificateButton));
    }

    public void basicParameters(String phone, String email, String fio) {
        typePhone(phone);
        typeEmail(email);
        typeFio(fio);
    }

    public void courierDeliveryInfo(String city, String address, String apartment,
                                    String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
    }

    //Курьер
    public void orderWithAllStrings(String phone, String email, String fio, String city, String address, String apartment,
                                    String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        basicParameters(phone, email, fio);
        courierDeliveryInfo(city, address, apartment, frontDoor, floor, houseCode, commentForCourier);
        clickOnAddCommentButton();
        typeComment(comment);
        clickOnPayButton();
        new Order(driver);
    }

    public void orderWithWhatsApp(String phone, String email, String fio, String city, String address, String apartment,
                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        basicParameters(phone, email, fio);
        courierDeliveryInfo(city, address, apartment, frontDoor, floor, houseCode, commentForCourier);
        clickOnWhatsAppButton();
        clickOnAddCommentButton();
        typeComment(comment);
        clickOnPayButton();
    }

    public void orderWithSignIn() {
        clickOnCompanyStoreButton();
        clickOnPayButton();
    }

    //Заказы без оплаты
    public void orderWithNoPayAndPhone(String phone, String email, String fio, String city, String address, String apartment,
                                       String frontDoor, String floor, String houseCode, String commentForCourier) {
        basicParameters(phone, email, fio);
        courierDeliveryInfo(city, address, apartment, frontDoor, floor, houseCode, commentForCourier);
        clickOnNoPayButton();
        clickOnOrderButton();
    }

    //Цветной:
    public void orderWithCompanyStoreTsvetnoy(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnCompanyStoreButton();
        clickOnPayButton();

    }

    public void orderWithCompanyStoreTsvetnoyWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnCompanyStoreButton();
        clickOnWhatsAppButton();
        clickOnPayButton();
    }

    public void orderWithCompanyStoreTsvetnoySms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnCompanyStoreButton();
        clickOnSmsButton();
        clickOnPayButton();
    }

    public void tsvetnoyWithNoPayAndPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnCompanyStoreButton();
        clickOnNoPayButton();
        clickOnOrderButton();
    }

    public void tsvetnoyWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnCompanyStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    public void tsvetnoyWithNoPayAndSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnCompanyStoreButton();
        clickOnNoPayButton();
        clickOnSmsButton();
        clickOnOrderButton();
    }

    //Метрополис:
    public void orderWithCompanyStoreMetropolisPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnMetropolisStoreButton();
        clickOnPayButton();
    }

    public void orderWithCompanyStoreMetropolisWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnMetropolisStoreButton();
        clickOnWhatsAppButton();
        clickOnPayButton();
    }

    public void orderWithCompanyStoreMetropolisSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnMetropolisStoreButton();
        clickOnSmsButton();
        clickOnPayButton();
    }

    public void metropolisWithNoPayAndPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnMetropolisStoreButton();
        clickOnNoPayButton();
        clickOnOrderButton();
    }

    public void metropolisWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnMetropolisStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    public void metropolisWithNoPayAndSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnMetropolisStoreButton();
        clickOnNoPayButton();
        clickOnSmsButton();
        clickOnOrderButton();
    }

    //Атриум:
    public void orderWithCompanyStoreAtriumPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAtriumStoreButton();
        clickOnPayButton();
    }

    public void orderWithCompanyStoreAtriumWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAtriumStoreButton();
        clickOnWhatsAppButton();
        clickOnPayButton();
    }

    public void orderWithCompanyStoreAtriumSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAtriumStoreButton();
        clickOnSmsButton();
        clickOnPayButton();
    }

    public void atriumWithNoPayAndPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAtriumStoreButton();
        clickOnNoPayButton();
        clickOnOrderButton();
    }

    public void atriumWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAtriumStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    public void atriumWithNoPayAndSMS(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAtriumStoreButton();
        clickOnNoPayButton();
        clickOnSmsButton();
        clickOnOrderButton();
    }

    //У Красного моста:
    public void orderWithRedBridgePhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnRedBridgeStoreButton();
        clickOnPayButton();
    }

    public void orderWithRedBridgeWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnRedBridgeStoreButton();
        clickOnWhatsAppButton();
        clickOnPayButton();
    }

    public void orderWithRedBridgeSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnRedBridgeStoreButton();
        clickOnSmsButton();
        clickOnPayButton();
    }

    public void redBridgeWithNoPayAndPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnRedBridgeStoreButton();
        clickOnNoPayButton();
        clickOnOrderButton();
    }

    public void redBridgeWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnRedBridgeStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    public void redBridgeWithNoPayAndSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnRedBridgeStoreButton();
        clickOnNoPayButton();
        clickOnSmsButton();
        clickOnOrderButton();
    }

    //Афимолл:
    public void orderWithAfimollPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAfimollStoreButton();
        clickOnPayButton();
    }

    public void orderWithAfimollWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAfimollStoreButton();
        clickOnWhatsAppButton();
        clickOnPayButton();
    }

    public void orderWithAfimollSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAfimollStoreButton();
        clickOnSmsButton();
        clickOnPayButton();
    }

    public void afimollWithNoPayAndPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAfimollStoreButton();
        clickOnNoPayButton();
        clickOnOrderButton();
    }

    public void afimollWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAfimollStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    public void afimollWithNoPayAndSms(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnAfimollStoreButton();
        clickOnNoPayButton();
        clickOnSmsButton();
        clickOnOrderButton();
    }

    public void orderWithDvdNoPayAndWA(String phone, String email, String fio, String city, String address, String apartment,
                                       String frontDoor, String floor, String houseCode, String commentForCourier) {
        basicParameters(phone, email, fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnOrdinaryDeliveryButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    //Павелецкая плаза:
    public void paveletskayaWithPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnPaveletskayaStoreButton();
        clickOnPayButton();
    }

    public void paveletskayaWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnPaveletskayaStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    //Галерея Краснодар:
    public void galleryKrasnodarWithPhone(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnGalleryKrasnodarStoreButton();
        clickOnPayButton();
    }

    public void galleryKrasnodarWithNoPayAndWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnGalleryKrasnodarStoreButton();
        clickOnNoPayButton();
        clickOnWhatsAppButton();
        clickOnOrderButton();
    }

    /**
     * Казань Молл
     */

    public void kazanMallWithWA(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnKazanMallStoreButton();
        clickOnWhatsAppButton();
        clickOnPayButton();
    }

    public void kazanMallWithNoPayAndSMS(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnKazanMallStoreButton();
        clickOnNoPayButton();
        clickOnSmsButton();
        clickOnOrderButton();
    }

    //Доставить в другую страну:
    public void internationalWithPhone(String phone, String email, String fio, String city,
                                       String internationalCity, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
//        this.clickOnDeliveryButton();
        type(internationalCity, orderAddressButton);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Доставить в другую страну(доставка невозможна):
    public void deliveryIsNotPossible(String phone, String email, String fio, String city) {
        basicParameters(phone, email, fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        sleep(1000);
    }

    //Доставить в другую страну(Dubai):
    public void deliveryFromDubai(String phone, String email, String fio, String city, String address, String apartment,
                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        driver.findElement(orderPhone).clear();
        basicParameters(phone, email, fio);
        typeDeliveryCity(city);
        type(address, orderAddressButton);
        typeApartment(apartment);
        typeFrontDoor(frontDoor);
        clickOnAddAdresButton();
        typeFloor(floor);
        typeHouseCode(houseCode);
        typeCommentForCourier(commentForCourier);
        clickOnAddCommentButton();
        typeComment(comment);
        clickOnPayButton();
    }

    //Доставить в другую страну(USA):
    public void deliveryFromDubaiToNY(String phone, String email, String fio, String city, String address, String apartment,
                                      String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        driver.findElement(orderPhone).clear();
        basicParameters(phone, email, fio);
        typeDeliveryCity(city);
        type(address, orderAddressButton);
        typeApartment(apartment);
        typeFrontDoor(frontDoor);
        clickOnAddAdresButton();
        typeFloor(floor);
        typeHouseCode(houseCode);
        typeCommentForCourier(commentForCourier);
        clickOnAddCommentButton();
        typeComment(comment);
        clickOnPayButton();
    }


    //PickPoint
    public void orderWithPickPointPhone(String phone, String email, String fio, String country, String city, String search) {
        String oldWindowsSet = driver.getWindowHandle();
        basicParameters(phone, email, fio);
        this.clickOnPickPointButton();
        this.clickOnSelectPostomatButton();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement postomatFrame = wait.until(ExpectedConditions.presenceOfElementLocated(frame));
        driver.switchTo().frame(postomatFrame);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Россия']")));
        this.typeCountrySearchBox(country);
        this.typeCitySearchBox(city);
        this.typeSearchBox(search);
        this.clickOnSearchboxButton();
        this.clickOnRodonitButton();
        this.clickOnSelectButton();
        driver.switchTo().window(oldWindowsSet);
        this.clickOnPayButton();
    }

    public void orderWithPostamatCheck(String phone, String email, String fio) {
        basicParameters(phone, email, fio);
        clickOnPickPointButton();
        clickOnPayButton();
    }


    //Сертификаты:
    public void elCertificateWithPhone(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        clickOnCertificateButton();
        clickOnAddCommentButton();
        typeComment(comment);
        clickOnPayButton();
    }

    public void elCertificateWithWA(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnWhatsAppButton();
        //скрол вниз страницы
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithPhone(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Бумажный
    public void paperCertificateWithPhone(String phone, String email, String fio, String city,
                                          String address, String apartment, String frontDoor, String floor, String houseCode,
                                          String commentForCourier, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Бумажный

    public void certificateWithWA(String phone, String email, String fio, String city, String address, String apartment,
                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithTsvetnoyAndPhone(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }


    public void certificateWithMetropolisAndWA(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }


    public void certificateWithAtriumAndSMS(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithRedBridgeAndPhone(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithAfimallAndPhone(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithPaveletskayaAndWA(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnPaveletskayaStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Доставить в другую страну:
    public void certificateWithInternationalAndWA(String phone, String email, String fio, String city,
                                                  String internationalCity, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        type(internationalCity, orderAddressButton);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }


    //Бумажный сертификат без оплаты:
    public void certificateWithNoPayAndPhone(String phone, String email, String fio, String city, String address, String apartment,
                                             String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayMetropolisAndSMS(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayTsvetnoyAndWA(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayAtriumAndPhone(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayRedBridgeAndWA(String phone, String email, String fio, String comment) {
        basicParameters(phone, email, fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }


    //SQL
    public String getPhonePassword() {
        sleep(1000);
        String code = "";
        String query = "select code from user_authentication_code where phone=+79126459328";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String getPhonePasswordToBuy() {
        String code = "";
        String query = "select code from user_authentication_code where phone=+79126459328";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static void main(String[] args) {
        String code = "";
        String query = "select code from user_authentication_code where phone=+79126459328";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                code = resultSet.getString("code");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(code);
        worker.getSession().disconnect();
    }

}
