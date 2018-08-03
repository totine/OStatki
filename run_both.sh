#!/bin/bash
set -e
java -jar server/target/server-*.jar &
java -jar client/target/client-*.jar &
java -jar client/target/client-*.jar
