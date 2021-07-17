package com.idv.lili.ecshop.utils;

import com.idv.lili.ecshop.model.response.BaseResponse;
import org.springframework.http.HttpStatus;

public final class RestfulUtil {

	public static BaseResponse getSuccess(Object data, String message, Long total) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(data)
			.setStatus(HttpStatus.OK.value())
			.setTotal(total)
			.setMessage(message);

		return baseResponse;
	}

	public static BaseResponse getFailure(Object data, String message, int status) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(data)
			.setStatus(status)
			.setMessage(message);
		return baseResponse;
	}
}
