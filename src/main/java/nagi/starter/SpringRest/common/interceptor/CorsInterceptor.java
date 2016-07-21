package nagi.starter.SpringRest.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CorsInterceptor extends HandlerInterceptorAdapter {

	private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	private static final String REQUEST_HEADER_ORIGIN = "Origin";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String origin = request.getHeader(REQUEST_HEADER_ORIGIN);

		//LogBean.log.info("CorsInterceptor : " + origin);

		/*
		// CORS 가능하도록 응답 헤더 추가
		if (StringUtils.hasLength(origin)) {
			// 요청한 도메인에 대해 CORS 를 허용한다. 제한이 필요하다면 필요한 값으로 설정한다.
			response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
		
			// credentials 허용
			response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		}
		*/
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}
