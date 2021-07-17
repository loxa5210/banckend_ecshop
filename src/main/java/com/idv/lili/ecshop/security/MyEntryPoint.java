package com.idv.lili.ecshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.utils.RestfulUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 認證異常處理 AuthenticationEntryPoint
 */
public class MyEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		ObjectMapper objectMapper = new ObjectMapper();

		String responseJson = objectMapper.writeValueAsString(RestfulUtil.getFailure(null, ReturnMessageConstant.TOKEN_FIELD, HttpStatus.FORBIDDEN.value()));

		// 直接提示错误
		out.write(responseJson);
		out.flush();
		out.close();
	}
}
