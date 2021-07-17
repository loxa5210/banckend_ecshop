package com.idv.lili.ecshop.utils;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.excption.JwtFailureException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.Date;

@Slf4j
public final class JwtUtil {
	/**
	 * 这个秘钥是防止JWT被篡改的关键，决不能泄露
	 */
	private final static String secretKey = "whatever";
	/**
	 * 过期时间目前设置成2天，这个配置随业务需求而定
	 */
	private final static Duration expiration = Duration.ofDays(2);

	/**
	 * 生成JWT
	 *
	 * @param userName 用户名
	 * @return JWT
	 */
	public static String generate(String userName) {
		// 过期时间
		Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());

		return Jwts.builder()
			.setSubject(userName) // 将userName放进JWT
			.setIssuedAt(new Date()) // 设置JWT签发时间
			.setExpiration(expiryDate)  // 设置过期时间
			.signWith(SignatureAlgorithm.HS512, secretKey) // 设置加密算法和秘钥
			.compact();
	}

	/**
	 * 解析JWT
	 *
	 * @param token JWT字符串
	 * @return 解析成功返回Claims对象，解析失败返回null
	 */
	public static Claims parse(String token) throws JwtFailureException {


		// 如果是空字符串直接返回null
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		String[] bearer_s = token.split("Bearer ");

		Claims claims;
		// 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
		try {
			claims = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(bearer_s[1])
				.getBody();
		} catch (JwtException e) {
			log.error(e.toString());
			log.error(e.getMessage());
			throw new JwtFailureException(ReturnMessageConstant.TOKEN_FIELD);
		}
		return claims;
	}
}