package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.HangSanPham;
import shopsale.com.asmspringboot.Reponsitory.HangDTReponsitory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HangDienThoaiAPI {

    @Autowired
    HangDTReponsitory hangDTReponsitory;

    @GetMapping("/api/hangdt")
    public List<HangSanPham> list() {
        return hangDTReponsitory.findAll();
    }

    @GetMapping("/api/hangdt/{id}")
    public HangSanPham getById(@PathVariable("id") int id) {
        return hangDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/hangdt")
    public HangSanPham insert(@RequestBody @Validated HangSanPham hangsp) {
        return hangDTReponsitory.save(hangsp);
    }

    @PutMapping(value = "/api/hangdt")
    public HangSanPham update(@Validated @RequestBody HangSanPham hangsp) {
        return hangDTReponsitory.save(hangsp);
    }

    @DeleteMapping("/api/hangdt/{id}")
    public void delete(@PathVariable("id") int id) {
        hangDTReponsitory.deleteById(id);
    }

}
