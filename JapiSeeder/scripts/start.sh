#!/bin/bash
java -jar /root/jseeder/app.jar & echo $! > ./pid.file &
