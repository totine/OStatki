# Battleship
Instructions for Linux to run only client version:
chmod +x run_client.sh
./run_client.sh

To run server and client:

`mvn clean install`

`./run_client.sh`

`./run_server.sh`

If you encounter problems with running this script with success, make sure
you are using Java 8.

Attention: Please run with Oracle JDK 8, as it contains JavaFX packages.

cd OStatki
sudo update-alternatives --config java
[choose oracle jdk 8]
./run.sh


