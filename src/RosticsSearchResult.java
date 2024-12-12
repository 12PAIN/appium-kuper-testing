import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class RosticsSearchResult extends CoreTestCase {

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

        mainPageObject.waitForBrowser(3*1000);

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@id='testID_SearchInputButton']"),
                "Не удалось найти и кликнуть на кнопку поиска",
                10
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.widget.EditText']"),
                "Rostics",
                "Не удалось ввести текст в поле поиска",
                10
        );

        // Преобразование driver в AndroidDriver
        AndroidDriver driver = (AndroidDriver) this.driver;

        // Отправка события нажатия ENTER чтобы подтвердить поиск
        driver.pressKeyCode(AndroidKeyCode.ENTER);

        mainPageObject.waitForBrowser(3*1000);

        assertTrue(
                "Не удалось найти категорию 'Сочная курочка'",
                mainPageObject.isElementPresent(
                        By.xpath("//*[@contentDescription='Сочная курица']")
                )
        );
    }
}