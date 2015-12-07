#!/bin/sh

hadoop jar target/scala-2.10/scalding-template.jar \
com.twitter.scalding.Tool \
com.example.BloomFilterMonoidExample \
--hdfs \
--input /user/alvin/data/in/WA_Sales_Products.csv \
--output /user/alvin/data/out/bloomfilter