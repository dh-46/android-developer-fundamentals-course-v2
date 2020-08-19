package com.example.android.SimpleCalcTestHw;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by DanielHuang on 2020/8/19
 */
@RunWith(JUnit4.class)
@SmallTest
public class HomeworkTest {

    private Calculator mCalculator;

    @Before
    public void setup() {
        mCalculator = new Calculator();
    }

    /**
     * A test with positive integer operands.
     */
    @Test
    public void powPositiveInt(){
        double resultPow = mCalculator.pow(2,3);
        Assert.assertThat(resultPow, is(equalTo(8d)));
    }

    /**
     * A test with a negative integer as the first operand.
     */
    @Test
    public void powNegativeFirstOperand() {
        double resultPow = mCalculator.pow(-2,3);
        Assert.assertThat(resultPow, is(equalTo(-8d)));
    }

    /**
     * A test with a negative integer as the second operand.
     */
    @Test
    public void powNegativeSecondOperand() {
        double resultPow = mCalculator.pow(2,-3);
        Assert.assertThat(resultPow, is(equalTo(0.125)));
    }

    /**
     * A test with 0 as the first operand and a positive integer as the second operand.
     */
    @Test
    public void powZeroAndPositive(){
        double resultPow = mCalculator.pow(0,2);
        Assert.assertThat(resultPow, is(equalTo(0d)));
    }

    /**
     * A test with 0 as the second operand.
     */
    @Test
    public void powZeroSecond() {
        double resultPow = mCalculator.pow(2,0);
        Assert.assertThat(resultPow, is(equalTo(1d)));
    }

    /**
     * A test with 0 as the first operand and -1 as the second operand.
     */
    @Test
    public void powZeroFirstAndNegativeSecond() {
        double resultPow = mCalculator.pow(0, -1);
        Assert.assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    /**
     * A test with -0 as the first operand and any negative number as the second operand.
     */
    @Test
    public void powNegativeZeroAndNegativeOperand() {
        double resultPow = mCalculator.pow(-0, -2);
        Assert.assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }
}
