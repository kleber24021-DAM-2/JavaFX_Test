package Services.Person;

import DAO.DAOPerson;
import Models.Person;

public class ServiceModifyPerson {
    DAOPerson dao;

    public ServiceModifyPerson(DAOPerson dao) {
        this.dao = dao;
    }

    public void modifyPerson(Person target, Person newPerson) {
        dao.getSelectedList().set(dao.getSelectedList().indexOf(target), newPerson);
    }
}
