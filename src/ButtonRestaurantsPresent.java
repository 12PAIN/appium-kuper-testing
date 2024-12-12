import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class ButtonRestaurantsPresent extends CoreTestCase {

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
                By.xpath("//*[@contentDescription='Рестораны' and @class='android.widget.ImageButton']"),
                "Не удалось найти кнопку 'Рестораны'",
                10
        );

        mainPageObject.waitForBrowser(3*1000);

        assertTrue("Не удалось найти заголовок 'Рестораны'", mainPageObject.isElementPresent(By.xpath("//*[@text='Рестораны' and @height>0 and ./parent::*[@contentDescription='\uEA42']]")));

    }
}