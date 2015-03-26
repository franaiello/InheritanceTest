package com.aiello.oop.exercise;


import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The purpose of this class is to exercise a person's ability to comprehend inheritance.
 *
 *                  A
 *                  |
 *                  |
 *                  B
 *                 /\
 *                /  \
 *               C    D
 *  Below are the following questions to ask candidate and provide results.
 *
 *  1) B u = new C();
 *  2) D v = (D)u;
 *  3) C w = (C)u;
 *  4) D x = (D)(new A());
 *  5) A x = (A)(new D());
 *  6) D y = new D();
 *  7) C z = (C)y;
 */
public class InheritanceTest {

    @Test
    public void firstQuestion() {
        B u = new C();
        assertThat(u).isInstanceOf(C.class);
    }

    @Test(
            expectedExceptions = ClassCastException.class,
            dependsOnMethods = "firstQuestion"
    )
    public void secondQuestion() {
        B u = new C();
        D v = (D)u;
        assertThat(v).isSameAs(D.class).isSameAs(C.class);
    }


    @Test (
            dependsOnMethods = "secondQuestion"
    )
    public void thirdQuestion() {
        B u = new C();
        C w = (C)u;
        assertThat(w).isSameAs(u);
        assertThat(w).isInstanceOf(C.class);
    }

    @Test(
            expectedExceptions = ClassCastException.class,
            dependsOnMethods = "thirdQuestion"
    )
    public void fourthQuestion() {
        D x = (D)(new A());
    }

    @Test (
            dependsOnMethods = "fourthQuestion"
    )
    public void fifthQuestion() {
        A x = (A)(new D());
        assertThat(x).isInstanceOf(D.class);
    }

    @Test
    public void sixthQuestion() {
        D y = new D();
        assertThat(y).isInstanceOf(D.class);
    }

    @Test(
            enabled = false
    )
    public void seventhQuestion() {
        D y = new D();
        // Error:(79, 19) java: incompatible types: com.aiello.oop.exercise.D
        // cannot be converted to com.aiello.oop.exercise.C
        //C z = (C) y;
        assertThat(y).isSameAs(D.class);
    }
}
