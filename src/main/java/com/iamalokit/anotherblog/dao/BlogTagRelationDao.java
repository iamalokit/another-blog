package com.iamalokit.anotherblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iamalokit.anotherblog.entity.BlogTagRelation;

public interface BlogTagRelationDao {
	List<Long> selectDistinctTagIds(Long[] tagIds);

	int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);

	int deleteByBlogId(Long blogId);
}
