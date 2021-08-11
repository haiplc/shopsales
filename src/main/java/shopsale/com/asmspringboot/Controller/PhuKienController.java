package shopsale.com.asmspringboot.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shopsale.com.asmspringboot.Controller.GobalController.LAST_PAGE;
import shopsale.com.asmspringboot.Model.LoaiPhuKien;
import shopsale.com.asmspringboot.Model.PhuKien;
import shopsale.com.asmspringboot.Model.ThuongHieuPhuKien;
import shopsale.com.asmspringboot.Reponsitory.LoaiPKRepponsitory;
import shopsale.com.asmspringboot.Reponsitory.PhuKienReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ThuongHieuPKReponsitory;
import shopsale.com.asmspringboot.Service.CustomerService;

@Controller
public class PhuKienController {
    // số sản phẩm tối đa
    private static final int TOI_DA_PHU_KIEN_HOME = 12;
    private final String[] ORDER_PHU_KIEN_NAME = { "phukien_giaban", "created_date" };
    @Autowired
    PhuKienReponsitory phuKienReponsitory;

    @Autowired
    LoaiPKRepponsitory loaiPKRepponsitory;

    @Autowired
    ThuongHieuPKReponsitory thuongHieuPKReponsitory;

    @Autowired
    CustomerService customerService;

    @GetMapping("/phukien")
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

    @GetMapping("/phukien/search")
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
    @GetMapping("/phukien/chitiet")
    public String chiTietPhuKien(@RequestParam(name = "phukien_id") int phukien_id, Model model) {
        Optional<PhuKien> phukienOption = phuKienReponsitory.findById(phukien_id);
        if (phukienOption.isEmpty()) {
            return "redirect:/home/phukien";
        }
        model.addAttribute("chitiet", phukienOption.get());

        return "chiTietPhuKien";
    }

}
