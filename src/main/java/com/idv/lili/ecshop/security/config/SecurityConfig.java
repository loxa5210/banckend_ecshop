package com.idv.lili.ecshop.security.config;

import com.idv.lili.ecshop.security.MyEntryPoint;
import com.idv.lili.ecshop.security.filter.LoginFilter;
import com.idv.lili.ecshop.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginFilter loginFilter;
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Value("${viewUrl}")
	private List<String> viewUrl;

	/**
	 * AuthenticationManager 就是Spring Security用於執行身份驗證的組件，
	 * 只需要調用它的authenticate方法即可完成認證。
	 * Spring Security默認的認證方式就是在UsernamePasswordAuthenticationFilter這個過濾器中調用這個組件，該過濾器負責認證邏輯。
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 指定UserDetailService和加密器
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//禁用session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// 關閉csrf和frameOptions
		http.csrf().disable();
		// 開啟跨域讓前端調用
		http.cors().configurationSource(this.corsConfigurationSource());

		// 這是配置的關鍵，決定哪些接口開啟防護，哪些繞過防護
		http.authorizeRequests()
			// 注意这里，是允许前端跨域联调的一个必要配置
			//			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
			// 指定某些網址不需認證就可訪問
			.antMatchers("/api/login").permitAll()
			// 其它所有網頁需要認證才能訪問
			.anyRequest().authenticated()
			// 指定認證錯誤處理器
			.and().exceptionHandling().authenticationEntryPoint(new MyEntryPoint());
		// 將我們自定義的認證過濾器替換掉默認的認證過濾器  (UsernamePasswordAuthenticationFilter)
		http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(viewUrl); //or add * to allow all origins
		configuration.setAllowCredentials(true);
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); //to set allowed http methods
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setExposedHeaders(Arrays.asList("custom-header1", "custom-header2"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

