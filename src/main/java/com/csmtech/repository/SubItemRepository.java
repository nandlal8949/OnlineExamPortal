package com.csmtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.SubItem;

@Repository
public interface SubItemRepository extends JpaRepository<SubItem, Integer> {

	@Query("From SubItem where items.itemId = :itemId")
	List<SubItem> getSubItemListByItemId(Integer itemId);

}
