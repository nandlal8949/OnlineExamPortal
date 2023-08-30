package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Items;
import com.csmtech.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Override
	public List<Items> getItemList() {
		return itemRepository.findAll();
	}
	@Override
	public Items saveItem(Items item) {
		return itemRepository.save(item);
	}
	@Override
	public void deleteItem(Integer itemId) {
		itemRepository.deleteById(itemId);		
	}
	@Override
	public Items findItem(Integer itemId) {
		return itemRepository.findById(itemId).get();
	}

}
