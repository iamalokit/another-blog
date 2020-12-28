package com.iamalokit.anotherblog.mapper;

import com.iamalokit.anotherblog.entity.BlogConfig;
import com.iamalokit.anotherblog.entity.BlogConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogConfigMapper {
    long countByExample(BlogConfigExample example);

    int deleteByExample(BlogConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogConfig record);

    int insertSelective(BlogConfig record);

    List<BlogConfig> selectByExample(BlogConfigExample example);

    BlogConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogConfig record, @Param("example") BlogConfigExample example);

    int updateByExample(@Param("record") BlogConfig record, @Param("example") BlogConfigExample example);

    int updateByPrimaryKeySelective(BlogConfig record);

    int updateByPrimaryKey(BlogConfig record);
}