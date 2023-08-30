package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.SubItem;
import com.csmtech.repository.SubItemRepository;

@Service
public class SubItemServiceImpl implements SubItemService {

	@Autowired
	private SubItemRepository subItemRepository;
	@Override
	public List<SubItem> getSubItemList() {
		return subItemRepository.findAll();
	}
	@Override
	public SubItem saveSubItem(SubItem subItem) {
		return subItemRepository.save(subItem);
	}
	@Override
	public void deleteSubItem(Integer subItemId) {
		subItemRepository.deleteById(subItemId);
	}
	@Override
	public SubItem updateSubItem(Integer subItemId) {
		return subItemRepository.findById(subItemId).get();
	}
	@Override
	public List<SubItem> getSubItemListByitemId(Integer itemId) {
		return subItemRepository.getSubItemListByItemId(itemId);
	}

}
