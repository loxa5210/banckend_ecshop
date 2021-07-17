package com.idv.lili.ecshop.controller;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.entity.Menu;
import com.idv.lili.ecshop.model.response.BaseResponse;
import com.idv.lili.ecshop.model.response.MenuResponse;
import com.idv.lili.ecshop.service.MenuService;
import com.idv.lili.ecshop.utils.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 新增/更新菜單
	 *
	 * @param menu 菜單物件
	 * @return BaseResponse
	 */
	@PostMapping("/update")
	public ResponseEntity<BaseResponse> updateMenu(@RequestBody Menu menu) {

		return ResponseEntity.ok().body(RestfulUtil.getSuccess(menuService.updateMenu(menu), ReturnMessageConstant.SAVE_SUCCESSFUL, null));

	}

	/**
	 * 取得菜單清單
	 *
	 * @return BaseResponse
	 */
	@GetMapping("/list")
	public ResponseEntity<BaseResponse> getMenuList() {
		List<MenuResponse> menuList = menuService.getMenuList();
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(menuList, ReturnMessageConstant.QUERY_SUCCESSFUL, (long) menuList.size()));

	}

}
