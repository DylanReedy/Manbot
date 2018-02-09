package com.rapidprototyping.manbot.controller;

import java.util.ArrayList;

public interface InputTextTokenizer {
    /**
     * @param input Any non-null string
     * @return Returns a list of whitespace separated tokens.
     */
    boolean isCommandCandidate(String input);
    /**
     * @param input Any non-null string
     * @return Returns false if it finds no command indicator, true otherwise
     */
    ArrayList<String> tokenize(String input);
}
