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
			int lineNumber = 0;
			while (S.hasNextLine()) {
				lineNumber++;
				String line = S.nextLine();
				if (!isBlank(line)) {
					if (line.toLowerCase().contains(SearchStr.toLowerCase())) {
						System.out.println("\"" + SearchStr
								+ "\" Found on line: " + lineNumber);
					}
				}
			}
			System.out.println("\"" + SearchStr + "\" Not Found");
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
}