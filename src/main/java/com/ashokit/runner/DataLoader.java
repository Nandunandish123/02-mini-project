package com.ashokit.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashokit.entity.City;
import com.ashokit.entity.Country;
import com.ashokit.entity.State;
import com.ashokit.repo.CityRepo;
import com.ashokit.repo.CountryRepo;
import com.ashokit.repo.StateRepo;
@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CountryRepo countryrepo;
	
	@Autowired
	private StateRepo staterepo;
	
	@Autowired
	private CityRepo cityrepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		countryrepo.deleteAll();
		staterepo.deleteAll();
		cityrepo.deleteAll();
		
		Country c1 = new Country(1, "India");
		Country c2 = new Country(2, "usa");
		
		countryrepo.saveAll(Arrays.asList(c1,c2));
		
		State s1 = new State(1, "Ap", 1);
		State s2 = new State(2, "TG", 1);
		
		State s3 = new State(3, "texas", 2);
		State s4 = new State(4, "new jersy", 2);
		
		staterepo.saveAll(Arrays.asList(s1,s2,s3,s4));
		
		City ci1 = new City(1, "anantapur", 1);
		City ci2 = new City(2, "guntur", 1);
		
		City ci3 = new City(3, "kammam", 2);
		City ci4 = new City(4, "hyd", 2);
		
		City ci5 = new City(5, "queens", 3);
		City ci6 = new City(2, "broklyn", 3);
		
		City ci7 = new City(3, "washington dc", 4);
		City ci8 = new City(4, "chicago", 4);
		
		cityrepo.saveAll(Arrays.asList(ci1,ci2,ci3,ci4,ci5,ci6,ci7,ci8));
	}

}
