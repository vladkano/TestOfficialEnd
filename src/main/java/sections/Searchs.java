package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Searchs extends Base {
    private static final By ToSearch = By.xpath("//div[@class='header__right-side']/span[@class='icon-with-title header__icon-button _search hidden_mobile-tablet']");
    private final By InputToSearch = By.xpath("//input[@name='search']");

    public Searchs(WebDriver driver) {
        super(driver);
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
