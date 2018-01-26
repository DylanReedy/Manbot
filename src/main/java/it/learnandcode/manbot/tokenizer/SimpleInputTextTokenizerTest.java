package it.learnandcode.manbot.tokenizer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleInputTextTokenizerTest {

    @Test
    public void dqTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first second \"third and fourth\"";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "second", "third and fourth"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void sqTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first second \'third and fourth\'";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "second", "third and fourth"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void sqNestedDQTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first second \'third \"and\" fourth\'";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "second", "third \"and\" fourth"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void dqNestedSQTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first second \"third \'and\' fourth\"";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "second", "third \'and\' fourth"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }


}
