package com.iamalokit.anotherblog.mapper;

import com.iamalokit.anotherblog.entity.AdminUser;
import com.iamalokit.anotherblog.entity.AdminUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    long countByExample(AdminUserExample example);

    int deleteByExample(AdminUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    List<AdminUser> selectByExample(AdminUserExample example);

    AdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}