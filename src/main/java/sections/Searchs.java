package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Searchs extends Base {
    private static final By ToSearch = By.xpath("//div[@class='header__right-side']/span[@class='icon-with-title header__icon-button _search hidden_mobile-tablet']");
    private final By InputToSearch = By.xpath("//input[@name='search']");
    private final By successfulSearchText = By.xpath("//p[@class='catalog__message']");

    public Searchs(WebDriver driver) {
        super(driver);
    }
    public String getSearchText() {
        return driver.findElement(successfulSearchText).getText();
    }

    public void typeSearch(String search) {
        type(search, InputToSearch);
    }
    public void InputToSearchWeb(String search) {
        this.typeSearch(search);
    }
    public void clickInputToSearch(){
        click(InputToSearch);
    }
    public void clickToSearch(){
        click(ToSearch);
    }
}
