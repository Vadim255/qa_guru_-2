package frolov.vadim;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = ("https://demoqa.com");
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void successTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue("Vadim");
        $("#lastName").setValue("Frolov");
        $("#userEmail").setValue("vadim.frolov.1998@mail.ru");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("89968060794");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1998");
        $("[aria-label$='March 30th, 1998']").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png"));
        $("#currentAddress").setValue("Penzas");
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").click();

        $(".modal-content").shouldHave(
                text("Student Name"), text("Vadim Frolov"),
                text("Student Email"), text("vadim.frolov.1998@mail.ru"),
                text("Gender"), text("Other"),
                text("Mobile"), text("8996806079"),
                text("Date of Birth"), text("30 March,1998"),
                text("Subjects"), text("Computer Science"),
                text("Hobbies"), text("Sports"),
                text("Picture"), text("1.png"),
                text("Address"), text("Penzas"),
                text("State and City"), text("NCR Noida")
        );
    }
    @AfterAll
    static void after() {
        Configuration.holdBrowserOpen = true;
    }

}
