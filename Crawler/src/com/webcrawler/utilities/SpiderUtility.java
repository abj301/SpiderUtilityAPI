package com.webcrawler.utilities;;

public class SpiderUtility {
	public static String getStringWithinDelimiters(String pageSource,String startString,String endString)
	{
		if(pageSource == null  || pageSource.length() == 0)
			return "";

		if(startString == null || startString.length() == 0 || endString == null || endString.length() == 0)
			return "";
		int lengthofstartstring = startString.length();
		int startStringIndex = pageSource.indexOf(startString);
		int endStringIndex = pageSource.indexOf(endString);

		String s = pageSource.substring(startStringIndex+lengthofstartstring,endStringIndex);
		//System.out.println(s);
		return s;

	}

	static String getPlainStringwithouthtml(String inputString)
	{
		String noHtml = inputString.toString().replaceAll("<[^>]*>", "").replaceAll("\t","").replaceAll("\r","");
		String Nospace = noHtml.trim();
		System.out.println(Nospace);
		return Nospace;
	}
	
	public static String getPlainStringWithinDelimiters(String pageSource,String startString, String endString) {
		
		String output = getStringWithinDelimiters(pageSource, startString, endString);
		output = getPlainStringwithouthtml(output);
		return output;
		
	}
	
	
public static void main(String[] args) {
	String inputString = getStringWithinDelimiters("p> </p>\r\n" + 
			"  \r\n  " + 
			"<div class=\"question\">\r\n" + 
			"<h3>Questions 27–31</h3>\r\n" + 
			"\r\n" + 
			"<p>Reading passage 3 has six paragraphs, A–F.</p>\r\n" + 
			"\r\n" + 
			"<p>Which paragraph contains the following information?</p>\r\n" + 
			"\r\n" + 
			"<p> </p>\r\n" + 
			"\r\n" + 
			"<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td width=\"800 px\">\r\n" + 
			"			<h3>Questions</h3>\r\n" + 
			"			</td>\r\n" + 
			"		</tr><tr><td>27) a use for helium which makes an activity safer</td>\r\n" + 
			"		</tr><tr><td>28) the possibility of creating an alternative to helium</td>\r\n" + 
			"		</tr><tr><td>29) a term which describes the process of how helium is taken out of the ground</td>\r\n" + 
			"		</tr><tr><td>30) a reason why users of helium do not make efforts to conserve it</td>\r\n" + 
			"		</tr><tr><td>31) a contrast between helium’s chemical properties and how non-scientists think about it</td>\r\n" + 
			"		</tr></tbody></table></div>\r\n" + 
			"\r\n" + 
			"<p> </p>","Reading","why");	
	//getPlainString(inputString);

	System.out.println(inputString); 
	}
	
}
