package functionalTests;

import base.Base;
import baseForTests.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты корректной работы ссылок")
public class LinkTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        base = new Base(driver);

    }

    /**
     * Поверяем работу 3-х ссылок по каждому типу товаров на переход к товару: <p>
     * При нажатии на картинку, нажатии на название изделия, нажатии на наименование дизайнера. <p>
     * Раздел: Браслеты
     */
    @Test
    @Description("Проверяем работу ссылок: при нажатии на картинку(Браслеты)")
    public void linkByPictureInBraceletsSection() {
        driver.get(getUrl + "catalog/braslety");
        base.clickToGoodButton();
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 24), heading.substring(0, 24));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на название изделия(Браслеты)")
    public void linkByProductNameInBraceletsSection() {
        driver.get(getUrl + "catalog/braslety");
        base.clickToGoodButton();
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 20), heading.substring(0, 20));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на наименование дизайнера(Браслеты)")
    public void linkByDesignerNameInBraceletsSection() {
        driver.get(getUrl + "catalog/braslety");
        base.clickToGoodButton();
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    /**
     * Раздел: Серьги
     */
    @Test
    @Description("Проверяем работу ссылок: при нажатии на картинку(Серьги)")
    public void linkByPictureInEarringsSection() {
        driver.get(getUrl + "catalog/sergi");
        base.clickToGoodButton();
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 20), heading.substring(0, 20));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на название изделия(Серьги)")
    public void linkByProductNameInEarringsSection() {
        driver.get(getUrl + "catalog/sergi");
        base.clickToGoodButton();
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на наименование дизайнера(Серьги)")
    public void linkByDesignerNameInEarringsSection() {
        driver.get(getUrl + "catalog/sergi");
        base.clickToGoodButton();
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    /**
     * Раздел: Колье
     */
    @Test
    @Description("Проверяем работу ссылок: при нажатии на картинку(Колье)")
    public void linkByPictureInNecklacesSection() {
        driver.get(getUrl + "catalog/kole");
        base.clickToGoodButton();
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 26), heading.substring(0, 26));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на название изделия(Колье)")
    public void linkByProductNameInNecklacesSection() {
        driver.get(getUrl + "catalog/kole");
        base.clickToGoodButton();
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 26), heading.substring(0, 26));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на наименование дизайнера(Колье)")
    public void linkByDesignerNameInNecklacesSection() {
        driver.get(getUrl + "catalog/kole");
        base.clickToGoodButton();
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }


    /**
     * Раздел: Кольца
     */
    @Test
    @Description("Проверяем работу ссылок: при нажатии на картинку(Кольца)")
    public void linkByPictureInRingsSection() {
        driver.get(getUrl + "catalog/koltsa");
        base.clickToGoodButton();
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на название изделия(Кольца)")
    public void linkByProductNameInRingsSection() {
        driver.get(getUrl + "catalog/koltsa");
        base.clickToGoodButton();
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на наименование дизайнера(Кольца)")
    public void linkByDesignerNameInRingsSection() {
        driver.get(getUrl + "catalog/koltsa");
        base.clickToGoodButton();
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    /**
     * Раздел: Броши
     */
    @Test
    @Description("Проверяем работу ссылок: при нажатии на картинку(Броши)")
    public void linkByPictureInBroochesSection() {
        driver.get(getUrl + "catalog/broshi");
        base.clickToGoodButton();
        String s = base.getImageHeader();
        String header = s.replaceAll("\u200E", "");
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 24), heading.substring(0, 24));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на название изделия(Броши)")
    public void linkByProductNameInBroochesSection() {
        driver.get(getUrl + "catalog/broshi");
        base.clickToGoodButton();
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0, 24), heading.substring(0, 24));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на наименование дизайнера(Броши)")
    public void linkByDesignerNameInBroochesSection() {
        driver.get(getUrl + "catalog/broshi");
        base.clickToGoodButton();
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    /**
     * Раздел: Пирсинг
     */
    @Test
    @Description("Проверяем работу ссылок: при нажатии на картинку(Пирсинг)")
    public void linkByPictureInPirsingSection() {
        driver.get(getUrl + "catalog/pirsing");
        base.clickToGoodButton();
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0,20), heading.substring(0,20));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на название изделия(Пирсинг)")
    public void linkByProductNameInPirsingSection() {
        driver.get(getUrl + "catalog/pirsing");
        base.clickToGoodButton();
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header.substring(0,20), heading.substring(0,20));
    }

    @Test
    @Description("Проверяем работу ссылок: при нажатии на наименование дизайнера(Пирсинг)")
    public void linkByDesignerNameInPirsingSection() {
        driver.get(getUrl + "catalog/pirsing");
        base.clickToGoodButton();
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }
}
