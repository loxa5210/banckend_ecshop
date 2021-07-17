package com.idv.lili.ecshop.service.impl;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.entity.Users;
import com.idv.lili.ecshop.excption.ApiException;
import com.idv.lili.ecshop.model.request.LoginRequest;
import com.idv.lili.ecshop.model.request.QueryInfoRequest;
import com.idv.lili.ecshop.model.request.UsersRequest;
import com.idv.lili.ecshop.model.response.UsersResponse;
import com.idv.lili.ecshop.repository.UsersRepository;
import com.idv.lili.ecshop.service.UsersService;
import com.idv.lili.ecshop.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Users getCurrentUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return usersRepository.findTopByEmailIs(authentication.getName());
	}

	@Override public Users getUserInfo(String email) {
		return usersRepository.findTopByEmailIs(email);
	}

	@Override public Users getUserInfo(long usersId) {
		Optional<Users> usersOptional = usersRepository.findById(usersId);

		if (usersOptional.isPresent()) {
			return usersOptional.get();

		}
		throw new NullPointerException(ReturnMessageConstant.USERS_NOTFOUND);
	}

	@Override
	public UsersResponse checkLoginUsers(LoginRequest loginRequest) throws ApiException {
		Users users = usersRepository.findTopByEmailIs(loginRequest.getEmail());

		if (users == null) {
			//找不到該用戶
			throw new ApiException("找不到該用戶");
		} else if (!passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
			throw new ApiException("密碼錯誤");
		} else {
			UsersResponse usersResponse = new UsersResponse();

			usersResponse.setEmail(users.getEmail());
			usersResponse.setToken("Bearer  " + JwtUtil.generate(users.getEmail()));
			return usersResponse;

		}

	}

	@Override
	public Users addUsers(UsersRequest usersRequest) {
		Users users = new Users();

		BeanUtils.copyProperties(usersRequest, users);
		users.setPassword(passwordEncoder.encode(usersRequest.getPassword()));

		String email = getCurrentUserInfo().getEmail();
		users.setCreator(email);
		users.setModifier(email);
		return usersRepository.save(users);
	}

	@Override
	public Users modifyUsername(long usersId, String username) {
		Optional<Users> usersOptional = usersRepository.findById(usersId);
		if (usersOptional.isPresent()) {
			Users users = usersOptional.get();
			users.setUsername(username);
			users.setModifier(getCurrentUserInfo().getEmail());
			return usersRepository.save(users);

		}
		throw new NullPointerException(ReturnMessageConstant.USERS_NOTFOUND);

	}

	@Override
	public Page<Users> getUsersList(QueryInfoRequest queryInfoRequest) {

		PageRequest pageRequest = PageRequest.of(queryInfoRequest.getPageNum() - 1
			, queryInfoRequest.getPageSize(), Sort.by("email").ascending());

		if (StringUtils.isNotBlank(queryInfoRequest.getQuery())) {
			return usersRepository.findByEmailLikeOrUsernameLike(queryInfoRequest.getQuery(), queryInfoRequest.getQuery(), pageRequest);
		}

		return usersRepository.findAll(pageRequest);
	}

	@Override
	public Users modifyStatus(long usersId) {
		Optional<Users> usersOptional = usersRepository.findById(usersId);
		if (usersOptional.isPresent()) {
			Users users = usersOptional.get();
			users.setStatus(Boolean.FALSE);
			users.setModifier(getCurrentUserInfo().getEmail());
			return usersRepository.save(users);

		}
		throw new NullPointerException(ReturnMessageConstant.USERS_NOTFOUND);
	}

}
