package com.example

import com.twitter.algebird.{BF, BloomFilterMonoid}
import com.twitter.scalding.{Args, Csv, Job, Tsv}

/**
  * Created by Alvin.
  */
class BloomFilterMonoidExample(args: Args) extends Job(args) {

  val NUM_HASHES = 6
  val WIDTH = 32
  val SEED = 1

  //Bloom Filter implementation from Twitter Algebird
  val bfMonoid = new BloomFilterMonoid(NUM_HASHES, WIDTH, SEED)

  val inputSchema = List('country, 'orderMethod, 'retailerType, 'productLine, 'productType, 'productName, 'year, 'quarter, 'revenue, 'quantity, 'grossMargin)

  Csv(args("input"), ",", inputSchema, skipHeader = true)
    .groupBy('country) {
      _.foldLeft('productName -> 'productBloom)(bfMonoid.zero) {
        (bf: BF, itemId: String) => bf + itemId
      }
    }
    .map('productBloom -> 'hasSoldSunBlocker) { b: BF => b.contains("Sun Blocker").isTrue }
    .map('productBloom -> 'hasSoldLux) { b: BF => b.contains("Lux").isTrue }
    .discard('productBloom)
    .write(Tsv(args("output"), writeHeader = true))
}