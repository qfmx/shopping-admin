package com.springcloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.repository.UsersRepository;
import com.springcloud.service.UsersService;

/**
 * 
 * @author QFMX
 *
 */
@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public Users login(Integer userId,String userPassword,Integer jdictionId) {
		return this.usersRepository.login(userId, userPassword, jdictionId);
	}
	@Transactional
	@Override
	public Users insert(Users users) {
		
		return this.usersRepository.save(users);
	}

	@SuppressWarnings("serial")
	@Override
	public Page<Users> select(Users users, Integer pageNumber) {
		Specification<Users> specification = new Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> whereList = new ArrayList<>();
				
				if(users.getUserName()!=null&&!users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"), "%" + users.getUserName() + "%"));
				}
				if(users.getUserStatus()!=-1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"),users.getUserStatus()));
				}
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}
		};
		PageRequest of = PageRequest.of(pageNumber, PageUtils.PAGE_ROW_COUNT);
		return this.usersRepository.findAll(specification,of);
	}
	@Transactional
	@Override
	public Integer updateStatus(Integer userId, Integer userstatus) {
		return this.usersRepository.updateStatus(userId, userstatus);
	}
	@Override
	public Users selectById(Integer userId) {
		return this.usersRepository.findById(userId).get();
	}
	@Transactional
	@Override
	public Integer update(Users users) {
		return this.usersRepository.update(users);
	}
	@Transactional
	@Override
	public Integer updateMessage(Users users) {
		// 当得到信息不等于空 并且 得到的信息去空格后不等于空字符串时,执行更新操作
		if(users.getUserPhoto() != null && !users.getUserPhoto().trim().equals("")) {
			return this.usersRepository.updatePhoto(users);
		}else if(users.getUserName() != null && !users.getUserName().trim().equals("")) {
			return this.usersRepository.updateUserName(users);
		}else if(users.getUserPassword() != null && !users.getUserPassword().trim().equals("")) {
			return this.usersRepository.updateUserPassword(users);
		}
		return 0; // 失败返回0
	}
}
