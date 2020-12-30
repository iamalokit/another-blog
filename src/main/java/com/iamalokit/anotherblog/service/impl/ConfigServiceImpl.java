package com.iamalokit.anotherblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.entity.BlogConfig;
import com.iamalokit.anotherblog.entity.BlogConfigExample;
import com.iamalokit.anotherblog.mapper.BlogConfigMapper;
import com.iamalokit.anotherblog.service.ConfigService;
import com.iamalokit.anotherblog.util.BlogStringUtil;

@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private BlogConfigMapper configMapper;

	public static final String WEBSITE_NAME = "personal blog";
	public static final String WEBSITE_DESCRIPTION = "Springboot5 + Mybatis + Mybatis generator";
	public static final String WEBSITE_LOGO = "/admin/dist/img/logo2.png";
	public static final String WEBSITE_ICON = "/admin/dist/img/favicon.png";

	public static final String YOUR_AVATAR = "/admin/dist/img/13.png";
	public static final String YOUR_EMAIL = "iamalokit@gmail.com";
	public static final String YOUR_NAME = "Alokit";

	public static final String FOOTER_ABOUT = "My Personal Blog";
	public static final String FOOTER_ICP = "Version -1";
	public static final String FOOTER_COPYRIGHT = "@2020";
	public static final String FOOTER_POWEREDBY = "webforyou";
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
