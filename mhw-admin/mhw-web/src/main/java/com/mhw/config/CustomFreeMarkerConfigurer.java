package com.mhw.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@Slf4j
@Configuration
public class CustomFreeMarkerConfigurer {

	private static final String TEMPLATE_LOADER_PATH = "classpath:/views/freemarker/";

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(false);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setContentType("text/html; charset=UTF-8");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer FreeMarkerConfigurer() throws IOException, TemplateException {
		FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
		factory.setTemplateLoaderPath(TEMPLATE_LOADER_PATH);
		factory.setDefaultEncoding("UTF-8");
		FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		freemarker.template.Configuration config = factory.createConfiguration();
		config.setSharedVariable("fieldValue", new FieldValue());
		result.setConfiguration(config);
		return result;
	}

	public static class FieldValue implements TemplateMethodModelEx {

		@Override
		public Object exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
			if (CollectionUtils.isEmpty(args)) {
				throw new TemplateModelException("wrong size.");
			}
			if (args.get(1) == null) {
				return null;
			}

			StringModel fieldStringModel = (StringModel) args.get(0);
			StringModel instanceStringModel = (StringModel) args.get(1);
			Object result = null;
			try {

				Field field = (Field) fieldStringModel.getWrappedObject();
				field.setAccessible(true);
				result = field.get(instanceStringModel.getWrappedObject());
			} catch (Exception e) {

				log.error("FieldValue#exec is failed.");
				e.printStackTrace();
				return null;
			}
			return result;
		}
	}

}
