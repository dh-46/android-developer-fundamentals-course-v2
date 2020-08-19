/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalcTest;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 *
 * - imports: 可以注意到上面沒有使用到任何 Android framework
 * - test runner:
 *      - 是一種函式庫或是許多方法的集合，能夠讓測試執行或是印出結果。
 *      - 比較複雜的測試或是有其他的需求，可能使用Espresso之類的 test runner
 * - SmallTest:
 *      - 指出這個類別中的所有測試都是單元測試 (no dependencies, and run in milliseconds)
 *      - @SmallTest, @MediumTest, and @LargeTest 都是註解的慣例
 *      - 方便將測試根據功能項群組化
 * - 單元測試
 *      - 測試production code中的一個類別或方法
 *      - 通常會用很多不同的輸入去測試方法，並驗證各個輸出都是符合期待的。
 */
@RunWith(JUnit4.class) // 指出這個類別中的測試會用哪個runner跑。
@SmallTest // 指出這個類別中的所有測試都是單元測試
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     * - 建構測試所需的環境
     * - 會有@Before這個註解
     */
    @Before
    public void setUp() {
        // 建立Calculator物件實體
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     * - 實際的測試方法
     * - 用@Test註解標明，只有這樣才會被test runner視為是測試的方法。
     * - 根據慣例，測試方法的命名裡不用加 test
     * - 只有 `public` 、`package-protected` 的方法可以被測試
     * - Assertions:
     *      - 一定要做的運算式，且結果為true才代表測試通過
     */
    @Test
    public void addTwoNumbers() {
        // 呼叫Calculator物件的add方法
        // 只有 `public` 、`package-protected` 的方法可以被測試
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    /**
     * Task 2: Add more unit tests to CalculatorTest
     *
     * - 一般來說難以測試 add() 所有可能的輸入值，所以我們反向來測試比較奇怪的值
     *      1. 輸入值帶有負數的運算數
     *      2. 輸入值為浮點數
     *      3. 異常大的數值
     *      4. 運算數型態不同 (e.g. float, double)
     *      5. 其中一個運算數為零
     *      6. 運算數為無限大
     */

    /**
     * 輸入值帶有負數的運算數
     *
     * 不把這個跟上面的 addTwoNumbers() 寫在一起，是為了方便測試與除錯。
     * 一般來說，單一的assertion應該對應單一測試方法
     */
    @Test
    public void addTwoNumbersNegative() {
        // d 代表其值為double型別
        // 因為 add() 的參數型別為double，所以這裡不加其實也可以，float/int都會過
        // 但是為了測試的區別性，依然加上double型別
        double resultAdd = mCalculator.add(-1d, 2d);

        /**
         * assertThat()
         * - JUnit4 的 assertion方法
         * - 前一個參數要與後一個參數相等
         * - 舊版 JUnit 通常會使用以下三種
         *      1. assertEquals()
         *      2. assertNull()
         *      3. assertTrue()
         * - assertThat() 相對舊版方法較彈性、易除錯、易閱讀
         * - 與 matchers 搭配使用
         *
         * Matchers
         * - 來自 Hamcrest framework (Hamcrest 其實是 matchers的重組字XD)
         * - Hamcrest framework 提供許多基本款的matchers
         */
        assertThat(resultAdd, is(equalTo(1d)));
    }

    /**
     * 不同的型態相加
     */
    @Test
    public void addTwoNumbersFloats() {
        double resultAdd = mCalculator.add(1.111f, 1.111d);
        // assertThat(resultAdd, is(equalTo(2.222d)));

        /**
         * 結果:
         * java.lang.AssertionError:
         * Expected: is <2.222>
         *      but: was <2.2219999418258665>
         *
         * 原因:
         * - 浮點數的運算本來就是不精確的
         * - the promotion resulted in a side effect of additional precision
         *
         * 討論:
         * When you have a precision problem with promoting float arguments,
         * is that a problem with your code, or a problem with your test?
         * - 以這個APP的設計來說，兩個參數值不太可能會是不同的型別，所以這個測試有點不太實際。
         * - 但又如果APP是允許double與float同時存在，那可能要考慮的是，是否需要非常精確的運算。
         * - 若不需要精確的運算，則可使用closeTo() 這個方法。
         * org.hamcrest.number.IsCloseTo.closeTo(目標值, 加減buffer)
         */
        assertThat(resultAdd, is(closeTo(2.222d, 0.01)));
    }

    // **********2.2 Add unit tests for the other calculation methods***************

    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.sub(1d, 1d);
        assertThat(resultSub, is(equalTo(0d)));
    }

    @Test
    public void subWorksWithNegativeResults() {
        double resultSub = mCalculator.sub(1d, 17d);
        assertThat(resultSub, is(equalTo(-16d)));
    }

    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(32d, 2d);
        assertThat(resultMul, is(equalTo(64d)));
    }

    @Test
    public void mulTwoNumbersZero() {
        double resultMul = mCalculator.mul(32d, 0d);
        assertThat(resultMul, is(equalTo(0d)));
    }

    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(32d, 2d);
        assertThat(resultDiv, is(equalTo(16d)));
    }

    /**
     * with a double dividend(被除數) and zero as the divider.
     */
    @Test
    public void divTwoNumbersZero() {
        double resultDiv = mCalculator.div(32d, 0d);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }
}