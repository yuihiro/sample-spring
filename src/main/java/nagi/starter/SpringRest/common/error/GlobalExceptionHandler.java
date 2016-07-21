package nagi.starter.SpringRest.common.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import nagi.starter.SpringRest.common.CommonBean;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
		CommonBean.log.error("Exception : " + ex.getClass().getName() + " | " + ex.getMessage());
		if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
			throw ex;
		}

		/*
		String contentType = request.getHeader("Content-Type");
		System.out.println(contentType);
		 String reason= HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();;
		    int statusCode= HttpStatus.INTERNAL_SERVER_ERROR.value();
		    if(contentType!=null && MediaType.APPLICATION_JSON_VALUE.equals(contentType)){
		        model = new ModelAndView("jsonView");
		        ResponseStatus annotation = exception.getClass().getAnnotation(ResponseStatus.class);
		
		        if(annotation!=null){
		            reason = annotation.reason();
		            statusCode = annotation.value().value();
		        }
		*/

		//response.setHeader("Refresh", "10; URL=/");

		ModelAndView model = new ModelAndView();
		model.addObject("exception", ex);
		model.addObject("url", request.getRequestURL());
		model.setViewName("/error/error");
		return model;
	}
}