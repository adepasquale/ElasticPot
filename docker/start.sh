#!/usr/bin/env bash

redis-server &
cd /data/ElasticPot
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
./grailsw run-app