package shopsale.com.asmspringboot.Controller;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shopsale.com.asmspringboot.Model.Orders;
import shopsale.com.asmspringboot.Reponsitory.OrderItemsReponsitory;
import shopsale.com.asmspringboot.Reponsitory.OrderReponsitory;

@Controller
public class ChiTietDonHangController {
	@Autowired
	OrderReponsitory oderReponsitory;
	@Autowired
	OrderItemsReponsitory orderItemReponsitory;

	// số sản phẩm tối đa
	private static final int TOI_DA_SAN_PHAM = 3;

	@GetMapping("/admin/quanlydonhang")
	public String getDonHang(@RequestParam(value = "key", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int pageIndex, Model model) {

		// Pagi
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		Page<Orders> order = oderReponsitory.findAll(pager);
		model.addAttribute("orders", order.getContent());

		Page<Orders> orderPage = oderReponsitory.findByNameOrderContaining(search, pager);
		model.addAttribute("orders", orderPage.getContent());
		model.addAttribute("maxPage", orderPage.getTotalPages());
		model.addAttribute("maxPage", order.getTotalPages());
		model.addAttribute("key", search);
		model.addAttribute("page", pageIndex);

		return "admin/quanLyDonHang";
	}

	@GetMapping("/admin/quanlydonhang/search")
	public String getDonHangSearch(@RequestParam(value = "key", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int pageIndex, Model model) {

		// Pagi
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		Page<Orders> order = oderReponsitory.findAll(pager);
		model.addAttribute("orders", order.getContent());

		Page<Orders> orderPage = oderReponsitory.findByNameOrderContaining(search, pager);
		model.addAttribute("orders", orderPage.getContent());
		model.addAttribute("maxPage", orderPage.getTotalPages());
		model.addAttribute("maxPage", order.getTotalPages());
		model.addAttribute("key", search);
		model.addAttribute("page", pageIndex);

		return "admin/quanLyDonHang";
	}
}
