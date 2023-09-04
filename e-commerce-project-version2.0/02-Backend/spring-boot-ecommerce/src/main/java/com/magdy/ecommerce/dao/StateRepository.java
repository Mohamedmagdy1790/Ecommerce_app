package com.magdy.ecommerce.dao;

import com.magdy.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State,Long> {
  //http://localhost:8080/api/states/search/findByCountryCode?codes=BR
    //he will make join between this table and the other one to get states
  List<State> findByCountryCode(@Param("code") String code);}
