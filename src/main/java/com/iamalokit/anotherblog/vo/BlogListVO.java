package com.iamalokit.anotherblog.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlogListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String blogTitle;

	private String blogSubUrl;

	private String blogCoverImage;

	private Integer blogCategoryId;

	private String blogCategoryIcon;

	private String blogCategoryName;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogSubUrl() {
		return blogSubUrl;
	}

	public void setBlogSubUrl(String blogSubUrl) {
		this.blogSubUrl = blogSubUrl;
	}

	public String getBlogCoverImage() {
		return blogCoverImage;
	}

	public void setBlogCoverImage(String blogCoverImage) {
		this.blogCoverImage = blogCoverImage;
	}

	public Integer getBlogCategoryId() {
		return blogCategoryId;
	}

	public void setBlogCategoryId(Integer blogCategoryId) {
		this.blogCategoryId = blogCategoryId;
	}

	public String getBlogCategoryIcon() {
		return blogCategoryIcon;
	}

	public void setBlogCategoryIcon(String blogCategoryIcon) {
		this.blogCategoryIcon = blogCategoryIcon;
	}

	public String getBlogCategoryName() {
		return blogCategoryName;
	}

	public void setBlogCategoryName(String blogCategoryName) {
		this.blogCategoryName = blogCategoryName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
