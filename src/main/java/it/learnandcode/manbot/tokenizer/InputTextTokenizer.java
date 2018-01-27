package it.learnandcode.manbot.tokenizer;

import java.util.ArrayList;
import java.util.List;

public interface InputTextTokenizer {
    boolean isCommandCandidate(String input);
    ArrayList<String> tokenize(String input);
}
