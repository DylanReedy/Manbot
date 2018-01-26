package it.learnandcode.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleInputTextTokenizer implements InputTextTokenizer {


    @Override
    public Boolean isCommandCandidate(String input) {
        String firstString = tokenize(input).get(0);
        return firstString.charAt(0) == '!';
    }

    @Override
    public ArrayList<String> tokenize(String input) {
        ArrayList<String> tokenizedMessage = new ArrayList<>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(input);
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
}
