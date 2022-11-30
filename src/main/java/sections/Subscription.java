package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Subscription extends Base {

    private final By subscriptionText = By.xpath("//div[@class='footer__subscription']/h2");
    private final By subscriptionButton = By.xpath("//button[@class='footer__subscription-button button-fill']/span");
    private final By subscriptionMail = By.xpath("//input[@type='email']");
    private final By successfulSubscriptionText = By.xpath("//div[@class='footer__success-message']/h2");
    private final By failedSubscriptionText = By.xpath("//p[@class='message message_error']");


    public Subscription(WebDriver driver) {
        super(driver);
    }


    public void typeEmail(String email) {
        type(email, subscriptionMail);
    }

    public String getSubscriptionText() {
        return driver.findElement(subscriptionText).getText();
    }

    public String getSubscriptionButtonText() {
        return driver.findElement(subscriptionButton).getText();
    }

    public void clickOnSubscriptionButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(subscriptionButton));
//        click(subscriptionButton);
    }

    public String getSuccessfulSubscriptionText() {
        return driver.findElement(successfulSubscriptionText).getText();
    }

    public String getFailedSubscriptionText() {
        return driver.findElement(failedSubscriptionText).getText();
    }

    public void subscriptionWithEmail(String email) {
        this.typeEmail(email);
        this.clickOnSubscriptionButton();
    }


}
