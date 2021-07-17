package com.idv.lili.ecshop.service;

import com.idv.lili.ecshop.entity.Goods;
import com.idv.lili.ecshop.model.request.GoodsRequest;
import com.idv.lili.ecshop.model.request.QueryInfoRequest;
import org.springframework.data.domain.Page;

public interface GoodsService {
	/**
	 * 新增 商品
	 *
	 * @param goodsRequest 商品請求物件
	 * @return Goods
	 */
	Goods addGoods(GoodsRequest goodsRequest);

	/**
	 * 更新 商品
	 *
	 * @param GoodsId      商品Id
	 * @param goodsRequest 商品請求物件
	 * @return Goods
	 */
	Goods updateGoods(long GoodsId, GoodsRequest goodsRequest);

	/**
	 * 取得某商品
	 *
	 * @param GoodsId 商品Id
	 * @return Goods
	 */
	Goods getGoods(long GoodsId);

	/**
	 * 取得 商品
	 *
	 * @param queryInfoRequest
	 * @return List<Goods>
	 */
	Page<Goods> getGoodsList(QueryInfoRequest queryInfoRequest);

	/**
	 * 確認該分類是否有商品使用
	 *
	 * @param categoryId 分類Id
	 * @return Boolean
	 */
	Boolean existsByCategoryId(Long categoryId);
}
