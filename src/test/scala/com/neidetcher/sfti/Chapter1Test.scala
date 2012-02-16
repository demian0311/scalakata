package com.neidetcher.sfti

import org.junit.Test
import org.junit.Assert._
import scala.util.Random

class Chapter1Test {

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
	 */
	@Test def one_7 {
		assertEquals(2, "Hello World!".count(_.isUpper))
		assertEquals("Hungry", "Harry".patch(1, "ung", 2))

		// http://www.scala-lang.org/api/current/index.html#scala.math.package
		import math._
		assertEquals("3.14", Pi.toString().take(4))
		assertEquals(5, 4.9.ceil.toInt)
		assertEquals(4, 4.9.floor.toInt)

		// the count method
		assertEquals(2, "Hello World".count(_.equals('o')))
		assertEquals(2, "Hello World".count(_.isUpper))
	}

	@Test def one_8 {

		// 1) In the Scala REPL, type 3. followed by the Tab key. What methods can be applied?
		// 2) In the Scala REPL, compute the square root of 3, and then square that value. By how much does the result differ from 3? (Hint: The res variables are your friend.)
		assertEquals(3, math.sqrt(9).toInt)

		// 4) Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL. What does this operation do? Where can you find it in Scaladoc?
		// http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.StringOps
		assertEquals("crazy crazy crazy ", "crazy " * 3)

		// 5) What does 10 max 2 mean? In which class is the max method defined?
		// http://www.scala-lang.org/api/current/index.html#scala.runtime.RichInt
		assertEquals(10, 10 max 2)

		// 6) Using BigInt, compute 2^1024.
		val two: BigInt = 2
		val expectedResult = "179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137216"
		assertEquals(expectedResult, two.pow(1024).toString)

		// 7) What do you need to import so that you can get a random prime as probablePrime(100, Random), without any qualifiers before probablePrime and Random?
		BigInt.probablePrime(100, util.Random)

		import scala.math.BigInt.probablePrime
		import util._
		probablePrime(100, Random)

		// 8) One way to create random file or directory names is to produce a random BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul". Poke around Scaladoc to find a way of doing this in Scala.
		println("this would be a good random file name: " + BigInt(100, Random).toString(36))

		// 9) How do you get the first character of a string in Scala? The last character?
		assertEquals('F', "FooBar".charAt(0))
		assertEquals('F', "FooBar".head)
		assertEquals('r', "FooBar".last)

		// 10) What do the take, drop, takeRight, and dropRight string functions do? What advantage or disadvantage do they have over using substring?
		assertEquals("Foo", "FooBar".take(3))
		assertEquals("Foo", "Foo".take(1024)) // this is awesome
		assertEquals("Bar", "FooBar".takeRight(3))
		assertEquals("Foo", "FooBar".dropRight(3))
	}
}

case class Person(name: String)