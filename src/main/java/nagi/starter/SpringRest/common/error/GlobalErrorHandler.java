package nagi.starter.SpringRest.common.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import nagi.starter.SpringRest.common.CommonBean;

@Controller
public class GlobalErrorHandler implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes error_attr;

	@RequestMapping(value = PATH)
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response) {
		Map info = getErrorAttributes(request, CommonBean.debug_mode);
		CommonBean.log.info("Error : " + info.get("status") + " | " + info.get("error") + " | " + info.get("message"));

		ModelAndView model = new ModelAndView();
		model.addObject("status", info.get("status"));
		model.addObject("message", info.get("error"));
		int status = response.getStatus();

		status = 500;
		if (status == 401) {
			model.setViewName("/error/401");
		} else if (status == 404) {
			model.setViewName("/error/404");
		} else if (status == 500) {
			model.setViewName("/error/500");
		} else {
			model.setViewName("/error/error");
		}
		return model;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return error_attr.getErrorAttributes(requestAttributes, includeStackTrace);
	}
}
