package functionalTests;

import baseForTests.TestBase;
import filters.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Epic("Тесты фильтров")
public class FiltersTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl + "catalog/");
        filters = new Filters(driver);
        material = new Material(driver);
        colorsAndCoverage = new ColorsAndCoverage(driver);
        size = new Size(driver);
        designersFilter = new DesignersFilter(driver);
        filters.clickToOkButton();
    }

    /**
     * Вспомогательные методы для тестов: <p>
     * Получить список изделий с сайта
     */
    public void getProductsListFromPage() {
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getAttribute("textContent");
            siteList.add(s);
            siteSize = siteList.size();
        }
    }

    /**
     * Фильтр по вставкам
     */
    public void getFilterByInserts() {
        filters.clickToFilterButton();
        material.clickToInsertButton();
    }

    /**
     * Фильтр по материалам
     */
    public void getFilterByMaterials() {
        filters.clickToFilterButton();
        material.clickToMaterialButton();
    }

    /**
     * Фильтр по цвету изделия
     */
    public void getColorFilter() {
        filters.clickToFilterButton();
        colorsAndCoverage.clickToColorButton();
    }

    /**
     * Фильтр по покрытию
     */
    public void getCoveringFilter() {
        filters.clickToFilterButton();
        colorsAndCoverage.clickToCoveringButton();
    }

    /**
     * Фильтр по размеру кольца
     */
    public void getSizeFilter() {
        filters.clickToFilterButton();
        size.clickToSizeButton();
    }

    /**
     * Фильтр по дизайнеру
     */
    public void getDesignersFilter() {
        filters.clickToFilterButton();
        designersFilter.clickToDesignersButton();
    }


    /**
     * Проверяем работу фильтров: <p>
     * По типу изделия: <p>
     * Серьги
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Серьги)")
    public void typeOfItemEarrings() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getEarringNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)),
                () -> assertEquals(sqlList.get(5).substring(0,20), siteList.get(5).substring(0,20))
        );
    }

    /**
     * Кольца
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Кольца)")
    public void typeOfItemRings() {
        filters.clickToFilterButton();
        filters.clickToAllRingsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getRingNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(10).substring(0,20), siteList.get(10).substring(0,20)));
    }

    /**
     * Колье
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Колье)")
    public void typeOfItemNecklaces() {
        filters.clickToFilterButton();
        filters.clickToAllNecklacesButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getNecklacesNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(8).substring(0,20), siteList.get(8).substring(0,20)));
    }

    /**
     * Браслеты
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Браслеты)")
    public void typeOfItemBracelets() {
        filters.clickToFilterButton();
        filters.clickToAllBraceletsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getBraceletsNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * Броши
     */
    @Test
    @Description("Проверяем работу фильтров: По типу изделия(Броши)")
    public void typeOfItemBrooches() {
        filters.clickToFilterButton();
        filters.clickToAllBroochesButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getBroochesNamesForFilters();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }


//    //Вставки поломались 13.05.2022, должны починится, проверить после релиза таски: https://tracker.yandex.ru/PD-1659
    //Нужно переписать тесты в новой логике, после выезда переводов
//    /**
//     * По вставкам: <p>
//     * Жемчуг
//     */
//    @Test
//    @Description("Проверяем работу фильтров: По вставкам(Жемчуг)")
//    public void getPearl() {
//        getFilterByInserts();
//        material.clickToZemcugButton();
//        filters.clickToShowProductsButton();
//        List<String> sqlList = material.getListOfZemcug();
//        getProductsListFromPage();
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        Assertions.assertAll(
//                () -> assertEquals(sqlList.size(), numberOnly),
//                () -> assertEquals(sqlList.get(0), siteList.get(0)),
//                () -> assertEquals(sqlList.get(2), siteList.get(2)));
//    }
//
//    /**
//     * Кристаллы
//     */
//    @Test
//    @Description("Проверяем работу фильтров: По вставкам(Кристаллы)")
//    public void getCrystals() {
//        getFilterByInserts();
//        material.clickToKristallyButton();
//        filters.clickToShowProductsButton();
//        List<String> sqlList = material.getListOfKristally();
//        getProductsListFromPage();
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        Assertions.assertAll(
//                () -> assertEquals(sqlList.size(), numberOnly),
//                () -> assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20)),
//                () -> assertEquals(sqlList.get(2), siteList.get(2)));
//    }
//
//    /**
//     * Камни
//     */
//    @Test
//    @Description("Проверяем работу фильтров: По вставкам(Камни)")
//    public void getStones() {
//        getFilterByInserts();
//        material.clickToKamenButton();
//        filters.clickToShowProductsButton();
//        List<String> sqlList = material.getListOfKamen();
//        getProductsListFromPage();
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        Assertions.assertAll(
//                () -> assertEquals(sqlList.size(), numberOnly),
//                () -> assertEquals(sqlList.get(0), siteList.get(0)),
//                () -> assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20)));
//    }
//
//    /**
//     * Стекло
//     */
//    @Test
//    @Description("Проверяем работу фильтров: По вставкам(Стекло)")
//    public void getGlass() {
//        getFilterByInserts();
//        material.clickToStekloButton();
//        filters.clickToShowProductsButton();
//        List<String> sqlList = material.getListOfSteklo();
//        getProductsListFromPage();
//        String countHeader = filters.getCountHeader();
//        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
//        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
//        Assertions.assertAll(
//                () -> assertEquals(sqlList.size(), numberOnly),
//                () -> assertEquals(sqlList.get(0), siteList.get(0)),
//                () -> assertEquals(sqlList.get(2), siteList.get(2)));
//    }

    /**
     * По материалам: <p>
     * Бронза
     */
    @Test
    @Description("Проверяем работу фильтров: По материалам(Бронза)")
    public void getBronze() {
        getFilterByMaterials();
        material.clickToBronzeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfBronze();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Серебро
     */
    @Test
    @Description("Проверяем работу фильтров: По материалам(Серебро)")
    public void getSilver() {
        getFilterByMaterials();
        material.clickToSilverButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = material.getListOfSilver();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(20).substring(0,20), siteList.get(20).substring(0,20)));
    }


    /**
     * Тесты слайдера с ценой: <p>
     * Правый ползунок
     */
    @Test
    @Description("Проверяем работу фильтров: Тест слайдера с ценой(Правый ползунок)")
    public void rightBarWithPrice() {
        filters.clickToFilterButton();
        String firstPriceRangeRight = filters.getPriceRangeRight();
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vue-slider-dot-handle']")));
        WebElement priceBarRight = driver.findElement(By.xpath("(//div[@class='vue-slider-dot-handle'])[2]"));
        Actions actionsPriceBarRight = act.dragAndDropBy(priceBarRight, -263, 0);
        actionsPriceBarRight.build().perform();
        sleep(1000);
        String secondPriceRangeRight = filters.getPriceRangeRight();
        assertNotEquals(firstPriceRangeRight, secondPriceRangeRight);
    }


    /**
     * Тесты слайдера с ценой: <p>
     * Левый ползунок
     */
    @Test
    @Description("Проверяем работу фильтров: Тест слайдера с ценой(Левый ползунок)")
    public void leftBarWithPrice() {
        filters.clickToFilterButton();
        String firstPriceRangeLeft = filters.getPriceRangeLeft();
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vue-slider-dot-handle']")));
        WebElement priceBarLeft = driver.findElement(By.xpath("//div[@class='vue-slider-dot-handle']"));
        Actions actionsPriceBarLeft = act.dragAndDropBy(priceBarLeft, 100, 0);
        actionsPriceBarLeft.build().perform();
        sleep(1000);
        String secondPriceRangeLeft = filters.getPriceRangeLeft();
        assertNotEquals(firstPriceRangeLeft, secondPriceRangeLeft);
    }


    /**
     * По цвету изделия:<p>
     * Зелёный
     */
    @Test
    @Description("Проверяем работу фильтров: По цвету изделия(Зелёный)")
    public void getGreen() {
        getColorFilter();
        colorsAndCoverage.clickToGreenButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colorsAndCoverage.getListOfGreenColor();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(2).substring(0,20), siteList.get(2).substring(0,20)));
    }

    /**
     * Синий
     */
    @Test
    @Description("Проверяем работу фильтров: По цвету изделия(Синий)")
    public void getBlue() {
        getColorFilter();
        colorsAndCoverage.clickToBlueButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colorsAndCoverage.getListOfBlueColor();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * Мульти
     */
    @Test
    @Description("Проверяем работу фильтров: По цвету изделия(Мульти)")
    public void getMix() {
        getColorFilter();
        colorsAndCoverage.clickToMixButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colorsAndCoverage.getListOfMixColor();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * По покрытию: <p>
     * Родий
     */
    @Test
    @Description("Проверяем работу фильтров: По покрытию(Родий)")
    public void getRhodium() {
        getCoveringFilter();
        colorsAndCoverage.clickToRodiiButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colorsAndCoverage.getListOfRodii();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0, 17), siteList.get(0).substring(0, 17)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * золото
     */
    @Test
    @Description("Проверяем работу фильтров: По покрытию(золото)")
    public void getCoveringGold() {
        getCoveringFilter();
        colorsAndCoverage.clickToGoldButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = colorsAndCoverage.getListGold();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)));
    }

    /**
     * По размеру кольца: <p>
     * 14
     */
    @Test
    @Description("Проверяем работу фильтров: По размеру кольца(14))")
    public void getFirstSize() {
        getSizeFilter();
        size.clickToFirstSizeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = size.getListOfFirstSize();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * 15.5
     */
    @Test
    @Description("Проверяем работу фильтров: По размеру кольца(15.5))")
    public void getSecondSize() {
        getSizeFilter();
        size.clickToSecondSizeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = size.getListOfSecondSize();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * 16
     */
    @Test
    @Description("Проверяем работу фильтров: По размеру кольца(Universal))")
    public void getUniversalSize() {
        getSizeFilter();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = size.getListOfUniversalSize();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }


    /**
     * По дизайнеру: <p>
     * Aleksandr Sinitsyn
     */
    @Test
    @Description("Проверяем работу фильтров: По дизайнеру(Aleksandr Sinitsyn)")
    public void getSinitsyn() {
        getDesignersFilter();
        designersFilter.clickToSinitsynButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = designersFilter.getListOfSinitsyn();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(6).substring(0,20), siteList.get(6).substring(0,20)));
    }

    /**
     * Prosto Jewlry
     */
    @Test
    @Description("Проверяем работу фильтров: По дизайнеру(Prosto Jewlry)")
    public void getProstoJewlry() {
        getDesignersFilter();
        designersFilter.clickToJewlryButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = designersFilter.getListOfJewlry();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }

    /**
     * Avgvst
     */
    @Test
    @Description("Проверяем работу фильтров: По дизайнеру(Avgvst)")
    public void getAvgvst() {
        getDesignersFilter();
        designersFilter.clickToAvgvstButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = designersFilter.getListOfAvgvst();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0), siteList.get(0)),
                () -> assertEquals(sqlList.get(2), siteList.get(2)));
    }


    /**
     * Проверка кнопки "Сбросить всё" фильтр <p>
     * Внутри фильтра
     */
    @Test
    @Description("Проверка кнопки 'Сбросить всё' фильтр. Внутри фильтра")
    public void resetFilter() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        String countHeader = filters.getCountHeader();
        filters.clickToResetButtonInFilter();
        String countHeader2 = filters.getCountHeader();
        assertNotEquals(countHeader, countHeader2);
    }

    /**
     * Выйдя из фильтра. В каталоге
     */
    @Test
    @Description("Проверка кнопки 'Сбросить всё' фильтр. Выйдя из фильтра. В каталоге")
    public void resetFilterExit() {
        filters.clickToFilterButton();
        filters.clickToAllEarringsButton();
        filters.clickToShowProductsButton();
        String countHeader = filters.getCountHeader();
        filters.clickToCatalogResetButton();
        String countHeader2 = filters.getCountHeader();
        assertNotEquals(countHeader, countHeader2);
    }


    /**
     * Сброс фильтров по категориям(Последовательно выбираем фильтры: Серьги, Ювелирный сплав, белый).<p>
     * Затем сбрасываем их в обратной последовательности.<p>
     * Смотрим, чтобы менялось кол-во
     */
    @Test
    @Description("Сброс фильтров по категориям(Последовательно выбираем фильтры: Серьги, Ювелирный сплав, белый)")
    public void resetFiltersByCategory() {
        filters.clickToFilterButton();
        sleep(1000);
        String numberOfProducts1 = filters.getNumberOfProducts();
        filters.clickToAllEarringsButton();
        sleep(1000);
        String numberOfProducts2 = filters.getNumberOfProducts();
        material.clickToMaterialButton();
        sleep(1000);
        material.clickToJewelryAlloyButton();
        sleep(1000);
        String numberOfProducts3 = filters.getNumberOfProducts();
        colorsAndCoverage.clickToColorButton();
        sleep(1000);
        colorsAndCoverage.clickToWhiteButton();
        sleep(1000);
        String numberOfProducts4 = filters.getNumberOfProducts();
        colorsAndCoverage.clickToWhiteButton();
        sleep(1000);
        String numberOfProducts5 = filters.getNumberOfProducts();
        material.clickToJewelryAlloyButton();
        sleep(1000);
        String numberOfProducts6 = filters.getNumberOfProducts();
        filters.clickToAllEarringsButton2();
        sleep(1000);
        String numberOfProducts7 = filters.getNumberOfProducts();
        Assertions.assertAll(
                () -> assertNotEquals(numberOfProducts1, numberOfProducts2),
                () -> assertNotEquals(numberOfProducts3, numberOfProducts4),
                () -> assertEquals(numberOfProducts5, numberOfProducts3),
                () -> assertEquals(numberOfProducts6, numberOfProducts2),
                () -> assertEquals(numberOfProducts7, numberOfProducts1));
    }


    /**
     * Фильтры со скидками: <p>
     * Проверка фильтра со скидками(переключение между фильтрами: Все скидки, от 10, 30, 50%) <p>
     * Смотрим чтобы менялось кол-во
     */
    @Test
    @Description("Проверка фильтра со скидками(переключение между фильтрами: Все скидки, от 10, 30, 50%)")
    public void discountsFilter() {
        filters.clickToFilterButton();
//        String numberOfProducts1 = filters.getNumberOfProducts();
//        filters.clickToAllDiscountsButton();
//        sleep(2000);
        String numberOfProducts2 = filters.getNumberOfProducts();
        filters.clickToTenPercentButton();
        sleep(2000);
        String numberOfProducts3 = filters.getNumberOfProducts();
        filters.clickToThirtyPercentButton();
        sleep(2000);
        String numberOfProducts4 = filters.getNumberOfProducts();
        filters.clickToFiftyPercentButton();
        sleep(2000);
        String numberOfProducts5 = filters.getNumberOfProducts();
        Assertions.assertAll(
//                () -> assertNotEquals(numberOfProducts1, numberOfProducts2),
                () -> assertNotEquals(numberOfProducts2, numberOfProducts3),
                () -> assertNotEquals(numberOfProducts3, numberOfProducts4),
                () -> assertNotEquals(numberOfProducts4, numberOfProducts5));
    }

    /**
     * Проверяем, что выводится правильное кол-во изделий: <p>
     * Кнопка 'Все скидки'
     */
    @Test
    @Description("Проверяем, что выводится правильное кол-во изделий: Кнопка 'Все скидки'")
    public void allDiscounts() {
        filters.clickToFilterButton();
        filters.clickToAllDiscountsButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getTenPercentDiscountItems();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0,12), siteList.get(0).substring(0,12)),
                () -> assertEquals(sqlList.get(2).substring(0, 19), siteList.get(2).substring(0, 19)));
    }

    /**
     * Кнопка 'От 30%'
     */
    @Test
    @Description("Проверяем, что выводится правильное кол-во изделий: Кнопка 'От 30%'")
    public void thirtyPercentDiscounts() {
        filters.clickToFilterButton();
        filters.clickToThirtyPercentButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getThirtyPercentDiscountItems();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
                () -> assertEquals(sqlList.get(0).substring(0, 20), siteList.get(0).substring(0, 20)),
                () -> assertEquals(sqlList.get(2).substring(0, 20), siteList.get(2).substring(0, 20)));
    }

    /**
     * Кнопка 'От 50%'
     */
    @Test
    @Description("Проверяем, что выводится правильное кол-во изделий: Кнопка 'От 50%'")
    public void fiftyPercentDiscounts() {
        filters.clickToFilterButton();
        filters.clickToFiftyPercentButton();
        filters.clickToShowProductsButton();
        List<String> sqlList = filters.getFiftyPercentDiscountItems();
        getProductsListFromPage();
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        //Сравниваем 2 элемента и размеры списков. Все сравнить невозможно так как на сайте не полностью отображаются длинные названия
        Assertions.assertAll(
                () -> assertEquals(sqlList.size(), numberOnly),
//                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
                () -> assertEquals(sqlList.get(0).substring(0,20), siteList.get(0).substring(0,20)),
                () -> assertEquals(sqlList.get(2).substring(0,20), siteList.get(2).substring(0,20)));
    }

}
