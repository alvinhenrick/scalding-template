package com.example.ignore

import com.twitter.scalding.{Args, IterableSource, Job, TextLine}

/**
  * Created by Alvin on 11/15/15.
  */
class TestClass(args: Args) extends Job(args) {

  val data = "data/in/somefile.txt"

  TextLine(data)
    .flatMap('line -> 'word) { line: String => line.split(",").map(_.toInt) }

  IterableSource(List(1, 2, 30, 42), 'num)
    .map('num -> 'lessThanTen) { i: Int => i < 10 }
    .groupBy('lessThanTen) {
      _.size
    }

}


