package com.wikipedia.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikipedia.utils.Locators;
import com.wikipedia.utils.Properties;

public class ArticlePage {
	public WebDriver driver;
	public JavascriptExecutor jse;
	public WebDriverWait wait;

	/**
	 * Constructs a new HomePage object, using the driver created in <class>tests.BaseTest</class>
	 * It also initializes its web elements by using the PageFactory method, pointing to all elements
	 * defined in this very class.
	 * @param driver WebDriver used to run the tests.
	 */	
	public ArticlePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Properties.LONG_WAIT);
		jse = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);

	}
	/* WebElement Section*/

	@FindBy(how = How.ID, using = Locators.ARTICLE_HEADER_ID)
	WebElement articleHdr;

	@FindBy(how = How.ID, using = Locators.EXTERNAL_ARTICLES_HEADER_ID)
	WebElement externalArticlesHdr;
	
	@FindBy(how = How.XPATH, using = Locators.STUDIO_ALBUMS_VALUE_XPATH)
	WebElement studioAlbumsValue;

	/* Automation method section */
	/**
	 * Navigates to 'External Links' section.
	 */
	public void navigateToExternalLinks() {
		jse.executeScript("arguments[0].scrollIntoView(true);", externalArticlesHdr);
	}
	
	/**
	 * Validates an artist's albums according to  by inputting the it into the top search box.
	 * @param article
	 */
	public void validateAlbums(List<String> albumList) {
		for(String albumName: albumList) {
			assertTrue(this.studioAlbumsValue.getText().contains(albumName));
		}
	}
	
	/**
	 * Navigates to 'External links' section, and hovers over a given albumName
	 * @param albumName
	 */
	public void hoverOverAlbumLink(String albumName) {
		navigateToExternalLinks();
		WebElement albumNameLink = this.studioAlbumsValue.findElement(By.linkText(albumName));
		Actions builder = new Actions(driver);
		builder.moveToElement(albumNameLink).perform();
	}
	
	/**
	 * Validates hover message contains a given message as a substring.
	 * @param message
	 */
	public void validateHoverMessagePresent(String message) {
		String hoverMsgXpath = "//*[@id='mwe-popups-svg']/following-sibling::div[contains(@class, 'mwe-popups')]";
		WebElement albumElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(hoverMsgXpath)));
		assertTrue(albumElement.isDisplayed());
		System.out.println(albumElement.getText());
		assertTrue(albumElement.getText().contains(message));
	}
}
