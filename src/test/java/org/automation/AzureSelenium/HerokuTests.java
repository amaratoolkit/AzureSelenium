package org.automation.AzureSelenium;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class HerokuTests extends BaseTest {

	@Test
	public void verifyTitleOfTheApplication() {

		System.out.println("Get Title ============ " + driver.getTitle());
		String title = driver.getTitle();

		AssertJUnit.assertEquals(title, "The Internet");

	}

	@Test
	public void verifyURLOfTheApplication() {
		System.out.println("Get URL of the Application ======= " + driver.getCurrentUrl());
		String url = driver.getCurrentUrl();
		AssertJUnit.assertEquals(url, "http://the-internet.herokuapp.co/");
	}


	@Test
	public void dropDwown() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(normalize-space(),'Dropdown')]")).click();
		Select select = new Select(driver.findElement(By.id("dropdown")));
		select.selectByIndex(1);
		select.selectByVisibleText("Option 2");
		select.selectByValue("1");

		System.out.println("value========" + select.getFirstSelectedOption().getText());
		
		Thread.sleep(2000);
		driver.navigate().back();
	}

	@Test
	public void sliderTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(normalize-space(),'Horizontal Slider')]")).click();
		WebElement slider = driver.findElement(By.xpath("//input[@value='0']"));
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(slider, 0, 100).build();
		action.perform();
		
		Thread.sleep(2000);
		driver.navigate().back();
	}

	 @Test
	public void mouseHoverAndClick() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(normalize-space(),'Hovers')]")).click();

		WebElement hoverElement = driver.findElement(By.cssSelector("div.example>div:first-of-type"));
		WebElement clickElement = driver.findElement(By.cssSelector(".figcaption>h5+a[href$='1']"));

		Actions actions = new Actions(driver);
		actions.moveToElement(hoverElement).perform();
		actions.moveToElement(clickElement).click().perform();

		driver.findElement(By.xpath("//h1[contains(normalize-space(),'Not Found')]")).isDisplayed();

		System.out.println("Pause");
		Thread.sleep(2000);
		driver.navigate().back();
		driver.navigate().back();

	}



	@Test
	public void alerts() throws InterruptedException {
		driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");
		
		driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();

		Alert alert = driver.switchTo().alert();
		alert.accept();
		

		driver.findElement(By.cssSelector("button[onclick='jsConfirm()']")).click();
		alert.dismiss();
		

		driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();
		alert.sendKeys("Testing Prompt");
		alert.accept();
		
		Thread.sleep(2000);
		driver.navigate().back();
	}
}
