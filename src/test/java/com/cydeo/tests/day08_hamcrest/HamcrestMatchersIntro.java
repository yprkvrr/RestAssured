package com.cydeo.tests.day08_hamcrest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersIntro {
    @Test
    public void numbersTest() {
        assertThat(1 + 3, is(4));
        assertThat(5 + 5, equalTo(10));
        assertThat(10 + 5, is(equalTo(15)));
        assertThat(100 + 4, is(greaterThan(99)));
        int num = 10 + 2;
        assertThat(num, is(not(10)));
        assertThat(num, is(not(equalTo(9))));
    }

    @Test
    public void stringTest() {
        String word = "wooden spoon";
        assertThat(word, is("wooden spoon"));
        assertThat(word, equalTo("wooden spoon"));

        //startsWith/endsWith
        assertThat(word, startsWith("wood"));
        assertThat(word, startsWithIgnoringCase("WOOD"));
        assertThat("endsWith 'n'", word, endsWith("n"));

        //contains
        assertThat(word, containsString("den"));
        assertThat(word, containsStringIgnoringCase("SPOON"));

        //empty string
        String str = " ";
        assertThat(str, is(blankString()));
        assertThat(str.replace(" ",""), is(emptyOrNullString()));
        assertThat(str.trim(), is(emptyOrNullString()));

    }

    @Test
    public void listTest() {
        List<Integer> nums= Arrays.asList(5,4,20,54,1);
        List<String> words=Arrays.asList("java","selenium","git");

        assertThat(nums,hasSize(5));
        assertThat(words,hasSize(3));

        //contains
        assertThat(nums,hasItem(5));
        assertThat(words,hasItems("java","git"));
        assertThat(nums,containsInAnyOrder(4,20,1,5,54));
        //every item
        assertThat(nums,everyItem(greaterThanOrEqualTo(0)));
        assertThat(words,everyItem(not(blankString())));
    }
}
