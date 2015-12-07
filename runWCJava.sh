#!/bin/sh
hadoop jar target/scala-2.10/scalding-template.jar \
com.cascade.example.WCount \
/user/alvin/data/in/rain.txt \
/user/alvin/data/out/cascadewc
