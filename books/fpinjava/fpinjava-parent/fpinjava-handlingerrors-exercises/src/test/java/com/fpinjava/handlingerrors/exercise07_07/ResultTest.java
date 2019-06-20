package com.fpinjava.handlingerrors.exercise07_07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTest {

    Result<Integer> empty = Result.empty();
    Result<Integer> failure = Result.failure("failure message");
    Result<Integer> success = Result.success(4);

    @Test
    public void testMapFailureStringEmpty() {
        assertEquals("Empty()", empty.mapFailure("no data").toString());
    }

    @Test
    public void testMapFailureStringFailure() {
        assertEquals("Failure(no data)", failure.mapFailure("no data").toString());
    }

    @Test
    public void testMapFailureStringSuccess() {
        assertEquals("Success(4)", success.mapFailure("no data").toString());
    }
}
