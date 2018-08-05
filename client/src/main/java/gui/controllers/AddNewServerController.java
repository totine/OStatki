package gui.controllers;

import gui.data.ServerInfo;
import gui.instance.ClientAppRunner;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddNewServerController {
    private static final int DEFAULT_PORT = 8888;
    @FXML
    private TextField hostField;
    @FXML
    private TextField portField;

    @FXML
    void confirmAddingNewServer() {
        String hostName = hostField.getText();

        int port = assignPortIfNumeric(DEFAULT_PORT);

        ClientAppRunner appInstance = ClientAppRunner.getInstance();
        appInstance.addNewServer(ServerInfo.create(hostName, port));
    }

    private int assignPortIfNumeric(int port) {
        if (isNumeric(portField.getText())) {
            port = Integer.parseInt(portField.getText());
        }
        return port;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
