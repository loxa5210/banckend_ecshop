package com.idv.lili.ecshop.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Data
public class GoodsRequest {
	/**
	 * 商品名稱
	 */
	@NotBlank(message = "商品名稱為必填")
	private String name;
	/**
	 * 商品價格
	 */
	@NotNull(message = "商品價格為必填")
	@Min(value = 1, message = "商品價格為必填大於0")
	private Long price;
	/**
	 * 數量
	 */
	@NotNull(message = "商品數量為必填")
	@Min(value = 1, message = "商品數量為必填大於0")
	private Integer quantity;
	/**
	 * 內容
	 */
	private String context;

	/**
	 * 分類Id
	 */
	@NotNull(message = "商品分類為必填")
	private Long categoryId;

}
