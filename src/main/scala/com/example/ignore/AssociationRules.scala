package com.example.ignore

import com.twitter.scalding.{Args, Job, TextLine}

/**
  * Created by Alvin on 11/7/15.
  */
class AssociationRules(args: Args) extends Job(args) {

  TextLine(args("input")).flatMap('line -> 'items) {
    line: String => processTransaction(line.split(",").sorted.toList).map(a => (a, 1))
  }


  def processTransaction(xs: List[String]): Seq[List[String]] = {
    1 to xs.length flatMap (x => xs.combinations(x))
  }
}
