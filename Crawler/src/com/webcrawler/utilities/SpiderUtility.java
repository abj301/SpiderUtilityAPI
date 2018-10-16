package com.webcrawler.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;


public class SpiderUtility {
	//GETS STRING WITHIN DELIMTERS
	public static String getStringWithinDelimiters(String pageSource,String startString,String endString)
	{
		if(pageSource == null  || pageSource.length() == 0)
			return "";

		if(startString == null || startString.length() == 0 || endString == null || endString.length() == 0)
			return "";
		
		int lengthofstartstring = startString.length();
		int startStringIndex = pageSource.indexOf(startString);
		int endStringIndex = pageSource.indexOf(endString);

		
		//System.out.println(s);
		if(endStringIndex < 0) {
			return "";
		}
		String s = pageSource.substring(startStringIndex+lengthofstartstring,endStringIndex);
		return s;

	}
//GETS PLAIN STRING WITHOUT HTML
	static String getPlainStringwithouthtml(String inputString)
	{
		String noHtml = inputString.toString().replaceAll("<[^>]*>", "").replaceAll("\t","").replaceAll("\r","");
		String Nospace = noHtml.trim();
		//System.out.println(Nospace);
		return Nospace;
	}
//GETS PLAIN STRING WITHIN DELIMITER WITHOUT HTML
	public static String getPlainStringWithinDelimiters(String pageSource,String startString, String endString) {

		String output = getStringWithinDelimiters(pageSource, startString, endString);
		output = getPlainStringwithouthtml(output);
		return output;

	}
//GETS ALL THE STRINGS BETWEEN DELIMITERS FROM A PAGE SOURCE AND RETURNS A LIST OF THEM
	public static List<String> getListwithinDelimiter(String pageSource,String startString, String endString){
		
		if(startString == null || startString.length() == 0 || endString == null || endString.length() == 0)
		return Collections.emptyList();	
		
		
		ArrayList<String> ls = new ArrayList<String>(); 
		Pattern pattern = Pattern.compile(startString);
		Matcher matcher = pattern.matcher(pageSource);
		
		while(matcher.find()) {
			
			int startindex = matcher.start();
			String s = pageSource.substring(startindex);
			String ss = getStringWithinDelimiters(s, startString, endString);
			
			ls.add(ss);
		}
		//System.out.println(ls);
			
		return ls;



	}
	//GETS PLAIN LIST WITHIN DELIMITER WITHOUT HTML
	
	public static List<String> getPlainListWithinDelimiters(String pageSource,String startString, String endString){
		
		List<String> ls = getListwithinDelimiter(pageSource, startString, endString);
		ls = ls.stream().map(element -> getPlainStringwithouthtml(element)).collect(Collectors.toList());
		System.out.println(ls);
		return ls;		
	}

	public static void main(String[] args) {
		getStringWithinDelimiters("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<p>This is a paragraph.</p>\r\n" + 
				"<p>This is another paragraph.</p>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>","" , "");
	}

}
