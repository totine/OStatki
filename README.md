# Battleship
Instructions for Linux to run only GUI:
chmod +x run.sh
./run.sh

To run server and client:

`mvn clean install`

`java -jar server/target/server-0.2.1.jar`

`java -jar client/target/client-0.2.1.jar`

If you encounter problems with running this script with success, make sure
you are using Java 8.

Attention: Please run with Oracle JDK 8, as it contains JavaFX packages.

cd OStatki
sudo update-alternatives --config java
[choose oracle jdk 8]
./run.sh


