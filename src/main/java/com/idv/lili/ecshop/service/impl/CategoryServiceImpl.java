package com.idv.lili.ecshop.service.impl;

import com.idv.lili.ecshop.entity.Category;
import com.idv.lili.ecshop.model.response.CategoryResponse;
import com.idv.lili.ecshop.repository.CategoryRepository;
import com.idv.lili.ecshop.service.CategoryService;
import com.idv.lili.ecshop.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private GoodsService goodsService;

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<CategoryResponse> getCategoryTreeList() {

		//取的全部清單
		List<Category> all = categoryRepository.findAll();

		//找出頂程分類
		List<Category> parentCategoryList = all.stream().filter(category -> category.getIsChild().equals(Boolean.FALSE)).collect(Collectors.toList());
		//找出子曾分類
		List<Category> allChildList = all.stream().filter(category -> category.getIsChild().equals(Boolean.TRUE)).collect(Collectors.toList());

		return parentCategoryList.stream().map(parentCategory -> {
			CategoryResponse categoryResponse = new CategoryResponse();
			BeanUtils.copyProperties(parentCategory, categoryResponse);

			categoryResponse.setChildren(getChildCategoryList(allChildList, parentCategory.getCategoryId()));

			return categoryResponse;
		}).collect(Collectors.toList());
	}

	@Override
	public Page<Category> getCategoryList(int pageNum, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);// 依CREATE_TIME欄位降冪排序

		return categoryRepository.findAll(pageRequest);
	}

	@Override
	public Boolean removerById(Long categoryId) {
		if (goodsService.existsByCategoryId(categoryId)) {
			return Boolean.FALSE;
		} else {
			categoryRepository.deleteById(categoryId);
			return Boolean.TRUE;
		}
	}

	private List<CategoryResponse> getChildCategoryList(List<Category> allChildList, Long parentCategoryId) {

		//找出小孩
		return allChildList.stream().filter(child -> parentCategoryId.equals(child.getParentId()))
			.map(category -> {
				CategoryResponse childCategoryResponse = new CategoryResponse();
				BeanUtils.copyProperties(category, childCategoryResponse);

				if (category.getIsChild().equals(Boolean.TRUE)) {

					childCategoryResponse.setChildren(getChildCategoryList(allChildList, category.getCategoryId()));
				}
				return childCategoryResponse;
			}).collect(Collectors.toList());
	}
}
