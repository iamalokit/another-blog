package com.iamalokit.anotherblog.mapper;

import com.iamalokit.anotherblog.entity.BlogTagRelation;
import com.iamalokit.anotherblog.entity.BlogTagRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogTagRelationMapper {
    long countByExample(BlogTagRelationExample example);

    int deleteByExample(BlogTagRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogTagRelation record);

    int insertSelective(BlogTagRelation record);

    List<BlogTagRelation> selectByExample(BlogTagRelationExample example);

    BlogTagRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogTagRelation record, @Param("example") BlogTagRelationExample example);

    int updateByExample(@Param("record") BlogTagRelation record, @Param("example") BlogTagRelationExample example);

    int updateByPrimaryKeySelective(BlogTagRelation record);

    int updateByPrimaryKey(BlogTagRelation record);
}