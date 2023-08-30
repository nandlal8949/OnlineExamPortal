package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Items;

public interface ItemService {

	List<Items> getItemList();

	Items saveItem(Items item);

	void deleteItem(Integer itemId);

	Items findItem(Integer itemId);

}
