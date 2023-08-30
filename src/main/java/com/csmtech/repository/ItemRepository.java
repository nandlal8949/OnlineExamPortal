package com.csmtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Integer> {

}
