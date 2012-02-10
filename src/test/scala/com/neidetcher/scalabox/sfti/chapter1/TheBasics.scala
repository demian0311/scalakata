package com.neidetcher.scalabox.sfti.chapter1

import org.junit.Test
import org.junit.Assert._

class TheBasics {

    @Test def one_1() {
        val b = 8 * 5 + 2
        assertEquals(42, b)

        val expected: Double = 21
        //assertEquals(expected, (0.5 * b).toDouble)

        val result = "Hello, " + b
        assertEquals("Hello, 42", result)

        assertEquals("hello, 42", result.toLowerCase())
        assertEquals("HELLO, 42", result.toUpperCase())
    }

    @Test def one_2 {
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

    /**
     * scala gives more string stuff in StringOps
     * http://www.scala-lang.org/api/current/scala/collection/immutable/StringOps.html
     *
     * this magic is from RichInt
     * http://www.scala-lang.org/api/current/index.html#scala.runtime.RichInt
     */
    @Test def one_3 {
        assertEquals("1", 1.toString)

        // TODO: why doesn't this work?
        //assertEquals(Range(1, 2, 3), 1.to(3))
        assertEquals("lo", "Hello".intersect("World")) // yields "lo"

        1.to(5)

        assertEquals(99, 99.44.toInt)
        assertEquals('c', 99.toChar)
    }

    @Test def one_4 {
        val answer = 8 * 5 + 2
        assertEquals(42, answer)

        val a = 5
        val b = 3

        assertEquals(8, a + b)
        assertEquals(8, a.+(b))

        val x: BigInt = 1234567890
        val result = x * x * x // in Java you'd have to use .multiply
    }

    /**
     * http://www.scala-lang.org/api/current/index.html#scala.Math$
     *
     * http://www.scala-lang.org/api/current/index.html#scala.math.BigInt
     */
    @Test def one_5 {
        import math._

        assertEquals(3, sqrt(9).toInt)
        assertEquals(16, pow(2, 4).toInt)
        assertEquals(3, min(3, Pi).toInt)

        assertEquals("Helo", "Hello".distinct)
    }

    /**
     * The apply method
     */
    @Test def one_6 {
        assertEquals('o', "Hello"(4))

        assertEquals(BigInt("1234567890"), BigInt.apply("1234567890"))
        // BigInt has a companion object (a static) with an apply method
        // this is the idiomatic way to instantiate new classes in scala

        // case classes get you an apply method
        val p = Person("Dude")
        println("person: " + p.name)
    }

    /**
     * scaladoc
     *
     * TODO: get familiar with the Rich* scaladoc entries
     * - http://www.scala-lang.org/api/current/index.html#scala.runtime.RichBoolean
     *
     * TODO: get familiar with everything in the scala package:
     * - http://www.scala-lang.org/api/current/index.html#scala.package
     *
     * TODO: look at the math functions in scala.math._
     *
     * TODO: use the count features:
     * def count(p: (Char) => Boolean) : Int
     */
    @Test def one_7 {
        assertEquals(2, "Hello World!".count(_.isUpper))
        assertEquals("Hungry", "Harry".patch(1, "ung", 2))
    }

    @Test def one_8 {

        // 1) In the Scala REPL, type 3. followed by the Tab key. What methods can be applied?
        //    done
        // 2) In the Scala REPL, compute the square root of 3, and then square that value. By how much does the result differ from 3? (Hint: The res variables are your friend.)
        assertEquals(3, math.sqrt(9).toInt)

        // TODO: 4) Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL. What does this operation do? Where can you find it in Scaladoc?
        assertEquals("crazy crazy crazy ", "crazy " * 3)

        // TODO: 5) What does 10 max 2 mean? In which class is the max method defined?
        assertEquals(10, 10 max 2)

        // TODO: 6) Using BigInt, compute 2^1024.

        // 7) What do you need to import so that you can get a random prime as probablePrime(100, Random), without any qualifiers before probablePrime and Random?

        BigInt.probablePrime(100, util.Random)

        import scala.math.BigInt.probablePrime
        import util._
        probablePrime(100, Random)

        // TODO: 8) One way to create random file or directory names is to produce a random BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul". Poke around Scaladoc to find a way of doing this in Scala.

        // TODO: 9) How do you get the first character of a string in Scala? The last character?
        // TODO: 10) What do the take, drop, takeRight, and dropRight string functions do? What advantage or disadvantage do they have over using substring?
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

case class Person(name: String)