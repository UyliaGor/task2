// import com.codeborne.selenide.SelenideElement;
// import com.codeborne.selenide.Selenide;
// import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
// import org.junit.jupiter.api.AfterAll;
import org.junit.After;
// import org.junit.Before;
// import org.junit.Before;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
// import static com.codeborne.selenide.Selectors.withText;
import java.io.File;

// import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
// import static org.junit.jupiter.api.Assertions.fail;

public class RunTest {
    @Test
    public void main() {
        // открываем страницу
        open("https://demoqa.com/automation-practice-form");
        Configuration.timeout = 10000;

        // заполняем форму
        $(By.xpath("//*[@id='close-fixedban']")).click();
        $(By.xpath("//*[@id='firstName']")).val("Julia").pressEnter();
        $(By.xpath("//*[@id='lastName']")).val("Ivanova").pressEnter();
        $(By.xpath("//*[@id='userEmail']")).val("julia@mail.ru").pressEnter();
        $(By.xpath("//*[@for='gender-radio-2']")).click();
        $(By.xpath("//*[@id='userNumber']")).val("9751053573").click();
        $(By.xpath("//*[@id='dateOfBirthInput']")).click();
        $(By.xpath("//select[@class='react-datepicker__year-select']")).selectOption("1991");
        $(By.xpath("//select[@class='react-datepicker__month-select']")).selectOption("November");
        $(By.xpath("//div[@aria-label='Choose Wednesday, November 13th, 1991']")).click();
        $(By.xpath("//input[@id='subjectsInput']")).scrollTo();
        $(By.xpath("//input[@id='subjectsInput']")).val("Testing").pressEnter();
        $(By.xpath("//div[@id='hobbiesWrapper']//label[1][@for='hobbies-checkbox-1']")).shouldBe(visible);
        $(By.xpath("//*[@id='uploadPicture']")).uploadFile(new File("src/test/img.jpg"));
        $(By.xpath("//textarea[@class='form-control']")).val("Moscow").pressEnter();
        $(By.xpath("//div[@id='state']")).shouldBe(visible);
        $(By.xpath("//div[@id='state']")).click();
        $(By.xpath("//div[contains(text(),'NCR')]")).click();
        $(By.xpath("//div[@id='city']")).click();
        $(By.xpath("//div[text()='Noida']")).click();
        $(By.xpath("//button[@id='submit']")).click();
        $(By.xpath("//*[@id='example-modal-sizes-title-lg']")).shouldHave(text("Thanks for submitting the form"));

        // проверяем форму
        Assertions.assertEquals($(By.xpath("//tbody/tr[1]/td[2]")).getText(), "Julia Ivanova", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[2]/td[2]")).getText(), "julia@mail.ru", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[3]/td[2]")).getText(), "Female", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[4]/td[2]")).getText(), "9751053573", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[5]/td[2]")).getText(), "13 November,1991", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[7]/td[2]")).getText(), "Music", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[9]/td[2]")).getText(), "Moscow", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[10]/td[2]")).getText(), "NCR Noida", "совпадение не найдено");

        // сравниваем форму с некорректными значениями
        Assertions.assertEquals($(By.xpath("//tbody/tr[1]/td[2]")).getText(), "Ivanova", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[6]/td[2]")).getText(), "Testing", "совпадение не найдено");
    }
    @After
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
