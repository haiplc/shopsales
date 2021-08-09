package shopsale.com.asmspringboot.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import shopsale.com.asmspringboot.Service.CustomerService;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	@Autowired
	private CustomerService user;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object hander) throws Exception {

		// tra ve trang dang nhap
		if (user.getUsers() == null) {
			respone.sendRedirect("/"); // trang dang nhap admin
			return false;
		}

		// neu khach hang vao admin thi tra ve home
		if (user.getUsers().getUser_chucvu().equals("Customer")) {
			// SET TRANG LAST_PAGE/////////////
			respone.sendRedirect("/home");
			return false;
		}
		return true;
	}
}
