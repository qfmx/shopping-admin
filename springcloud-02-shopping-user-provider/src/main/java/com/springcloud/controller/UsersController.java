package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

/**
 * 
 * @author QFMX
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 * 用户登录
	 * @param userId 用户编号 
	 * @param userPassword 用户密码
	 * @param jdictionId 权限编号
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		ResultValue rv = new ResultValue();
		try {
			Users login = this.usersService.login(userId, userPassword, jdictionId);
			if (login != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);
				return rv;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("登录信息有误");
		return rv;
	}
	/**
	 * 添加用户
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.usersService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("用户信息添加成功");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息添加失败");
		return rv;
	}
	/**
	 * 根据查询添加查询指定用户信息
	 * @param users 查询条件
	 * @param pageNumber 页码
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValue select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();

		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			List<Users> list = page.getContent();
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("userList", list);
				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				pageUtils.getPageButton();
				map.put("pageUtils", pageUtils);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

	/**
	 * 修改用戶状态
	 * 
	 * @param userId 用户编号 
	 * @param userStatus 用户状态
	 * @return 
	 */
	@RequestMapping(value = "/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,@RequestParam("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateStatus = this.usersService.updateStatus(userId, userStatus);
			if(updateStatus > 0) { // 修改成功
				rv.setCode(0);
				rv.setMessage("用户状态修改成功");
			}
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户状态修改失败");
		return rv;
	}
	/**
	 * 	查询指定编号的用户信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			Users selectById = this.usersService.selectById(userId);
			if(selectById != null) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("user", selectById);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		rv.setCode(1);
		rv.setMessage("获取用户信息失败");
		return rv;
	}
	/**
	 * 更新用户信息
	 * @param users 更新条件
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ResultValue update(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer update = this.usersService.update(users);
			if(update > 0) {
				rv.setCode(0);
				rv.setMessage("更新成功");
			}
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("更新用户信息失败");
		return rv;
	}
	/**
	 * 根据条件更新密码或者头像或者昵称
	 * @param users 更新条件
	 * @return 
	 */
	@RequestMapping(value = "/updateMessage")
	public ResultValue updateMessage(Users users) {
		ResultValue rv = new ResultValue();
		try {
			/**
			 * 调用模型层的更新密码或者头像或者昵称
			 */
			Integer update = this.usersService.updateMessage(users);
			System.out.println(update);
			if(update > 0) {
				rv.setCode(0);
				rv.setMessage("更新用户信息成功");
			}
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息更新失败");
		return rv;
	}
	@RequestMapping(value = "/countByUserName")
	public ResultValue countByUserName(@RequestParam("userName") String userName) {
		ResultValue rv = new ResultValue();
		try {
			
			Long update = this.usersService.selectCountByUserName(userName);
			if(update > 0) {
				
				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("内部服务器错误");
		return rv;
	}
	@RequestMapping(value = "/userLogin")
	public ResultValue userLogin(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword,@RequestParam("userStatus") Integer userStutus, @RequestParam("jdictionId")Integer jdictionId) {
		ResultValue rv = new ResultValue();
		try {
			Users userLogin = this.usersService.userLogin(userName, userPassword, userStutus, jdictionId);
			if(userLogin != null) {
				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("登录失败 了");
		return rv;
	}
}