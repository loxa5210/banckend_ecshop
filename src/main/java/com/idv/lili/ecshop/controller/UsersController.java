package com.idv.lili.ecshop.controller;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.entity.Users;
import com.idv.lili.ecshop.model.request.QueryInfoRequest;
import com.idv.lili.ecshop.model.request.UsersRequest;
import com.idv.lili.ecshop.model.response.BaseResponse;
import com.idv.lili.ecshop.service.UsersService;
import com.idv.lili.ecshop.utils.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UsersService usersService;

	/**
	 * 取的某使用者資訊
	 *
	 * @param usersId 使用者Id
	 * @return BaseResponse
	 */
	@GetMapping("/{usersId}")
	public ResponseEntity<BaseResponse> getUserInfo(@PathVariable long usersId) {
		return ResponseEntity.ok(RestfulUtil.getSuccess(usersService.getUserInfo(usersId), ReturnMessageConstant.QUERY_SUCCESSFUL, null));
	}

	/**
	 * 取得使用者列表
	 *
	 * @param pageNum  第幾頁
	 * @param pageSize 每頁筆數
	 * @param query    email or username
	 * @return BaseResponse
	 */
	@GetMapping("/list")
	public ResponseEntity<BaseResponse> getUserList(@RequestParam String query, int pageNum, int pageSize) {
		Page<Users> usersList = usersService.getUsersList(new QueryInfoRequest(query, pageNum, pageSize));
		return ResponseEntity.ok(RestfulUtil.getSuccess(usersList.getContent(), ReturnMessageConstant.QUERY_SUCCESSFUL, usersList.getTotalElements()));
	}

	/**
	 * 新增使用者
	 *
	 * @param usersRequest 使用者物件
	 * @return BaseResponse
	 */
	@PostMapping("/add")
	public ResponseEntity<BaseResponse> addUsers(@RequestBody UsersRequest usersRequest) {
		return ResponseEntity.ok(RestfulUtil.getSuccess(usersService.addUsers(usersRequest), ReturnMessageConstant.SAVE_SUCCESSFUL, null));
	}

	/**
	 * 修改使用者名稱
	 *
	 * @param usersId  使用者Id
	 * @param username 使用者名稱
	 * @return BaseResponse
	 */
	@PutMapping("/update/{usersId}")
	public ResponseEntity<BaseResponse> modifyUsername(@PathVariable long usersId, @RequestBody Map<String, String> username) {
		return ResponseEntity.ok(RestfulUtil.getSuccess(usersService.modifyUsername(usersId, username.get("username")), ReturnMessageConstant.UPDATE_SUCCESSFUL, null));
	}

	/**
	 * 修改使用者狀態為無效
	 *
	 * @param usersId 使用者Id
	 * @return BaseResponse
	 */
	@PutMapping("/status/{usersId}")
	public ResponseEntity<BaseResponse> modifyStatus(@PathVariable long usersId) {
		return ResponseEntity.ok(RestfulUtil.getSuccess(usersService.modifyStatus(usersId), ReturnMessageConstant.UPDATE_SUCCESSFUL, null));
	}

}
