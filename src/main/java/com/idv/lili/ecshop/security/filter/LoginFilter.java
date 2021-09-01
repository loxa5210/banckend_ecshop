package com.idv.lili.ecshop.security.filter;

import com.idv.lili.ecshop.security.service.UserDetailServiceImpl;
import com.idv.lili.ecshop.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定義的認證過濾器，對toke 進行驗證
 */
@Component
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@SneakyThrows @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");

		log.debug("authorization : "+ authorization);

		if (StringUtils.isNotBlank(authorization)) {

			Claims claims = JwtUtil.parse(authorization);

			if (claims != null) {

				// 从`JWT`中提取出之前存储好的用户名
				String email = claims.getSubject();
				// 查询出用户对象
				UserDetails user = userDetailService.loadUserByUsername(email);

				// 手动组装一个认证对象
				Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
				// 将认证对象放到上下文中
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
}