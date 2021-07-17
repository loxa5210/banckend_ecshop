package com.idv.lili.ecshop.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class BaseResponse {

	private Object data;

	private int status;

	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long total;

}
