package com.product.review.configuration;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder {

    protected Logger logger;
    protected ApplicationProperties applicationProperties;

    @Autowired
    public void context(ApplicationContext context) {
        logger = context.getBean(Logger.class);
        this.applicationProperties = context.getBean(ApplicationProperties.class);
    }

}

