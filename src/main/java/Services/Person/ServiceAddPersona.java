package Services.Person;

import DAO.DAOPersona;
import Models.Person;

public class ServiceAddPersona {
    public boolean addPerson(Person p, DAOPersona dao){
        return dao.addGeneralList(p);
    }
}
