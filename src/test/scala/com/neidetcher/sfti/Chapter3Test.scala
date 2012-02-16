package com.neidetcher.sfti

import org.junit.Test
import org.junit.Assert._

class Chapter3Test {
	/** Fixed-Length Arrays */
	@Test def three_1 {
		// a 10 element array of type int
		val nums = new Array[Int](10)
		println("nums: " + nums)

		// a 10 element array of type string
		val a = new Array[String](10)
		println("a: " + a)

		// compiler knows that you want a 2 element array of type string
		val s = Array("Hello", "World")
		println("s: " + s)

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
		if (a == null && b == null) true
		if (a == null || b == null) false
		if (a.length != b.length) false

		for (i <- 0 until a.length) {
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
		//assertTrue("arrays should have the same value", compareArrays(Array(4, 3, 5, 7, 11), resultB))

	}
}