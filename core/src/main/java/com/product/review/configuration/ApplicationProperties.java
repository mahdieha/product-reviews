package com.product.review.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
@PropertySources(
        {
                @PropertySource(name = "params", value = "classpath:/input-params.properties", encoding = "UTF-8", ignoreResourceNotFound = true),
                @PropertySource(name = "messages", value = "classpath:/messages-application.properties", encoding = "UTF-8", ignoreResourceNotFound = true),
        }
)

public class ApplicationProperties {
    @Resource
    private Environment environment;

    public String getProperty(String name) {

        return environment.getProperty(name);
    }

    public Integer getCode(String name) {
        String nameValue = environment.getProperty(name);
        if (nameValue != null)
            return Integer.parseInt(nameValue);
        else return -1;
    }
}

