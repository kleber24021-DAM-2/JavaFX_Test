package Services.Person;

import DAO.DAOPersona;
import Models.Person;

public class ServiceModifyPerson {
    DAOPersona dao;

    public ServiceModifyPerson(DAOPersona dao) {
        this.dao = dao;
    }

    public void modifyPerson(Person target, Person newPerson) {
        dao.getSelectedList().set(dao.getSelectedList().indexOf(target), newPerson);
    }
}
