package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request,Long>{

}
