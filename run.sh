#!/bin/bash
set -e
mvn clean install
java -jar client/target/client-0.2.1.jar



