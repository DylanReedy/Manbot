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

    @Test
    public void unpairedQuoteTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first second \"third and fourth";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "second", "third", "and", "fourth"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void preWSTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "   !first and second";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "and", "second"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void postWSTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first and second   ";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "and", "second"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void midWSTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "!first    and    second   ";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("!first", "and", "second"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(true, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void blankTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = " ";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList());
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(false, tokenizer.isCommandCandidate(testString));
    }

    @Test
    public void strangeCharTest(){
        SimpleInputTextTokenizer tokenizer = new SimpleInputTextTokenizer();
        String testString = "first !second third";
        ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList("first", "!second", "third"));
        Assert.assertArrayEquals(expectedList.toArray(),tokenizer.tokenize(testString).toArray());
        Assert.assertEquals(false, tokenizer.isCommandCandidate(testString));
    }

}
