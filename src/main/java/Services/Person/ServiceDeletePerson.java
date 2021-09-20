package Services.Person;

import DAO.DAOPerson;
import Models.Person;

public class ServiceDeletePerson {

    DAOPerson dao;
    public ServiceDeletePerson(DAOPerson dao){
        this.dao = dao;
    }

    public boolean deletePerson(Person p){
        return dao.getSelectedList().remove(p);
    }
}
