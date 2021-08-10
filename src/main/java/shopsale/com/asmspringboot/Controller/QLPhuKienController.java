
package shopsale.com.asmspringboot.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

import shopsale.com.asmspringboot.Model.ChatLieuPhuKien;
import shopsale.com.asmspringboot.Model.LoaiPhuKien;
import shopsale.com.asmspringboot.Model.MauSacSanPham;
import shopsale.com.asmspringboot.Model.PhuKien;
import shopsale.com.asmspringboot.Model.ThoiGianBaoHanhPhuKien;
import shopsale.com.asmspringboot.Model.ThuongHieuPhuKien;
import shopsale.com.asmspringboot.Model.TuongThichPhuKien;
import shopsale.com.asmspringboot.Model.XuatXuPhuKien;
import shopsale.com.asmspringboot.Reponsitory.ChatLieuPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiPKRepponsitory;
import shopsale.com.asmspringboot.Reponsitory.MauSacDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.PhuKienReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ThoiGianBaoHanhReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ThuongHieuPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.TuongThichPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.XuatXuPKReponsitory;

@Controller
public class QLPhuKienController {
	// get data select chức năng thêm mới
	@Autowired
	ChatLieuPKReponsitory chatLieuPKReponsitory;
	@Autowired
	MauSacDTReponsitory mausacReponsitory;
	@Autowired
	LoaiPKRepponsitory loaiPKRepponsitory;
	@Autowired
	TuongThichPKReponsitory tuongThichPKReponsitory;
	@Autowired
	ThoiGianBaoHanhReponsitory thoiGianBaoHanhReponsitory;
	@Autowired
	ThuongHieuPKReponsitory thuongHieuPKReponsitory;
	@Autowired
	XuatXuPKReponsitory xuatXuPKReponsitory;
	@Autowired
	PhuKienReponsitory phuKienReponsitory;

	@ModelAttribute("chatlieupk")
	public List<ChatLieuPhuKien> getChatLieuPK() {
		List<ChatLieuPhuKien> chatlieu = chatLieuPKReponsitory.findAll();
		return chatlieu;
	}

	@ModelAttribute("loaipk")
	public List<LoaiPhuKien> getLoaipk() {
		List<LoaiPhuKien> loai = loaiPKRepponsitory.findAll();
		return loai;
	}

	@ModelAttribute("mausacpk")
	public List<MauSacSanPham> getMauSacPK() {
		List<MauSacSanPham> mausac = mausacReponsitory.findAll();
		return mausac;
	}

	@ModelAttribute("tuongthichpk")
	public List<TuongThichPhuKien> getTuongThich() {
		List<TuongThichPhuKien> tuongthich = tuongThichPKReponsitory.findAll();
		return tuongthich;
	}

	@ModelAttribute("thoigianbaohanhpk")
	public List<ThoiGianBaoHanhPhuKien> getThoiGianBaoHanh() {
		List<ThoiGianBaoHanhPhuKien> thoigianbaohanh = thoiGianBaoHanhReponsitory.findAll();
		return thoigianbaohanh;
	}

	@ModelAttribute("thuonghieupk")
	public List<ThuongHieuPhuKien> getThuongHieu() {
		List<ThuongHieuPhuKien> thuonghieu = thuongHieuPKReponsitory.findAll();
		return thuonghieu;
	}

	@ModelAttribute("xuatxupk")
	public List<XuatXuPhuKien> getBoNhoDTS() {
		List<XuatXuPhuKien> xuatxu = xuatXuPKReponsitory.findAll();
		return xuatxu;
	}

	// mapping thêm mới
	@GetMapping("/admin/quanlysanpham/phukien/insert")
	public String insertPhuKien(Model model) {
		PhuKien phukien = new PhuKien();
		model.addAttribute("phukien", phukien);
		return "admin/quanlysanpham/phukien/insert";
	}

	@PostMapping(path = "/admin/quanlysanpham/phukien/insert")
	public String insertCompletePhukien(@Validated @ModelAttribute("phukien") PhuKien phukien, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "admin/quanlysanpham/phukien/insert";
		}
		phuKienReponsitory.save(phukien);
		return "redirect:/admin/quanlyphukien";
	}

	// chức năng sửa
	@GetMapping("/admin/quanlysanpham/phukien/edit")
	public String indexPhukien(@RequestParam(name = "phukien_id") int phukien_id, Model model) {
		Optional<PhuKien> pkOption = phuKienReponsitory.findById(phukien_id);
		if (pkOption.isEmpty()) {
			return "redirect:/admin/quanlyphukien";
		}
		model.addAttribute("phukien", pkOption.get());

		return "admin/quanlysanpham/phukien/insert";
	}

	@PostMapping("/admin/quanlysanpham/phukien/edit")
	public String indexSanPham(@Validated @ModelAttribute("phukien") PhuKien phukien, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlysanpham/phukien/insert";
		}
		Optional<PhuKien> phukienOption = phuKienReponsitory.findById(phukien.getPhukien_id());
		if (phukienOption.isEmpty()) {
			return "admin/quanlysanpham/phukien/insert";
		}
		PhuKien phukienld = phukienOption.get();

		phukienld.setPhukien_name(phukien.getPhukien_name());
		phukienld.setCreatedDate(phukien.getCreatedDate());
		phukienld.setPhukien_giaban(phukien.getPhukien_giaban());
		phukienld.setChatlieu(phukien.getChatlieu());
		phukienld.setLoaip(phukien.getLoaip());
		phukienld.setTuongthich(phukien.getTuongthich());
		phukienld.setThoigianbaohanh(phukien.getThoigianbaohanh());
		phukienld.setThuonghieu(phukien.getThuonghieu());
		phukienld.setMausac(phukien.getMausac());
		phukienld.setPhukien_tinhnang1(phukien.getPhukien_tinhnang1());
		phukienld.setPhukien_tinhnang2(phukien.getPhukien_tinhnang2());
		phukienld.setPhukien_anh1(phukien.getPhukien_anh1());
		phukienld.setPhukien_anh2(phukien.getPhukien_anh2());
		phukienld.setXuatxu(phukien.getXuatxu());
		phukienld.setPhukien_namsx(phukien.getPhukien_namsx());

		phuKienReponsitory.save(phukienld);
		return "redirect:/admin/quanlyphukien";
	}

	// chức năng xóa
	@GetMapping("/admin/quanlysanpham/phukien/delete")
	public String deleteSanPham(@RequestParam(name = "phukien_id") int phukien_id) {
		Optional<PhuKien> phukienOption = phuKienReponsitory.findById(phukien_id);
		if (phukienOption.isEmpty()) {
			return "redirect:/admin/quanlyphukien";
		}
		phuKienReponsitory.delete(phukienOption.get());
		return "redirect:/admin/quanlyphukien";
	}

	// số sản phẩm tối đa
	private static final int TOI_DA_SAN_PHAM = 4;

	// list danh sách và map danh sách ra trang chính
	@GetMapping("/admin/quanlyphukien")
	public String quanLyPhukien(@RequestParam(name = "key", defaultValue = "") String search,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		Page<PhuKien> phukien = phuKienReponsitory.findAll(pager);
		model.addAttribute("phukien", phukien.getContent());

		Page<PhuKien> sanphamPage = phuKienReponsitory.findByNameContaining(search, pager);
		model.addAttribute("phukien", sanphamPage.getContent());

		// max page
		model.addAttribute("maxPage", sanphamPage.getTotalPages());
		model.addAttribute("key", search);
		model.addAttribute("page", pageIndex);
		return "admin/quanLyPhuKien";
	}

	@GetMapping("/admin/quanlyphukien/search")
	public String quanLyPhukienSearch(@RequestParam(name = "key", defaultValue = "") String search,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		Page<PhuKien> phukien = phuKienReponsitory.findAll(pager);
		model.addAttribute("phukien", phukien.getContent());

		Page<PhuKien> sanphamPage = phuKienReponsitory.findByNameContaining(search, pager);
		model.addAttribute("phukien", sanphamPage.getContent());

		// max page
		model.addAttribute("maxPage", sanphamPage.getTotalPages());
		model.addAttribute("key", search);
		model.addAttribute("page", pageIndex);
		return "admin/quanLyPhuKien";
	}

	@GetMapping("/admin/quanlyphukien/order")
	public String sanPhamPage(@RequestParam(name = "orderBy", defaultValue = "phukien_idg") String phukien_sort,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		// lấy sản phẩm theo phân trang

		if (phukien_sort.equals("phukien_soluongt")) {
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsBySoLuongTang(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (phukien_sort.equals("phukien_soluongg")) {
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsBySoLuongGiam(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);

		} else if (phukien_sort.equals("phukien_namet")) {
			// te a -> z
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsByNameTang(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);

		} else if (phukien_sort.equals("phukien_nameg")) {
			// ten z->a
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsByNameGiam(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);

		} else if (phukien_sort.equals("phukien_giabant")) {
			// te a -> z
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsByGiaBanTang(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (phukien_sort.equals("phukien_giabang")) {
			// te a -> z
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsByGiaBanGiam(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (phukien_sort.equals("phukien_idt")) {
			// te a -> z
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsByIdTang(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else {
			// id san pham giam dan
			Page<PhuKien> phukien = phuKienReponsitory.SortProductsByIdGiam(phukien_sort, pager);
			model.addAttribute("phukien", phukien.getContent());
			// max page
			model.addAttribute("maxPage", phukien.getTotalPages());
			model.addAttribute("page", pageIndex);

		}
		// hàm có thay đổi/////////////////////////////////////////////////////

		return "admin/quanLyPhuKien";
	}

}
