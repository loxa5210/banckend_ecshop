package com.idv.lili.ecshop.model.request;

import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 使用者
 */
@Data
@Table
public class UsersRequest implements Serializable {
	/**
	 * 信箱/帳號
	 */
	@NotBlank(message = "信箱必填")
	@Email(message = "信箱格式錯誤")
	private String email;
	/**
	 * 使用者姓名
	 */
	@NotBlank(message = "使用者姓名必填")
	private String username;
	/**
	 * 密碼
	 */
	@NotBlank(message = "密碼必填")
	private String password;
	/**
	 * 電話
	 */
	@NotBlank(message = "電話必填")
	private String mobile;
}
