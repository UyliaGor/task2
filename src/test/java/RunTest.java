import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class RunTest {
    @BeforeAll
    static void setup(){
        Configuration.startMaximized=true;
    }
    @Test

    public void main() {
        // WebDriver driver=new ChromeDriver();
        Configuration conf = new Configuration();
        conf.pageLoadTimeout = 60000;
        // открываем cтраницу
        open("https://demoqa.com/automation-practice-form");
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
        $x("//input[@id='subjectsInput']").val("Maths").pressEnter();
        $(By.xpath("//div[@id='hobbiesWrapper']//label[1][@for='hobbies-checkbox-1']")).click();
        $(By.xpath("//*[@id='uploadPicture']")).uploadFile(new File("src/test/img.jpg"));
        $(By.xpath("//textarea[@class='form-control']")).val("Moscow").pressEnter();
        $x("//div[@id='state']").scrollTo();
        $x("//div[@id='state']").click();
        $x("//div[contains(@id,'react-select')][text()='NCR']").click();
        $(By.xpath("//div[@id='city']//input")).setValue("Noida").pressEnter();
        $(By.xpath("//button[@id='submit']")).click();
        $(By.xpath("//*[@id='example-modal-sizes-title-lg']")).shouldHave(text("Thanks for submitting the form"));

        // проверяем форму
        Assertions.assertEquals($(By.xpath("//tbody/tr[1]/td[2]")).getText(), "Julia Ivanova", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[2]/td[2]")).getText(), "julia@mail.ru", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[3]/td[2]")).getText(), "Female", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[4]/td[2]")).getText(), "9751053573", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[5]/td[2]")).getText(), "13 November,1991", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[9]/td[2]")).getText(), "Moscow", "совпадение не найдено");
        Assertions.assertEquals($(By.xpath("//tbody/tr[10]/td[2]")).getText(), "NCR Noida", "совпадение не найдено");

        // сравниваем форму с некорректными значениями
        Assertions.assertEquals($(By.xpath("//tbody/tr[1]/td[2]")).getText(), "Ivanova", "совпадение не найдено");

    }
    @After
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
