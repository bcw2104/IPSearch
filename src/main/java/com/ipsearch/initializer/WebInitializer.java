package com.ipsearch.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.ipsearch.config.RootContext;
import com.ipsearch.config.ServletAppContext;

public class WebInitializer implements WebApplicationInitializer{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = getContext(RootContext.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext servletAppContext = getContext(ServletAppContext.class);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(servletAppContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");

		FilterRegistration.Dynamic charaterEncodingFilter = servletContext.addFilter("charaterEncodingFilter", new CharacterEncodingFilter());
        charaterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
        charaterEncodingFilter.setInitParameter("encoding", "UTF-8");
        charaterEncodingFilter.setInitParameter("forceEncoding", "true");

        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	private AnnotationConfigWebApplicationContext getContext(Class<?> target) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(target);

		return context;
	}

}
