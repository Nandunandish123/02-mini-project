package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.State;
@Repository
public interface StateRepo extends JpaRepository<State, Integer>{

	public List<State> findByCountryId(Integer countryid);
}
