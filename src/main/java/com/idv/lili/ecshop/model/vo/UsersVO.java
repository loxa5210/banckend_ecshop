package com.idv.lili.ecshop.model.vo;

import com.idv.lili.ecshop.entity.Users;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Accessors
public class UsersVO extends User {

	private Users users;

	public UsersVO(Users users, Collection<? extends GrantedAuthority> authorities) {
		// 必须调用父类的构造方法，以初始化用户名、密码、权限
		super(users.getEmail(), users.getPassword(), authorities);
		this.users = users;
	}

}
