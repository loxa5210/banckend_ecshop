package com.idv.lili.ecshop.controller;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.entity.Category;
import com.idv.lili.ecshop.model.response.BaseResponse;
import com.idv.lili.ecshop.service.CategoryService;
import com.idv.lili.ecshop.utils.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 新增/更新 分類
	 *
	 * @param category 分類物件
	 * @return BaseResponse
	 */
	@PostMapping("/update")
	public ResponseEntity<BaseResponse> updateCategory(@RequestBody Category category) {
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(categoryService.updateCategory(category), ReturnMessageConstant.SAVE_SUCCESSFUL, null));

	}

	/**
	 * 取得分類樹
	 *
	 * @return BaseResponse
	 */
	@GetMapping("/tree/list")
	public ResponseEntity<BaseResponse> getCategoryTreeList() {
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(categoryService.getCategoryTreeList(), ReturnMessageConstant.QUERY_SUCCESSFUL, null));

	}

	/**
	 * 取得分類清單
	 *
	 * @param pageNum  第幾頁
	 * @param pageSize 每頁筆數
	 * @return BaseResponse
	 */
	@GetMapping("/list")
	public ResponseEntity<BaseResponse> getCategoryList(@RequestParam int pageNum, int pageSize) {
		Page<Category> categoryPage = categoryService.getCategoryList(pageNum, pageSize);
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(categoryPage.getContent(), ReturnMessageConstant.QUERY_SUCCESSFUL, categoryPage.getTotalElements()));

	}

	/**
	 * 移除分類
	 *
	 * @param categoryId 分類Id
	 * @return BaseResponse
	 */
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<BaseResponse> removeCategory(@PathVariable Long categoryId) {
		if (categoryService.removerById(categoryId)) {
			return ResponseEntity.ok().body(RestfulUtil.getSuccess(true, ReturnMessageConstant.DELETE_SUCCESSFUL, null));
		} else {
			return ResponseEntity.ok().body(RestfulUtil.getSuccess(false, ReturnMessageConstant.DELETE_FIELD_CATEGORY, null));

		}

	}

}
