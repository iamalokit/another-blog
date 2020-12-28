package com.iamalokit.anotherblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.iamalokit.anotherblog.entity.BlogConfig;
import com.iamalokit.anotherblog.entity.BlogConfigExample;
import com.iamalokit.anotherblog.mapper.BlogConfigMapper;
import com.iamalokit.anotherblog.service.ConfigService;
import com.iamalokit.anotherblog.util.BlogStringUtil;

public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private BlogConfigMapper configMapper;

	public static final String WEBSITE_NAME = "personal blog";
	public static final String WEBSITE_DESCRIPTION = "personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建";
	public static final String WEBSITE_LOGO = "/admin/dist/img/logo2.png";
	public static final String WEBSITE_ICON = "/admin/dist/img/favicon.png";

	public static final String YOUR_AVATAR = "/admin/dist/img/13.png";
	public static final String YOUR_EMAIL = "2449207463@qq.com";
	public static final String YOUR_NAME = "十三";

	public static final String FOOTER_ABOUT = "your personal blog. have fun.";
	public static final String FOOTER_ICP = "浙ICP备 xxxxxx-x号";
	public static final String FOOTER_COPYRIGHT = "@2018 十三";
	public static final String FOOTER_POWEREDBY = "personal blog";
	public static final String FOOTER_POWEREDBY_URL = "##";
	@Override
	public int updateConfig(String configName, String configValue) {
		BlogConfigExample blogConfigExample = new BlogConfigExample();
		blogConfigExample.createCriteria().andConfigNameEqualTo(configName);
		BlogConfig blogConfig = configMapper.selectByExample(blogConfigExample).get(0);
        if (blogConfig != null) {
            blogConfig.setConfigValue(configValue);
            blogConfig.setUpdateTime(new Date());
            return configMapper.updateByPrimaryKeySelective(blogConfig);
        }
        return 0;
	}
	@Override
	public Map<String, String> getAllConfigs() {
		List<BlogConfig> blogConfigs = configMapper.selectByExample(new BlogConfigExample());
		Map<String, String> configMap = blogConfigs.stream().collect(Collectors.toMap(BlogConfig::getConfigName, BlogConfig::getConfigValue));
		for (Map.Entry<String, String> config : configMap.entrySet()) {
            if ("websiteName".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(WEBSITE_NAME);
            }
            if ("websiteDescription".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(WEBSITE_DESCRIPTION);
            }
            if ("websiteLogo".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(WEBSITE_LOGO);
            }
            if ("websiteIcon".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(WEBSITE_ICON);
            }
            if ("yourAvatar".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(YOUR_AVATAR);
            }
            if ("yourEmail".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(YOUR_EMAIL);
            }
            if ("yourName".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(YOUR_NAME);
            }
            if ("footerAbout".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(FOOTER_ABOUT);
            }
            if ("footerICP".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(FOOTER_ICP);
            }
            if ("footerCopyRight".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(FOOTER_COPYRIGHT);
            }
            if ("footerPoweredBy".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(FOOTER_POWEREDBY);
            }
            if ("footerPoweredByURL".equals(config.getKey()) && BlogStringUtil.isNullOrEmpty(config.getValue())) {
                config.setValue(FOOTER_POWEREDBY_URL);
            }
        }
        return configMap;
	}
}
