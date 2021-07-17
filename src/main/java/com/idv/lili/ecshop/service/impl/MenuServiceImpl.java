package com.idv.lili.ecshop.service.impl;

import com.idv.lili.ecshop.entity.Menu;
import com.idv.lili.ecshop.model.response.MenuResponse;
import com.idv.lili.ecshop.repository.MenuRepository;
import com.idv.lili.ecshop.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu updateMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public List<MenuResponse> getMenuList() {

		//找母菜單
		List<Menu> parentList = menuRepository.findByIsChildIsFalse();
		//找出子菜單
		List<Menu> childList = menuRepository.findByIsChildIsTrue();

		return parentList.stream().map(parent -> {
			MenuResponse menuResponse = new MenuResponse();
			BeanUtils.copyProperties(parent, menuResponse);

			List<MenuResponse> collect = childList.stream().filter(child -> child.getParentId().equals(parent.getMenuId()))
				.map(child -> {
					MenuResponse menuChildResponse = new MenuResponse();
					BeanUtils.copyProperties(child, menuChildResponse);
					return menuChildResponse;
				})
				.sorted((Comparator.comparingInt(Menu::getPriority)))
				.collect(Collectors.toList());

			menuResponse.setChildren(collect);

			return menuResponse;
		}).collect(Collectors.toList());
	}
}
