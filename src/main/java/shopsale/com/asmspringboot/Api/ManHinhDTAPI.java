package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.ManHinhSanPham;
import shopsale.com.asmspringboot.Reponsitory.ManHinhDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ManHinhDTAPI {

    @Autowired
    ManHinhDTReponsitory manHinhDTReponsitory;

    @GetMapping("/api/manhinhdt")
    public List<ManHinhSanPham> list() {
        return manHinhDTReponsitory.findAll();
    }

    @GetMapping("/api/manhinhdt/{id}")
    public ManHinhSanPham getById(@PathVariable("id") int id) {
        return manHinhDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/manhinhdt")
    public ManHinhSanPham insert(@RequestBody @Validated ManHinhSanPham manhinhsanpham) {
        return manHinhDTReponsitory.save(manhinhsanpham);
    }

    @PutMapping(value = "/api/manhinhdt/{id}")
    public ManHinhSanPham update(@PathVariable("id") int id, @RequestBody ManHinhSanPham manhinhsanpham) {
        return manHinhDTReponsitory.save(manhinhsanpham);
    }

    @DeleteMapping("/api/manhinhdt/{id}")
    public void delete(@PathVariable("id") int id) {
        manHinhDTReponsitory.deleteById(id);
    }

}
