package com.api.wheatherproyect.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.wheatherproyect.model.entity.Queries;

@Repository
public interface QueriesRepository extends JpaRepository<Queries, Long>{
}
