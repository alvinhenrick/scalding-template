#!/bin/sh
hadoop jar target/scala-2.10/scalding-template.jar \
com.twitter.scalding.Tool \
com.example.BookSimilarity \
--hdfs \
--input /user/alvin/data/in/books.txt \
--output /user/alvin/data/out/tfidf