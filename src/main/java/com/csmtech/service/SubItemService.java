package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.SubItem;

public interface SubItemService {

	List<SubItem> getSubItemList();

	SubItem saveSubItem(SubItem subItem);

	void deleteSubItem(Integer subItemId);

	SubItem updateSubItem(Integer subItemId);

	List<SubItem> getSubItemListByitemId(Integer itemId);

}
