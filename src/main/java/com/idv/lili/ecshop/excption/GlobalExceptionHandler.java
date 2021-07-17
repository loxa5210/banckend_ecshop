package com.idv.lili.ecshop.excption;

import com.idv.lili.ecshop.model.response.BaseResponse;
import com.idv.lili.ecshop.utils.RestfulUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public BaseResponse handler(RuntimeException e) {
		log.error(e.toString());
		log.error(e.getMessage());
		return RestfulUtil.getFailure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public BaseResponse handler(NullPointerException e) {
		log.error(e.toString());
		log.error(e.getMessage());
		return RestfulUtil.getFailure(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = JwtFailureException.class)
	public BaseResponse handler(JwtFailureException e) {
		log.error(e.toString());
		log.error(e.getMessage());
		return RestfulUtil.getFailure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ApiException.class)
	public BaseResponse handler(ApiException e) {
		log.error(e.toString());
		log.error(e.getMessage());
		return RestfulUtil.getFailure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public BaseResponse handler(MethodArgumentNotValidException e) {
		log.error(e.toString());
		log.error(e.getMessage());

		StringBuffer stringBuffer = new StringBuffer();
		e.getBindingResult().getAllErrors()
			.forEach(objectError -> stringBuffer.append(objectError.getDefaultMessage()).append(","));

		return RestfulUtil.getFailure(null, stringBuffer.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());

	}

}
