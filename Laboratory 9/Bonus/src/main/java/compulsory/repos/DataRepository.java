package compulsory.repos;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {
    private final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public T findById(ID id) {
        return null;
    }

    public T findByName(String name) {
        return null;
    }

    public void persist(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }


}
