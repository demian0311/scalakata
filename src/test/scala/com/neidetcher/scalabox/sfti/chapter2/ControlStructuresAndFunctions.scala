package com.neidetcher.scalabox.sfti.chapter2

import org.junit.Test
import org.junit.Assert._

class ControlStructuresAndFunctions {
	/**
	 * Conditional Expressions
	 */
	@Test def two_1 {

		// in scala, if statements resolve to values
		// so it's like the ternary operator in Java
		// this makes it easier for you to do with out the 'var'
		val x = 2
		assertEquals(1, if (x > 0) 1 else -1)

		assertEquals("java.lang.Integer", (if (x > 0) 1 else "foo").getClass().getName())
		assertEquals("java.lang.String", (if (x < 0) 1 else "foo").getClass().getName())

		// () is Unit
		assertEquals("void", ().getClass().getName())
	}

	/**
	 * Statement termination.
	 */
	@Test def two_2 {
		// to put multiple statements on the same line, use the ;
		var n = 1
		var r = 3
		val result = if (n > 0) { r = r * n; n -= 1 }
		assertEquals(3, r)

		// to span multiple lines with the same statements, end a line in something that
		// can't possibly be the end of the statement, like the user of an operator

		val a = 1 +
			3
		assertEquals(4, a)
	}

	/**
	 * Block Expressions and Assignments
	 */
	@Test def two_3 {
		import scala.math._
		val x = 3
		val x0 = 9
		val y = 7
		val y0 = 13

		// the last expression is what is returned from the block
		val distance = { val dx = x - x0; val dy = y - y0; sqrt(dx * dx + dy * dy) }
		assertEquals("8.48528137423857", distance.toString())
	}

	/** Input and Output */
	@Test def two_4 {
		// print("foo")
		// println("bar")

	}

	/** Loops */
	@Test def two_5 {

		// while loops, you need to have something as a var to make the while loop work
		var n = 5
		var r = 1
		while (n > 0) {
			r = r * n
			n -= 1

			println(r + ":" + n)
		}
		assertEquals(120, r)
		assertEquals(0, n)

		// the for construct operates on ranges
		for (i <- 1 to 5) {
			println("i: " + i)
		}

		// you should not use loops
	}

	/** Advanced for Loops and for Comprehensions */
	@Test def two_6 {

	}

}