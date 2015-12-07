#!/bin/sh
hadoop jar target/scala-2.10/scalding-template.jar \
com.twitter.scalding.Tool \
com.example.WordCountJob \
--hdfs \
--input /user/alvin/data/in/rain.txt \
--output /user/alvin/data/out/wc