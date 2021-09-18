package Services.Person;

import DAO.DAOPersona;
import Models.Person;

public class ServiceAddPersona {
    DAOPersona dao;

    public ServiceAddPersona(DAOPersona dao) {
        this.dao = dao;
    }

    public boolean addPerson(Person p) {
        return dao.addGeneralList(p);
    }
}
