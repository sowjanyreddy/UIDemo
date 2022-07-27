package com.demo.seleniumspring.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demo.seleniumspring.util.LogUtil;

@Configuration
public class ExtentReportManagerConfig {

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentTest test;

	@Lazy
	@Autowired
	private static LogUtil log;

	@Bean
	public static ExtentReports createExtentReport() {

		System.out.println("report bean loaded");
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport/" + "MyReport_" + ".html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("POC Test Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "SpringBoot-POC");
		extent.setSystemInfo("Tester", "Sowjanya");
		extent.setSystemInfo("OS", "Win10");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);

		return extent;

	}

	public static String getReportName() {

		Date d = new Date();
		String fileName = "AutomationReport_" + d.toString().replace(":", "-").replace(" ", "-") + ".html";
		return fileName;

	}

}
