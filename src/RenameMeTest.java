import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class RenameMeTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);

    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSearchPopup() {

        String firstName = "Lord";
        String lastName = "Voldemort";

        // XPath для попапа
        By popupLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"\uE934\"]");

        // Закрываем попап, если он существует
        mainPageObject.waitForBrowser(10*1000);
        if (mainPageObject.isElementPresent(popupLocator)) {
            mainPageObject.waitForElementAndClick(
                    popupLocator,
                    "Не удалось закрыть попап",
                    30
            );
        }

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@contentDescription='\uED96' and @height>0]"),
                "Не удалось найти кнопку профиля",
                10
        );

        WebElement nameElement = mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text and @class='android.widget.TextView' and @width>0 and @height>0 and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[@id='illustration']]]"),
                "Не удалось перейти в профиль",
                10
        );

        if (nameElement.getText().contains(firstName + " " + lastName.charAt(0) + ".")) {
            firstName = "Aboba";
            lastName = "Abobovich";
        }

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@id='firstName_input_textInput']"),
                firstName,
                "Не удалось ввести имя",
                10
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@id='lastName_input_textInput']"),
                lastName,
                "Не удалось ввести фамилию",
                10
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@id='profileEditSave_buttonDefault']"),
                "Не удалось сохранить изменения",
                10
        );

        mainPageObject.waitForBrowser(3*1000);
        nameElement = mainPageObject.waitForElementPresent(
                By.xpath("//*[@text and @class='android.widget.TextView' and @width>0 and @height>0 and ./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.ViewGroup' and ./*[@id='illustration']]]"),
                "Не удалось перейти в профиль",
                10
        );


        assertTrue(
                "Имя не совпадает. Ожидалось:" + firstName + " " + lastName.charAt(0) + "." + "; Результат: " + nameElement.getText(),
                nameElement.getText().contains(firstName + " " + lastName.charAt(0) + ".")
        );
    }
}