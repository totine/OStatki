#!/bin/bash
set -e
mvn clean install
java -jar server/target/server-*.jar &
java -jar client/target/client-*.jar
