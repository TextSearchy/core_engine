package com.mmcoe.core;

import java.util.Arrays;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.mmcoe.core.utils.StringFinder;

public class Engine {

    private static String WHY = "WHY";
    private static String WHICH = "WHICH";
    private static String WHAT = "WHAT";
    private static String WHEN = "WHEN";
    private static String HOW = "HOW";
    private static String WHO = "WHO";
    private static String[] QTYPE = {WHY,WHICH,WHAT,WHEN,HOW,WHO};
    public static void main(String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("s", true, "Search String");
        options.addOption("f", true, "File Name");
        options.addOption("q", true, "Question");
        CommandLine commandLine = parser.parse(options, args);
        String QUESTION_TYPE = getOption('q', commandLine);
        String SEARCH_STRING = getOption('s', commandLine);
        String[] FILE_NAME = commandLine.getArgs();
        StringFinder SF = new StringFinder();
        if (QUESTION_TYPE != null) {
            QUESTION_TYPE = QUESTION_TYPE.toUpperCase();
            if(Arrays.asList(QTYPE).contains(QUESTION_TYPE)) {
                for (int i = 0; i < FILE_NAME.length; i++) {
                    SF.getFirstOccurance(SEARCH_STRING, FILE_NAME[i], 1);
                }
            }

        } else {
            for (int i = 1; i < FILE_NAME.length; i++) {
                SF.findString(SEARCH_STRING, FILE_NAME[i]);
            }
        }

    }

    public static String getOption(final char option,
                                   final CommandLine commandLine) {

        if (commandLine.hasOption(option)) {
            return commandLine.getOptionValue(option);
        }

        return null;
    }

}
