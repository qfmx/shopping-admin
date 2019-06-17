package com.springcloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *	用于封装用户表的一行数据
 * @author Ya
 *
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor //生成当前类的无参构造方法
@AllArgsConstructor //生产当前类的有参构造方法
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 2504015899170623862L;
	/**
	 * 用户编号 主键 自增长
	 */
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 身份证号码
	 */
	@Column(name = "user_number")
	private String userNumber;
	/**
	 * 密码
	 */
	@Column(name = "user_password")
	private String userPassword;
	/**
	 * 性别
	 */
	@Column(name = "user_sex")
	private Integer userSex;
	/**
	 * 电话
	 */
	@Column(name = "user_phone")
	private String userPhone;
	/**
	 * 地址
	 */
	@Column(name = "user_site")
	private String userSite;
	/**
	 * 出生日期
	 */
	@Column(name = "user_birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date userBirthday;
	/**
	 * 邮箱
	 */
	@Column(name = "user_email")
	private String userEmail;
	/**
	 * 头像
	 */
	@Column(name = "user_photo")
	private String userPhoto;
	/**
	 * 权限编号
	 */
	@Column(name = "jdiction_id")
	private Integer jdictionId;
	/**
	 * 用户状态 0未禁用 1已禁用
	 */
	@Column(name = "user_status")
	private Integer userStatus;
}
