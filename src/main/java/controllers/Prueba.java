package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Prueba {
    public Button buttonErase;
    @FXML
    private Button buttonIntroduce;
    @FXML
    private ListView<String> listLeft;
    @FXML
    private ListView<String> listRight;
    @FXML
    private TextField textInput;
    @FXML
    private Button botonRight;
    @FXML
    private Button botonLeft;

    @FXML
    private void introduceText(ActionEvent actionEvent) {
        if (textInput.getText().isBlank()){
            System.out.println("Texto vacío");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se pueden añadir elementos vacíos");
            alert.showAndWait();
        }else {
            listLeft.getItems().add(textInput.getText());
            textInput.setText("");
        }
    }

    @FXML
    private void eraseContent(ActionEvent actionEvent) {
        if (listLeft.getSelectionModel().getSelectedItem() != null){
            listLeft.getItems().remove(listLeft.getSelectionModel().getSelectedItem());
        }else if (listRight.getSelectionModel().getSelectedItem() != null){
            listRight.getItems().remove(listRight.getSelectionModel().getSelectedItem());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe seleccionar un elemento para borrarlo");
            alert.showAndWait();
        }
    }

    @FXML
    private void moveElement(ActionEvent actionEvent) {
        if (listLeft.getSelectionModel().getSelectedItem() != null){
            listRight.getItems().add(listLeft.getSelectionModel().getSelectedItem());
            listLeft.getItems().remove(listLeft.getSelectionModel().getSelectedItem());
            listLeft.getSelectionModel().clearSelection();

        }else if (listRight.getSelectionModel().getSelectedItem() != null){
            listLeft.getItems().add(listRight.getSelectionModel().getSelectedItem());
            listRight.getItems().remove(listRight.getSelectionModel().getSelectedItem());
            listRight.getSelectionModel().clearSelection();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe seleccionar un elemento para moverlo");
            alert.showAndWait();
        }
    }
}
