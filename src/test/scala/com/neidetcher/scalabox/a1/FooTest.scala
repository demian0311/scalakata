package com.neidetcher.scalabox.a1

import org.junit.Test
import org.junit.Assert._

class JavaLikeStatementsAndExpressions {

    @Test
    def java_like_statements() {
        val x = 10

        // flow control
        if (x != 10) {
            fail("should not be here")
        }

        // for loop
        for (i <- 0 until x) {
            println("for: " + i)
        }

        // while loop
        var counter = 0
        while (counter < x) {
            counter = counter + 1
            println("while: " + counter)
        }
    }
}