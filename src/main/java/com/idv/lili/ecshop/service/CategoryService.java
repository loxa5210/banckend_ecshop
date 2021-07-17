package com.idv.lili.ecshop.service;

import com.idv.lili.ecshop.entity.Category;
import com.idv.lili.ecshop.model.response.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

	/**
	 * 新增/更新 分類
	 *
	 * @param category 分類物件
	 * @return Category
	 */
	Category updateCategory(Category category);

	/**
	 * 取的分類樹清單
	 *
	 * @return List<CategoryResponse>
	 */
	List<CategoryResponse> getCategoryTreeList();

	/**
	 * 取的分類清單
	 *
	 * @param pageSize ;
	 * @param pageNum  ;
	 * @return Page<Category>
	 */
	Page<Category> getCategoryList(int pageNum, int pageSize);

	/**
	 * 移除某分類
	 *
	 * @param categoryId 分類Id
	 * @return Boolean
	 */
	Boolean removerById(Long categoryId);
}
