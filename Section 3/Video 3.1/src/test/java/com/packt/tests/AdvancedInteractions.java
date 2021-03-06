package com.packt.tests;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.packt.base.BaseTest;

public class AdvancedInteractions extends BaseTest {

	@Test
	public void newWindowTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/windows");
		System.out.println("Page opened!");

		sleep(3000);

		// maximize window
		driver.manage().window().maximize();

		sleep(1000);

		// Clicking link
		WebElement link = driver.findElement(By.linkText("Click Here"));
		link.click();

		sleep(2000);

		// Print page tytle and url after link click
		System.out.println("Page title after click: " + driver.getTitle());
		System.out.println("Page url after click: " + driver.getCurrentUrl());

		// Switching to new window
		String firstWindow = driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();

		while (windowsIterator.hasNext()) {
			String windowHandle = windowsIterator.next().toString();
			if (!windowHandle.equals(firstWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		// Print page title and url after switch to new window
		System.out.println("Page title after switch: " + driver.getTitle());
		System.out.println("Page url after switch: " + driver.getCurrentUrl());
	}


	@Test
	public void iframeTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/iframe");
		System.out.println("Page opened!");

		sleep(3000);

		// maximize window
		driver.manage().window().maximize();

		// Switching to iFrame
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);

		sleep(3000);

		// Typing text
		WebElement textArea = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
		textArea.clear();

		// Regular sendKeys will not work on wysiwyg-editor
		// We can use Actions class or JavascriptExecutor
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].innerHTML = 'Lets type our text into wysiwyg-editor'", textArea);

		sleep(5000);

		System.out.println("The end of text");
	}


	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
