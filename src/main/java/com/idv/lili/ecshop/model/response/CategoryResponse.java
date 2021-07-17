package com.idv.lili.ecshop.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idv.lili.ecshop.entity.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors
@Data
public class CategoryResponse extends Category {

	/**
	 * 子
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<CategoryResponse> children;

}
