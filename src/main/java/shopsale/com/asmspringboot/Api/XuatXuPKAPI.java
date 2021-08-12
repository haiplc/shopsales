package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.XuatXuPhuKien;
import shopsale.com.asmspringboot.Reponsitory.XuatXuPKReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class XuatXuPKAPI {

    @Autowired
    XuatXuPKReponsitory xuatXuPKReponsitory;

    @GetMapping("/api/xuatxupk")
    public List<XuatXuPhuKien> list() {
        return xuatXuPKReponsitory.findAll();
    }

    @GetMapping("/api/xuatxupk/{id}")
    public XuatXuPhuKien getById(@PathVariable("id") int id) {
        return xuatXuPKReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/xuatxupk")
    public XuatXuPhuKien insert(@RequestBody @Validated XuatXuPhuKien xuatxu) {
        return xuatXuPKReponsitory.save(xuatxu);
    }

    @PutMapping(value = "/api/xuatxupk/{id}")
    public XuatXuPhuKien update(@PathVariable("id") int id, @RequestBody XuatXuPhuKien xuatxu) {
        return xuatXuPKReponsitory.save(xuatxu);
    }

    @DeleteMapping("/api/xuatxupk/{id}")
    public void delete(@PathVariable("id") int id) {
        xuatXuPKReponsitory.deleteById(id);
    }

}
