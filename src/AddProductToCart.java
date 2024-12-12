import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class AddProductToCart extends CoreTestCase {

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
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='\uE99F']"),
                "Не удалось очистить корзину",
                10
        );
        mainPageObject.waitForBrowser(3*1000);
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='УДАЛИТЬ']"),
                "Не удалось удалить товары из корзины",
                10
        );
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

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup' and @width>0 and @height>0 and ./*[@contentDescription='\uED41, \uED3A, \uED41'] and ./parent::*[@contentDescription='Соус Сырный24 г']]"),
                "Не удалось найти сырный соус",
                10
        );

        mainPageObject.waitForBrowser(3*1000);

        if(mainPageObject.isElementPresent(By.xpath("//*[@text='Пока можно только собрать корзину']"))){
            mainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='Продолжить']"),
                    "Не удалось нажать кнопку продолжить",
                    10
            );
        }
        mainPageObject.waitForBrowser(3*1000);
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@contentDescription='Добавить']"),
                "Не удалось нажать кнопку добавить",
                10
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='\uE9BE']"),
                "Не удалось нажать кнопку корзины",
                10
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text=concat('Rostic', \"'\", 's')]"),
                "Не удалось найти ресторан в корзине",
                10
        );

        mainPageObject.waitForBrowser(3*1000);
        assertTrue("Не удалось найти сырный соус в корзине",
                mainPageObject.isElementPresent(By.xpath("//*[@text='Соус Сырный']")));

    }
}