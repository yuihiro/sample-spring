package nagi.starter.SpringRest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nagi.starter.SpringRest.repository.AppRepository;

@RestController
@RequestMapping("/api/app")
public class AppController {

	@Value("${spring.application.name}")
	private String message;

	@Autowired
	AppRepository repository;

	//@ApiOperation(value = "센서목록 SELECT", httpMethod = "POST")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map login(@RequestParam Map<String, String> param, HttpSession session) {
		String id = param.get("id");
		String pwd = param.get("pwd");

		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(id);
		Map<String, Object> login_result = repository.login(id, pwd);
		if (login_result != null) {
			result = login_result;
			if (pwd.equals(login_result.get("pwd"))) {
				result.put("login_type", "sucess");
			} else {
				result.put("login_type", "fail_pwd");
			}
			session.setMaxInactiveInterval(60);
			System.out.println("로그인 세션 : " + session.getId());
			session.setAttribute("id", id);
			System.out.println("ID : " + session.getAttribute("id"));
			result.put("session_id", session.getId());
		} else {
			result.put("login_type", "fail_id");
		}
		return result;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request) {
		System.out.println("로그아웃 세션 : ");
		HttpSession session = request.getSession(false);
		session.invalidate();
		System.out.println("ID : " + session.getAttribute("id"));
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public Map session(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession(false);
		System.out.println(session.getMaxInactiveInterval());
		System.out.println("확인 세션 : " + session.getId());
		System.out.println("ID : " + session.getAttribute("id"));

		if (session.getAttribute("id") != null) {
			result.put("auth", true);
			result.put("id", session.getAttribute("id"));
		} else {
			result.put("auth", false);
			result.put("id", null);
		}
		//response.setHeader("SET-COOKIE", "JSESSIONID=" + session.getId() + ";Path=/; HttpOnly");
		return result;
	}

	@RequestMapping(value = "/hi/{in}", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String echo(@PathVariable(value = "in") final String in) {
		return in + message;
	}

	/*
	@ModelAttribute("common_data")
	public String commonData() {
		List<String> log_types = new ArrayList<String>();
		log_types.add("하하");
		log_types.add("호호");
		log_types.add("히히");
	
		Map data = new HashMap<>();
		data.put("log_types", log_types);
		return "data";
	}
	
	@ModelAttribute("app_info")
	public Map getAppInfo(HttpServletRequest request) {
		String session_id = request.getSession().getId();
		String referer = request.getHeader("Referer");
		Map app_info = new HashMap<>();
	
		if (StringUtils.hasText(referer) == false) {
			referer = "/";
		}
	
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String originalURL = urlPathHelper.getOriginatingRequestUri(request);
	
		app_info.put("session_id", session_id);
		app_info.put("referer", referer);
		return app_info;
	}	
	*/
}
