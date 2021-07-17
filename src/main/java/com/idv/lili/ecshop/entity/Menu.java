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
 * 功能列表
 */

@Data
@Entity
@Table
@Accessors
@EntityListeners(AuditingEntityListener.class)
public class Menu implements Serializable {
	/**
	 * id
	 */
	@Id
	@SequenceGenerator(name = "menu_generator", sequenceName = "menu_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_generator")
	@Column
	private Integer menuId;

	/**
	 * 功能名稱
	 */
	@Column
	private String name;
	/**
	 * 是否為子菜單
	 */
	@Column
	private Boolean isChild;
	/**
	 * 主菜單Id
	 */
	@Column
	private Integer parentId;
	/**
	 * 排序
	 */
	@Column
	private Integer priority;
	/**
	 * 路徑 只有子菜單才會有
	 */
	private String path;
	/**
	 * 建立者 userId
	 */
	private Integer creator;

	/**
	 * 建立時間 帶入系統日期與時間
	 */
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
	private Date createDate;

	/**
	 * 修改者 userId
	 */
	private Integer modifier;

	/**
	 * 修改時間
	 */
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
	private Date modifyDate;
}
