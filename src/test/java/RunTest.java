import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class RunTest {
    @BeforeAll
    static void setup(){
        Configuration.startMaximized=true;
        Configuration conf = new Configuration();
        conf.pageLoadTimeout = 60000;


    }
    @Test
    public void main(){
        // открываем cтраницу
        open("https://demoqa.com/automation-practice-form");
        // заполняем форму
        $x("//*[@id='close-fixedban']").click();
        $x("//*[@id='firstName']").val("Julia").pressEnter();
        $x("//*[@id='lastName']").val("Ivanova").pressEnter();
        $x("//*[@id='userEmail']").val("julia@mail.ru").pressEnter();
        $x("//*[@for='gender-radio-2']").click();
        $x("//*[@id='userNumber']").val("9751053573").click();
        $x("//*[@id='dateOfBirthInput']").click();
        $x("//select[@class='react-datepicker__year-select']").selectOption("1991");
        $x("//select[@class='react-datepicker__month-select']").selectOption("November");
        $x("//div[@aria-label='Choose Wednesday, November 13th, 1991']").click();
        $x("//input[@id='subjectsInput']").scrollTo();
        $x("//input[@id='subjectsInput']").val("Maths").pressEnter();
        $x("//div[@id='hobbiesWrapper']//label[1][@for='hobbies-checkbox-1']").click();
        $x("//*[@id='uploadPicture']").uploadFile(new File("src/test/img.jpg"));
        $x("//textarea[@class='form-control']").val("Moscow").pressEnter();
        $x("//div[@id='state']").scrollTo();
        $x("//div[@id='state']").click();
        $x("//div[contains(@id,'react-select')][text()='NCR']").click();
        $x("//div[@id='city']//input").setValue("Noida").pressEnter();
        $x("//button[@id='submit']").click();
        $x("//*[@id='example-modal-sizes-title-lg']").shouldHave(text("Thanks for submitting the form"));

        // проверяем форму используя Assert
        Assertions.assertEquals($x("//tbody/tr[1]/td[2]").getText(), "Julia Ivanova", "совпадение не найдено");
        Assertions.assertEquals($x("//tbody/tr[2]/td[2]").getText(), "julia@mail.ru", "совпадение не найдено");
        Assertions.assertEquals($x("//tbody/tr[3]/td[2]").getText(), "Female", "совпадение не найдено");
        Assertions.assertEquals($x("//tbody/tr[4]/td[2]").getText(), "9751053573", "совпадение не найдено");
        Assertions.assertEquals($x("//tbody/tr[5]/td[2]").getText(), "13 November,1991", "совпадение не найдено");
        Assertions.assertEquals($x("//tbody/tr[9]/td[2]").getText(), "Moscow", "совпадение не найдено");
        Assertions.assertEquals($x("//tbody/tr[10]/td[2]").getText(), "NCR Noida", "совпадение не найдено");

        // сравниваем форму с некорректными значениями
        Assertions.assertEquals($x("//tbody/tr[1]/td[2]").getText(), "Ivanova", "совпадение не найдено");

    }
    @After
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
