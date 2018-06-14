package com.dice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dice_job_search {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver dr = new ChromeDriver();

		List<String> jobs = new ArrayList<>();
		FileReader file = new FileReader("jobsearch.txt");
		BufferedReader bf = new BufferedReader(file);

		String jobss = "";
		while ((jobss = bf.readLine()) != null) {
			jobs.add(jobss);
		}

		dr.switchTo();
		dr.manage().window().fullscreen();
		dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		dr.get("http://dice.com");
		for (int i = 0; i < 2; i++) {
			dr.findElement(By.id("search-field-location")).clear();
			dr.findElement(By.id("search-field-location")).sendKeys("20707");
			dr.findElement(By.id("search-field-keyword")).clear();
			dr.findElement(By.id("search-field-keyword")).sendKeys(jobs.get(i));

			dr.findElement(By.id("findTechJobs")).click();
			if (dr.getTitle().contains("jobs")) {
				System.out.println("working well");
			} else {
				System.out.println("failed");
				throw new RuntimeException("dice page didnt loaded successfully");
			}
			String a = dr.findElement(By.id("posiCountId")).getText();
			
			System.out.println("Job Title : "+jobs.get(i)+ " available positions : "+ a);
			
			System.out.println("test complete" + LocalDateTime.now());

//			String count = dr.findElement(By.xpath("//*[@id=\"posiCountMobileId\"]")).toString();
//			System.out.println(count);
			Thread.sleep(1000);
			dr.navigate().back();
		}
		bf.close();
//		dr.close();
		dr.quit();
	}
}
