package com.neidetcher.scalabox.sfti.chapter1

import org.junit.Test
import org.junit.Assert._

class TheBasics {

    @Test
    def One_1() {
        val b = 8 * 5 + 2
        assertEquals(42, b)

        val expected: Double = 21
        // TODO: not sure what's wrong here
        // assertEquals(expected, 0.5 * b)

        val result = "Hello, " + b
        assertEquals("Hello, 42", result)

        assertEquals("hello, 42", result.toLowerCase())
        assertEquals("HELLO, 42", result.toUpperCase())
    }

    @Test
    def One_2 {
        val answer = 8 * 5 + 2
        assertEquals(42, answer)

        var counter = 0
        counter = 1
        assertEquals(1, counter)

        val greeting1: String = null
        val greeting2: Any = "Hello"

        val xmax, ymax = 100
        assertEquals(xmax, ymax)

        var greeting, message: String = null
        assertNull(greeting)
        assertNull(message)
    }

    @Test
    def One_3 {
        assertEquals("1", 1.toString)

        // TODO: why doesn't this work?
        //assertEquals(Range(1, 2, 3), 1.to(3))

        assertEquals("lo", "Hello".intersect("World")) // yields "lo"
    }

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