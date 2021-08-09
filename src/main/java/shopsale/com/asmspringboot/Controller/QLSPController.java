
package shopsale.com.asmspringboot.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shopsale.com.asmspringboot.Model.BoNhoSanPham;
import shopsale.com.asmspringboot.Model.ChipSanPham;
import shopsale.com.asmspringboot.Model.HangSanPham;
import shopsale.com.asmspringboot.Model.LoaiSanPham;
import shopsale.com.asmspringboot.Model.ManHinhSanPham;
import shopsale.com.asmspringboot.Model.MauSacSanPham;
import shopsale.com.asmspringboot.Model.RamSanPham;
import shopsale.com.asmspringboot.Model.SanPhamDT;
import shopsale.com.asmspringboot.Reponsitory.BoNhoDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ChipDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.HangDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ManHinhDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.MauSacDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.RamDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.SanPhamDTReponsitory;

@Controller
public class QLSPController {
	// get data select chức năng thêm mới
	@Autowired
	HangDTReponsitory hangDTReponsitory;
	@Autowired
	MauSacDTReponsitory mausacReponsitory;
	@Autowired
	BoNhoDTReponsitory bonhoReponsitory;
	@Autowired
	ChipDTReponsitory chipReponsitory;
	@Autowired
	LoaiDTReponsitory loaiReponsitory;
	@Autowired
	ManHinhDTReponsitory manhinhReponsitory;
	@Autowired
	RamDTReponsitory ramReponsitory;

	@ModelAttribute("hangdts")
	public List<HangSanPham> getHangDTS() {
		List<HangSanPham> hangdts = hangDTReponsitory.findAll();
		return hangdts;
	}

	@ModelAttribute("loaidts")
	public List<LoaiSanPham> getLoaiDTS() {
		List<LoaiSanPham> loaidts = loaiReponsitory.findAll();
		return loaidts;
	}

	@ModelAttribute("mausacdts")
	public List<MauSacSanPham> getMauSacDTS() {
		List<MauSacSanPham> mausacdts = mausacReponsitory.findAll();
		return mausacdts;
	}

	@ModelAttribute("chipdts")
	public List<ChipSanPham> getChipDTS() {
		List<ChipSanPham> chipdts = chipReponsitory.findAll();
		return chipdts;
	}

	@ModelAttribute("manhinhdts")
	public List<ManHinhSanPham> getManHinhDTS() {
		List<ManHinhSanPham> manhinhdts = manhinhReponsitory.findAll();
		return manhinhdts;
	}

	@ModelAttribute("ramdts")
	public List<RamSanPham> getRamDTS() {
		List<RamSanPham> ramdts = ramReponsitory.findAll();
		return ramdts;
	}

	@ModelAttribute("bonhodts")
	public List<BoNhoSanPham> getBoNhoDTS() {
		List<BoNhoSanPham> bonhodts = bonhoReponsitory.findAll();
		return bonhodts;
	}

	// mapping thêm mới
	@GetMapping("/admin/quanlysanpham/dienthoai/insert")
	public String insertSanPham(Model model) {
		SanPhamDT sanphamdt = new SanPhamDT();
		model.addAttribute("sanphamdt", sanphamdt);
		model.addAttribute("titleNut", "Thêm mới");
		return "admin/quanlysanpham/dienthoai/insert";
	}

	@Autowired
	SanPhamDTReponsitory sanphamReponsitory;

	@PostMapping(path = "/admin/quanlysanpham/dienthoai/insert")
	public String insertCompleteSanPham(@Validated @ModelAttribute("sanphamdt") SanPhamDT dienthoai, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "admin/quanlysanpham/dienthoai/insert";
		}
		sanphamReponsitory.save(dienthoai);
		return "redirect:/admin/quanlysanpham";
	}

	// chức năng sửa
	@GetMapping("/admin/quanlysanpham/dienthoai/edit")
	public String indexSanPham(@RequestParam(name = "sanpham_dt_id") int sanpham_id, Model model) {
		Optional<SanPhamDT> sanphamOption = sanphamReponsitory.findById(sanpham_id);
		if (sanphamOption.isEmpty()) {
			return "redirect:/admin/quanlysanpham";
		}
		model.addAttribute("sanphamdt", sanphamOption.get());

		return "admin/quanlysanpham/dienthoai/insert";
	}

	@PostMapping("/admin/quanlysanpham/dienthoai/edit")
	public String indexSanPham(@Validated @ModelAttribute("sanphamdt") SanPhamDT sanpham, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlysanpham/dienthoai/insert";
		}
		Optional<SanPhamDT> sanphamOption = sanphamReponsitory.findById(sanpham.getSanpham_id());
		if (sanphamOption.isEmpty()) {
			return "admin/quanlysanpham/dienthoai/insert";
		}
		SanPhamDT sanphamld = sanphamOption.get();

		sanphamld.setSanpham_name(sanpham.getSanpham_name());
		sanphamld.setBonho(sanpham.getBonho());
		sanphamld.setChip(sanpham.getChip());
		sanphamld.setCreatedDate(sanpham.getCreatedDate());
		sanphamld.setHang(sanpham.getHang());
		sanphamld.setLoai(sanpham.getLoai());
		sanphamld.setManhinh(sanpham.getManhinh());
		sanphamld.setMausac(sanpham.getMausac());
		sanphamld.setRam(sanpham.getRam());
		sanphamld.setSanpham_anh1(sanpham.getSanpham_anh1());
		sanphamld.setSanpham_anh2(sanpham.getSanpham_anh2());
		sanphamld.setSanpham_giaban(sanpham.getSanpham_giaban());
		sanphamld.setSanpham_soluong(sanpham.getSanpham_soluong());
		sanphamld.setSanpham_url(sanpham.getSanpham_url());
		sanphamld.setSanpham_mota(sanpham.getSanpham_mota());
		sanphamld.setSanpham_lienhe(sanpham.getSanpham_lienhe());

		sanphamReponsitory.save(sanphamld);
		return "redirect:/admin/quanlysanpham";
	}

	// chức năng xóa
	@GetMapping("/admin/quanlysanpham/dienthoai/delete")
	public String deleteSanPham(@RequestParam(name = "sanpham_dt_id") int sanpham_id) {
		Optional<SanPhamDT> sanphamOption = sanphamReponsitory.findById(sanpham_id);
		if (sanphamOption.isEmpty()) {
			return "redirect:/admin/quanlysanpham";
		}
		sanphamReponsitory.delete(sanphamOption.get());
		return "redirect:/admin/quanlysanpham";
	}

	// số sản phẩm tối đa
	private static final int TOI_DA_SAN_PHAM = 4;

	// list danh sách và map danh sách ra trang chính
	@GetMapping("/admin/quanlysanpham")
	public String quanLySanPham(@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		Page<SanPhamDT> sanphamdt = sanphamReponsitory.findAll(pager);
		model.addAttribute("sanphamdt", sanphamdt.getContent());

		Page<SanPhamDT> sanphamPage = sanphamReponsitory.findByNameContaining(search, pager);
		model.addAttribute("sanphamdt", sanphamPage.getContent());

		// max page
		model.addAttribute("maxPage", sanphamPage.getTotalPages());
		model.addAttribute("search", search);
		model.addAttribute("page", pageIndex);
		return "admin/quanLySanPham";
	}

	@GetMapping("/admin/quanlysanpham/order")
	public String sanPhamPage(@RequestParam(name = "orderBy", defaultValue = "sanpham_idg") String sanpham_sort,
			@RequestParam(name = "page", defaultValue = "0") int pageIndex, Model model) {
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);
		// lấy sản phẩm theo phân trang

		if (sanpham_sort.equals("sanpham_soluongt")) {
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsBySoLuongTang(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (sanpham_sort.equals("sanpham_soluongg")) {
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsBySoLuongGiam(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);

		} else if (sanpham_sort.equals("sanpham_namet")) {
			// te a -> z
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsByNameTang(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);

		} else if (sanpham_sort.equals("sanpham_nameg")) {
			// ten z->a
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsByNameGiam(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);

		} else if (sanpham_sort.equals("sanpham_giabant")) {
			// te a -> z
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsByGiaBanTang(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (sanpham_sort.equals("sanpham_giabang")) {
			// te a -> z
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsByGiaBanGiam(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else if (sanpham_sort.equals("sanpham_idt")) {
			// te a -> z
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsByIdTang(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);
		} else {
			// id san pham giam dan
			Page<SanPhamDT> sanphamdt = sanphamReponsitory.SortProductsByIdGiam(sanpham_sort, pager);
			model.addAttribute("sanphamdt", sanphamdt.getContent());
			// max page
			model.addAttribute("maxPage", sanphamdt.getTotalPages());
			model.addAttribute("page", pageIndex);

		}
		// hàm có thay đổi/////////////////////////////////////////////////////

		return "admin/quanLySanPham";
	}

}
