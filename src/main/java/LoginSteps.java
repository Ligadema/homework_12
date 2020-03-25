import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    private String mainURL = "http://mail.ru";
    private String inboxURL = "https://e.mail.ru/inbox/?back=1&afterReload=1";
    private String nameLogin = "mymailboxgo";
    private String namePassword = "asd97654321";
    private WebDriver webDriver;
    private LoginPage loginPage;

    public LoginSteps() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Programs\\Setups\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
    }

    @Given("^I am on a main application page$")
    public void loadMainPage() {
        webDriver.get(mainURL);
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() {
        loginPage.setLoginAndPassword(nameLogin, namePassword);
    }

    @Then("I see user e-mail link")
    public void seeLogoutLink() {
        Assert.assertTrue(loginPage.logoutLinkPresents());
    }

    @Given("^I am on a inbox application page$")
    public void loadInboxPage(){
        loginPage.navigateToInboxPage();
    }

    @When("^I select \"([^\"]*)\" letters$")
    public void selectInboxLetters(int number){

        loginPage.labelsOfLettersClicker(number);
    }

    @Then("^I see selected letters$")
    public void iSeeSelectedLetters() {
        System.out.println("OK"); }
}
