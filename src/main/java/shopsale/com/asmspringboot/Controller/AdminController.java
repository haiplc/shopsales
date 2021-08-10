package shopsale.com.asmspringboot.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shopsale.com.asmspringboot.Model.BoNhoSanPham;
import shopsale.com.asmspringboot.Model.ChatLieuPhuKien;
import shopsale.com.asmspringboot.Model.ChipSanPham;
import shopsale.com.asmspringboot.Model.HangSanPham;
import shopsale.com.asmspringboot.Model.LoaiPhuKien;
import shopsale.com.asmspringboot.Model.LoaiSanPham;
import shopsale.com.asmspringboot.Model.ManHinhSanPham;
import shopsale.com.asmspringboot.Model.MauSacSanPham;
import shopsale.com.asmspringboot.Model.RamSanPham;
import shopsale.com.asmspringboot.Model.ThoiGianBaoHanhPhuKien;
import shopsale.com.asmspringboot.Model.ThuongHieuPhuKien;
import shopsale.com.asmspringboot.Model.TuongThichPhuKien;
import shopsale.com.asmspringboot.Model.Users;
import shopsale.com.asmspringboot.Model.XuatXuPhuKien;
import shopsale.com.asmspringboot.Reponsitory.BoNhoDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ChatLieuPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ChipDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.HangDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiPKRepponsitory;
import shopsale.com.asmspringboot.Reponsitory.ManHinhDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.MauSacDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.RamDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ThoiGianBaoHanhReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ThuongHieuPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.TuongThichPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.UserReponsitory;
import shopsale.com.asmspringboot.Reponsitory.XuatXuPKReponsitory;

@Controller

public class AdminController {
	// mapping trang chinh admin kèm các trang liên quan
	@GetMapping("admin/dashboard")
	public String home() {
		return "admin/dashboard";
	}

	@GetMapping("admin/thongtinadmin")
	public String thongTinAdmin() {
		return "admin/thongTinAdmin";
	}

	// 1
	@Autowired
	HangDTReponsitory hangDTReponsitory;

	// chức năng thêm sửa xóa hãng:
	@GetMapping("/admin/quanlydanhmuc/hangdt/insert")
	public String insertHang(Model model) {
		HangSanPham hang = new HangSanPham();
		model.addAttribute("hangdt", hang);

		return "admin/quanlydanhmuc/hangdt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/hangdt/insert")
	public String insertCompleteHang(@Validated @ModelAttribute("hangdt") HangSanPham hang, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/hangdt/insert";
		}
		hangDTReponsitory.save(hang);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/hangdt/edit")
	public String indexHang(@RequestParam(name = "hang_id") int hang_id, Model model) {
		Optional<HangSanPham> hangOption = hangDTReponsitory.findById(hang_id);
		if (hangOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("hangdt", hangOption.get());

		return "admin/quanlydanhmuc/hangdt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/hangdt/edit")
	public String indexHang(@Valid @ModelAttribute("hangdt") HangSanPham hang, Errors errors, ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/hangdt/insert";
		}
		Optional<HangSanPham> hangOption = hangDTReponsitory.findById(hang.getHang_id());
		if (hangOption.isEmpty()) {
			return "admin/quanlydanhmuc/hangdt/insert";
		}
		HangSanPham hangld = hangOption.get();

		hangld.setHang_name(hang.getHang_name());

		hangDTReponsitory.save(hangld);

		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/hangdt/delete")
	public String deleteHang(@RequestParam(name = "hang_id") int hang_id) {
		Optional<HangSanPham> hangOption = hangDTReponsitory.findById(hang_id);
		if (hangOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		hangDTReponsitory.delete(hangOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 2
	@Autowired
	MauSacDTReponsitory mausacReponsitory;

	@GetMapping("/admin/quanlydanhmuc/mausacdt/insert")
	public String insertMauSac(Model model) {
		MauSacSanPham mau = new MauSacSanPham();
		model.addAttribute("mausacdt", mau);

		return "admin/quanlydanhmuc/mausacdt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/mausacdt/insert")
	public String insertCompleteMauSac(@Validated @ModelAttribute("mausacdt") MauSacSanPham mau, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/mausacdt/insert";
		}
		mausacReponsitory.save(mau);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/mausacdt/edit")
	public String indexMauSac(@RequestParam(name = "mausac_id") int mausac_id, Model model) {
		Optional<MauSacSanPham> mauSacOption = mausacReponsitory.findById(mausac_id);
		if (mauSacOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("mausacdt", mauSacOption.get());

		return "admin/quanlydanhmuc/mausacdt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/mausacdt/edit")
	public String indexMauSac(@Valid @ModelAttribute("mausacdt") MauSacSanPham mau, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/mausacdt/insert";

		}
		Optional<MauSacSanPham> mauSacOption = mausacReponsitory.findById(mau.getMausac_id());
		if (mauSacOption.isEmpty()) {
			return "admin/quanlydanhmuc/mausacdt/insert";
		}
		MauSacSanPham mausacld = mauSacOption.get();
		mausacld.setMausac_name(mau.getMausac_name());
		mausacReponsitory.save(mausacld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/mausacdt/delete")
	public String deleteMauSac(@RequestParam(name = "mausac_id") int mausac_id) {
		Optional<MauSacSanPham> mauSacOption = mausacReponsitory.findById(mausac_id);
		if (mauSacOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		mausacReponsitory.delete(mauSacOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 3
	@Autowired
	BoNhoDTReponsitory bonhoReponsitory;

	@GetMapping("/admin/quanlydanhmuc/bonhodt/insert")
	public String insertBoNho(Model model) {
		BoNhoSanPham bonho = new BoNhoSanPham();
		model.addAttribute("bonhodt", bonho);
		return "admin/quanlydanhmuc/bonhodt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/bonhodt/insert")
	public String insertCompleteBoNho(@Validated @ModelAttribute("bonhodt") BoNhoSanPham bonho, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/bonhodt/insert";
		}
		bonhoReponsitory.save(bonho);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/bonhodt/edit")
	public String indexBoNho(@RequestParam(name = "bonho_id") int bonho_id, Model model) {
		Optional<BoNhoSanPham> bonhoOption = bonhoReponsitory.findById(bonho_id);
		if (bonhoOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("bonhodt", bonhoOption.get());

		return "admin/quanlydanhmuc/bonhodt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/bonhodt/edit")
	public String indexBoNho(@Validated @ModelAttribute("bonhodt") BoNhoSanPham bonho, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/bonhodt/insert";

		}
		Optional<BoNhoSanPham> bonhoOption = bonhoReponsitory.findById(bonho.getBonho_id());
		if (bonhoOption.isEmpty()) {
			return "admin/quanlydanhmuc/bonhodt/insert";
		}
		BoNhoSanPham bonhold = bonhoOption.get();
		bonhold.setBonho_name(bonho.getBonho_name());
		bonhoReponsitory.save(bonhold);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/bonhodt/delete")
	public String deleteBoNho(@RequestParam(name = "bonho_id") int bonho_id) {
		Optional<BoNhoSanPham> bonhoOption = bonhoReponsitory.findById(bonho_id);
		if (bonhoOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		bonhoReponsitory.delete(bonhoOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 4
	@Autowired
	ChipDTReponsitory chipReponsitory;

	@GetMapping("/admin/quanlydanhmuc/chipdt/insert")
	public String insertChip(Model model) {
		ChipSanPham chip = new ChipSanPham();
		model.addAttribute("chipdt", chip);

		return "admin/quanlydanhmuc/chipdt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/chipdt/insert")
	public String insertCompleteChip(@Validated @ModelAttribute("chipdt") ChipSanPham chip, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/chipdt/insert";
		}
		chipReponsitory.save(chip);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/chipdt/edit")
	public String indexChip(@RequestParam(name = "chip_id") int chip_id, Model model) {
		Optional<ChipSanPham> chipOption = chipReponsitory.findById(chip_id);
		if (chipOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("chipdt", chipOption.get());
		return "admin/quanlydanhmuc/chipdt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/chipdt/edit")
	public String indexChip(@Validated @ModelAttribute("chipdt") ChipSanPham chip, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/chipdt/insert";

		}
		Optional<ChipSanPham> chipOption = chipReponsitory.findById(chip.getChip_id());
		if (chipOption.isEmpty()) {
			return "admin/quanlydanhmuc/chipdt/insert";
		}
		ChipSanPham chipld = chipOption.get();
		chipld.setChip_name(chip.getChip_name());
		chipReponsitory.save(chipld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/chipdt/delete")
	public String deleteChip(@RequestParam(name = "chip_id") int chip_id) {
		Optional<ChipSanPham> chipOption = chipReponsitory.findById(chip_id);
		if (chipOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		chipReponsitory.delete(chipOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 5
	@Autowired
	LoaiDTReponsitory loaiReponsitory;

	@GetMapping("/admin/quanlydanhmuc/loaidt/insert")
	public String insertLoai(Model model) {
		LoaiSanPham loai = new LoaiSanPham();
		model.addAttribute("loaidt", loai);

		return "admin/quanlydanhmuc/loaidt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/loaidt/insert")
	public String insertCompleteLoai(@Validated @ModelAttribute("loaidt") LoaiSanPham loai, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/loaidt/insert";
		}
		loaiReponsitory.save(loai);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/loaidt/edit")
	public String indexLoai(@RequestParam(name = "loai_id") int loai_id, Model model) {
		Optional<LoaiSanPham> loaiOption = loaiReponsitory.findById(loai_id);
		if (loaiOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("loaidt", loaiOption.get());

		return "admin/quanlydanhmuc/loaidt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/loaidt/edit")
	public String indexLoai(@Validated @ModelAttribute("loaidt") LoaiSanPham loai, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/loaidt/insert";

		}
		Optional<LoaiSanPham> loaiOption = loaiReponsitory.findById(loai.getLoai_id());
		if (loaiOption.isEmpty()) {
			return "admin/quanlydanhmuc/loaidt/insert";
		}
		LoaiSanPham loaild = loaiOption.get();
		loaild.setLoai_name(loai.getLoai_name());
		loaiReponsitory.save(loaild);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/loaidt/delete")
	public String deleteLoai(@RequestParam(name = "loai_id") int loai_id) {
		Optional<LoaiSanPham> loaiOption = loaiReponsitory.findById(loai_id);
		if (loaiOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		loaiReponsitory.delete(loaiOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 6
	@Autowired
	ManHinhDTReponsitory manhinhReponsitory;

	@GetMapping("/admin/quanlydanhmuc/manhinhdt/insert")
	public String insertManHinh(Model model) {
		ManHinhSanPham manhinh = new ManHinhSanPham();
		model.addAttribute("manhinhdt", manhinh);

		return "admin/quanlydanhmuc/manhinhdt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/manhinhdt/insert")
	public String insertCompleteManHinh(@Validated @ModelAttribute("manhinhdt") ManHinhSanPham manhinh, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/manhinhdt/insert";
		}
		manhinhReponsitory.save(manhinh);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/manhinhdt/edit")
	public String indexManHinh(@RequestParam(name = "manhinh_id") int manhinh_id, Model model) {
		Optional<ManHinhSanPham> manhinhOption = manhinhReponsitory.findById(manhinh_id);
		if (manhinhOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("manhinhdt", manhinhOption.get());

		return "admin/quanlydanhmuc/manhinhdt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/manhinhdt/edit")
	public String indexManHinh(@Validated @ModelAttribute("manhinhdt") ManHinhSanPham manhinh, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/manhinhdt/insert";

		}
		Optional<ManHinhSanPham> manhinhOption = manhinhReponsitory.findById(manhinh.getManhinh_id());
		if (manhinhOption.isEmpty()) {
			return "admin/quanlydanhmuc/manhinhdt/insert";
		}
		ManHinhSanPham manhinhld = manhinhOption.get();
		manhinhld.setManhinh_name(manhinh.getManhinh_name());
		manhinhReponsitory.save(manhinhld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/manhinhdt/delete")
	public String deleteManHinh(@RequestParam(name = "manhinh_id") int manhinh_id) {
		Optional<ManHinhSanPham> manhinhOption = manhinhReponsitory.findById(manhinh_id);
		if (manhinhOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		manhinhReponsitory.delete(manhinhOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 7
	@Autowired
	RamDTReponsitory ramReponsitory;

	@GetMapping("/admin/quanlydanhmuc/ramdt/insert")
	public String insertRam(Model model) {
		RamSanPham ram = new RamSanPham();
		model.addAttribute("ramdt", ram);

		return "admin/quanlydanhmuc/ramdt/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/ramdt/insert")
	public String insertCompleteRam(@Validated @ModelAttribute("ramdt") RamSanPham ram, Errors errors, Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/ramdt/insert";
		}
		ramReponsitory.save(ram);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/ramdt/edit")
	public String indexRam(@RequestParam(name = "ram_id") int ram_id, Model model) {
		Optional<RamSanPham> ramOption = ramReponsitory.findById(ram_id);
		if (ramOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("ramdt", ramOption.get());

		return "admin/quanlydanhmuc/ramdt/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/ramdt/edit")
	public String indexRam(@Validated @ModelAttribute("ramdt") RamSanPham ram, Errors errors, ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/ramdt/insert";

		}
		Optional<RamSanPham> ramOption = ramReponsitory.findById(ram.getRam_id());
		if (ramOption.isEmpty()) {
			return "admin/quanlydanhmuc/ramdt/insert";
		}
		RamSanPham ramld = ramOption.get();
		ramld.setRam_name(ram.getRam_name());
		ramReponsitory.save(ramld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/ramdt/delete")
	public String deleteRam(@RequestParam(name = "ram_id") int ram_id) {
		Optional<RamSanPham> ramOption = ramReponsitory.findById(ram_id);
		if (ramOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		ramReponsitory.delete(ramOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// PHỤ KIỆN
	// 1
	@Autowired
	ChatLieuPKReponsitory chatLieuPKReponsitory;

	@GetMapping("/admin/quanlydanhmuc/chatlieupk/insert")
	public String insertChatLieu(Model model) {
		ChatLieuPhuKien chatlieu = new ChatLieuPhuKien();
		model.addAttribute("chatlieupk", chatlieu);

		return "admin/quanlydanhmuc/chatlieupk/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/chatlieupk/insert")
	public String insertCompleteChatLieu(@Validated @ModelAttribute("chatlieupk") ChatLieuPhuKien chatlieu,
			Errors errors, Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/chatlieupk/insert";
		}
		chatLieuPKReponsitory.save(chatlieu);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/chatlieupk/edit")
	public String indexChatLieu(@RequestParam(name = "chatlieu_id") int chatlieu_id, Model model) {
		Optional<ChatLieuPhuKien> chatLieuOption = chatLieuPKReponsitory.findById(chatlieu_id);
		if (chatLieuOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("chatlieupk", chatLieuOption.get());

		return "admin/quanlydanhmuc/chatlieupk/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/chatlieupk/edit")
	public String indexChatLieu(@Validated @ModelAttribute("chatlieupk") ChatLieuPhuKien chatlieu, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/chatlieupk/insert";

		}
		Optional<ChatLieuPhuKien> chatlieuOption = chatLieuPKReponsitory.findById(chatlieu.getChatlieu_id());
		if (chatlieuOption.isEmpty()) {
			return "admin/quanlydanhmuc/chatlieupk/insert";
		}
		ChatLieuPhuKien chatlieuld = chatlieuOption.get();
		chatlieuld.setChatlieu_name(chatlieu.getChatlieu_name());
		chatLieuPKReponsitory.save(chatlieuld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/chatlieupk/delete")
	public String deleteChatLieu(@RequestParam(name = "chatlieu_id") int chatlieu_id) {
		Optional<ChatLieuPhuKien> chatLieuOption = chatLieuPKReponsitory.findById(chatlieu_id);
		if (chatLieuOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		chatLieuPKReponsitory.delete(chatLieuOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 2
	@Autowired
	LoaiPKRepponsitory loaiPKRepponsitory;

	@GetMapping("/admin/quanlydanhmuc/loaipk/insert")
	public String insertLoaiPhuKien(Model model) {
		LoaiPhuKien loai = new LoaiPhuKien();
		model.addAttribute("loaipk", loai);

		return "admin/quanlydanhmuc/loaipk/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/loaipk/insert")
	public String insertCompleteLoaiPhuKien(@Validated @ModelAttribute("loaipk") LoaiPhuKien loai, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/loaipk/insert";
		}
		loaiPKRepponsitory.save(loai);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/loaipk/edit")
	public String indexLoaiPhuKien(@RequestParam(name = "loaip_id") int loaip_id, Model model) {
		Optional<LoaiPhuKien> loaiOption = loaiPKRepponsitory.findById(loaip_id);
		if (loaiOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("loaipk", loaiOption.get());

		return "admin/quanlydanhmuc/loaipk/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/loaipk/edit")
	public String indexLoaiPhuKien(@Validated @ModelAttribute("loaipk") LoaiPhuKien loai, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/loaipk/insert";

		}
		Optional<LoaiPhuKien> loaiOption = loaiPKRepponsitory.findById(loai.getLoaip_id());
		if (loaiOption.isEmpty()) {
			return "admin/quanlydanhmuc/loaipk/insert";
		}
		LoaiPhuKien loaild = loaiOption.get();
		loaild.setLoaip_name(loai.getLoaip_name());
		loaiPKRepponsitory.save(loaild);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/loaipk/delete")
	public String deleteLoaiPhuKien(@RequestParam(name = "loaip_id") int loai_id) {
		Optional<LoaiPhuKien> loaiOption = loaiPKRepponsitory.findById(loai_id);
		if (loaiOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		loaiPKRepponsitory.delete(loaiOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 3
	@Autowired
	TuongThichPKReponsitory tuongThichPKReponsitory;

	@GetMapping("/admin/quanlydanhmuc/tuongthichpk/insert")
	public String insertTuongThich(Model model) {
		TuongThichPhuKien tuongthich = new TuongThichPhuKien();
		model.addAttribute("tuongthichpk", tuongthich);

		return "admin/quanlydanhmuc/tuongthichpk/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/tuongthichpk/insert")
	public String insertCompleteTuongThich(@Validated @ModelAttribute("tuongthichpk") TuongThichPhuKien tuongthich,
			Errors errors, Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/tuongthichpk/insert";
		}
		tuongThichPKReponsitory.save(tuongthich);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/tuongthichpk/edit")
	public String indexTuongThich(@RequestParam(name = "tuongthich_id") int tuongthich_id, Model model) {
		Optional<TuongThichPhuKien> tuongthichOption = tuongThichPKReponsitory.findById(tuongthich_id);
		if (tuongthichOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("tuongthichpk", tuongthichOption.get());

		return "admin/quanlydanhmuc/tuongthichpk/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/tuongthichpk/edit")
	public String indexTuongThich(@Validated @ModelAttribute("tuongthichpk") TuongThichPhuKien tuongthich,
			Errors errors, ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/tuongthichpk/insert";

		}
		Optional<TuongThichPhuKien> tuongthichOption = tuongThichPKReponsitory.findById(tuongthich.getTuongthich_id());
		if (tuongthichOption.isEmpty()) {
			return "admin/quanlydanhmuc/tuongthichpk/insert";
		}
		TuongThichPhuKien tuongthichld = tuongthichOption.get();
		tuongthichld.setTuongthich_name(tuongthich.getTuongthich_name());
		tuongThichPKReponsitory.save(tuongthichld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/tuongthichpk/delete")
	public String deleteTuongThich(@RequestParam(name = "tuongthich_id") int tuongthich_id) {
		Optional<TuongThichPhuKien> tuongthichOption = tuongThichPKReponsitory.findById(tuongthich_id);
		if (tuongthichOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		tuongThichPKReponsitory.delete(tuongthichOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 4
	@Autowired
	ThoiGianBaoHanhReponsitory thoiGianBaoHanhReponsitory;

	@GetMapping("/admin/quanlydanhmuc/thoigianbaohanhpk/insert")
	public String insertThoiGian(Model model) {
		ThoiGianBaoHanhPhuKien thoigianbaohanh = new ThoiGianBaoHanhPhuKien();
		model.addAttribute("thoigianbaohanhpk", thoigianbaohanh);

		return "admin/quanlydanhmuc/thoigianbaohanhpk/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/thoigianbaohanhpk/insert")
	public String insertCompleteThoiGian(
			@Validated @ModelAttribute("thoigianbaohanhpk") ThoiGianBaoHanhPhuKien thoigianbaohanh, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/thoigianbaohanhpk/insert";
		}
		thoiGianBaoHanhReponsitory.save(thoigianbaohanh);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/thoigianbaohanhpk/edit")
	public String indexThoiGian(@RequestParam(name = "thoigianbaohanh_id") int thoigianbaohanh_id, Model model) {
		Optional<ThoiGianBaoHanhPhuKien> thoigianbaohanhOption = thoiGianBaoHanhReponsitory
				.findById(thoigianbaohanh_id);
		if (thoigianbaohanhOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("thoigianbaohanhpk", thoigianbaohanhOption.get());

		return "admin/quanlydanhmuc/thoigianbaohanhpk/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/thoigianbaohanhpk/edit")
	public String indexThoiGian(@Validated @ModelAttribute("thoigianbaohanhpk") ThoiGianBaoHanhPhuKien thoigianbaohanh,
			Errors errors, ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/thoigianbaohanhpk/insert";

		}
		Optional<ThoiGianBaoHanhPhuKien> thoigianbaohanhOption = thoiGianBaoHanhReponsitory
				.findById(thoigianbaohanh.getThoigianbaohanh_id());
		if (thoigianbaohanhOption.isEmpty()) {
			return "admin/quanlydanhmuc/thoigianbaohanhpk/insert";
		}
		ThoiGianBaoHanhPhuKien thoigianbaohanhld = thoigianbaohanhOption.get();
		thoigianbaohanhld.setThoigianbaohanh_name(thoigianbaohanh.getThoigianbaohanh_name());
		thoiGianBaoHanhReponsitory.save(thoigianbaohanhld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/thoigianbaohanhpk/delete")
	public String deleteThoiGian(@RequestParam(name = "thoigianbaohanh_id") int thoigianbaohanh_id) {
		Optional<ThoiGianBaoHanhPhuKien> thoigianbaohanhOption = thoiGianBaoHanhReponsitory
				.findById(thoigianbaohanh_id);
		if (thoigianbaohanhOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		thoiGianBaoHanhReponsitory.delete(thoigianbaohanhOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 5
	@Autowired
	ThuongHieuPKReponsitory thuongHieuPKReponsitory;

	@GetMapping("/admin/quanlydanhmuc/thuonghieupk/insert")
	public String insertThuongHieu(Model model) {
		ThuongHieuPhuKien thuonghieu = new ThuongHieuPhuKien();
		model.addAttribute("thuonghieupk", thuonghieu);

		return "admin/quanlydanhmuc/thuonghieupk/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/thuonghieupk/insert")
	public String insertCompleteThuongHieu(@Validated @ModelAttribute("thuonghieupk") ThuongHieuPhuKien thuonghieu,
			Errors errors, Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/thuonghieupk/insert";
		}
		thuongHieuPKReponsitory.save(thuonghieu);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/thuonghieupk/edit")
	public String indexThuongHieu(@RequestParam(name = "thuonghieu_id") int thuonghieu_id, Model model) {
		Optional<ThuongHieuPhuKien> thuonghieuOption = thuongHieuPKReponsitory.findById(thuonghieu_id);
		if (thuonghieuOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("thuonghieupk", thuonghieuOption.get());

		return "admin/quanlydanhmuc/thuonghieupk/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/thuonghieupk/edit")
	public String indexThuongHieu(@Validated @ModelAttribute("thuonghieupk") ThuongHieuPhuKien thuonghieu,
			Errors errors, ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/thuonghieupk/insert";

		}
		Optional<ThuongHieuPhuKien> thuonghieuOption = thuongHieuPKReponsitory.findById(thuonghieu.getThuonghieu_id());
		if (thuonghieuOption.isEmpty()) {
			return "admin/quanlydanhmuc/thuonghieupk/insert";
		}
		ThuongHieuPhuKien thuonghieuld = thuonghieuOption.get();
		thuonghieuld.setThuonghieu_name(thuonghieu.getThuonghieu_name());
		thuongHieuPKReponsitory.save(thuonghieuld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/thuonghieupk/delete")
	public String deleteThuongHieu(@RequestParam(name = "thuonghieu_id") int thuonghieu_id) {
		Optional<ThuongHieuPhuKien> thuonghieuOption = thuongHieuPKReponsitory.findById(thuonghieu_id);
		if (thuonghieuOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		thuongHieuPKReponsitory.delete(thuonghieuOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	// 6
	@Autowired
	XuatXuPKReponsitory xuatXuPKReponsitory;

	@GetMapping("/admin/quanlydanhmuc/xuatxupk/insert")
	public String insertXuatXu(Model model) {
		XuatXuPhuKien xuatxu = new XuatXuPhuKien();
		model.addAttribute("xuatxupk", xuatxu);

		return "admin/quanlydanhmuc/xuatxupk/insert";
	}

	@PostMapping(path = "/admin/quanlydanhmuc/xuatxupk/insert")
	public String insertCompleteXuatXu(@Validated @ModelAttribute("xuatxupk") XuatXuPhuKien xuatxu, Errors errors,
			Model model) {
		if (errors.hasErrors()) {

			return "admin/quanlydanhmuc/xuatxupk/insert";
		}
		xuatXuPKReponsitory.save(xuatxu);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm sửa
	@GetMapping("/admin/quanlydanhmuc/xuatxupk/edit")
	public String indexXuatXu(@RequestParam(name = "xuatxu_id") int xuatxu_id, Model model) {
		Optional<XuatXuPhuKien> xuatxuOption = xuatXuPKReponsitory.findById(xuatxu_id);
		if (xuatxuOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		model.addAttribute("xuatxupk", xuatxuOption.get());

		return "admin/quanlydanhmuc/xuatxupk/insert";
	}

	@PostMapping("/admin/quanlydanhmuc/xuatxupk/edit")
	public String indexXuatXu(@Validated @ModelAttribute("xuatxupk") XuatXuPhuKien xuatxu, Errors errors,
			ModelAttribute model) {
		if (errors.hasErrors()) {
			return "admin/quanlydanhmuc/xuatxupk/insert";

		}
		Optional<XuatXuPhuKien> xuatxuOption = xuatXuPKReponsitory.findById(xuatxu.getXuatxu_id());
		if (xuatxuOption.isEmpty()) {
			return "admin/quanlydanhmuc/xuatxupk/insert";
		}
		XuatXuPhuKien xuatxuld = xuatxuOption.get();
		xuatxuld.setXuatxu_name(xuatxu.getXuatxu_name());
		xuatXuPKReponsitory.save(xuatxuld);
		return "redirect:/admin/quanlydanhmuc";
	}

	// hàm xóa
	@GetMapping("/admin/quanlydanhmuc/xuatxupk/delete")
	public String deleteXuatXu(@RequestParam(name = "xuatxu_id") int xuatxu_id) {
		Optional<XuatXuPhuKien> xuatxuOption = xuatXuPKReponsitory.findById(xuatxu_id);
		if (xuatxuOption.isEmpty()) {
			return "redirect:/admin/quanlydanhmuc";
		}
		xuatXuPKReponsitory.delete(xuatxuOption.get());
		return "redirect:/admin/quanlydanhmuc";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("admin/quanlydanhmuc")
	public String quanLyDanhMuc(Model model) {
		// 1
		List<MauSacSanPham> mausacdt = mausacReponsitory.findAll();
		model.addAttribute("mausacdt", mausacdt);
		// 2
		List<LoaiSanPham> loaidt = loaiReponsitory.findAll();
		model.addAttribute("loaidts", loaidt);
		// 3
		List<BoNhoSanPham> bonhodt = bonhoReponsitory.findAll();
		model.addAttribute("bonhodts", bonhodt);
		// 4
		List<ChipSanPham> chipdt = chipReponsitory.findAll();
		model.addAttribute("chipdts", chipdt);
		// 5
		List<ManHinhSanPham> manhinhdt = manhinhReponsitory.findAll();
		model.addAttribute("manhinhdts", manhinhdt);
		// 6
		List<RamSanPham> ramdt = ramReponsitory.findAll();
		model.addAttribute("ramdts", ramdt);
		// 7
		List<HangSanPham> hangdts = hangDTReponsitory.findAll();
		model.addAttribute("hangdts", hangdts);

		// PHỤ KIỆN
		// 1
		List<ChatLieuPhuKien> chatlieu = chatLieuPKReponsitory.findAll();
		model.addAttribute("chatlieupk", chatlieu);
		// 2
		List<LoaiPhuKien> loaip = loaiPKRepponsitory.findAll();
		model.addAttribute("loaipk", loaip);
		// 3
		List<TuongThichPhuKien> tuongthich = tuongThichPKReponsitory.findAll();
		model.addAttribute("tuongthichpk", tuongthich);
		// 4
		List<ThoiGianBaoHanhPhuKien> thoigian = thoiGianBaoHanhReponsitory.findAll();
		model.addAttribute("thoigianbaohanhpk", thoigian);
		// 5
		List<ThuongHieuPhuKien> thuonghieu = thuongHieuPKReponsitory.findAll();
		model.addAttribute("thuonghieupk", thuonghieu);
		// 6
		List<XuatXuPhuKien> xuatxu = xuatXuPKReponsitory.findAll();
		model.addAttribute("xuatxupk", xuatxu);

		return "admin/quanLyDanhMuc";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Autowired
	UserReponsitory usersReponsitory;

	@GetMapping("admin/quanlyusers")
	public String quanLyUsers() {
		return "admin/quanLyUsers";
	}

	@GetMapping("admin/quanlyusers/createUser")
	public String themUser() {

		return ""; // trang them user
	}

	@PostMapping("admin/quanlyusers/createUser")
	public String themUserPost(@Valid @ModelAttribute("dangky") Users user, Errors errors, Model model) {
		if (errors.hasErrors()) {

			return "redirect:/admin/quanlyusers/createUser";
		}
		// dat chuc vu user cho khach hang dang ki
		user.setUser_chucvu("User");

		usersReponsitory.save(user);

		return "redirect:/shopsale/dangnhap";
	}

}
