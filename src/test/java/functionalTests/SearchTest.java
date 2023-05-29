package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import collectionAndSet.Collection;
import config.TestConfig;
import filters.Filters;
import filters.Size;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sections.Searchs;
import sections.Wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты поиска")
  public class SearchTest extends TestBase{
      @BeforeEach
      public void setUp() {
          mainSetUp();
          wishlist = new Wishlist(driver);
          filters = new Filters(driver);
          size = new Size(driver);
          collection = new Collection(driver);
          basket = new Basket(driver);
          search = new Searchs(driver);
      }

      /*
       * Поиск товара по наименованию дизайнера "Moonka"
       */
      @Test()
      public void searchItemToDesigner() {
          driver.get(TestConfig.SITE_URL);
          search.clickToSearch();
          search.clickInputToSearch();
          search.InputToSearchWeb("Moonka" + "\n");
          String url = driver.getCurrentUrl();
          Assertions.assertAll(
                  () -> assertEquals(TestConfig.SITE_URL + "search?search=Moonka", url));
      }
}
