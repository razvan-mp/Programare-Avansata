import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");
            Database.getConnection().commit();

            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            System.out.println(europeId);

            String continentName = continents.findById(1);
            System.out.println(continentName);

            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);

            System.out.println(countries.findAll(continents.findByName("Europe")));

            Database.getConnection().commit();

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
//            Database.rollback();
        }
    }
}
