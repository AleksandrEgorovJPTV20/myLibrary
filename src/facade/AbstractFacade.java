package facade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public abstract class AbstractFacade<T> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myLibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    private Class<T> entityClass;
    
    protected abstract EntityManager getEntityManager();
    
    public void create(T entity) {
        tx.begin();
            em.persist(entity);
        tx.commit();
    }
    public T find(Long id){
       return em.find(entityClass, id);
    }
    public List<T> findAll(){
        return em.createQuery("SELECT entity FROM "+entityClass.getName()+" entity").getResultList();
    }
    public void edit(T entity){
        tx.begin();
            em.merge(entity);
        tx.commit();
    }
    
}
