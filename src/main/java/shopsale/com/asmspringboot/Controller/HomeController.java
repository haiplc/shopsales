package shopsale.com.asmspringboot.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shopsale.com.asmspringboot.Controller.GobalController.LAST_PAGE;
import shopsale.com.asmspringboot.Model.FooterInfo;
import shopsale.com.asmspringboot.Model.GioHang;
import shopsale.com.asmspringboot.Model.HangSanPham;
import shopsale.com.asmspringboot.Model.LoaiPhuKien;
import shopsale.com.asmspringboot.Model.OrderItems;
import shopsale.com.asmspringboot.Model.Orders;
import shopsale.com.asmspringboot.Model.PhuKien;
import shopsale.com.asmspringboot.Model.SanPhamDT;
import shopsale.com.asmspringboot.Model.ThuongHieuPhuKien;
import shopsale.com.asmspringboot.Model.Users;
import shopsale.com.asmspringboot.Model.Login;
import shopsale.com.asmspringboot.Reponsitory.BoNhoDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ChipDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.HangDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiPKRepponsitory;
import shopsale.com.asmspringboot.Reponsitory.MauSacDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.OrderItemsReponsitory;
import shopsale.com.asmspringboot.Reponsitory.OrderReponsitory;
import shopsale.com.asmspringboot.Reponsitory.PhuKienReponsitory;
import shopsale.com.asmspringboot.Reponsitory.RamDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.SanPhamDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ThuongHieuPKReponsitory;
import shopsale.com.asmspringboot.Reponsitory.UserReponsitory;
import shopsale.com.asmspringboot.Service.CustomerService;
import shopsale.com.asmspringboot.Service.GioHangService;
import shopsale.com.asmspringboot.Service.MailService;

@Controller
@RequestMapping("")
public class HomeController {

    private final String[] ORDER_NAME = { "sanpham_giaban", "created_date" };

    // danh sách sản phẩm, thuộc tính: id, tên sản phẩm, loại, hãng, màu sắc, chíp,
    // RAM, màn hình, bộ nhớ, image1, image2, mô tả, liên hệ, ngày tạo, ghi chú
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
    RamDTReponsitory ramReponsitory;

    @Autowired
    SanPhamDTReponsitory sanphamReponsitory;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderReponsitory orderRepository;

    @Autowired
    OrderItemsReponsitory orderItemsRepository;

    @Autowired
    MailService mailService;

    @Autowired
    UserReponsitory usersReponsitory;

    @ModelAttribute("hangdts")
    public List<HangSanPham> getHangDTS() {
        List<HangSanPham> hangdts = hangDTReponsitory.findAll();
        return hangdts;
    }

    // số sản phẩm tối đa
    private static final int TOI_DA_SAN_PHAM_HOME = 12;

    @GetMapping(value = { "", "home" })
    public String home(@RequestParam(name = "key", defaultValue = "") String key,
            @RequestParam(name = "hang", defaultValue = "0") int hang_id,
            @RequestParam(name = "order", defaultValue = "1") int order,
            @RequestParam(name = "min", defaultValue = "0") int min,
            @RequestParam(name = "max", defaultValue = "99999999") int max,
            @RequestParam(name = "page", defaultValue = "0") int pageIndex,
            @RequestParam(name = "sort", defaultValue = "0") int sort, Model model) {
        // XU LY order col name
        if (order < 0 || order > ORDER_NAME.length - 1)
            order = 1;

        // Lấy sản phẩm phân trang:
        Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM_HOME, // pagin
                sort == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, // sort
                ORDER_NAME[order]); // order

        Page<SanPhamDT> sanphamdt = sanphamReponsitory.SearchProductsOrder(key, hang_id, min, max, pager);

        model.addAttribute("sanphamdts", sanphamdt.getContent());
        model.addAttribute("maxPage", sanphamdt.getTotalPages());

        model.addAttribute("key", key);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("hang", hang_id);
        model.addAttribute("order", order);
        model.addAttribute("page", pageIndex);
        model.addAttribute("sort", sort);
        model.addAttribute("currentUser", customerService.getUsers());
        customerService.setLastPage(LAST_PAGE.HOME);
        return "home";
    }

    @GetMapping("/home/search")
    public String homeSearch(@RequestParam(name = "key", defaultValue = "") String key,
            @RequestParam(name = "hang", defaultValue = "0") int hang_id,
            @RequestParam(name = "order", defaultValue = "1") int order,
            @RequestParam(name = "page", defaultValue = "0") int pageIndex,
            @RequestParam(name = "min", defaultValue = "0") int min,
            @RequestParam(name = "max", defaultValue = "99999999") int max,
            @RequestParam(name = "sort", defaultValue = "0") int sort, Model model) {
        // XU LY order col name
        if (order < 0 || order > ORDER_NAME.length - 1)
            order = 0;

        // Lấy sản phẩm phân trang:
        Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM_HOME, // pagin
                sort == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, // sort
                ORDER_NAME[order]); // order

        Page<SanPhamDT> sanphamdt = sanphamReponsitory.SearchProductsOrder(key, hang_id, min, max, pager);

        model.addAttribute("sanphamdts", sanphamdt.getContent());
        model.addAttribute("maxPage", sanphamdt.getTotalPages());

        model.addAttribute("key", key);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("hang", hang_id);
        model.addAttribute("order", order);
        model.addAttribute("page", pageIndex);
        model.addAttribute("sort", sort);
        model.addAttribute("currentUser", customerService.getUsers());
        customerService.setLastPage(LAST_PAGE.HOME);
        return "home";
    }

    // lấy sản phẩm theo id để biết về nội dung sản phẩm
    // hãng được liên kết nên từ sản phẩm. hãng, so sánh với id hãng để lấy nội dung
    @GetMapping("/sanpham/chitiet")
    public String chiTietSanPham(@RequestParam(name = "sanpham_id") int sanpham_id, Model model) {
        Optional<SanPhamDT> sanphamOption = sanphamReponsitory.findById(sanpham_id);
        if (sanphamOption.isEmpty()) {
            return "redirect:/home";
        }
        model.addAttribute("product", sanphamOption.get());

        return "chiTietSanPham";
    }

    // truyền giá trị vào footer
    @ModelAttribute("footerInfo")
    public FooterInfo getFooterInfo() {
        return new FooterInfo();
    }

    @GetMapping("/lienhe")
    public String lienHe() {
        return "lienHe";
    }

    @GetMapping("/tuyendung")
    public String tuyenDung() {
        return "tuyenDung";
    }

    // giỏ hàng
    @Autowired
    GioHangService giohangService;

    @GetMapping("/home/giohang")
    public String gioHang(Model model) {
        // System.out.println(giohangService.getGioHang().getChiTietGioHang());
        model.addAttribute("giohang", giohangService.getGioHang());

        return "giohangmini";
    }

    @GetMapping("/home/giohang/themsanpham/{id}")
    public String addGioHang(@PathVariable("id") int sanpham_id) {
        giohangService.themSanPham(sanpham_id);
        return "redirect:/home";
    }

    @GetMapping("/home/giohang/congsanpham/{id}")
    public String congGioHang(@PathVariable("id") int sanpham_id) {
        giohangService.themSanPham(sanpham_id);
        return "redirect:/home/giohang";
    }

    @GetMapping("/home/giohang/trusanpham/{id}")
    public String truSPGioHang(@PathVariable("id") int sanpham_id) {
        giohangService.truSanPham(sanpham_id);
        return "redirect:/home/giohang";
    }

    @GetMapping("/home/giohang/xoasanpham/{id}")
    public String xoaSPGioHang(@PathVariable("id") int sanpham_id) {
        giohangService.xoaSanPham(sanpham_id);
        return "redirect:/home/giohang";
    }

    // đăng ký đăng nhập
    @GetMapping("/shopsale/dangnhap")
    public String dangNhap(Model model) {
        model.addAttribute("dangnhap", new Login());
        return "dangNhap";
    }

    @PostMapping("/shopsale/dangnhap")
    public String loginPostMapping(@Valid @ModelAttribute("dangnhap") Login dangnhap, Model model) {

        if (customerService.dangNhap(dangnhap.getTenDangNhap(), dangnhap.getMatKhau())) {

            if (customerService.getLastPage() == LAST_PAGE.CART) {

                return "redirect:/home/chitietgiohang";
            }
            return "redirect:/home";

        } else {
            model.addAttribute("dangnhap", dangnhap);
            model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
            return "dangNhap";
        }
    }

    @GetMapping("/shopsale/dangky")
    public String dangKy(Model model) {
        model.addAttribute("dangky", new Users());
        return "dangKy";
    }

    @PostMapping("/shopsale/dangky")
    public String dangKyThanhCong(@Validated @ModelAttribute("dangky") Users user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "dangKy";
        }
        // dat chuc vu user cho khach hang dang ki
        user.setUser_chucvu("Customer");

        usersReponsitory.save(user);

        mailService.guiMailDangKyThanhCong(user);

        return "redirect:/shopsale/dangnhap";
    }

    @GetMapping("home/chitietgiohang")
    public String CtGioHang(Model model) {

        if (!customerService.isUserLogin()) {
            return "redirect:/shopsale/dangnhap";
        }
        model.addAttribute("giohang", giohangService.getGioHang());

        customerService.setLastPage(LAST_PAGE.CART);
        model.addAttribute("currentUser", customerService.getUsers());

        model.addAttribute("order", new Orders());

        return "gioHang";
    }

    @PostMapping("home/chitietgiohang")
    public String hoanthanhgio(@Valid @ModelAttribute("order") Orders order, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "gioHang";
        }

        // luu user
        order.setUser(customerService.getUsers());
        order.setTotal(giohangService.getTotal());
        orderRepository.save(order);
        // luu chi tiet gio hang

        Map<SanPhamDT, Integer> listItems = giohangService.getGioHang().getChiTietGioHang();
        for (SanPhamDT dt : listItems.keySet()) {
            OrderItems item = new OrderItems();
            item.setAmount(listItems.get(dt));
            item.setName(dt.getSanpham_name());
            item.setOrder(order);
            item.setPrice(dt.getSanpham_giaban());
            item.setSanpham(dt);
            item.setTotal((listItems.get(dt)) * (dt.getSanpham_giaban()));
            orderItemsRepository.save(item);
        }
        model.addAttribute("currentUser", customerService.getUsers());

        // gui email
        mailService.guiMailXacNhanDonHang(order, listItems);
        // làm mới giỏ hàng
        giohangService.getGioHang().getChiTietGioHang().clear();
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        customerService.dangXuat();
        return "redirect:/home";
    }

    // errorPage
    @GetMapping("pageNotFound")
    public String pageNotFound() {
        return "errorPage";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // số sản phẩm tối đa
    private static final int TOI_DA_PHU_KIEN_HOME = 12;
    private final String[] ORDER_PHU_KIEN_NAME = { "phukien_giaban", "created_date" };
    @Autowired
    PhuKienReponsitory phuKienReponsitory;

    @Autowired
    LoaiPKRepponsitory loaiPKRepponsitory;

    @Autowired
    ThuongHieuPKReponsitory thuongHieuPKReponsitory;

    @GetMapping("/home/phukien")
    public String homePhuKien(@RequestParam(name = "key", defaultValue = "") String key,
            @RequestParam(name = "loaip", defaultValue = "0") int loaip_id,
            @RequestParam(name = "thuonghieu", defaultValue = "0") int thuonghieu,
            @RequestParam(name = "order", defaultValue = "1") int order,
            @RequestParam(name = "page", defaultValue = "0") int pageIndex,
            @RequestParam(name = "sort", defaultValue = "0") int sort, Model model) {
        // XU LY order col name
        if (order < 0 || order > ORDER_PHU_KIEN_NAME.length - 1)
            order = 1;

        // Lấy sản phẩm phân trang:
        Pageable pager = PageRequest.of(pageIndex, TOI_DA_PHU_KIEN_HOME, // pagin
                sort == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, // sort
                ORDER_PHU_KIEN_NAME[order]); // order

        Page<PhuKien> phukien = phuKienReponsitory.SearchProductsOrder(key, loaip_id, thuonghieu, pager);

        model.addAttribute("phukien", phukien.getContent());
        model.addAttribute("maxPage", phukien.getTotalPages());

        model.addAttribute("key", key);
        model.addAttribute("loaip", loaip_id);
        model.addAttribute("thuonghieu", thuonghieu);
        model.addAttribute("order", order);
        model.addAttribute("page", pageIndex);
        model.addAttribute("sort", sort);
        model.addAttribute("currentUser", customerService.getUsers());

        List<LoaiPhuKien> loaipklist = loaiPKRepponsitory.findAll();
        List<ThuongHieuPhuKien> thuonghieulist = thuongHieuPKReponsitory.findAll();
        model.addAttribute("loaipk", loaipklist);
        model.addAttribute("thuonghieupk", thuonghieulist);

        customerService.setLastPage(LAST_PAGE.HOME);
        return "phuKienPage";
    }

    @GetMapping("/home/phukien/search")
    public String homePhuKienSearch(@RequestParam(name = "key", defaultValue = "") String key,
            @RequestParam(name = "loaip", defaultValue = "0") int loaip_id,
            @RequestParam(name = "thuonghieu", defaultValue = "0") int thuonghieu,
            @RequestParam(name = "order", defaultValue = "1") int order,
            @RequestParam(name = "page", defaultValue = "0") int pageIndex,
            @RequestParam(name = "sort", defaultValue = "0") int sort, Model model) {
        // XU LY order col name
        if (order < 0 || order > ORDER_PHU_KIEN_NAME.length - 1)
            order = 1;

        // Lấy sản phẩm phân trang:
        Pageable pager = PageRequest.of(pageIndex, TOI_DA_PHU_KIEN_HOME, // pagin
                sort == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, // sort
                ORDER_PHU_KIEN_NAME[order]); // order

        Page<PhuKien> phukien = phuKienReponsitory.SearchProductsOrder(key, loaip_id, thuonghieu, pager);

        model.addAttribute("phukien", phukien.getContent());
        model.addAttribute("maxPage", phukien.getTotalPages());

        model.addAttribute("key", key);
        model.addAttribute("loaip", loaip_id);
        model.addAttribute("thuonghieu", thuonghieu);
        model.addAttribute("order", order);
        model.addAttribute("page", pageIndex);
        model.addAttribute("sort", sort);
        model.addAttribute("currentUser", customerService.getUsers());

        List<LoaiPhuKien> loaipklist = loaiPKRepponsitory.findAll();
        List<ThuongHieuPhuKien> thuonghieulist = thuongHieuPKReponsitory.findAll();
        model.addAttribute("loaipk", loaipklist);
        model.addAttribute("thuonghieupk", thuonghieulist);

        customerService.setLastPage(LAST_PAGE.HOME);
        return "phuKienPage";
    }

    // lấy sản phẩm theo id để biết về nội dung sản phẩm
    // hãng được liên kết nên từ sản phẩm. hãng, so sánh với id hãng để lấy nội dung
    @GetMapping("/home/phukien/chitiet")
    public String chiTietPhuKien(@RequestParam(name = "phukien_id") int phukien_id, Model model) {
        Optional<PhuKien> phukienOption = phuKienReponsitory.findById(phukien_id);
        if (phukienOption.isEmpty()) {
            return "redirect:/home/phukien";
        }
        model.addAttribute("chitiet", phukienOption.get());

        return "chiTietPhuKien";
    }

    @GetMapping("/home/khuyenmai")
    public String khuyenMaiPage() {
        return "khuyenMai";
    }

    @GetMapping("/home/huongdanmuahang")
    public String huongDanMuaHang() {
        return "huongDanMuaHang";
    }
}
