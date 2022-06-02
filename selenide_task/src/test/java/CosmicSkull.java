import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CosmicSkull {

    private String blueVinylItem = "Science Fiction: Interstellar Blue Edition Vinyl";

    @Test
    public void CosmicSkullTest() {
        Configuration.browserSize = "1920x1080";
        open("https://cosmicskull.org/");
        $(By.xpath("//span[text()='Shop']")).click();
        $(By.xpath("//*[@id='woocommerce-product-search-field-0']")).shouldBe(Condition.visible);

        $(By.xpath("//*[@id='woocommerce-product-search-field-0']")).val("Blue Edition Vinyl").pressEnter();
        $(By.xpath("//h2[contains(text(),'Blue Edition Vinyl')]")).scrollTo();
        $(By.xpath("//h2[contains(text(),'Blue Edition Vinyl')]")).shouldHave(Condition.exactText(blueVinylItem));

        $(By.xpath("//a[@href='?add-to-cart=73541']")).click();
        $(By.xpath("//div/h1[text()='Basket']")).should(Condition.appear);
        $(By.xpath("//td/a[contains(text(),'Science Fiction:')]")).shouldHave(Condition.exactText(blueVinylItem));
        $(By.xpath("//input[@type='number']")).shouldHave(Condition.attribute("value", "1"));
        //$(By.xpath("//td[@class='product-subtotal']//bdi"))
        $(By.xpath("//*[text()='Update basket']")).shouldBe(Condition.disabled);

        $(By.xpath("//input[@type='number']")).clear();
        $(By.xpath("//input[@type='number']")).val("2");
        $(By.xpath("//*[text()='Update basket']")).shouldBe(Condition.enabled);

        $(By.xpath("//*[text()='Update basket']")).click();
        $(By.xpath("//input[@type='number']")).shouldHave(Condition.attribute("value", "2"));

        //$$(By.xpath("//*//select[contains(@class,'woo')]/*")).shouldHave(CollectionCondition.size(12));









        ////*[@data-id="b9f6b82"]//select[@class="woocommerce-currency-selector"]/child::*


    }
}