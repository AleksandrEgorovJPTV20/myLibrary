package facade;

import entity.UserRoles;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import tools.Singleton;


public class UserRolesFacade extends AbstractFacade<UserRoles>{
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserRolesFacade() {
        super(UserRoles.class);
        init();
    }
    private void init(){
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }
}
