package com.idv.lili.ecshop.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors
@Data
public class QueryInfoRequest {

	/**
	 *
	 */
	private String query;

	private Integer pageNum;

	private Integer pageSize;

	public QueryInfoRequest() {
	}

	public QueryInfoRequest(String query, Integer pageNum, Integer pageSize) {
		this.query = query;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
}
