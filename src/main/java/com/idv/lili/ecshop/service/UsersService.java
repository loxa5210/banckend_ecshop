package com.idv.lili.ecshop.service;

import com.idv.lili.ecshop.entity.Users;
import com.idv.lili.ecshop.excption.ApiException;
import com.idv.lili.ecshop.model.request.LoginRequest;
import com.idv.lili.ecshop.model.request.QueryInfoRequest;
import com.idv.lili.ecshop.model.request.UsersRequest;
import com.idv.lili.ecshop.model.response.UsersResponse;
import org.springframework.data.domain.Page;

public interface UsersService {
	/**
	 * 取得當前使用者
	 */
	Users getCurrentUserInfo();

	/**
	 * 使用信箱取得使用者資訊
	 *
	 * @param email 信箱
	 * @return Users
	 */
	Users getUserInfo(String email);

	/**
	 * 使用usersId取得使用者資訊
	 *
	 * @param usersId 使用者Id
	 * @return Users
	 */
	Users getUserInfo(long usersId);

	/**
	 * @param loginRequest 登入物件
	 * @return UsersResponse
	 * @throws ApiException
	 */
	UsersResponse checkLoginUsers(LoginRequest loginRequest) throws ApiException;

	/**
	 * 新增使用者
	 *
	 * @param usersRequest users請求物件
	 * @return
	 */
	Users addUsers(UsersRequest usersRequest);

	/**
	 * 修改某使用者username
	 *
	 * @param username 使用者名稱
	 * @return Users
	 */
	Users modifyUsername(long usersId, String username);

	/**
	 * 取的所有會員列表
	 */
	Page<Users> getUsersList(QueryInfoRequest queryInfoRequest);

	/**
	 * 修改某使用者狀態為無效
	 *
	 * @return Users
	 */
	Users modifyStatus(long usersId);
}
