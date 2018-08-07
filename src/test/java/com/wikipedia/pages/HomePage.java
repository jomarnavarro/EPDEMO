package com.wikipedia.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikipedia.utils.Locators;
import com.wikipedia.utils.Properties;

public class HomePage {
	public WebDriver driver;
	public JavascriptExecutor jse;
	public WebDriverWait wait;

	/**
	 * Constructs a new HomePage object, using the driver created in <class>skeleton.StepDefs</class>
	 * It also initializes its web elements by using the PageFactory method, pointing to all elements
	 * defined in this very class.
	 * @param driver WebDriver used to run the tests.
	 */	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Properties.LONG_WAIT);
		jse = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);

	}
	/* WebElement Section*/

	@FindBy(how = How.XPATH, using = Locators.WIKIPEDIA_LOGO_XPATH)
	WebElement wikiLogoImg;

	@FindBy(how = How.ID, using = Locators.SEARCH_BOX_ID)
	WebElement searchTxt;

	/* Automation method section */
	/**
	 * Verifies the Wikipedia Home Page is Loaded
	 * @returns true if the wiki Logo Image is displayed, false otherwise.
	 */
	public boolean isAt() {
		return wait.until(ExpectedConditions.visibilityOf(wikiLogoImg)).isDisplayed();
	}
	
	/**
	 * Searches for an artist by inputting the it into the top search box.
	 * @param article
	 */
	public void searchArtist(String artistName) {
		searchTxt.sendKeys(artistName);
		searchTxt.submit();
	}

}
