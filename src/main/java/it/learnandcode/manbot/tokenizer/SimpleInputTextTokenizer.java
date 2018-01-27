package it.learnandcode.manbot.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleInputTextTokenizer implements InputTextTokenizer {

    ArrayList<String> tokenizedMessage = new ArrayList<>();
    Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
    Matcher regexMatcher;
    @Override
    public boolean isCommandCandidate(String input) {
        String shortString = input.trim();
        return shortString.charAt(0) == '!';
    }

    @Override
    public ArrayList<String> tokenize(String input) {
        if(isCommandCandidate(input)){
        regexMatcher = regex.matcher(input);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // DQ_TOKEN
                tokenizedMessage.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // SQ_TOKEN
                tokenizedMessage.add(regexMatcher.group(2));
            } else {
                // SIMPLE_TOKEN
                tokenizedMessage.add(regexMatcher.group());
            }
        }
            return tokenizedMessage;
        }
        else
            return null; //consider throwing an error message, or returning a particular value when the input is not a command
    }
}
