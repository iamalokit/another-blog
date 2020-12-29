package com.iamalokit.anotherblog.mapper;

import com.iamalokit.anotherblog.entity.BlogTag;
import com.iamalokit.anotherblog.entity.BlogTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogTagMapper {
    long countByExample(BlogTagExample example);

    int deleteByExample(BlogTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    List<BlogTag> selectByExample(BlogTagExample example);

    BlogTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogTag record, @Param("example") BlogTagExample example);

    int updateByExample(@Param("record") BlogTag record, @Param("example") BlogTagExample example);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);
}