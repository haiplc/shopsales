package shopsale.com.asmspringboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import shopsale.com.asmspringboot.Model.FooterInfo;
import shopsale.com.asmspringboot.Service.GioHangService;

@Controller
public class CartController {
	@Autowired
	GioHangService giohangService;

	// truyền giá trị vào footer
	@ModelAttribute("footerInfo")
	public FooterInfo getFooterInfo() {
		return new FooterInfo();
	}
}
