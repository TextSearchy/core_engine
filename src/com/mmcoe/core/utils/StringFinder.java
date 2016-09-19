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
				if (S.nextLine().toLowerCase()
						.contains(SearchStr.toLowerCase())) {
					System.out.println("\"" + SearchStr + "\" Found on line: "
							+ lineNumber);
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
}
