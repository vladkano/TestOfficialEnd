package functionalTests;

import baseForTests.TestBase;
import catalog.CatalogNavigation;
import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tags.Tags;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты тегов у товаров в каталоге")
public class TagTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        tag = new Tags(driver);
        navigation = new CatalogNavigation(driver);
    }

    /**
     * Отображение тегов в каталогах:<p>
     * Серьги
     */
    @Test
    @Description("Отображение тегов в каталогах: Серьги")
    public void tagIsVisibleEarrings() {
        driver.get(TestConfig.SITE_URL + "catalog/sergi");
        for (int i = 0; i < 5; i++){
            navigation.clickOnShowMoreButton();
        }
        String firstTag = tag.getTag();
        String sqlTag = tag.nameEarringsTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }

    /**
     * Браслеты
     */
    @Test
    @Description("Отображение тегов в каталогах: Браслеты")
    public void tagIsVisibleBracelets() {
        driver.get(TestConfig.SITE_URL + "catalog/braslety");
        for (int i = 0; i < 6; i++){
            navigation.clickOnShowMoreButton();
        }
        String firstTag = tag.getTag();
        String braceletTags = tag.braceletTags();
        assertEquals(braceletTags.toUpperCase(), firstTag);
    }

    /**
     * Кольца
     */
    @Test
    @Description("Отображение тегов в каталогах: Кольца")
    public void tagIsVisibleRings() {
        driver.get(TestConfig.SITE_URL + "catalog/koltsa");
        for (int i = 0; i < 4; i++){
            navigation.clickOnShowMoreButton();
        }
        String firstTag = tag.getTag();
        String sqlTag = tag.nameOfRingTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }

    /**
     * Отображение всех тегов по товару:<p>
     * Серьги
     */
    @Test
    @Description("Отображение всех тегов по товару: Серьги")
    public void tagIsCorrectEarrings() {
        driver.get(TestConfig.SITE_URL + "catalog/sergi");
        for (int i = 0; i < 3; i++){
            navigation.clickOnShowMoreButton();
        }
        String firstTag = tag.getEarringsTag();
        String tagsFromSql = tag.nameEarringsTags();
        assertEquals(tagsFromSql, firstTag);
    }

    /**
     * Кольца
     */
    @Test
    @Description("Отображение всех тегов по товару: Кольца")
    public void tagIsCorrectRings() {
        driver.get(TestConfig.SITE_URL + "catalog/koltsa");
        for (int i = 0; i < 4; i++){
            navigation.clickOnShowMoreButton();
        }
        String firstTag = tag.getRingsTag();
        String output = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameOfRingTags();
        assertEquals(tagsFromSql, output);
    }

}
