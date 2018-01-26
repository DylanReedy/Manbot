package it.learnandcode.tokenizer;

import java.util.ArrayList;
import java.util.List;

public interface InputTextTokenizer {
    Boolean isCommandCandidate(String input);
    ArrayList<String> tokenize(String input);
}
