package com.example.android.SimpleCalcTestHw;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by DanielHuang on 2020/8/18
 */
@RunWith(JUnit4.class)
@SmallTest
public class CodingChallengeTest {

    private Calculator mCalculator;

    @Before
    public void setup() {
        mCalculator = new Calculator();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Challenge 1:
     * Dividing by zero is always worth testing for,
     * because it is a special case in arithmetic.
     * How might you change the app to more gracefully handle divide by zero?
     * To accomplish this challenge,
     * start with a test that shows what the right behavior should be.
     *
     * Remove the divTwoNumbersZero() method from CalculatorTest,
     * and add a new unit test called divByZeroThrows()
     * that tests the div() method with a second argument of zero,
     * with the expected result as IllegalArgumentException.class.
     * This test will pass,
     * and as a result it will demonstrate
     * that any division by zero will result in this exception.
     *
     * After you learn how to write code for an Exception handler,
     * your app can handle this exception gracefully by, for example,
     * displaying a Toast message to the user to change Operand 2 from zero to another number.
     *
     * 原本Challenge1的測試
     * 是要接住div()拋出的 IllegalArgumentException
     * 但是原本的production code並不會拋出此例外，
     * 所以我的解法是，修改production code，在除數為0時拋出IllegalArug的例外，
     * 然後再測試這裡去驗證是否有拋例外。
     * 這樣的寫法，之後在production code 也可以使用try catch來處理
     */
    @Test
    public void divByZeroThrows() {
        expectedException.expect(IllegalArgumentException.class);
        double resultDiv = mCalculator.div(32d, 0d);
    }
}
