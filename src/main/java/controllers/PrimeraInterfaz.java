package controllers;

import DAO.DAOPersona;
import Models.Person;
import Services.Person.ServiceAddPersona;
import Services.Person.ServiceMovePerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimeraInterfaz implements Initializable {


    private Alert alert;
    private DAOPersona daoPersona;
    private ServiceAddPersona serviceAddPersona;
    private ServiceMovePerson serviceMovePerson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.ERROR);
        daoPersona = new DAOPersona();
        serviceAddPersona = new ServiceAddPersona();
        serviceMovePerson = new ServiceMovePerson();
    }

    @FXML
    private RadioButton rbHombre;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textName;
    @FXML
    private TextField textEdad;
    @FXML
    private ListView<Person> listLeft;
    @FXML
    private ListView<Person> listRight;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonPassListRight;
    @FXML
    private Button buttonPassListLeft;
    @FXML
    private Button buttonDelete;


    @FXML
    private void addPersona(ActionEvent actionEvent) {
        Person p = new Person();
        try {

            p.setName(textName.getText());
            p.setAge(Integer.parseInt(textEdad.getText()));
            p.setRegistryDate(datePicker.getValue());
            p.setMale(rbHombre.isSelected());

            if (serviceAddPersona.addPerson(p, daoPersona)){
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Persona añadida");
                refreshLists();
            }else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No se ha podido añadir a la persona");
            }
            alert.showAndWait();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    private void refreshLists(){
        listLeft.getItems().clear();
        listRight.getItems().clear();

        for (Person p : daoPersona.getGeneralList()){
            listLeft.getItems().add(p);
        }
        for (Person p : daoPersona.getSelectedList()){
            listRight.getItems().add(p);
        }
    }

    @FXML
    private void updateList(ActionEvent actionEvent) {
    }

    @FXML
    private void moveRight(ActionEvent actionEvent) {
        if (listLeft.getSelectionModel().getSelectedItem() != null){
            serviceMovePerson.moveToSelected(listLeft.getSelectionModel().getSelectedItem(), daoPersona);
            refreshLists();
        }else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleccione una persona para mover");
            alert.showAndWait();
        }
    }

    @FXML
    private void moveLeft(ActionEvent actionEvent) {
            if (listRight.getSelectionModel().getSelectedItem() != null){
                serviceMovePerson.moveToGeneral(listRight.getSelectionModel().getSelectedItem(), daoPersona);
                refreshLists();
            }else {
             alert.setAlertType(Alert.AlertType.ERROR);
             alert.setContentText("Seleccione una persona para mover");
             alert.showAndWait();
            }
    }

    @FXML
    private void deletePersona(ActionEvent actionEvent) {
    }


}
