package com.idv.lili.ecshop.security.service;

import com.idv.lili.ecshop.entity.Users;
import com.idv.lili.ecshop.model.vo.UsersVO;
import com.idv.lili.ecshop.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsersService usersService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users userInfo = usersService.getUserInfo(username);

		return new UsersVO(userInfo, Collections.emptyList());
	}
}
