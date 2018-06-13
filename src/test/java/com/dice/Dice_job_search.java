package com.dice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dice_job_search {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"/Users/Tarik/Documents/ selenium dependencies/drivers/chromedriver");

		WebDriver dr = new ChromeDriver();
		dr.switchTo();
		dr.manage().window().fullscreen();
		dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		dr.get("http://dice.com");
		dr.findElement(By.id("search-field-location")).clear();
		dr.findElement(By.id("search-field-location")).sendKeys("20707");
		dr.findElement(By.id("search-field-keyword")).clear();
		dr.findElement(By.id("search-field-keyword")).sendKeys("find tech jobs");
		
		
		dr.findElement(By.id("findTechJobs")).click();
		if (dr.getTitle().contains("find tech jobs jobs in 20707") ) {
			System.out.println("working well");
		} else {
			System.out.println("failed");
			throw new RuntimeException("dice page didnt loaded successfully");
		}
		String a=dr.findElement(By.id("posiCountMobileId")).getText();
		System.out.println(a);
		
		System.out.println("test complete"+ LocalDateTime.now());
		Thread.sleep(2000);
		dr.close();
	}
}
