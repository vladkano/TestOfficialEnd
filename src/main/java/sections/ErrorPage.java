package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorPage extends Base {


    private final By errorHeader = By.xpath("//p[@class='error-page__status']");
    private final By bestsellersHeader = By.xpath("//h2[@class='block-title']");

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorHeader() {
        return driver.findElement(errorHeader).getText();
    }

    public String getBestsellersHeader() {
        return driver.findElement(bestsellersHeader).getText();
    }

}
