package com.jbk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

    public static void main(String[] args) {
        String text = "pen1";

        String regex = ".*\\d.*";  // regex to check if string contains any numbers
        Pattern pattern = Pattern.compile(regex);  // compiles the regex

        // find match between given string and pattern
        Matcher matcherText = pattern.matcher(text);

        // return true if the string matched the regex
        Boolean textMatches = matcherText.matches();

        System.out.println(textMatches);  // true
    }
}