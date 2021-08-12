package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.BoNhoSanPham;
import shopsale.com.asmspringboot.Reponsitory.BoNhoDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BoNhoDTAPI {

    @Autowired
    BoNhoDTReponsitory boNhoDTReponsitory;

    @GetMapping("/api/bonhodt")
    public List<BoNhoSanPham> list() {
        return boNhoDTReponsitory.findAll();
    }

    @GetMapping("/api/bonhodt/{id}")
    public BoNhoSanPham getById(@PathVariable("id") int id) {
        return boNhoDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/bonhodt")
    public BoNhoSanPham insert(@RequestBody @Validated BoNhoSanPham bonhosp) {
        return boNhoDTReponsitory.save(bonhosp);
    }

    @PutMapping(value = "/api/bonhodt/{id}")
    public BoNhoSanPham update(@PathVariable("id") int id, @RequestBody BoNhoSanPham bonhosp) {
        return boNhoDTReponsitory.save(bonhosp);
    }

    @DeleteMapping("/api/bonhodt/{id}")
    public void delete(@PathVariable("id") int id) {
        boNhoDTReponsitory.deleteById(id);
    }

}
