package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.LoaiPhuKien;
import shopsale.com.asmspringboot.Reponsitory.LoaiPKRepponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoaiPhuKienAPI {

    @Autowired
    LoaiPKRepponsitory loaiPKRepponsitory;

    @GetMapping("/api/loaipk")
    public List<LoaiPhuKien> list() {
        return loaiPKRepponsitory.findAll();
    }

    @GetMapping("/api/loaipk/{id}")
    public LoaiPhuKien getById(@PathVariable("id") int id) {
        return loaiPKRepponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/loaipk")
    public LoaiPhuKien insert(@RequestBody @Validated LoaiPhuKien loaip) {
        return loaiPKRepponsitory.save(loaip);
    }

    @PutMapping(value = "/api/loaipk/{id}")
    public LoaiPhuKien update(@PathVariable("id") int id, @RequestBody LoaiPhuKien loaip) {
        return loaiPKRepponsitory.save(loaip);
    }

    @DeleteMapping("/api/loaipk/{id}")
    public void delete(@PathVariable("id") int id) {
        loaiPKRepponsitory.deleteById(id);
    }

}
