package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.City;
@Repository
public interface CityRepo extends JpaRepository<City, Integer>{

	public List<City> findByStateId(Integer stateId);
}
