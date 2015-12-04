package com.demo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PeopleBeanDefinitionParser implements BeanDefinitionParser {
	private final Class<?> beanClass;

    public PeopleBeanDefinitionParser(Class<?> beanClass) {
	this.beanClass = beanClass;
    }
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		
		String name = element.getAttribute("name");  
        String age = element.getAttribute("age");  
        String id = element.getAttribute("id");  
        if (StringUtils.hasText(id)) {  
        	beanDefinition.getPropertyValues().addPropertyValue("id", id);  
        }  
        if (StringUtils.hasText(name)) {  
        	beanDefinition.getPropertyValues().addPropertyValue("name", name);  
        }  
        if (StringUtils.hasText(age)) {  
        	beanDefinition.getPropertyValues().addPropertyValue("age", Integer.valueOf(age));  
        } 
        parserContext.getRegistry().registerBeanDefinition("people", beanDefinition);
		return beanDefinition;
	}
}
