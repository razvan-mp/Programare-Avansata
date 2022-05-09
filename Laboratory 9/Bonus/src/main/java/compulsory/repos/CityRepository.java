package compulsory.repos;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compulsory.entities.BonusCityEntity;
import org.chocosolver.solver.variables.IntVar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityRepository extends DataRepository<BonusCityEntity, Integer> {
    private static final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory();

    public void create(BonusCityEntity city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public BonusCityEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (BonusCityEntity) entityManager.createQuery("select c from BonusCityEntity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public BonusCityEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (BonusCityEntity) entityManager.createNamedQuery("BonusCity.findByName").setParameter("name", name).getResultList().get(0);
    }

    public void insertCities() {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/databook2018.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        records.remove(0);

        for (List<String> city : records) {
            BonusCityEntity cityEntity = new BonusCityEntity();
            System.out.println(city.get(1).replaceAll("'", " "));
            cityEntity.setName(city.get(1).replaceAll("'", " "));
            cityEntity.setCountry("United Kingdom");
            cityEntity.setSisterName(city.get(2).replaceAll("'", " "));
            System.out.println(city.get(2).replaceAll("'", " "));
            cityEntity.setSisterCountry(city.get(3).replaceAll("'", " "));
            System.out.println(city.get(3).replaceAll("'", " "));
            System.out.println();
            create(cityEntity);
        }
    }
}

