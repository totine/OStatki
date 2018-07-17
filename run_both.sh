#!/bin/bash
set -e
mvn clean install
x-terminal-emulator -e java -jar server/target/server-0.2.1.jar
x-terminal-emulator -e java -jar client/target/client-0.2.1.jar
