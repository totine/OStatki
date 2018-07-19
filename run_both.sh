#!/bin/bash
set -e
mvn clean install
java -jar server/target/server-0.3.1.jar &
java -jar client/target/client-0.3.1.jar
