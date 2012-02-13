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

		// for loops can have multiple generators
		for (i <- 1 to 3; j <- 1 to 5) {
			print(i + ":" + j + " ")
		}
		println("")

		// generators can have guards
		for (i <- 1 to 3; j <- 1 to 3 if i != j) print((10 * i + j) + " ")
		println("")

		// you can have definitions with variables derived from generators
		for (i <- 1 to 3; from = 4 - i; j <- from to 3) print((10 * i + j) + " ")
		println("")

		// yield makes the loop create a colleciton
		// this is a for comprehension, where the for creates a result
		assertEquals(Vector(1, 2, 0, 1, 2, 0, 1, 2, 0, 1), (for (i <- 1 to 10) yield i % 3))

		assertEquals("HIeflmlmop", for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar)

		assertEquals(Vector('H', 'e', 'l', 'l', 'o', 'I', 'f', 'm', 'm', 'p'), for (i <- 0 to 1; c <- "Hello") yield (c + i).toChar)
	}

	/** Functions */
	@Test def two_7 {
		// methods operate on objects
		// functions don't don't need objects

		def abs(x: Int) = if (x > 0) x else -x
		assertEquals(1, abs(-1))

		// functions can be placed in blocks
		def countUpperCaseLetters(stringIn: String) = {
			stringIn.count(_.isUpper)
		}
		assertEquals(2, countUpperCaseLetters("Hello World"))

		// for recursive functions you have to specify the return type
		def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)
		assertEquals(120, fac(5))
	}

	/** Default and Named Arguments L1 */
	@Test def two_8 {
		def decorate(str: String, left: String = "[", right: String = "]") = left + str + right
		assertEquals("[foo]", decorate("foo"))
		assertEquals("{foo]", decorate("foo", "{"))

		// you can also supply parameter names
		// hmmm, IDE doesn't help with that, surprising
		assertEquals("<foo>", decorate(right = ">", left = "<", str = "foo"))
	}

	/** Variable Arguments L1*/
	@Test def two_9 {
		// this is actually a Seq data type
		def sum(args: Int*) = {
			var result = 0
			for (arg <- args) result += arg
			result
		}

		assertEquals(5, sum(1, 3, 1))

		// consider the range to be an argument sequence
		assertEquals(15, sum(1 to 5: _*))
	}

	/** Procedures */
	@Test def two_10 {
		// a procedure returns no value, you only call it the side effect
		// side effects are a bad thing but sometimes necessary, like when
		// you want to print to the console

		// Effectively if you leave out the equals sign it is like having
		// the return type of Unit
		def box(s: String) {
			val border = "_" * s.length() + "__\n"
			println(border + "|" + s + "|\n" + border)
		}

		box("Demian was here")
	}

	/** Lazy Values L1 */
	@Test def two_11 {
		var aEvaluation, bEvaluation, cEvaluation = 0

		val a = aEvaluation += 1 // evaluated at this line
		lazy val b = bEvaluation += 1 // not evaluated on this line
		def c = cEvaluation += 1 // defines a function that is evaluated every time it
		// is called

		assertEquals(1, aEvaluation) // a regular var, evaluated when we declare it
		assertEquals(0, bEvaluation) // it's lazy, so hasn't been evaluated yet
		assertEquals(0, cEvaluation) // the def was just declared

		println("a: " + a)
		println("b: " + b)
		println("c: " + c)
		assertEquals(1, aEvaluation)
		assertEquals(1, bEvaluation)
		assertEquals(1, cEvaluation)

		println("a: " + a)
		println("b: " + b)
		println("c: " + c)
		assertEquals(1, aEvaluation)
		assertEquals(1, bEvaluation)
		assertEquals(2, cEvaluation) // evaluated every time we reference it
	}

	/** Exceptions */
	@Test def two_12 {
		import scala.math._
		val x = 25

		// even though the else block throws an exception, Scala sees it as a 'Nothing'.
		// this is a special situation where we don't make the result the super type of
		// the if statement, we just disregard that block (that returns 'Nothing') when
		// figuring out the result type of the if statement.
		val result = if (x >= 0) { sqrt(x) } else throw new IllegalArgumentException("x should not be negative")
		assertEquals("double", result.getClass().getName())

		import java.io.IOException
		import java.net.MalformedURLException
		import java.net.URL

		val urlString = "http://horstmann.com/fred-tiny.gif"
		try {
			val url = new URL(urlString)
		}
		catch {
			case _: MalformedURLException => println("Bad URL: " + urlString)
			case ex: IOException => ex.printStackTrace()
		}
		finally {
			println("all done")
		}
	}

}