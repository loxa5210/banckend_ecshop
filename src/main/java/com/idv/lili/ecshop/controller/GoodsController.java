package com.idv.lili.ecshop.controller;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.entity.Goods;
import com.idv.lili.ecshop.model.request.GoodsRequest;
import com.idv.lili.ecshop.model.request.QueryInfoRequest;
import com.idv.lili.ecshop.model.response.BaseResponse;
import com.idv.lili.ecshop.service.GoodsService;
import com.idv.lili.ecshop.utils.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	/**
	 * 新增商品
	 *
	 * @param goodsRequest 新增商品請求物件
	 * @return BaseResponse
	 */
	@PostMapping("/add")
	public ResponseEntity<BaseResponse> addProduct(@RequestBody @Valid GoodsRequest goodsRequest) {
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(goodsService.addGoods(goodsRequest), ReturnMessageConstant.SAVE_SUCCESSFUL, null));

	}

	/**
	 * 更新商品
	 *
	 * @param goodsId      商品Id
	 * @param goodsRequest 更新商品物件請求物件
	 * @return BaseResponse
	 */
	@PutMapping("/update/{goodsId}")
	public ResponseEntity<BaseResponse> updateProduct(@PathVariable long goodsId, @Valid @RequestBody GoodsRequest goodsRequest) {
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(goodsService.updateGoods(goodsId, goodsRequest), ReturnMessageConstant.UPDATE_SUCCESSFUL, null));

	}

	/**
	 * 取得某商品
	 *
	 * @param goodsId 商品Id
	 * @return BaseResponse
	 */
	@GetMapping("/{goodsId}")
	public ResponseEntity<BaseResponse> getProduct(@PathVariable long goodsId) {
		return ResponseEntity.ok().body(RestfulUtil.getSuccess(goodsService.getGoods(goodsId), ReturnMessageConstant.QUERY_SUCCESSFUL, null));

	}

	/**
	 * 取得商品清單
	 *
	 * @param query    商品名稱
	 * @param pageNum  第幾頁
	 * @param pageSize 每頁筆數
	 * @return BaseResponse
	 */
	@GetMapping("/list")
	public ResponseEntity<BaseResponse> getProductList(@RequestParam String query, int pageNum, int pageSize) {
		Page<Goods> goodsList = goodsService.getGoodsList(new QueryInfoRequest(query, pageNum, pageSize));

		return ResponseEntity.ok().body(RestfulUtil.getSuccess(goodsList.getContent(), ReturnMessageConstant.QUERY_SUCCESSFUL, goodsList.getTotalElements()));

	}
}
