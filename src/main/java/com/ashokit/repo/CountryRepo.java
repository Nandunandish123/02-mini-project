package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.Country;
@Repository
public interface CountryRepo extends JpaRepository<Country, Integer>{

}
