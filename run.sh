#!/bin/bash
set -e
mvn clean install
java -jar target/battleships-0.1.2.jar

