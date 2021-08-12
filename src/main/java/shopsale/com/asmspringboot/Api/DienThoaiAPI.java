package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DienThoaiAPI {

    @Autowired
    SanPhamDTReponsitory sanPhamDTReponsitory;

    @GetMapping("/api/dienthoai")
    public List<SanPhamDT> list() {
        return sanPhamDTReponsitory.findAll();
    }

    @GetMapping("/api/dienthoai/{id}")
    public SanPhamDT getById(@PathVariable("id") int id) {
        return sanPhamDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/dienthoai")
    public SanPhamDT insert(@RequestBody @Validated SanPhamDT sanphamdienthoai) {
        return sanPhamDTReponsitory.save(sanphamdienthoai);
    }

    @PutMapping(value = "/api/dienthoai/{id}")
    public SanPhamDT update(@PathVariable("id") int id, @RequestBody SanPhamDT sanphamdienthoai) {
        return sanPhamDTReponsitory.save(sanphamdienthoai);
    }

    @DeleteMapping("/api/dienthoai/{id}")
    public void delete(@PathVariable("id") int id) {
        sanPhamDTReponsitory.deleteById(id);
    }

}
