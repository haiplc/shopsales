package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.ThuongHieuPhuKien;
import shopsale.com.asmspringboot.Reponsitory.ThuongHieuPKReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ThuongHieuPKAPI {

    @Autowired
    ThuongHieuPKReponsitory thuongHieuPKReponsitory;

    @GetMapping("/api/thuonghieupk")
    public List<ThuongHieuPhuKien> list() {
        return thuongHieuPKReponsitory.findAll();
    }

    @GetMapping("/api/thuonghieupk/{id}")
    public ThuongHieuPhuKien getById(@PathVariable("id") int id) {
        return thuongHieuPKReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/thuonghieupk")
    public ThuongHieuPhuKien insert(@RequestBody @Validated ThuongHieuPhuKien tuonghieu) {
        return thuongHieuPKReponsitory.save(tuonghieu);
    }

    @PutMapping(value = "/api/thuonghieupk/{id}")
    public ThuongHieuPhuKien update(@PathVariable("id") int id, @RequestBody ThuongHieuPhuKien tuonghieu) {
        return thuongHieuPKReponsitory.save(tuonghieu);
    }

    @DeleteMapping("/api/thuonghieupk/{id}")
    public void delete(@PathVariable("id") int id) {
        thuongHieuPKReponsitory.deleteById(id);
    }

}
