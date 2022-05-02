import compulsory.entities.ContinentsEntity;
import compulsory.entities.HcitiesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            HcitiesEntity city = new HcitiesEntity();
            city.setName("Random");
            city.setCapital(true);
            city.setCountry("Romania");
            city.setId(900);
            city.setLatitude(0.0);
            city.setLongitude(0.0);
            entityManager.persist(city);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
