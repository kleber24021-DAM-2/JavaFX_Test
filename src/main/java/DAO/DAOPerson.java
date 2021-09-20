package DAO;

import Models.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DAOPerson {

    private List<Person> generalList;
    private List<Person> selectedList;

    public DAOPerson() {
        if (generalList == null) {
            generalList = new ArrayList<>();
            selectedList = new ArrayList<>();
        }
    }

    public boolean addGeneralList(Person p) {
        return generalList.add(p);
    }

}
