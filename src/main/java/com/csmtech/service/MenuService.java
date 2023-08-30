package com.csmtech.service;

import java.util.List;

import com.csmtech.bean.MenuText;

public interface MenuService {

	List<MenuText> getAllMenus(Integer roleId);

	List<MenuText> getOtherMenus(Integer roleId);

	List<MenuText> getCandidateMenus();

}
