package Services.Person;

import DAO.DAOPersona;
import Models.Person;

public class ServiceDeletePerson {

    DAOPersona dao;
    public ServiceDeletePerson(DAOPersona dao){
        this.dao = dao;
    }

    public boolean deletePerson(Person p){
        return dao.getSelectedList().remove(p);
    }
}
