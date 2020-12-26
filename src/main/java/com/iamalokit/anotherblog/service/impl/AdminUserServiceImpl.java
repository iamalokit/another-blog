package com.iamalokit.anotherblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.entity.AdminUser;
import com.iamalokit.anotherblog.mapper.AdminUserMapper;
import com.iamalokit.anotherblog.service.AdminUserService;
import com.iamalokit.anotherblog.util.MD5Util;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Resource
	private AdminUserMapper adminUserMapper;

	@Override
	public AdminUser login(String userName, String password) {
		String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
		return adminUserMapper.login(userName, passwordMD5);
	}

	@Override
	public AdminUser getUserDetailById(Long loginUserId) {
		return adminUserMapper.selectByPrimaryKey(loginUserId);
	}

	@Override
	public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {
		AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
		if (adminUser != null) {
			String originalPasswordMD5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
			String newPasswordMD5 = MD5Util.MD5Encode(newPassword, "UTF-8");
			if (originalPasswordMD5.equals(adminUser.getLoginPassword())) {
				adminUser.setLoginPassword(newPasswordMD5);
				if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {
		AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
		if(adminUser != null) {
			adminUser.setLoginUserName(loginUserName);
			adminUser.setNickName(nickName);
			if(adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
				return true;
			}
		}
		
		return false;
	}

}
