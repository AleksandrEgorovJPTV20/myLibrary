package tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Singleton {
    private EntityManager em;
    private static Singleton instance;
    
    private Singleton(){
        init();
    }
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myLibraryPU");
        em = emf.createEntityManager();
    }
    public EntityManager getEntityManager(){
        return em;
    }
}
