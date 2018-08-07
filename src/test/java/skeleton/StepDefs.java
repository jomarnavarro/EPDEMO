package skeleton;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wikipedia.pages.ArticlePage;
import com.wikipedia.pages.HomePage;
import com.wikipedia.utils.Properties;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	
	protected WebDriver driver;
	protected HomePage home;
	protected ArticlePage article;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://www.wikipedia.org/");
		driver.manage().timeouts().implicitlyWait(Properties.MEDIUM_WAIT, TimeUnit.SECONDS);
		home = new HomePage(driver);
		article = new ArticlePage(driver);
	}
	
	@After
	public void tearDown() {
		driver.quit();
		home = null;
		article = null;
	}

	@Given("The Wikipedia home page is loaded")
	public void the_Wikipedia_home_page_is_loaded() throws Throwable {
		assertTrue(home.isAt());
			
	}

	@Given("User searches for {string}")
	public void user_searches_for_Taylor_Swift(String artistName) throws Throwable {
		home.searchArtist(artistName);
	}

	@When("User navigates to Studio Albums under External links")
	public void user_navigates_to_Studio_Albums_under_External_links() throws Throwable {
		article.navigateToExternalLinks();
	}

	@Then("Studio Albums section contains the following albums")
	public void studio_Albums_section_contains_the_following_albums(List<String> albumList) throws Throwable {
		article.validateAlbums(albumList);
	}

	@When("User hovers over {string} link")
	public void user_hovers_over_Reputation_link(String albumName) throws Throwable {
		article.hoverOverAlbumLink(albumName);
	}

	@Then("Hover message {string} appears")
	public void hover_message_long_message_appears(String message) throws Throwable {
		article.validateHoverMessagePresent(message);
	}
}
