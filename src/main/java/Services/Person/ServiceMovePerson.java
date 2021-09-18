package Services.Person;

import DAO.DAOPersona;
import Models.Person;

public class ServiceMovePerson {

    DAOPersona dao;

    public ServiceMovePerson(DAOPersona dao) {
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
