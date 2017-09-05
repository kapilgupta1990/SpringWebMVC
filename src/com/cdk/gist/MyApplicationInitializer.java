package com.cdk.gist;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
//		AnnotationConfigWebApplicationContext rootContext=new AnnotationConfigWebApplicationContext();
//		rootContext.register(AppConfig.class);
		AnnotationConfigWebApplicationContext webApplicationContext=new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(WebConfig.class);
		servletContext.addListener(new ContextLoaderListener(webApplicationContext));
		DispatcherServlet dispatcherServlet=new DispatcherServlet(webApplicationContext);
		Dynamic dynamic=servletContext.addServlet("spring", dispatcherServlet);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/");
		
		
	}

}
