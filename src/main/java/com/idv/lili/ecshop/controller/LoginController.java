package com.idv.lili.ecshop.controller;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.excption.ApiException;
import com.idv.lili.ecshop.model.request.LoginRequest;
import com.idv.lili.ecshop.model.response.BaseResponse;
import com.idv.lili.ecshop.model.response.UsersResponse;
import com.idv.lili.ecshop.service.UsersService;
import com.idv.lili.ecshop.utils.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@Autowired
	private UsersService usersService;

	/**
	 * 登入
	 *
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/api/login")
	public ResponseEntity<BaseResponse> login(@RequestBody @Validated LoginRequest loginRequest) throws ApiException {

		UsersResponse usersResponse = usersService.checkLoginUsers(loginRequest);

		return ResponseEntity.ok(RestfulUtil.getSuccess(usersResponse, ReturnMessageConstant.LOGIN_SUCCESSFUL, null));
	}

}
