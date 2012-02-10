package com.neidetcher.scalabox.sfti.chapter1

import org.junit.Test
import org.junit.Assert._

class TheBasics {

    @Test
    def one_1() {
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
    def one_2 {
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
    def one_3 {
        assertEquals("1", 1.toString)

        // TODO: why doesn't this work?
        //assertEquals(Range(1, 2, 3), 1.to(3))

        assertEquals("lo", "Hello".intersect("World")) // yields "lo"

        // scala gives more string stuff in StringOps
        // http://www.scala-lang.org/api/current/scala/collection/immutable/StringOps.html

        // this magic is from RichInt
        // http://www.scala-lang.org/api/current/index.html#scala.runtime.RichInt
        1.to(5)

        assertEquals(99, 99.44.toInt)
        assertEquals('c', 99.toChar)
    }

    @Test
    def one_4 {
        val answer = 8 * 5 + 2
        assertEquals(42, answer)

        val a = 5
        val b = 3

        assertEquals(8, a + b)
        assertEquals(8, a.+(b))

        val x: BigInt = 1234567890
        val result = x * x * x // in Java you'd have to use .multiply
    }

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