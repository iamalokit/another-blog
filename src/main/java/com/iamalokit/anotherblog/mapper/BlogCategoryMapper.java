package com.iamalokit.anotherblog.mapper;

import com.iamalokit.anotherblog.entity.BlogCategory;
import com.iamalokit.anotherblog.entity.BlogCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogCategoryMapper {
    long countByExample(BlogCategoryExample example);

    int deleteByExample(BlogCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    List<BlogCategory> selectByExample(BlogCategoryExample example);

    BlogCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    int updateByExample(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);
}