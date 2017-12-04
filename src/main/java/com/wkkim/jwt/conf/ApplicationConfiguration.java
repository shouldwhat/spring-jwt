package com.wkkim.jwt.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration implements InitializingBean{

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfiguration.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		LOG.info("==========================================================");
		LOG.info("= Application Configuration has Initialized...");
		LOG.info("==========================================================");
	}
	
	/* JSON HTTP Message Converter */
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		
//		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//		return mappingJackson2HttpMessageConverter;
//	}

}
