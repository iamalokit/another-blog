package com.iamalokit.anotherblog.service;

import com.iamalokit.anotherblog.entity.AdminUser;

public interface AdminUserService {
	
	AdminUser login(String userName, String password);
	
	AdminUser getUserDetailById(Long loginUserId);
	
	Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword);
	
	Boolean updateName(Long loginUserId, String loginUserName, String nickName);
	
}
