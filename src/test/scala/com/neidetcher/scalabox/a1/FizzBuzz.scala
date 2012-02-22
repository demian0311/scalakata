package com.neidetcher.scalabox.a1

import org.junit.Test
import org.junit.Assert._
import org.junit.Ignore

class FizzBuzz {

	def fizzBuzz() {
		for (i <- 1 to 100) {
			println(i + ": " + processLine(i))
		}
	}

	def processLine(valIn: Int): String = {
		(if (valIn % 3 == 0) "Fizz" else "") + (if (valIn % 5 == 0) "Buzz" else "")
	}

	@Test
	@Ignore
	def runIt() {
		fizzBuzz()
	}

	@Test
	def testIt() {
		assertEquals("FizzBuzz", processLine(15))
		assertEquals("Fizz", processLine(3))
		assertEquals("Buzz", processLine(10))
	}

}