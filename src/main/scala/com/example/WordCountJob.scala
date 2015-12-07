package com.example

import com.twitter.scalding.{Args, Job, TextLine, Tsv}

/**
  * Created by Alvin.
  */


class WordCountJob(args: Args) extends Job(args) {
  TextLine(args("input"))
    .flatMap('line -> 'word) { line: String =>
      line.toLowerCase.split("\\s+")
    }
    .groupBy('word) {
      _.size
    }
    .write(Tsv(args("output")))
}
