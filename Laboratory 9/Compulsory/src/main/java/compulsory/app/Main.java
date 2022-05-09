package compulsory.app;

import compulsory.repos.model.CityRepository;
import compulsory.utilities.ConstraintSolver;

public class Main {
    public static void main(String[] args) {
//        CityRepository cityRepository = new CityRepository();
//
//        System.out.println(cityRepository.findById(1));
//        System.out.println(cityRepository.findByName("Ankara"));
//
//        CityEntity city = new CityEntity();
//        city.setCapital(false);
//        city.setCountry("Romania");
//        city.setName("Focsani");

//        cityRepository.create(city);

//        long startTime = System.nanoTime();
//        cityRepository.insertCities();
//        long endTime = System.nanoTime();
//        System.out.println("Cities insertion time: " +
//                TimeUnit.SECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)
//                + "seconds");

        ConstraintSolver.solver('M', 39_878_999, 39_879_001);

//        System.out.println(cityRepository.findByName("Odorhei"));
//
//        CountryRepository countryRepository = new CountryRepository();
//        System.out.println(countryRepository.findByName("Italy"));
//
//        ContinentRepository continentRepository = new ContinentRepository();
//        System.out.println(continentRepository.findByName("Australia"));
    }
}
