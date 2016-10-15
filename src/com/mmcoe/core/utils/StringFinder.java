package com.mmcoe.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringFinder {
    private File file;
    private String SearchStr;
    private int FirstOccuranceFlag = 0;
    private int[] occuranceMap = new int[3];

    public StringFinder() {
        // Constructor
    }

    public void findString(String query, String filePath) {
        file = new File(filePath);
        SearchStr = query;
        try {
            Scanner S = new Scanner(file);
            int lineNumber = 0, occurancesFound = 0, paraNumber = 1;
            if (FirstOccuranceFlag != 0) {
                lineNumber = occuranceMap[0];
                occurancesFound = occuranceMap[1];
                paraNumber = occuranceMap[2];
            }
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
                        occurancesFound += occurancesFoundInLine;

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

    public void getFirstOccurance(String query, String filePath, int PrintFlag) {
        FirstOccuranceFlag = 1;
        file = new File(filePath);
        SearchStr = query;
        int lineNumber = 0, occurancesFound = 0, paraNumber = 1;
        try {
            Scanner S = new Scanner(file);

            while (S.hasNextLine()) {
                lineNumber++;
                String line = S.nextLine();
                if (!isBlank(line)) {
                    if (line.toLowerCase().contains(SearchStr.toLowerCase())) {
                        int occurancesFoundInLine = getAllIndexesOfSubstringInString(
                                                        line, SearchStr);
                        occurancesFound += occurancesFoundInLine;
                        break;
                    }
                } else {
                    paraNumber++;
                }
            }
            S.close();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner Cannot Scan the file: " + filePath
                               + "\n\nLog:\n\n");
            e.printStackTrace();
        }

        occuranceMap[0] = lineNumber;
        occuranceMap[1]=occurancesFound;
        occuranceMap[2]=paraNumber;
        if(PrintFlag==0) {
            return;
        } else {
            System.out.println("\"" + SearchStr
                               + "\" Found on: Paragraph " + occuranceMap[2]
                               + ", line: " + occuranceMap[0] + ": "
                               + occuranceMap[1] + " Occurances");
        }

    }
}