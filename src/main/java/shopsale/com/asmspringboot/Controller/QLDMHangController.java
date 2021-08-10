package shopsale.com.asmspringboot.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shopsale.com.asmspringboot.Model.HangSanPham;
import shopsale.com.asmspringboot.Reponsitory.DMHangDTReponsitory;

@Controller
public class QLDMHangController {
	// get data select chức năng thêm mới
	@Autowired
	DMHangDTReponsitory dmhangDTReponsitory;

	// 1

	// chức năng thêm sửa xóa hãng:
	@GetMapping("/admin/quanlydanhmuchang/hangdt/insert")
	public String insertHang(Model model) {
		HangSanPham hang = new HangSanPham();
		model.addAttribute("hangdt", hang);
		return "admin/quanlydanhmuchang/hangdt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuchang/hangdt/insert")
	public String insertCompleteHang(@Validated @ModelAttribute("hangdt") HangSanPham hang, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuchang/hangdt/insert";
		}
		dmhangDTReponsitory.save(hang);
		return "redirect:/admin/quanlydanhmuchang";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuchang/hangdt/edit")
	public String indexHang(@RequestParam(name = "hang_id") int hang_id, Model model) {
		Optional<HangSanPham> hangOption = dmhangDTReponsitory.findById(hang_id);
		if (hangOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuchang/page";
		}
		model.addAttribute("hangdt", hangOption.get());
		return "admin/quanlydanhmuchang/hangdt/insert";
	}

	@PostMapping("/admin/quanlydanhmuchang/hangdt/edit")
	public String indexHang(@Validated @ModelAttribute("hangdt") HangSanPham hang, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuchang/hangdt/insert";
		}
		Optional<HangSanPham> hangOption = dmhangDTReponsitory.findById(hang.getHang_id());
		if (hangOption.isEmpty()) {
			return "admin/quanlydanhmuchang/hangdt/insert";
		}
		HangSanPham hangld = hangOption.get();

		hangld.setHang_name(hang.getHang_name());

		dmhangDTReponsitory.save(hangld);

		return "redirect:/admin/quanlydanhmuchang";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuchang/hangdt/delete")
	public String deleteHang(@RequestParam(name = "hang_id") int hang_id) {
		Optional<HangSanPham> hangOption = dmhangDTReponsitory.findById(hang_id);
		if (hangOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuchang";
		}
		dmhangDTReponsitory.delete(hangOption.get());
		return "redirect:/admin/quanlydanhmuchang";
	}

	@GetMapping("/admin/quanlydanhmuchang")
	public String quanLyHang(@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);

		Page<HangSanPham> hangsp = dmhangDTReponsitory.findAll(pager);
		model.addAttribute("hangdts", hangsp.getContent());

		Page<HangSanPham> hangPage = dmhangDTReponsitory.findByNameHangContaining(search, pager);
		model.addAttribute("hangdts", hangPage.getContent());
		model.addAttribute("maxPage", hangPage.getTotalPages());
		model.addAttribute("search", search);
		model.addAttribute("page", pageIndex);

		return "admin/quanLyDanhMucHang";
	}

	@GetMapping("/admin/quanlydanhmuchang/search")
	public String quanLyHangSearch(@RequestParam(name = "key", defaultValue = "") String search,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);

		Page<HangSanPham> hangsp = dmhangDTReponsitory.findAll(pager);
		model.addAttribute("hangdts", hangsp.getContent());

		Page<HangSanPham> hangPage = dmhangDTReponsitory.findByNameHangContaining(search, pager);
		model.addAttribute("hangdts", hangPage.getContent());
		model.addAttribute("maxPage", hangPage.getTotalPages());
		model.addAttribute("key", search);
		model.addAttribute("page", pageIndex);

		return "admin/quanLyDanhMucHang";
	}

	// số sản phẩm tối đa
	private static final int TOI_DA_SAN_PHAM = 5;

	// list danh sách và map danh sách ra trang chính

	@GetMapping("/admin/quanlydanhmuchang/order")
	public String HangSanPhamPage(@RequestParam(name = "orderBy", defaultValue = "hang_idg") String hang_sort,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		// lấy sản phẩm theo phân trang
		if (hang_sort.equals("hang_namet")) {
			// te a -> z
			Page<HangSanPham> hangdt = dmhangDTReponsitory.SortProductsByNameTang(hang_sort, pager);
			model.addAttribute("hangdts", hangdt.getContent());
			// max page
			model.addAttribute("maxPage", hangdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (hang_sort.equals("hang_nameg")) {
			// ten z->a
			Page<HangSanPham> hangdt = dmhangDTReponsitory.SortProductsByNameGiam(hang_sort, pager);
			model.addAttribute("hangdts", hangdt.getContent());
			// max page
			model.addAttribute("maxPage", hangdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (hang_sort.equals("hang_idt")) {
			// te a -> z
			Page<HangSanPham> hangdt = dmhangDTReponsitory.SortProductsByIdTang(hang_sort, pager);
			model.addAttribute("hangdts", hangdt.getContent());
			// max page
			model.addAttribute("maxPage", hangdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else {
			// id san pham giam dan
			Page<HangSanPham> hangdt = dmhangDTReponsitory.SortProductsByIdGiam(hang_sort, pager);
			model.addAttribute("hangdts", hangdt.getContent());
			// max page
			model.addAttribute("maxPage", hangdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		}
		// hàm có thay đổi/////////////////////////////////////////////////////

		return "admin/quanLyDanhMucHang";
	}

}
