package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SimpleController implements HttpRequestHandler {
	private final UserDao userDao;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		User user  = userDao.get(Integer.valueOf(request.getParameter("id")));
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("user", user);
	}
}
