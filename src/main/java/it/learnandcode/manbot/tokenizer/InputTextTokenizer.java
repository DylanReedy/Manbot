package it.learnandcode.manbot.tokenizer;

import java.util.ArrayList;
import java.util.List;


public interface InputTextTokenizer {
    /**
     * @input
     * @return Returns a list of whitespace separated tokens.
     */
    boolean isCommandCandidate(String input);
    /**
     * @input
     * @return Returns false if it finds no command indicator, true otherwise
     */
    ArrayList<String> tokenize(String input);
}
