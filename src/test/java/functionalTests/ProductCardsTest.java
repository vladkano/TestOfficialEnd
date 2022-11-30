package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import catalog.Bracelets;
import catalog.Earrings;
import catalog.Necklaces;
import catalog.Rings;
import collectionAndSet.Collection;
import collectionAndSet.Set;
import filters.Filters;
import filters.Size;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import productCards.Picture;
import productCards.ProductCard;
import search.Search;
import sections.Designers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Epic("Тесты карточки товара")
public class ProductCardsTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        filters = new Filters(driver);
        size = new Size(driver);
        basket = new Basket(driver);
        picture = new Picture(driver);
        productCard = new ProductCard(driver);
        earrings = new Earrings(driver);
        bracelets = new Bracelets(driver);
        necklaces = new Necklaces(driver);
        rings = new Rings(driver);
        collection = new Collection(driver);
        set = new Set(driver);
        designers = new Designers(driver);
    }

    /**
     * Тесты переключения между размерами <p>
     * Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами, далее
     * смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину <p>
     * С 14 размером
     */
    @Test
    @Description("Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами, далее" +
            " смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину(С 14 размером)")
    public void changeSize14() {
        driver.get(getUrl + "catalog/koltsa/");
        basket.clickToOkButton();
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToFirstSizeButton();
        filters.clickToShowProductsButton();
        size.clickOnSecondImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
//        size.clickToSecondCurrentSizeButton();
//        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        Assertions.assertAll(
                () -> assertNotEquals(firstCurrentSize, secondCurrentSize),
//                () -> assertNotEquals(secondCurrentSize, thirdCurrentSize),
//                () -> assertNotEquals(firstCurrentSize, thirdCurrentSize),
                () -> assertEquals("Размер: " + secondCurrentSize, sizeHeader));
    }

    /**
     * С 15.5 размером
     */
    @Test
    @Description("Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами, далее" +
            " смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину(С 15.5 размером)")
    public void changeSize155() {
        driver.get(getUrl + "catalog/koltsa/");
        basket.clickToOkButton();
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToSecondSizeButton();
        filters.clickToShowProductsButton();
        size.clickOnSecondImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
//        size.clickToSecondCurrentSizeButton();
//        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        Assertions.assertAll(
                () -> assertNotEquals(firstCurrentSize, secondCurrentSize),
//                () -> assertNotEquals(secondCurrentSize, thirdCurrentSize),
//                () -> assertNotEquals(firstCurrentSize, thirdCurrentSize),
                () -> assertEquals("Размер: " + secondCurrentSize, sizeHeader));
    }

    /**
     * С 16 размером
     */
    @Test
    @Description("Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами, далее" +
            " смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину(С 16 размером)")
    public void changeSize16() {
        driver.get(getUrl + "catalog/koltsa/");
        basket.clickToOkButton();
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToThirdSizeButton();
        filters.clickToShowProductsButton();
        size.clickOnSecondImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
//        size.clickToSecondCurrentSizeButton();
//        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        Assertions.assertAll(
                () -> assertNotEquals(firstCurrentSize, secondCurrentSize),
//                () -> assertNotEquals(secondCurrentSize, thirdCurrentSize),
//                () -> assertNotEquals(firstCurrentSize, thirdCurrentSize),
                () -> assertEquals("Размер: " + secondCurrentSize, sizeHeader));
    }

    /**
     * Тест неактуален, так как в данный момент(31.03.2022) на виртуальном складе числятся только подарочные сертификаты
     * Проверяем, что если товар в наличии только на виртуальном складе, то в карточке товара должна быть плашка 'Доставка от 3-5 дней' (storage id = 5)
     */
//    @Test
//    @Description("Проверяем, что если товар в наличии только на виртуальном складе, то в карточке товара должна быть плашка 'Доставка от 3-5 дней'")
//    public void firstCheckPlate() {
//        search = new Search(driver);
//        driver.get(getUrl + "catalog");
//        basket.clickToOkButton();
//        String firstItem = size.findItem35days();
//        search.getSearch(firstItem + "\n");
//        size.clickOnImageLink();
//        String plateHeader = size.getPlateHeader();
//        assertEquals("Доставка от 3-5 дней", plateHeader);
//    }

    //Все плашки отключены на сайте https://tracker.yandex.ru/PD-1818
//    /**
//     * Проверяем, что если товар в наличии только на складе в Питере, то в карточке товара должна быть плашка 'Доставка от 7 дней' (storage id = 1)
//     */
//    @Test
//    @Description("Проверяем, что если товар в наличии только на складе в Питере, то в карточке товара должна быть плашка 'Доставка от 7 дней'")
//    public void secondCheckPlate() {
//        search = new Search(driver);
//        driver.get(getUrl + "catalog");
//        basket.clickToOkButton();
//        String secondItem = size.findItem7days();
//        search.getSearch(secondItem + "\n");
//        size.clickOnImageLink();
//        String plateHeader = size.getPlateHeader();
//        assertEquals("Доставка от 7 дней", plateHeader);
//    }

    /**
     * Проверяем отображение картинок в карточке товара по разделам:
     */
    @Test
    @Description("Проверяем отображение картинок в карточке товара по разделам: Серьги")
    public void checkPictureListSergi() {
        driver.get(getUrl + "catalog/sergi");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    @Description("Проверяем отображение картинок в карточке товара по разделам: Браслеты")
    public void checkPictureListBraslety() {
        driver.get(getUrl + "catalog/braslety");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    @Description("Проверяем отображение картинок в карточке товара по разделам: Кольца")
    public void checkPictureListKoltsa() {
        driver.get(getUrl + "catalog/koltsa");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    @Description("Проверяем отображение картинок в карточке товара по разделам: Колье")
    public void checkPictureListKole() {
        driver.get(getUrl + "catalog/kole");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        System.out.println(size);
        assertNotEquals(0, size);
    }

    /**
     * Если товара нет в наличии, то кнопки "в корзину" быть не должно. Проверка по разделам:
     */
    @Test
    @Description("Проверяем если товара нет в наличии, то кнопки 'в корзину' быть не должно. Проверка по разделам: Серьги")
    public void checkCartButtonSergi() {
        String s = earrings.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/sergi/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    @Description("Проверяем если товара нет в наличии, то кнопки 'в корзину' быть не должно. Проверка по разделам: Браслеты")
    public void checkCartButtonBraslety() {
        String s = bracelets.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/braslety/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    @Description("Проверяем если товара нет в наличии, то кнопки 'в корзину' быть не должно. Проверка по разделам: Колье")
    public void checkCartButtonKole() {
        String s = necklaces.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/kole/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    @Description("Проверяем если товара нет в наличии, то кнопки 'в корзину' быть не должно. Проверка по разделам: Кольца")
    public void checkCartButtonKoltsa() {
        String s = rings.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/koltsa/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("этого украшения сейчас нет в наличии", noBasketHeader);
    }


    /**
     * Если товар входит в коллекцию, то должен отображаться блок "Украшения из образа"
     * + кликаем на первый товар из блока и смотрим правильно ли совершается переход на страницу товара.
     * Проверка по разделам:
     */
    @Test
    @Description("Если товар входит в коллекцию, то должен отображаться блок 'Украшения из образа' " +
            "+ кликаем на первый товар из блока и смотрим правильно ли совершается переход на страницу товара. Проверка по разделам: Серьги")
    public void checkSetWindowSergi() {
        String s = earrings.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/sergi/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals("украшения из образа", setHeader),
                () -> assertEquals(href, currentUrl));
    }

    @Test
    @Description("Если товар входит в коллекцию, то должен отображаться блок 'Украшения из образа' " +
            "+ кликаем на первый товар из блока и смотрим правильно ли совершается переход на страницу товара. Проверка по разделам: Браслеты")
    public void checkSetWindowBraslety() {
        String s = bracelets.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/braslety/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals("украшения из образа", setHeader),
                () -> assertEquals(href, currentUrl));
    }

    @Test
    @Description("Если товар входит в коллекцию, то должен отображаться блок 'Украшения из образа' " +
            "+ кликаем на первый товар из блока и смотрим правильно ли совершается переход на страницу товара. Проверка по разделам: Колье")
    public void checkSetWindowKole() {
        String s = necklaces.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/kole/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals("украшения из образа", setHeader),
                () -> assertEquals(href, currentUrl));
    }

    @Test
    @Description("Если товар входит в коллекцию, то должен отображаться блок 'Украшения из образа' " +
            "+ кликаем на первый товар из блока и смотрим правильно ли совершается переход на страницу товара. Проверка по разделам: Кольца")
    public void checkSetWindowKoltsa() {
        String s = rings.getItemsFromSet().get(0);
        driver.get(getUrl + "catalog/koltsa/" + s);
        set.getSetWindow();
        String setHeader = set.getSetHeader();
        String href = set.getHrefFirstItemFromSet();
        basket.clickToOkButton();
        set.clickOnFirstItemFromSet();
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertAll(
                () -> assertEquals("украшения из образа", setHeader),
                () -> assertEquals(href, currentUrl));
    }


    //Тесты товаров из блока "Украшения из образа" нельзя перенести в корзину Баг: https://tracker.yandex.ru/PD-2156
    //Оказывается так задумано инфа от Ромы

//    /**
//     * Проверяем, что товары из блока "Украшения из образа" можно перенести в корзину.
//     * Проверка по разделам:
//     */
//    @Test
//    @Description("Проверяем, что товары из блока 'Украшения из образа' можно перенести в корзину. Проверка по разделам: Серьги")
//    public void checkBasketSergi() {
//        earrings = new Earrings(driver);
//        String s = earrings.getItemsFromSet().get(0);
//        driver.get(getUrl + "catalog/sergi/" + s);
//        basket.clickToOkButton();
//        basket.clickToSetItemInBasketButton();
//        String number = basket.getBasketNumber();
//        assertEquals("1", number);
//    }
//
//    @Test
//    @Description("Проверяем, что товары из блока 'Украшения из образа' можно перенести в корзину. Проверка по разделам: Браслеты")
//    public void checkBasketBraslety() {
//        bracelets = new Bracelets(driver);
//        String s = bracelets.getItemsFromSet().get(0);
//        driver.get(getUrl + "catalog/braslety/" + s);
//        basket.clickToOkButton();
//        basket.clickToSetItemInBasketButton();
//        String number = basket.getBasketNumber();
//        assertEquals("1", number);
//    }
//
//    @Test
//    @Description("Проверяем, что товары из блока 'Украшения из образа' можно перенести в корзину. Проверка по разделам: Колье")
//    public void checkBasketKole() {
//        necklaces = new Necklaces(driver);
//        String s = necklaces.getItemsFromSet().get(0);
//        driver.get(getUrl + "catalog/kole/" + s);
//        basket.clickToOkButton();
//        basket.clickToSetItemInBasketButton();
//        String number = basket.getBasketNumber();
//        assertEquals("1", number);
//    }
//
//    @Test
//    @Description("Проверяем, что товары из блока 'Украшения из образа' можно перенести в корзину. Проверка по разделам: Кольца")
//    public void checkBasketKoltsa() {
//        set = new Set(driver);
//        rings = new Rings(driver);
//        String s = rings.getItemsFromSet().get(0);
//        driver.get(getUrl + "catalog/koltsa/" + s);
//        basket.clickToOkButton();
//        basket.clickToSetItemInBasketButton();
//        String number = basket.getBasketNumber();
//        assertEquals("1", number);
//    }

    /**
     * Проверяем отображение блока дизайнеры(фото, название, описание).
     * Проверка по разделам:
     */
    @Test
    @Description("Проверяем отображение блока дизайнеры(фото, название, описание). Проверка по разделам: Серьги")
    public void checkingDesignersBlockSergi() {
        driver.get(getUrl + "catalog/sergi/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        String descriptionNew = description.replaceAll("\n", "");
        Assertions.assertAll(
                () -> assertEquals(text, photoAlt),
                () -> assertEquals(text, designerName),
                () -> assertEquals(descriptionNew.substring(0, 20), designerText.substring(0, 20)));
    }

    @Test
    @Description("Проверяем отображение блока дизайнеры(фото, название, описание). Проверка по разделам: Браслеты")
    public void checkingDesignersBlockBraslety() {
        driver.get(getUrl + "catalog/braslety/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        Assertions.assertAll(
                () -> assertEquals(text, photoAlt),
                () -> assertEquals(text, designerName),
                () -> assertEquals(description.substring(0, 20), designerText.substring(0, 20)));
    }

    @Test
    @Description("Проверяем отображение блока дизайнеры(фото, название, описание). Проверка по разделам: Колье")
    public void checkingDesignersBlockKole() {
        driver.get(getUrl + "catalog/kole/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        String descriptionNew = description.replaceAll("<br>", "");
        Assertions.assertAll(
                () -> assertEquals(text, photoAlt),
                () -> assertEquals(text, designerName),
                () -> assertEquals(descriptionNew.substring(0, 20), designerText.substring(0, 20)));
    }

    @Test
    @Description("Проверяем отображение блока дизайнеры(фото, название, описание). Проверка по разделам: Кольца")
    public void checkingDesignersBlockKoltsa() {
        driver.get(getUrl + "catalog/koltsa/");
        String text = designers.getDescriptions();
        designers.clickOnItemName();
        String photoAlt = designers.getDesignerPhotoAlt();
        String designerName = designers.getDesignerName();
        String designerText = designers.getDesignerText();
        String description = designers.getDesignerDescription(text);
        Assertions.assertAll(
                () -> assertEquals(text, photoAlt),
                () -> assertEquals(text, designerName),
                () -> assertEquals(description.substring(0, 20), designerText.substring(0, 20)));
    }

    /**
     * Проверяем работу кнопки перехода к товарам дизайнера.
     * Проверка по разделам:
     */
    @Test
    @Description("Проверяем работу кнопки перехода к товарам дизайнера. Проверка по разделам: Серьги")
    public void buttonForNavigatingToDesignerProductsSergi() {
        driver.get(getUrl + "catalog/sergi/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    @Test
    @Description("Проверяем работу кнопки перехода к товарам дизайнера. Проверка по разделам: Браслеты")
    public void buttonForNavigatingToDesignerProductsBraslety() {
        driver.get(getUrl + "catalog/braslety/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    @Test
    @Description("Проверяем работу кнопки перехода к товарам дизайнера. Проверка по разделам: Колье")
    public void buttonForNavigatingToDesignerProductsKole() {
        driver.get(getUrl + "catalog/kole/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    @Test
    @Description("Проверяем работу кнопки перехода к товарам дизайнера. Проверка по разделам: Кольца")
    public void buttonForNavigatingToDesignerProductsKoltsa() {
        driver.get(getUrl + "catalog/koltsa/");
        String designerNameBeforeClick = designers.getDescriptions();
        designers.clickOnItemName();
        designers.clickToDesignerButton();
        String designerNameAfterClick = designers.getDescriptions();
        assertEquals(designerNameBeforeClick, designerNameAfterClick);
    }

    /**
     * Раздел SHOP THE LOOK отключен 17.03.2022
     */
    //Отображение блока SHOP THE LOOK(надпись, фото и клик по нему)
//    @Test
//    public void checkingShopTheLookBlockSergi() {
//        shopTheLook = new ShopTheLook(driver);
//        driver.get(getUrl + "catalog/sergi/rockah_bronzovye_sergi_s_barocnymi_zemcuzinami/?pokritie=zoloto");
//        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
//        shopTheLook.clickOnShopTheLookPhoto();
//        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
//        Assertions.assertAll(
//                () -> assertEquals("Shop the look", shopTheLookHeader),
//                () -> assertEquals("Пожаловаться", frisbuyMarker));
//    }
//
//    @Test
//    public void checkingShopTheLookBlockBraslety() {
//        shopTheLook = new ShopTheLook(driver);
//        driver.get(getUrl + "catalog/braslety/osnovnaya_xarakteristika_razmer_19_74");
//        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
//        shopTheLook.clickOnShopTheLookPhoto();
//        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
//        Assertions.assertAll(
//                () -> assertEquals("Shop the look", shopTheLookHeader),
//                () -> assertEquals("Пожаловаться", frisbuyMarker));
//    }
//
//    @Test
//    public void checkingShopTheLookBlockKole() {
//        shopTheLook = new ShopTheLook(driver);
//        driver.get(getUrl + "catalog/kole/crystalline_pozolocennoe_kole_cep_s_kamnyami/?stone=zelyonyi-zad");
//        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
//        shopTheLook.clickOnShopTheLookPhoto();
//        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
//        Assertions.assertAll(
//                () -> assertEquals("Shop the look", shopTheLookHeader),
//                () -> assertEquals("Пожаловаться", frisbuyMarker));
//    }
//
//    @Test
//    public void checkingShopTheLookBlockKoltsa() {
//        shopTheLook = new ShopTheLook(driver);
//        driver.get(getUrl + "catalog/koltsa/kolco_s_lunnym_kamnem_ogranki_baget_16");
//        String shopTheLookHeader = shopTheLook.getShopTheLookHeader();
//        shopTheLook.clickOnShopTheLookPhoto();
//        String frisbuyMarker = shopTheLook.getFrisbuyMarker();
//        Assertions.assertAll(
//                () -> assertEquals("Shop the look", shopTheLookHeader),
//                () -> assertEquals("Пожаловаться", frisbuyMarker));
//    }


    //Ошибка в разделе "оплата, возврат" в карточке https://tracker.yandex.ru/PD-2157

    /**
     * Отображение блоков: <p>
     * -ПОЛУЧЕНИЕ <p>
     * -СОСТАВ И ХАРАКТЕРИСТИКИ <p>
     * -НАЛИЧИЕ В МАГАЗИНАХ <p>
     * -ОПЛАТА, ВОЗВРАТ <p>
     * -ГАРАНТИЯ 6 МЕСЯЦЕВ <p>
     * -УХОД ЗА УКРАШЕНИЯМИ <p>
     * Отображение 6 блоков(ПОЛУЧЕНИЕ, СОСТАВ И ХАРАКТЕРИСТИКИ, НАЛИЧИЕ В МАГАЗИНАХ, "ОПЛАТА, ВОЗВРАТ", ГАРАНТИЯ 6 МЕСЯЦЕВ, УХОД ЗА УКРАШЕНИЯМИ) <p>
     * Проверка по разделам:
     */
    @Test
    @Description("Проверяем отображение 6 блоков(ПОЛУЧЕНИЕ, СОСТАВ И ХАРАКТЕРИСТИКИ, НАЛИЧИЕ В МАГАЗИНАХ, " +
            "'ОПЛАТА, ВОЗВРАТ', ГАРАНТИЯ 6 МЕСЯЦЕВ, УХОД ЗА УКРАШЕНИЯМИ). Проверка по разделам: Серьги")
    public void checkingBlocksSergi() {
        driver.get(getUrl + "catalog/sergi/");
        size.clickOnImageLink();
        String receivingText = productCard.getReceivingText();
        String receivingCity = productCard.getReceivingCity();
        String location = productCard.getLocation();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();
        String paveletskaya = productCard.getPaveletskaya();
        String krasnodar = productCard.getKrasnodar();
        String kazan = productCard.getKazan();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();
        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();
        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();
        Assertions.assertAll(
                () -> assertEquals("Тип изделия:", specification.substring(0, 12)),
                () -> assertEquals("Цветной", tsvetnoy),
                () -> assertEquals("Метрополис", metropolis),
                () -> assertEquals("Афимолл", afimoll),
                () -> assertEquals("Атриум", atrium),
                () -> assertEquals("Павелецкая плаза", paveletskaya),
                () -> assertEquals("Галерея Краснодар", krasnodar),
                () -> assertEquals("APR Санкт-Петербург", redBridge),
                () -> assertEquals("KazanMall", kazan),
                () -> assertEquals("доставка", receivingText),
                () -> assertEquals("Россия", location),
                () -> assertEquals("ювелирные украшения", jewelryCareHeader),
                () -> assertEquals("носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося в них спирта).", jewelryCareText),
                () -> assertEquals("бижутерия", bijouterieCareHeader),
                () -> assertEquals("украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте их перед душем и нанесением косметики.", bijouterieCareText),
                () -> assertEquals("оплатить заказ можно как наличными курьеру непосредственно при получении заказа", deliveryText.substring(0, 79)),
                () -> assertEquals("на украшения, купленные в магазинах или на сайте Poison Drop, действует гарантия шесть месяцев, если украшение было с производственным браком. Что это?", guaranteeText.substring(0, 151)));
    }

    @Test
    @Description("Проверяем отображение 6 блоков(ПОЛУЧЕНИЕ, СОСТАВ И ХАРАКТЕРИСТИКИ, НАЛИЧИЕ В МАГАЗИНАХ, " +
            "'ОПЛАТА, ВОЗВРАТ', ГАРАНТИЯ 6 МЕСЯЦЕВ, УХОД ЗА УКРАШЕНИЯМИ). Проверка по разделам: Браслеты")
    public void checkingBlocksBraslety() {
        driver.get(getUrl + "catalog/braslety/");
        size.clickOnImageLink();
        String receivingText = productCard.getReceivingText();
        String receivingCity = productCard.getReceivingCity();
        String location = productCard.getLocation();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();
        String paveletskaya = productCard.getPaveletskaya();
        String krasnodar = productCard.getKrasnodar();
        String kazan = productCard.getKazan();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();
        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();
        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();
        Assertions.assertAll(
                () -> assertEquals("Тип изделия:", specification.substring(0, 12)),
                () -> assertEquals("Цветной", tsvetnoy),
                () -> assertEquals("Метрополис", metropolis),
                () -> assertEquals("Афимолл", afimoll),
                () -> assertEquals("Атриум", atrium),
                () -> assertEquals("Павелецкая плаза", paveletskaya),
                () -> assertEquals("Галерея Краснодар", krasnodar),
                () -> assertEquals("APR Санкт-Петербург", redBridge),
                () -> assertEquals("KazanMall", kazan),
                () -> assertEquals("доставка", receivingText),
                () -> assertEquals("Россия", location),
                () -> assertEquals("ювелирные украшения", jewelryCareHeader),
                () -> assertEquals("носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося в них спирта).", jewelryCareText),
                () -> assertEquals("бижутерия", bijouterieCareHeader),
                () -> assertEquals("украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте их перед душем и нанесением косметики.", bijouterieCareText),
                () -> assertEquals("оплатить заказ можно как наличными курьеру непосредственно при получении заказа", deliveryText.substring(0, 79)),
                () -> assertEquals("на украшения, купленные в магазинах или на сайте Poison Drop, действует гарантия шесть месяцев, если украшение было с производственным браком. Что это?", guaranteeText.substring(0, 151)));
    }

    @Test
    @Description("Проверяем отображение 6 блоков(ПОЛУЧЕНИЕ, СОСТАВ И ХАРАКТЕРИСТИКИ, НАЛИЧИЕ В МАГАЗИНАХ, " +
            "'ОПЛАТА, ВОЗВРАТ', ГАРАНТИЯ 6 МЕСЯЦЕВ, УХОД ЗА УКРАШЕНИЯМИ). Проверка по разделам: Колье")
    public void checkingBlocksKole() {
        driver.get(getUrl + "catalog/kole/");
        size.clickOnImageLink();
        String receivingText = productCard.getReceivingText();
        String receivingCity = productCard.getReceivingCity();
        String location = productCard.getLocation();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();
        String paveletskaya = productCard.getPaveletskaya();
        String krasnodar = productCard.getKrasnodar();
        String kazan = productCard.getKazan();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();
        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();
        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();
        Assertions.assertAll(
                () -> assertEquals("Тип изделия:", specification.substring(0, 12)),
                () -> assertEquals("Цветной", tsvetnoy),
                () -> assertEquals("Метрополис", metropolis),
                () -> assertEquals("Афимолл", afimoll),
                () -> assertEquals("Атриум", atrium),
                () -> assertEquals("Павелецкая плаза", paveletskaya),
                () -> assertEquals("Галерея Краснодар", krasnodar),
                () -> assertEquals("APR Санкт-Петербург", redBridge),
                () -> assertEquals("KazanMall", kazan),
                () -> assertEquals("доставка", receivingText),
                () -> assertEquals("Россия", location),
                () -> assertEquals("ювелирные украшения", jewelryCareHeader),
                () -> assertEquals("носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося в них спирта).", jewelryCareText),
                () -> assertEquals("бижутерия", bijouterieCareHeader),
                () -> assertEquals("украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте их перед душем и нанесением косметики.", bijouterieCareText),
                () -> assertEquals("оплатить заказ можно как наличными курьеру непосредственно при получении заказа", deliveryText.substring(0, 79)),
                () -> assertEquals("на украшения, купленные в магазинах или на сайте Poison Drop, действует гарантия шесть месяцев, если украшение было с производственным браком. Что это?", guaranteeText.substring(0, 151)));
    }

    @Test
    @Description("Проверяем отображение 6 блоков(ПОЛУЧЕНИЕ, СОСТАВ И ХАРАКТЕРИСТИКИ, НАЛИЧИЕ В МАГАЗИНАХ, " +
            "'ОПЛАТА, ВОЗВРАТ', ГАРАНТИЯ 6 МЕСЯЦЕВ, УХОД ЗА УКРАШЕНИЯМИ). Проверка по разделам: Кольца")
    public void checkingBlocksKoltsa() {
        driver.get(getUrl + "catalog/koltsa/");
        size.clickOnImageLink();
        String receivingText = productCard.getReceivingText();
        String receivingCity = productCard.getReceivingCity();
        String location = productCard.getLocation();
        productCard.clickToStructureButton();
        String specification = productCard.getSpecification();
        String tsvetnoy = productCard.clickToAvailabilityButton()
                .getTsvetnoy();
        String metropolis = productCard.getMetropolis();
        String afimoll = productCard.getAfimoll();
        String atrium = productCard.getAtrium();
        String redBridge = productCard.getRedBridge();
        String paveletskaya = productCard.getPaveletskaya();
        String krasnodar = productCard.getKrasnodar();
        String kazan = productCard.getKazan();

        String jewelryCareHeader = productCard.clickToJewelryCareButton()
                .getJewelryCareHeader();
        String jewelryCareText = productCard.getJewelryCareText();
        String bijouterieCareHeader = productCard.getBijouterieCareHeader();
        String bijouterieCareText = productCard.getBijouterieCareText();
        String deliveryText = productCard.clickToDeliveryButton()
                .getDeliveryText();
        String guaranteeText = productCard.clickToGuaranteeButton()
                .getGuaranteeText();
        Assertions.assertAll(
                () -> assertEquals("Тип изделия:", specification.substring(0, 12)),
                () -> assertEquals("Цветной", tsvetnoy),
                () -> assertEquals("Метрополис", metropolis),
                () -> assertEquals("Афимолл", afimoll),
                () -> assertEquals("Атриум", atrium),
                () -> assertEquals("Павелецкая плаза", paveletskaya),
                () -> assertEquals("Галерея Краснодар", krasnodar),
                () -> assertEquals("APR Санкт-Петербург", redBridge),
                () -> assertEquals("KazanMall", kazan),
                () -> assertEquals("доставка", receivingText),
                () -> assertEquals("Россия", location),
                () -> assertEquals("ювелирные украшения", jewelryCareHeader),
                () -> assertEquals("носим не снимая, но помним, что натуральные камни не любят духи (из-за содержащегося в них спирта).", jewelryCareText),
                () -> assertEquals("бижутерия", bijouterieCareHeader),
                () -> assertEquals("украшения из ювелирного сплава боятся воды (особенно солёной), крема и парфюма — снимайте их перед душем и нанесением косметики.", bijouterieCareText),
                () -> assertEquals("оплатить заказ можно как наличными курьеру непосредственно при получении заказа", deliveryText.substring(0, 79)),
                () -> assertEquals("на украшения, купленные в магазинах или на сайте Poison Drop, действует гарантия шесть месяцев, если украшение было с производственным браком. Что это?", guaranteeText.substring(0, 151)));
    }

    /**
     * Отображение верного кода изделия в карточке товара <p>
     * Проверка по разделам:
     */
    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Серьги")
    public void checkingCodeSergi() {
        driver.get(getUrl + "catalog/sergi/");
        earrings.clickOnNameLink();
        String codeFromBase = earrings.getCodes().get(0);
        String codeFromSite = productCard.getItemCode();
        assertEquals("артикул " + codeFromBase, codeFromSite);
    }

    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Браслеты")
    public void checkingCodeBraslety() {
        driver.get(getUrl + "catalog/braslety/");
        bracelets.clickOnNameLink();
        String codeFromBase = bracelets.getCodes().get(0);
        String codeFromSite = productCard.getItemCode();
        assertEquals("артикул " + codeFromBase, codeFromSite);
    }

    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Колье")
    public void checkingCodeKole() {
        driver.get(getUrl + "catalog/kole/");
        necklaces.clickOnNameLink();
        String codeFromBase = necklaces.getCodes().get(0);
        String codeFromSite = productCard.getItemCode();
        assertEquals("артикул " + codeFromBase, codeFromSite);
    }

    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Кольца")
    public void checkingCodeKoltsa() {
        driver.get(getUrl + "catalog/koltsa/");
        rings.clickOnNameLink();
        String codeFromBase = rings.getCodes().get(0);
        String codeFromSite = productCard.getItemCode();
        assertEquals("артикул " + codeFromBase, codeFromSite);
    }


    /**
     * Проверяем переключение между элементами конструктора(золото/серебро и т.п.) <p>
     * Проверка по разделам:
     */
    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Серьги")
    public void constructorSergi() {
        driver.get(getUrl + "catalog/sergi/");
        collection.clickOnFirstHref();
        String firstName = basket.getHeader();
        collection.clickOnSecondItem();
        String secondName = basket.getHeader();
        assertNotEquals(firstName, secondName);
    }

    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Браслеты")
    public void constructorBraslety() {
        driver.get(getUrl + "catalog/braslety/");
        collection.clickOnFirstHref();
        String firstName = basket.getHeader();
        collection.clickOnSecondItem();
        String secondName = basket.getHeader();
        assertNotEquals(firstName, secondName);
    }

    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Колье")
    public void constructorKole() {
        driver.get(getUrl + "catalog/kole/");
        collection.clickOnFirstHref();
        String firstName = basket.getHeader();
        collection.clickOnSecondItem();
        String secondName = basket.getHeader();
        assertNotEquals(firstName, secondName);
    }

    @Test
    @Description("Проверяем отображение верного кода изделия в карточке товара. Проверка по разделам: Кольца")
    public void constructorKoltsa() {
        driver.get(getUrl + "catalog/koltsa/");
        collection.clickOnFirstHref();
        String firstName = basket.getHeader();
        collection.clickOnSecondItem();
        String secondName = basket.getHeader();
        assertNotEquals(firstName, secondName);
    }

    /**
     * Тесты секции "Недавно просмотренных товаров" в карточке товара: <p>
     * Последовательный просмотр 5 товаров и проверка того, что они отразились в секции: <p>
     * Проверка по наименованию дизайнера
     */
    @Test
    @Description("Проверяем секцию 'Недавно просмотренных товаров'. Последовательный просмотр 5 товаров и проверка того, что они отразились в секции." +
            " Проверка по наименованию дизайнера")
    public void checkRecentlyViewedProductsDesignerName() {
        List<String> productList = new ArrayList<>();
        List<String> viewedProductList = new ArrayList<>();
        driver.get(getUrl + "catalog");
        for (int i = 0; i <= 4; i++) {
            List<WebElement> elements = driver.findElements(nameLink);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", elements.get(i));
            String designerName = basket.getNextDesignerHeader();
            String designerNameFromViewedProducts = productCard.getDesignerNameFromRecentlyViewedProducts();
            productList.add(designerName);
            System.out.println(designerNameFromViewedProducts);
            viewedProductList.add(designerNameFromViewedProducts);
            basket.clickOnCatalogButton();
        }
        basket.clickOnNameLink();
        String recentlyViewedProductsHeader = productCard.getRecentlyViewedProductsHeader();
        Assertions.assertAll(
                () -> assertEquals("вы смотрели", recentlyViewedProductsHeader),
                () -> assertEquals(productList, viewedProductList));
    }

    /**
     * Проверка по цене товара
     */
    @Test
    @Description("Проверяем секцию 'Недавно просмотренных товаров'. Последовательный просмотр 5 товаров и проверка того, что они отразились в секции." +
            " Проверка по цене товара")
    public void checkRecentlyViewedProductsPrice() {
        List<String> listOfPrices = new ArrayList<>();
        List<String> viewedListOfPrices = new ArrayList<>();
        driver.get(getUrl + "catalog");
        for (int i = 0; i <= 4; i++) {
            List<WebElement> elements = driver.findElements(nameLink);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", elements.get(i));
            String price = basket.getPriceFromProductCard();
            String priceFromRecentlyViewedProducts = productCard.getPriceFromRecentlyViewedProducts();
            listOfPrices.add(price);
            viewedListOfPrices.add(priceFromRecentlyViewedProducts);
            basket.clickOnCatalogButton();
        }
        assertEquals(listOfPrices, viewedListOfPrices);
    }

    /**
     * Смотрим 21 товар и проверяем, что среди просмотренных общее кол-во равно 20.
     */
    @Test
    @Description("Проверяем секцию 'Недавно просмотренных товаров'. Смотрим 21 товар и проверяем, что среди просмотренных общее кол-во равно 20")
    public void productsNoMoreThan20() {
        driver.get(getUrl + "catalog");
        for (int i = 0; i <= 20; i++) {
            List<WebElement> elements = driver.findElements(nameLink);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", elements.get(i));
            basket.clickOnCatalogButton();
        }
        basket.clickOnNameLink();
        List<WebElement> productList = driver.findElements(By.xpath("//section[@class='products-slider viewed-products product-card__products-slider']//div[@class='catalog-card__designer']/a"));
        assertEquals(20, productList.size());
    }

}
