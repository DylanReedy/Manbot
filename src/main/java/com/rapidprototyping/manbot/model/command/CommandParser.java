package com.rapidprototyping.manbot.model.command;

import java.util.ArrayList;

public interface CommandParser
{
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
