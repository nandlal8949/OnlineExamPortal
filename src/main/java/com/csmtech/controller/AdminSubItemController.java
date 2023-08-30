package com.csmtech.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csmtech.entity.Items;
import com.csmtech.entity.SubItem;
import com.csmtech.service.ItemService;
import com.csmtech.service.SubItemService;

@Controller
public class AdminSubItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SubItemService subItemService;
	
	@GetMapping("admin/subitems")
	public String getSubItemList(Model model) {
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		List<SubItem> subItemList = subItemService.getSubItemList();
		List<Items> itemList = itemService.getItemList();
		
		hashMap.put("SUBITEMLIST", subItemList);
		hashMap.put("ITEMS", itemList);
		hashMap.put("SUBMITBUTTON", "ADD SUBITEM");
		
		model.addAllAttributes(hashMap);
		return "admin/subitems";
	}
	
	@PostMapping("admin/subitems")
	public String postSubitem(@ModelAttribute SubItem subItem) {
		if(subItem.getSubItemId() != null) {
			subItem.setSubItemId(subItem.getSubItemId());
		}
			subItemService.saveSubItem(subItem);
		return "redirect:/admin/subitems";
	}
	
	@GetMapping("admin/subitems/delete/{subItemId}")
	public String deleteSubItem(@PathVariable Integer subItemId) {
		subItemService.deleteSubItem(subItemId);
		return "redirect:/admin/subitems";
	}
	
	@GetMapping("admin/subitems/update/{subItemId}")
	public String updateSubItem(@PathVariable Integer subItemId, Model model) {
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		List<SubItem> subItemList = subItemService.getSubItemList();
		List<Items> itemList = itemService.getItemList();
		SubItem sItem = subItemService.updateSubItem(subItemId);
		
		hashMap.put("SUBITEMLIST", subItemList);
		hashMap.put("ITEMS", itemList);
		hashMap.put("SUBITEM", sItem);
		hashMap.put("SUBMITBUTTON", "UPDATE SUBITEM");
		
		model.addAllAttributes(hashMap);
		
		return "admin/subitems";
	}
	
}
