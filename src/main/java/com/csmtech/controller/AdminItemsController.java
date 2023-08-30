package com.csmtech.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csmtech.entity.Items;
import com.csmtech.service.ItemService;
import com.csmtech.service.SubItemService;

@Controller
public class AdminItemsController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SubItemService subItemService;
	
	//testing
	@GetMapping("admin/items")
	public String getItemList11(Model model){
		List<Items> itemList = itemService.getItemList();
		model.addAttribute("ITEMLIST", itemList);
		return "admin/items";
	}
	
//	@GetMapping("items1")
//	public void getItemList(HttpServletResponse resp) throws IOException {
//		List<Items> itemList = itemService.getItemList();
//		String itemList1 = itemList.stream().map(t-> "<tr><td>"+t.getItemId()+"</td><td>"+t.getItemName()+"</td><td> <span class='text-primary' onclick='editItem("+t.getItemId()+")'>Edit</span> &nbsp;&nbsp; <span class='text-primary' onclick='deleteItem("+t.getItemId()+")'>Delete</span> </td> </tr>").collect(Collectors.joining());
//		resp.setContentType("text/html");
//		resp.getWriter().print(itemList1);
//	}
	
	@PostMapping("items1")
	public void dataSave(@ModelAttribute Items item, HttpServletResponse resp) throws IOException {
		if (item.getItemId() != null) {
			item.setItemId(item.getItemId());
		}
		
		resp.setContentType("text/html");
		boolean anyMatch = itemService.getItemList().stream().anyMatch(t->t.getItemName() != null &&  t.getItemName().equalsIgnoreCase(item.getItemName()));
		
		if(anyMatch == true) {
			resp.getWriter().print("warning");
		}else if(item.getItemName().length() < 3){
			resp.getWriter().print("lengthError");
		}else {
			try {
				itemService.saveItem(item);			
				resp.getWriter().print("success");
			} catch (Exception e) {
				resp.getWriter().print("failed");
			}			
		}
	}
		
	
	@GetMapping("items/delete/{itemId}")
	public void deleteItem1(@PathVariable Integer itemId, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
			boolean anyMatch = subItemService.getSubItemList().stream().anyMatch(t-> t.getItems().getItemId() == itemId);
			if(anyMatch == true) {
				resp.getWriter().print("failed");
			}else {
				itemService.deleteItem(itemId);
				resp.getWriter().print("success");				
			}
	}
	
	@GetMapping("items/edit/{itemId}")
	public void findItem22(@PathVariable Integer itemId, HttpServletResponse resp ) throws IOException {
		resp.setContentType("text/html");
		try {
			Items item = itemService.findItem(itemId);
			JSONObject jsonObject = new JSONObject(item);
			resp.getWriter().print(jsonObject);
		} catch (Exception e) {
			resp.getWriter().print("failed");
		}
	}
	

}
