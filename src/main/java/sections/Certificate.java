package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Certificate extends Base {

    private final By mainCertHeader = By.xpath("//h2[@class='certificate-page__heading']");
    private final By certificateAmount = By.xpath("//span[@class='certificate-page__amount']");
    private final By certificateText = By.xpath("//p[@class='certificate-page__text']");
    private final By applicationSection = By.xpath("//p[@class='certificate-page__help-text']");
    private final By mailSection = By.xpath("(//p[@class='certificate-page__text'])[2]");
    private final By friendButton = By.xpath("//button[@class='certificate-page__tab']");

    private final By firstSectionPlusButton = By.xpath("//button[@class='certificate-page__btn-plus']");
    private final By firstSectionMinusButton = By.xpath("//button[@class='certificate-page__btn-minus']");
    private final By firstSectionOrderButton = By.xpath("//button[@class='certificate-page__send-button button-fill']/span");

    private final By checkValue = By.xpath("//input[@name='quantity']");
    private final By certificatesButton = By.xpath("//a[text()='сертификаты']");

    private final By receiverName = By.xpath("//input[@id='receiverName']");
    private final By senderName = By.xpath("//input[@id='senderName']");
    private final By userEmail = By.xpath("//input[@id='userEmail']");

    private final By receiverEmail = By.xpath("//input[@id='receiverEmail']");
    private final By postcardWishes = By.xpath("//textarea[@id='message']");


    public Certificate(WebDriver driver) {
        super(driver);
    }

    public String getMainCertHeader() {
        return driver.findElement(mainCertHeader).getText();
    }

    public String getCertificateAmount() {
        return driver.findElement(certificateAmount).getText();
    }

    public String getCertificateText() {
        return driver.findElement(certificateText).getText();
    }

    public String getApplicationSection() {
        return driver.findElement(applicationSection).getText();
    }

    public String getMailSection() {
        return driver.findElement(mailSection).getText();
    }

    public void clickToFirstSectionPlusButton() {
        click(firstSectionPlusButton);
    }

    public void clickToFirstSectionMinusButton() {
        click(firstSectionMinusButton);
    }

    public void clickToFirstSectionOrderButton() {
        click(firstSectionOrderButton);
    }

    public void clickToFriendButton() {
        click(friendButton);
    }

    public void clickToSecondSectionOrderButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstSectionOrderButton));
    }

    public String getBasketNumber() {
        return driver.findElement(checkValue).getAttribute("value");
    }


    public void typeReceiverName(String komu) {
        driver.findElement(receiverName).sendKeys(komu);
    }

    public void typeSenderName(String kogo) {
        driver.findElement(senderName).sendKeys(kogo);
    }

    public void typeEmail(String email) {
        driver.findElement(userEmail).sendKeys(email);
    }

    public void typeReceiverEmail(String email) {
        driver.findElement(receiverEmail).sendKeys(email);
    }

    public void typePostcardWishes(String wishes) {
        driver.findElement(postcardWishes).sendKeys(wishes);
    }

    public void secondSectionOrder(String komu, String kogo, String email, String wishes) {
        this.typeReceiverName(komu);
        this.typeSenderName(kogo);
        this.typeReceiverEmail(email);
        this.typePostcardWishes(wishes);
        this.clickToSecondSectionOrderButton();
        new Certificate(driver);
    }

    public void clickToCertificateButton() {
        click(certificatesButton);
    }

}
