package com.demo.seleniumspring.config;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

@Configuration
public class WebDriverConfig {

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentTest test;

	// this is the bean class for edge driver
	@Bean
	@ConditionalOnProperty(name = "browser", havingValue = "edge")
	public WebDriver edgeDriver() {

		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}

	@Bean
	@Scope("browserscope") // use custom scope
	@ConditionalOnProperty(name = "browser", havingValue = "chrome")
	public WebDriver chromeDriver() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--headless");
		return new ChromeDriver();

	}

	@Bean
	public void loadLogPropertyFile() {

		DOMConfigurator.configure("log4j.xml");

	}

	

}
