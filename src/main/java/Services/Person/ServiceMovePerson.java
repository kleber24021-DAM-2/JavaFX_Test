package Services.Person;

import DAO.DAOPerson;
import Models.Person;

public class ServiceMovePerson {

    DAOPerson dao;

    public ServiceMovePerson(DAOPerson dao) {
        this.dao = dao;
    }

    public boolean moveToSelected(Person p) {
        dao.getGeneralList().remove(p);
        return dao.getSelectedList().add(p);
    }

    public boolean moveToGeneral(Person p) {
        dao.getSelectedList().remove(p);
        return dao.getGeneralList().add(p);
    }
}
