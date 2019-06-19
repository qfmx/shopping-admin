package com.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcloud.entity.Users;

/**
 * 持久化层：定义对USERS表进行操作的方法
 * 
 * @author QFMX
 *
 */
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

	/**
	 * 判断用户是否登录成功 site
	 * 
	 * @param userId       用户编号
	 * @param userPsw      用户密码
	 * @param permissionId 用户权限编号
	 * @return 成功则返回登录用户完整的信息，失败则返回null
	 */
	@Query("select new com.springcloud.entity.Users(u.userId,u.userName,u.userNumber,u.userPassword,u.userSex,u.userPhone,u.userSite,u.userBirthday,u.userEmail,u.userPhoto,u.jdictionId,u.userStatus) "
			+ "from Users u where u.userId=:userId and u.userPassword=:userPassword and u.jdictionId=:jdictionId and u.userStatus=0")
	public abstract Users login(@Param("userId") Integer userId, @Param("userPassword") String userPassword,
			@Param("jdictionId") Integer jdictionId);
	/**
	 * 修改USERS表中指定编号的用户状态
	 * 
	 * @param userId     用户编号
	 * @param userstatus 用户状态
	 * @return 修改成功返回大于0的整数，否则返回0
	 */
	@Modifying
	@Query("update Users set userStatus=:userStatus where userId=:userId")
	public abstract Integer updateStatus(@Param("userId") Integer userId, @Param("userStatus") Integer userstatus);

	/**
	 * 更新用户
	 * 
	 * @param users
	 * @return
	 */
	@Modifying
	@Query("update Users u set u.userNumber=:#{#users.userNumber},u.userSex=:#{#users.userSex},u.userPhone=:#{#users.userPhone},"
			+ "u.userSite=:#{#users.userSite},u.userBirthday=:#{#users.userBirthday},u.userEmail=:#{#users.userEmail} where u.userId=:#{#users.userId}")
	public abstract Integer update(@Param("users") Users users);

	/**
	 * 更新指定编号的用户头像
	 * 
	 * @param usres 更新条件
	 * @return 成功返回大于0的数，失败返回小于等于0的整数
	 */
	@Modifying
	@Query("update Users u set u.userPhoto=:#{#users.userPhoto} where u.userId=:#{#users.userId}")
	public abstract Integer updatePhoto(@Param("users") Users users);

	/**
	 * 修改昵称
	 * 
	 * @param users 修改条件
	 * @return 成功返回大于0的数，失败返回小于等于0的整数
	 */
	@Modifying
	@Query("update Users u set u.userName=:#{#users.userName} where u.userId=:#{#users.userId}")
	public abstract Integer updateUserName(@Param("users") Users users);

	/**
	 * 修改指定users表中指定user_id的密码
	 * 
	 * @param users 修改条件
	 * @return 成功返回大于0的数，失败返回小于等于0的整数
	 */
	@Modifying
	@Query("update Users u set u.userPassword=:#{#users.userPassword} where u.userId=:#{#users.userId}")
	public abstract Integer updateUserPassword(@Param("users") Users users);
	/**
	 * 
	 * @param userName 查询条件
	 * @return
	 */
	public abstract Long countByUserName(String userName);
	/**
	 * 普通用户登录
	 * @param users 登录条件
	 * @return 成功则返回登录用户完整的信息，失败则返回null
	 */
	public abstract Users findByUserNameAndUserPasswordAndUserStatusAndJdictionId(String userName,String userPassword,Integer userStutus,Integer jdictionId);
}
