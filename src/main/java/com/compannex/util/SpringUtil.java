package com.compannex.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

public class SpringUtil {
	private static final Logger log = Logger.getLogger(SpringUtil.class);

	private static SpringUtil instance;
	private SpringUtil() { instance = this; }

	private AnnotationMethodHandlerAdapter handlerAdapter;

	public void setHandlerAdapter(AnnotationMethodHandlerAdapter handlerAdapter) {
		this.handlerAdapter = handlerAdapter;
	}

	public AnnotationMethodHandlerAdapter getHandlerAdapter() {
		return handlerAdapter;
	}

	
	/**
	 * Return the controller method that will handle the given servlet request.
	 */
	public static Method getHandlerMethod(HttpServletRequest request, Object handler) {
		try {
			AnnotationMethodHandlerAdapter handlerAdapter = instance.getHandlerAdapter();

			Method getMethodResolver = ReflectionUtils.findMethod(handlerAdapter.getClass(), "getMethodResolver", (Class[]) null);
			getMethodResolver.setAccessible(true);
			Object methodResolver = getMethodResolver.invoke(handlerAdapter, handler);

			Method resolveHandlerMethod = ReflectionUtils.findMethod(methodResolver.getClass(), "resolveHandlerMethod", (Class[]) null);
			resolveHandlerMethod.setAccessible(true);
			Method handlerMethod = (Method) resolveHandlerMethod.invoke(methodResolver, request);

			return handlerMethod;

		} catch (Exception e) {
			log.error("Unable to get controller method for request: " + request.getRequestURI(), e);
		}

		return null;
	}

	/**
	 * Returns the annotation on the controller method that will handle the given request.  Also checks at the class
	 * level if the annotation is not present on the method itself.
	 */
	public static <A extends java.lang.annotation.Annotation> A findHandlerAnnotation(HttpServletRequest request, Object handler, Class<A> annotationClass) {
		Method method = getHandlerMethod(request, handler);

		A annotation = AnnotationUtils.findAnnotation(method, annotationClass);
		if (annotation == null) annotation = AnnotationUtils.findAnnotation(method.getClass(), annotationClass);

		return annotation;
	}
}
