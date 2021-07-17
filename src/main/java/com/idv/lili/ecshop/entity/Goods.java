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
 * 商品
 */
@Data
@Entity
@Table
@Accessors
@EntityListeners(AuditingEntityListener.class)
public class Goods implements Serializable {
	@Id
	@SequenceGenerator(name = "goods_generator", sequenceName = "goods_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_generator")
	@Column
	private Long goodsId;
	/**
	 * 商品名稱
	 */
	@Column
	private String name;
	/**
	 * 商品價格
	 */
	@Column
	private Long price;
	/**
	 * 數量
	 */
	@Column
	private Integer quantity;
	/**
	 * 內容
	 */
	@Column
	private String context;

	/**
	 * 分類Id
	 */
	@Column
	private Long categoryId;

	/**
	 * 建立者 userId
	 */
	private String creator;

	/**
	 * 建立時間 帶入系統日期與時間
	 */
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
	private Date createDate;

	/**
	 * 修改者 userId
	 */
	private String modifier;

	/**
	 * 修改時間
	 */
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
	private Date modifyDate;
}
