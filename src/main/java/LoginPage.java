import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final int LINK_PRESENSE_TIMEOUT = 15;
    private String inboxURL = "https://e.mail.ru/inbox/?back=1&afterReload=1";

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonSubmitOnLoginPage;

    @FindBy(xpath = "//*[@id='PH_user-email']")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[@href = '/inbox/']")
    private WebElement buttonInboxInMenu;
    @FindBy(xpath = "(//div[@class = 'dataset__items']/a[contains(@href, '/inbox/')])")
    public List<WebElement> inboxLetterList;
    @FindBy(xpath = "(//a[contains(@class, 'letter-bottom')]//span[@class = 'mb-checkbox__wrapper'])")
    public List<WebElement> checkBoxList;

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    public LoginPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void setLoginAndPassword(String login, String password) {
        loginField.clear();
        loginField.sendKeys(login);
        buttonSubmitOnLoginPage.click();
        passwordField.sendKeys(password);
        buttonSubmitOnLoginPage.click();
    }

    public boolean logoutLinkPresents() {
        driver.navigate().refresh();
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).isDisplayed();
    }

    public void navigateToInboxPage() {
        driver.get(inboxURL);
        wait.until(ExpectedConditions.elementToBeClickable(buttonInboxInMenu));
        buttonInboxInMenu.click();
    }
    public void labelsOfLettersClicker(int numberOfLetters) {
        int i = 0;
        while (i < numberOfLetters) {
            wait.until(ExpectedConditions.elementToBeClickable(inboxLetterList.get(i)));
            action = new Actions(driver);
            action.moveToElement(inboxLetterList.get(i)).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(checkBoxList.get(i)));
            action.click(checkBoxList.get(i)).build().perform();
            i++;
        }
    }
}
