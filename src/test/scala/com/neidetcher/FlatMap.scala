package com.neidetcher

import org.junit.Test
import org.junit.Assert._

class FlatMap {

  /**
   * http://bcomposes.wordpress.com/2011/08/24/first-steps-in-scala-for-beginning-programmers-part-2/
   */
  @Test def flatMapVsMap(){
    val names = List("Demian", "Leo", "Neidetcher")

    // flatMap will flatten out the collections
    val flatMapResult = names.flatMap(x => x.toUpperCase())
    assertEquals('D', flatMapResult(0))

    // map preserves the collections
    val mapResult = names.map(x => x.toUpperCase())
    assertEquals("DEMIAN", mapResult(0))
  }

}
