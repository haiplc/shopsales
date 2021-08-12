package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.LoaiSanPham;
import shopsale.com.asmspringboot.Reponsitory.LoaiDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoaiDienThoaiAPI {

    @Autowired
    LoaiDTReponsitory loaiDTReponsitory;

    @GetMapping("/api/loaidt")
    public List<LoaiSanPham> list() {
        return loaiDTReponsitory.findAll();
    }

    @GetMapping("/api/loaidt/{id}")
    public LoaiSanPham getById(@PathVariable("id") int id) {
        return loaiDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/loaidt")
    public LoaiSanPham insert(@RequestBody @Validated LoaiSanPham loaidienthoai) {
        return loaiDTReponsitory.save(loaidienthoai);
    }

    @PutMapping(value = "/api/loaidt/{id}")
    public LoaiSanPham update(@PathVariable("id") int id, @RequestBody LoaiSanPham loaidienthoai) {
        return loaiDTReponsitory.save(loaidienthoai);
    }

    @DeleteMapping("/api/loaidt/{id}")
    public void delete(@PathVariable("id") int id) {
        loaiDTReponsitory.deleteById(id);
    }

}
