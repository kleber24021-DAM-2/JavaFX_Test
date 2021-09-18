package DAO;

import Models.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DAOPersona {

    private List<Person> generalList;
    private List<Person> selectedList;

    public DAOPersona() {
        if (generalList == null) {
            generalList = new ArrayList<>();
            selectedList = new ArrayList<>();
        }
    }

    public boolean addGeneralList(Person p) {
        return generalList.add(p);
    }

}
