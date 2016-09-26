package com.mmcoe.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringFinder {
	private File file;
	private String SearchStr;

	public StringFinder() {
		// Constructor
	}

	public void findString(String query, String filePath) {
		file = new File(filePath);
		SearchStr = query;
		try {
			Scanner S = new Scanner(file);
			int lineNumber = 0, occurancesFound = 0, paraNumber = 1;
			while (S.hasNextLine()) {
				lineNumber++;
				String line = S.nextLine();
				if (!isBlank(line)) {
					if (line.toLowerCase().contains(SearchStr.toLowerCase())) {
						int occurancesFoundInLine = getAllIndexesOfSubstringInString(
								line, SearchStr);
						System.out.println("\"" + SearchStr
								+ "\" Found on: Paragraph " + paraNumber
								+ ", line: " + lineNumber + ": "
								+ occurancesFoundInLine + " Occurances");
						occurancesFound +=occurancesFoundInLine;

					}
				} else {
					paraNumber++;
				}
			}
			if (occurancesFound == 0) {
				System.out.println("\"" + SearchStr + "\" Not Found");
			} else {
				System.out
						.println(occurancesFound + " Total Occurances Found.");
			}
			S.close();
		} catch (FileNotFoundException e) {
			System.out.println("Scanner Cannot Scan the file: " + filePath
					+ "\n\nLog:\n\n");
			e.printStackTrace();
		}
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	private static int getAllIndexesOfSubstringInString(String fullString,
			String substring) {
		int atIndex = 0;
		int count = 0;
		while (atIndex != -1) {
			atIndex = fullString.indexOf(substring, atIndex);

			if (atIndex != -1) {
				count++;
				atIndex += substring.length();
			}
		}
		return count;
	}
}