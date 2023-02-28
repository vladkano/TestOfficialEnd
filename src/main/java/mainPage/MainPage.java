package mainPage;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainPage extends Base {

    private final By sigInButton = By.xpath("//span[@class='icon-with-title header__icon-button']");
    private final By lcButton = By.xpath("//a[@href='/profile?section=personalData']");
    private final By loginWithPhone = By.id("authLogin");
    private final By loginWithMail = By.xpath("//input[@type='email']");
    private final By getPassword = By.xpath("//span[@class='auth-popup__button-inner']");
    private final By authEmail = By.xpath("//input[@id ='authEmail']");

    private final By authName = By.id("authFullName");
    private final By consentButton = By.xpath("//label[@for='authPersonalDataAgreement']");
    private final By registerButton = By.xpath("//button[@class='auth-popup__button-send']/span");
    private final By registerButtonAttribute = By.xpath("//button[@class='auth-popup__button-send']");
    private final By authPassword = By.xpath("//input[@id='authCode']");
    private final By exitButton = By.xpath("//a[@class='header-profile__link _logout']");
    private final By phoneFromSite = By.xpath("//span[@class='info-box-number']");
    private final By mailFromSite = By.id("mail");
    private final By loginByMailButton = By.xpath("//button[@class='auth-popup__button-email']");
    private final By returnButton = By.xpath("//button[@aria-label='Вернуться на предыдущий шаг']");
    private final By closeButton = By.xpath("//button[@aria-label='Закрыть форму авторизации']");


    //headers
    private final By sigInHeader = By.xpath("//h2[@class='auth-popup__title']");
    private final By incorrectSigInHeader = By.xpath("//p[@class='auth-popup__error']");
    private final By sigOutHeader = By.xpath("//h2[@class='auth-popup__title']");
    private final By incorrectSigInCodeHeader = By.xpath("//p[@class='auth-popup__error']");
    private final By sigInEmailHeader = By.xpath("//p[@class='auth-popup__prompt']");


    public MainPage(WebDriver driver) {
        super(driver);
    }


    //    ---Методы и хедеры--------
    public void clickOnCloseButton() {
        click(closeButton);
    }

    public void clickOnReturnButton() {
        click(returnButton);
        sleep(500);
    }

    public void clickOnConsentButton() {
        click(consentButton);
    }

    public String getPhoneFromSite() {
        return driver.findElement(phoneFromSite).getAttribute("textContent");
    }

    public String getMailFromSite() {
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Копия']")));
        return driver.findElement(mailFromSite).getAttribute("value");
    }

    public void clickOnLoginByMailButton() {
        click(loginByMailButton);
    }

    public void clickOnExitButton() {
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(lcButton);
        action.moveToElement(elem);
        action.perform();
        click(exitButton);
    }

    public void clickOnSigInButton() {
        click(sigInButton);
    }

    public void clickOnLcInButton() {
        click(lcButton);
    }

    public void typeLoginWithPhone(String phone) {
        type(phone, loginWithPhone);
    }

    public void typeLoginWithMail(String email) {
        type(email, loginWithMail);
    }

    public void typeEmail(String email) {
        type(email, authEmail);
    }


    public void typeName(String name) {
        type(name, authName);
    }

    public void clickOnRegisterButton() {
        click(registerButton);
    }

    public Boolean getRegisterButtonAttribute() {
        return driver.findElement(registerButtonAttribute).isEnabled();
    }

    public void typePassword(String password) {
        type(password, authPassword);
    }


    public void clickOnGetPasswordButton() {
        click(getPassword);
    }

    public MainPage sigInWithPhone(String phone) {
        this.clickOnSigInButton();
        this.typeLoginWithPhone(phone);
        this.clickOnGetPasswordButton();
//        sleep(1000);
        return new MainPage(driver);
    }

    public void sigInToComWithPhone(String phone) {
        clickOnSigInButton();
        driver.findElement(loginWithPhone).clear();
        type(phone, loginWithPhone);
        clickOnGetPasswordButton();
    }

    public void sigInWithEmail(String email) {
        this.clickOnSigInButton();
        this.clickOnLoginByMailButton();
        this.typeLoginWithMail(email);
//        sleep(1000);
        this.clickOnGetPasswordButton();
        sleep(1000);
    }


    public void sigInWithPassword(String password) {
        this.typePassword(password);
    }

    public void registerWithPhoneNumber(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnRegisterButton();
    }

    public void registerWithoutConsent(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
    }

    public String getIncorrectSigInHeader() {
        return driver.findElement(incorrectSigInHeader).getText();
    }

    public String getIncorrectSigInCodeHeader() {
        return driver.findElement(incorrectSigInCodeHeader).getText();
    }

    public String getSigInEmailHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sigInEmailHeader));
        return driver.findElement(sigInEmailHeader).getText();
    }

    public String getSigInHeader() {
        return driver.findElement(sigInHeader).getAttribute("textContent");
    }

    public String getSigOutHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sigOutHeader));
        return driver.findElement(sigOutHeader).getText();
    }


    //    ------------SQL---------------------
    public String getPhonePassword() {
        String code = "";
        String query = "select code from user_authentication_code where phone=" + phoneForRegistration;
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

    public String getPhonePasswordForLC() {
        sleep(1000);
        String code = "";
        String query = "select code from user_authentication_code where user_id = 157982 and id=(SELECT MAX(id) FROM user_authentication_code)";

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


    public String getEmailPassword() {
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = null;
        String query = "select code from user_authentication_code where user_id = 157982 and id=(SELECT MAX(id) FROM user_authentication_code)";

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
