package com.springcloud.service;

import org.springframework.data.domain.Page;

import com.springcloud.entity.Users;

/**
 * 
 * @author QFMX
 *
 */
public interface UsersService {

	/**
	 * 根据用户编号 用户密码 权限编号 验证登录
	 * @param userId 用户编号
	 * @param userPassword 用户密码
	 * @param jdictionId 权限编号
	 * @return 成功返回Users类型的一行数据，失败返回空
	 */
	public abstract Users login(Integer userId,String userPassword,Integer jdictionId);
	/**
	 *  用户录入
	 * @param users 录入的用户信息
	 * @return 成功返回Users类型的一行数据，失败返回空
	 */
	public abstract Users insert(Users users);
	/**
	 * 查询用户（具有分页功能）
	 * @param users 查询条件
	 * @param pageNumber 页码
	 * @return 成功返回Page<Users>类型的实例，失败返回空
	 */
	public abstract Page<Users> select(Users users,Integer pageNumber);
	/**
	 * 修改用户状态
	 * @param userId 用户编号
	 * @param userStatus 用户状态
	 * @return 成功返回大于0的数，失败返回小于等于0的整数
	 */
	public abstract Integer updateStatus(Integer userId, Integer userstatus);
	/**
	 * 	根据用户编号查询指定编号的用户信息
	 * @param userId 用户编号
	 * @return 成功返回Users类型的一行数据，失败返回空
	 */
	public abstract Users selectById(Integer userId);
	/**
	 * 	更新用户的基本信息
	 * @param usres
	 * @return 成功返回大于0的数，失败返回小于等于0的整数
	 */
	public abstract Integer update(Users users);
	/**
	 * 根据修改指定编号的用户头像或用户密码或者用户昵称
	 * @param users
	 * @return 成功返回大于0的数，失败返回小于等于0的整数
	 */
	public abstract Integer updateMessage(Users users);
}
