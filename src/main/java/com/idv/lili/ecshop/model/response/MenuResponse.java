package com.idv.lili.ecshop.model.response;

import com.idv.lili.ecshop.entity.Menu;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors
@Data
public class MenuResponse extends Menu {

	/**
	 * 子
	 */
	private List<MenuResponse> children;

}
