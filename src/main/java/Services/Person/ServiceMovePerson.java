package Services.Person;

import DAO.DAOPersona;
import Models.Person;

public class ServiceMovePerson {

    public boolean moveToSelected(Person p, DAOPersona dao){
        dao.getGeneralList().remove(p);
        return dao.getSelectedList().add(p);
    }

    public boolean moveToGeneral(Person p, DAOPersona dao){
        dao.getSelectedList().remove(p);
        return dao.getGeneralList().add(p);
    }
}
