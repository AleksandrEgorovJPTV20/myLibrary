package facade;

import entity.Author;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class AuthorFacade extends AbstractFacade<Author>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myLibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public AuthorFacade(Class<Author> entityClass) {
        super(entityClass);
    }
}
