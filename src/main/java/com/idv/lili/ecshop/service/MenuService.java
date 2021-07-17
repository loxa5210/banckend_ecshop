package com.idv.lili.ecshop.service;

import com.idv.lili.ecshop.entity.Menu;
import com.idv.lili.ecshop.model.response.MenuResponse;

import java.util.List;

public interface MenuService {
	/**
	 * 新增/更新 菜單
	 *
	 * @param menu 菜單物件
	 * @return Menu
	 */
	Menu updateMenu(Menu menu);

	/**
	 * 取得 菜單
	 *
	 * @return List<MenuResponse>
	 */
	List<MenuResponse> getMenuList();

}
