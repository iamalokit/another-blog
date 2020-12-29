package com.iamalokit.anotherblog.dao;

import java.util.List;

public interface BlogTagRelationDao {
	List<Long> selectDistinctTagIds(Long[] tagIds);
}
