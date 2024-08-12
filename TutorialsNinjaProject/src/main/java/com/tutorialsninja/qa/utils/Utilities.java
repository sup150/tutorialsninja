package com.tutorialsninja.qa.utils;

import java.util.Date;

public class Utilities {

	public static String generateEmailWithTimeStamp()
	{
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "amotoori"+timestamp+"@gmail.com";
	}
}
