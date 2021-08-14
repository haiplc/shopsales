package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.SanPhamDT;
import shopsale.com.asmspringboot.Reponsitory.SanPhamDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SanPhamDTAPI {

    @Autowired
    SanPhamDTReponsitory sanPhamDTReponsitory;

    @GetMapping("/api/sanphamdt")
    public List<SanPhamDT> list() {
        return sanPhamDTReponsitory.findAll();
    }

    @GetMapping("/api/sanphamdt/{id}")
    public SanPhamDT getById(@PathVariable("id") int id) {
        return sanPhamDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/sanphamdt")
    public SanPhamDT insert(@RequestBody @Validated SanPhamDT dienthoai) {
        return sanPhamDTReponsitory.save(dienthoai);
    }

    @PutMapping(value = "/api/sanphamdt/{id}")
    public SanPhamDT update(@PathVariable("id") int id, @RequestBody SanPhamDT dienthoai) {
        return sanPhamDTReponsitory.save(dienthoai);
    }

    @DeleteMapping("/api/sanphamdt/{id}")
    public void delete(@PathVariable("id") int id) {
        sanPhamDTReponsitory.deleteById(id);
    }

    // số sản phẩm tối đa
    private static final int TOI_DA_SAN_PHAM = 4;

    // list danh sách và map danh sách ra trang chính
    @GetMapping("/api/sanphamdt/search")
    public Page<SanPhamDT> quanLySanPham(@RequestBody String search, @RequestBody int pageIndex) {
        Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);

        Page<SanPhamDT> sanphamPage = sanPhamDTReponsitory.findByNameContaining(search, pager);
        return sanphamPage;
    }
}
