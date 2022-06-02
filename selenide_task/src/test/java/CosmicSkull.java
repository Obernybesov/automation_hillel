import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;

public class CosmicSkull {

    String blueVinylItem = "Science Fiction: Interstellar Blue Edition Vinyl";
    String addToCartAlert = "Item added to your cart!";
    String cartUpdateAlert = "Basket updated.";
    String oneItemTotalPrice = "20.00";
    String twoItemsTotalPrice = "40.00";

   @BeforeTest
    public void testsSetup() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
        open("https://cosmicskull.org/");}


    @Test
    public void checkShopSearchField() {
        $(By.xpath("//span[text()='Shop']")).click();
        $(By.xpath("//*[contains(@id,'search-field')]")).shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = {"checkShopSearchField"})
    public void searchShopItem() {
        $(By.xpath("//*[contains(@id,'search-field')]")).val("Blue Edition Vinyl").pressEnter();
        $(By.xpath("//h2[contains(text(),'Blue Edition Vinyl')]")).scrollTo();
        $(By.xpath("//h2[contains(text(),'Blue Edition Vinyl')]")).shouldHave(Condition.exactText(blueVinylItem));
    }

    @Test(dependsOnMethods = {"checkShopSearchField", "searchShopItem"})
    public void addItemToChart() {
        $(By.xpath("//a[@href='?add-to-cart=73541']")).click();
        $(By.xpath("//div[@role='alert']")).shouldHave(Condition.text(addToCartAlert));
        $(By.xpath("//form/table[contains(@class,'shop_table')]")).should(Condition.appear);
        $(By.xpath("//td/a[contains(text(),'Science Fiction:')]")).shouldHave(Condition.exactText(blueVinylItem));
        $(By.xpath("//input[@type='number']")).shouldHave(Condition.attribute("value", "1"));
        $(By.xpath("//td[@class='product-subtotal']//bdi")).shouldHave(Condition.text(oneItemTotalPrice));
        $(By.xpath("//*[text()='Update basket']")).shouldBe(Condition.disabled);
    }

    @Test(dependsOnMethods = {"checkShopSearchField", "searchShopItem", "addItemToChart"})
    public void increaseChartItemAmount() {
        $(By.xpath("//input[@type='number']")).clear();
        $(By.xpath("//input[@type='number']")).val("2");
        $(By.xpath("//*[text()='Update basket']")).shouldBe(Condition.enabled);
    }

    @Test(dependsOnMethods = {"checkShopSearchField", "searchShopItem", "addItemToChart", "increaseChartItemAmount"})
    public void changeChartItemsAmount() {
        $(By.xpath("//*[text()='Update basket']")).click();
        $(By.xpath("//div[@role='alert']")).shouldHave(Condition.text(cartUpdateAlert));
        $(By.xpath("//input[@type='number']")).shouldHave(Condition.attribute("value", "2"));
        $(By.xpath("//td[@class='product-subtotal']//bdi")).shouldHave(Condition.text(twoItemsTotalPrice));
    }
}
