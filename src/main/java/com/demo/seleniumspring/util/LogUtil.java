package com.demo.seleniumspring.util;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LogUtil {
	
	 
	 // Initialize Log4j logs
	 public static Logger Log = Logger.getLogger(LogUtil.class.getClass());//

	 public void startTestCase(String sTestCaseName){    
	   Log.info("====================================="+sTestCaseName+" TEST START=========================================");
	   }
	 
	 public void endTestCase(String sTestCaseName){
	   
	  Log.info("====================================="+sTestCaseName+" TEST END=========================================");
	   }
	 
	 // Need to create below methods, so that they can be called  

	  public void info(String message) {

	   Log.info(message);

	   }

	  public void warn(String message) {

	     Log.warn(message);

	  }

	  public void error(String message) {

	     Log.error(message);

	  }

	  public void fatal(String message) {

	     Log.fatal(message);

	  }

	  public void debug(String message) {

	     Log.debug(message);

	  }
	 
}


