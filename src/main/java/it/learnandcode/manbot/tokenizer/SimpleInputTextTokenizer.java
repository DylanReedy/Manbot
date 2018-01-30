package it.learnandcode.manbot.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleInputTextTokenizer implements InputTextTokenizer {

    Pattern pattern = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");

    /**
     *
     * @param input Command candidates first non-ws character must be a '!'
     * @return Returns false if it finds no command indicator, true otherwise
     */

    @Override
    public boolean isCommandCandidate(String input) {
        String shortstring = input.trim();
        if(input==null || shortstring.length() == 0){
            return false;
        }else{
            return shortstring.charAt(0) == '!';
        }
    }

    @Override
    public ArrayList<String> tokenize(String input) {
        ArrayList<String> tokenizedMessage = new ArrayList<>();
        Matcher regexMatcher;
        regexMatcher = pattern.matcher(input);
        while (regexMatcher.find())
        {
            if (regexMatcher.group(1) != null)
            {
                //DQ_Token
                tokenizedMessage.add(regexMatcher.group(1));
            }
            else if (regexMatcher.group(2) != null) {
                //SQ_Token
                tokenizedMessage.add(regexMatcher.group(2));
            }
            else {
                //Regular_Token
                tokenizedMessage.add(regexMatcher.group());
            }
        }
        return tokenizedMessage;
    }
}
