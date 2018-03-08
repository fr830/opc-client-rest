package com.betasoft.tools;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/03/07
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.betasoft.tools"})
public class SpringWebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Nullable
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Nullable
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ SpringWebConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
