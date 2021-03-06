package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
	private final UserDao userDao;

	@RequestMapping(value = "/user")
	public User getUser(@RequestParam("id") Integer id) {
		return userDao.findById(id).get();
	}

	@RequestMapping("/exception")
	public void exception() {
		throw new RuntimeException("어이쿠!");
	}

	@GetMapping(path = "/upload")
	public void upload() {

	}

	@PostMapping(path = "/upload")
	public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		File path = new File(request.getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		bufferedOutputStream.write(file.getBytes());
		bufferedOutputStream.close();

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("url", "/images/" + file.getOriginalFilename());

		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception e) {
		ModelAndView modelAndView = new ModelAndView("error");

		modelAndView.addObject("e", e);

		return modelAndView;
	}
}
