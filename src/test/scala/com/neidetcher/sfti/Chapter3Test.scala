package com.neidetcher.sfti

import org.junit.Test
import org.junit.Assert._

class Chapter3Test {
	/** Fixed-Length Arrays */
	@Test def three_1 {
		// a 10 element array of type int
		val nums = new Array[Int](10)

		nums.foreach(println(_))

		// a 10 element array of type string
		val a = new Array[String](10)
		a.foreach(println(_))

		// compiler knows that you want a 2 element array of type string
		val s = Array("Hello", "World")
		println("s: " + s)
		s.foreach(println(_))

		assertEquals("World Hello", (s(1) + " " + s(0)))
	}

	/** Variable-Length Arrays: Array Buffers */
	@Test def three_2 {
		import collection.mutable.ArrayBuffer

		val b = ArrayBuffer[Int]()
		b += 1 // append 
		assertEquals(ArrayBuffer(1), b)

		b += (1, 2, 3, 5)
		assertEquals(ArrayBuffer(1, 1, 2, 3, 5), b)

		b ++= Array(8, 13, 21) // append collection
		assertEquals(ArrayBuffer(1, 1, 2, 3, 5, 8, 13, 21), b)

		b.trimEnd(5)
		assertEquals(ArrayBuffer(1, 1, 2), b)

		b.insert(2, 6)
		assertEquals(ArrayBuffer(1, 1, 6, 2), b)

		b.insert(2, 7, 8, 9)
		assertEquals(ArrayBuffer(1, 1, 7, 8, 9, 6, 2), b)

		b.remove(2)
		assertEquals(ArrayBuffer(1, 1, 8, 9, 6, 2), b)

		b.remove(2, 3)
		assertEquals(ArrayBuffer(1, 1, 2), b)

		// comparison doesn't work: assertEquals(Array(1, 1, 2), b.toArray)
	}

	/** Traversing Arrays and Array Buffers */
	@Test def three_3 {
		val a = new Array[String](10)
		a(3) = "foo"
		a(7) = "bar"
		a(8) = "baz"

		for (i <- 0 until a.length) println(i + ": " + a(i))
		// use 'until' instead of 'to' so you don't have to do the -1

		// to print every other element
		for (i <- 0 until (a.length, 2)) println(i + ": " + a(i))

		for (i <- (0 until a.length).reverse) println(i + ": " + a(i))

		println("if you don't need the index")
		for (currA <- a) println(currA)
	}

	def compareArrays[A](a: Array[A], b: Array[A]): Boolean = {

		println("comparing arrays")
		println("a: " + a.foreach(print(_)))
		println("b: " + b.foreach(print(_)))

		if (a == null && b == null) true
		if (a == null || b == null) false
		if (a.length != b.length) false

		for (i <- 0 until a.length) {
			println("comparing(" + a(i) + "=" + b(i) + ")")
			if (a(i) != b(i)) return false
		}

		true
	}

	/*
	class ArrayHelper[TYPE](a: Array[TYPE]) {
		override def toString(): String = {
			"array" // a.<stuff>
		}
	}

	implicit def toString[A](arr: Array[A]) = new ArrayHelper[A](arr)
	 */

	/** Transforming Arrays */
	@Test def three_4 {

		val a = Array(2, 3, 5, 7, 11)
		val result = for (elem <- a) yield 2 * elem
		assertTrue("arrays should have same value", compareArrays(Array(4, 6, 10, 14, 22), result))

		// this is from the book, it doesn't work
		//for (elem <- a if (a % 2 == 0)) yield 2 * elem

		val resultB = a.filter(_ % 2 == 0).map(2 * _)
		println("resultB: " + resultB.toString())
		println("resultB___")
		resultB.foreach(println _)
		assertTrue("arrays should have the same value", compareArrays(Array(4), resultB))
	}

	/** Common Algorithms */
	@Test def three_5 {
		val a = Array(1, 7, 2, 9)
		val b = Array(-60, 55, 0, 40)

		assertEquals(19, a.sum)
		assertEquals(55, b.max)

		//val bSorted = b.sorted(_ < _) // TODO: this does not work for me

		// you can sort an array in place
		util.Sorting.quickSort(b)
		assertTrue(compareArrays(Array(-60, 0, 40, 55), b))

		assertEquals("-60 and 0 and 40 and 55", b.mkString(" and "))
		assertEquals("<-60 and 0 and 40 and 55>", b.mkString("<", " and ", ">"))
	}

	/** Deciphering Scaladoc */
	@Test def three_6 {
		// methods for Array class are listed under ArrayOps, the Java Array is
		// converted to an ArrayOps before any operations are applied

		val a = Array(34, 62, 2, 8, 143)
		assertEquals(4, a.count(_ % 2 == 0))

		// append
		// appendAll
		// += 
		// copyToArray
	}

	/** Multidimensional Arrays */
	@Test def three_7 {
		val matrix = Array.ofDim[Double](3, 4) // 3 rows, 4 cols
		matrix(2)(3) = 42
	}

	/** Interoperating with Java */
	@Test def three_8 {
		import collection.JavaConversions.bufferAsJavaList
		import collection.mutable.ArrayBuffer

		val command = ArrayBuffer("ls", "-al", "/home/cay") // a scala data structure
		val pb = new ProcessBuilder(command) // going from scala to java

		import collection.JavaConversions.asScalaBuffer
		import collection.mutable.Buffer
		val cmd: Buffer[String] = pb.command() // Java to Scala

		assertEquals(command, cmd)
	}

	@Test def exercises_1 {

		import scala.util.Random
		import scala.collection.mutable.ArrayBuffer

		def createRandomIntegers(n: Int): Array[Int] = {
			val arrayBuffer = ArrayBuffer[Int]()

			for (currIndex <- 0 to n) {
				arrayBuffer += Random.nextInt(n)
			}

			arrayBuffer.toArray
		}

		val result = createRandomIntegers(20)
		result.foreach(println(_))
	}

	/**
	 * TODO: probably should be more elegant
	 * Write a loop that swaps adjacent elements of an array of integers. For example,
	 * Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
	 */
	@Test def exercises_2 {

		def swapAdjacent(arrayIn: Array[Int]): Array[Int] = {
			val arrayOut = new Array[Int](arrayIn.length)
			for (index <- 0 until arrayIn.length) {

				val currElement = arrayIn(index)
				println(currElement)

				if (currElement % 2 == 0) {
					arrayOut(index - 1) = currElement
				}
				else {
					if (index != arrayIn.length - 1) {
						arrayOut(index + 1) = currElement
					}
					else {
						arrayOut(index) = currElement
					}
				}
			}

			arrayOut
		}

		assertTrue(compareArrays(Array(2, 1, 4, 3, 5), swapAdjacent(Array(1, 2, 3, 4, 5))))
	}

	/**
	 * TODO: I don't get how I could do it.
	 * do the same as exercise 2 but use for / yield
	 *
	 * Here is an example of
	 * http://gleichmann.wordpress.com/2010/11/21/functional-scala-comprehending-comprehensions/
	 */
	@Test def exercise_3 {
		def swapAdjacent(arrayIn: Array[Int]): Array[Int] = {
			val result = for (elem <- arrayIn if elem % 2 == 0) yield elem
			result
		}
		//assertTrue(compareArrays(Array(2, 1, 4, 3, 5), swapAdjacent(Array(1, 2, 3, 4, 5))))
	}

	/**
	 * TODO:
	 * Given an array of integers, produce a new array that contains all positive values
	 * of the original array, in their original order, followed by all values that are
	 * zero or negative, in their original order.
	 */
	@Test def exercise_4 {
	}
}