package com.neidetcher.sfti

import org.junit.Test
import org.junit.Assert._

class Chapter4Test {
	/** Fixed-Length Arrays */
	@Test def one {
		// this creates an immutable map
		val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
		println("scores: " + scores)

		val mutableScores = collection.mutable.Map("foo" -> 1, "bar" -> 2)

		val startingEmptyScores = new collection.mutable.HashMap[String, Int]

		// maps just take tuples, the -> notation is nothing more than an n=2 tuple
		assertEquals(("Alice", 10), "Alice" -> 10)
	}

	/** Accessing Map Values */
	@Test def two {
		val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
		assertEquals(3, scores("Bob"))
		assertEquals(99, scores.getOrElse("foo", 99))

		val result = scores.get("Alice")
		assertEquals(Some(10), result)

		val result2 = scores.get("foo")
		assertEquals(None, result2)
	}

	/** Updating Map Values */
	@Test def three {
		val scores = collection.mutable.Map("Bob" -> 5)

		scores("Bob") = 10
		scores("Fred") = 7

		scores += ("Jim" -> 6, "Ted" -> 5)

		scores -= "Fred"
		println("scores: " + scores)

		// we removed Fred
		assertEquals(Map("Bob" -> 10, "Ted" -> 5, "Jim" -> 6), scores)
	}

	/** Iterating over Maps*/
	@Test def four {
		val map = Map("Bob" -> 10, "Ted" -> 5, "Jim" -> 6)

		for ((k, v) <- map) { println(k + "->" + v) }

	}

}