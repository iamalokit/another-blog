package com.iamalokit.anotherblog.service.impl;

import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.entity.AdminUser;
import com.iamalokit.anotherblog.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Override
	public AdminUser login(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminUser getUserDetailById(Integer loginUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
		// TODO Auto-generated method stub
		return null;
	}

}
