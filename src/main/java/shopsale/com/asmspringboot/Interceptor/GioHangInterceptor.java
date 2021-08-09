package shopsale.com.asmspringboot.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import shopsale.com.asmspringboot.Model.GioHang;

@Component
public class GioHangInterceptor implements HandlerInterceptor {
	@Autowired
	private GioHang gioHang;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object hander) throws Exception {
		if (gioHang.getChiTietGioHang().size() == 0 || gioHang.getChiTietGioHang() == null) {
			respone.sendRedirect("/home");
			return false;
		}
		return true;
	}
}
