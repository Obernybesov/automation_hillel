import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CosmicSkull {

    String blueVinylItem = "Science Fiction: Interstellar Blue Edition Vinyl";
    String addToCartAlert = "Item added to your cart!";
    String cartUpdateAlert = "Basket updated.";
    String oneItemTotalPrice = "20.00";
    String twoItemsTotalPrice = "40.00";

    @Test
    public void CosmicSkullTest() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 6000;
        open("https://cosmicskull.org/");
        $(By.xpath("//span[text()='Shop']")).click();
        $(By.xpath("//*[contains(@id,'search-field')]")).shouldBe(Condition.visible);

        $(By.xpath("//*[contains(@id,'search-field')]")).val("Blue Edition Vinyl").pressEnter();
        $(By.xpath("//h2[contains(text(),'Blue Edition Vinyl')]")).scrollTo();
        $(By.xpath("//h2[contains(text(),'Blue Edition Vinyl')]")).shouldHave(Condition.exactText(blueVinylItem));

        $(By.xpath("//a[@href='?add-to-cart=73541']")).click();
        $(By.xpath("//div[@role='alert']")).shouldHave(Condition.text(addToCartAlert));
        $(By.xpath("//form/table[contains(@class,'shop_table')]")).should(Condition.appear);
        $(By.xpath("//td/a[contains(text(),'Science Fiction:')]")).shouldHave(Condition.exactText(blueVinylItem));
        $(By.xpath("//input[@type='number']")).shouldHave(Condition.attribute("value", "1"));
        $(By.xpath("//td[@class='product-subtotal']//bdi")).shouldHave(Condition.text(oneItemTotalPrice));
        $(By.xpath("//*[text()='Update basket']")).shouldBe(Condition.disabled);

        $(By.xpath("//input[@type='number']")).clear();
        $(By.xpath("//input[@type='number']")).val("2");
        $(By.xpath("//*[text()='Update basket']")).shouldBe(Condition.enabled);

        $(By.xpath("//*[text()='Update basket']")).click();
        $(By.xpath("//div[@role='alert']")).shouldHave(Condition.text(cartUpdateAlert));
        $(By.xpath("//input[@type='number']")).shouldHave(Condition.attribute("value", "2"));
        $(By.xpath("//td[@class='product-subtotal']//bdi")).shouldHave(Condition.text(twoItemsTotalPrice));










        ////*[@data-id="b9f6b82"]//select[@class="woocommerce-currency-selector"]/child::*


    }
}