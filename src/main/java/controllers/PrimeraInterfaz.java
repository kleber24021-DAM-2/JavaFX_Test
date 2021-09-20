package controllers;

import DAO.DAOPerson;
import Models.Person;
import Services.Person.ServiceAddPersona;
import Services.Person.ServiceDeletePerson;
import Services.Person.ServiceModifyPerson;
import Services.Person.ServiceMovePerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PrimeraInterfaz implements Initializable {

    @FXML
    private RadioButton rbMujer;
    private Alert alert;
    //Creamos un DAO para el controlador. De esta forma podemos utilizar siempre la misma instancia del DAO.
    //Además instanciamos los diferentes servicios que vamos a utilizar

    private DAOPerson daoPerson;
    private ServiceAddPersona serviceAddPersona;
    private ServiceMovePerson serviceMovePerson;
    private ServiceDeletePerson serviceDeletePerson;
    private ServiceModifyPerson serviceModifyPerson;

    @FXML
    private RadioButton rbHombre;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textName;
    @FXML
    private TextField textEdad;
    @FXML
    private ListView<Person> generalList;
    @FXML
    private ListView<Person> selectedList;
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

    //Instanciamos la alerta, el dao y los servicios que se van a utilizar
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.ERROR);
        daoPerson = new DAOPerson();
        serviceAddPersona = new ServiceAddPersona(daoPerson);
        serviceMovePerson = new ServiceMovePerson(daoPerson);
        serviceDeletePerson = new ServiceDeletePerson(daoPerson);
        serviceModifyPerson = new ServiceModifyPerson(daoPerson);

        comboBox.getItems().add("Hombre");
        comboBox.getItems().add("Mujer");
    }


    @FXML
    private void addPersona(ActionEvent actionEvent) {
        //Crea una nueva persona y trata de cambiar los atributos del
        Person p = new Person();
        try {
            p.setName(textName.getText());
            p.setAge(Integer.parseInt(textEdad.getText()));
            p.setRegistryDate(datePicker.getValue());
            p.setMale(rbHombre.isSelected());

            if (serviceAddPersona.addPerson(p)) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Persona añadida");
                refreshLists();
                textEdad.setText("");
                textName.setText("");
                datePicker.getEditor().clear();
                sexo.selectToggle(null);
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No se ha podido añadir a la persona. Revise los datos");
            }
            alert.showAndWait();
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    private void refreshLists() {
        generalList.getItems().clear();
        selectedList.getItems().clear();

        for (Person p : daoPerson.getGeneralList()) {
            generalList.getItems().add(p);
        }
        for (Person p : daoPerson.getSelectedList()) {
            selectedList.getItems().add(p);
        }
    }

    @FXML
    private void updatePerson(ActionEvent actionEvent) {
        Person p = new Person();
        Person targetPerson = selectedList.getSelectionModel().getSelectedItem();

        if (targetPerson != null) {
            try {
                p.setName(textName.getText());
                p.setAge(Integer.parseInt(textEdad.getText()));
                p.setRegistryDate(datePicker.getValue());
                p.setMale(rbHombre.isSelected());
                serviceModifyPerson.modifyPerson(targetPerson, p);
                refreshLists();

                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Persona modificada");

            } catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText(e.toString());
            } finally {
                alert.showAndWait();
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Selecciona una persona de la lista derecha");
            alert.showAndWait();
        }
    }

    @FXML
    private void moveRight(ActionEvent actionEvent) {
        if (generalList.getSelectionModel().getSelectedItem() != null) {
            serviceMovePerson.moveToSelected(generalList.getSelectionModel().getSelectedItem());
            refreshLists();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleccione una persona para mover");
            alert.showAndWait();
        }
    }

    @FXML
    private void moveLeft(ActionEvent actionEvent) {
        if (selectedList.getSelectionModel().getSelectedItem() != null) {
            serviceMovePerson.moveToGeneral(selectedList.getSelectionModel().getSelectedItem());
            refreshLists();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleccione una persona para mover");
            alert.showAndWait();
        }
    }

    @FXML
    private void deletePersona(ActionEvent actionEvent) {
        Person toDelete = selectedList.getSelectionModel().getSelectedItem();
        if (toDelete != null) {
            if (serviceDeletePerson.deletePerson(toDelete)) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Se ha eliminado a la persona");
                refreshLists();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No ha sido posible eliminar ese dato");
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleccione una persona antes de eliminar");
        }
        alert.showAndWait();
    }


    @FXML
    private void filterContents(ActionEvent actionEvent) {
        String selected = comboBox.getSelectionModel().getSelectedItem();
        List<Person> generalList = daoPerson.getGeneralList();
        this.generalList.getItems().clear();
        this.generalList.getItems().addAll(generalList.stream().filter(
                person -> {
                    if (selected.equals("Hombre"))
                        return person.isMale();
                    else
                        return !person.isMale();
                }
        ).collect(Collectors.toList()));
    }

    @FXML
    private void showPersonInfo(MouseEvent mouseEvent) {
        Person selected = selectedList.getSelectionModel().getSelectedItem();
        try{
            textName.setText(selected.getName());
            textEdad.setText(Integer.toString(selected.getAge()));
            datePicker.setValue(selected.getRegistryDate());
            if (selected.isMale()) {
                rbHombre.setSelected(true);
            } else {
                rbMujer.setSelected(true);
            }
        }catch (Exception e){
           alert.setAlertType(Alert.AlertType.INFORMATION);
           alert.setContentText("No existe ningún elemento en la lista derecha");
           alert.showAndWait();
        }
    }
}
