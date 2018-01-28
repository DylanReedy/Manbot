package it.learnandcode.manbot.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleInputTextTokenizer implements InputTextTokenizer {

    Pattern pattern = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");

    /**
     *
     * @param input must have at least one non-whitespace character
     * @return determines whether or not the lead character of the input is a '!' character or not
     */

    @Override
    public boolean isCommandCandidate(String input) {
        if(input==null || input.trim().length() == 0){
            return false;
        }else{
            return input.trim().charAt(0) == '!';
        }
    }

    @Override
    public ArrayList<String> tokenize(String input) {
        ArrayList<String> tokenizedMessage = new ArrayList<>();
        Matcher regexMatcher;
        regexMatcher = pattern.matcher(input);
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
