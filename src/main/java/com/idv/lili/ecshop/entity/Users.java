package com.idv.lili.ecshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 使用者
 */
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="email")})
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)

public class Users implements Serializable {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long userId;
	/**
	 * 信箱/帳號
	 */
	@Column
	private String email;
	/**
	 * 使用者姓名
	 */
	@Column
	private String username;
	/**
	 * 密碼
	 */
	@Column
	private String password;
	/**
	 * 手機
	 */
	private String mobile;
	/**
	 * 建立者 email
	 */
	private String creator;
	/**
	 * 狀態
	 */
	private Boolean status;
	/**
	 * 建立時間 帶入系統日期與時間
	 */
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
	private Date createDate;

	/**
	 * 修改者 email
	 */
	private String modifier;

	/**
	 * 修改時間
	 */
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
	private Date modifyDate;
}
