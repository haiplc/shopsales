package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.ChipSanPham;
import shopsale.com.asmspringboot.Reponsitory.ChipDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ChipDTAPI {

    @Autowired
    ChipDTReponsitory chipDTReponsitory;

    @GetMapping("/api/chipdt")
    public List<ChipSanPham> list() {
        return chipDTReponsitory.findAll();
    }

    @GetMapping("/api/chipdt/{id}")
    public ChipSanPham getById(@PathVariable("id") int id) {
        return chipDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/chipdt")
    public ChipSanPham insert(@RequestBody @Validated ChipSanPham chipsp) {
        return chipDTReponsitory.save(chipsp);
    }

    @PutMapping(value = "/api/chipdt/{id}")
    public ChipSanPham update(@PathVariable("id") int id, @RequestBody ChipSanPham chipsp) {
        return chipDTReponsitory.save(chipsp);
    }

    @DeleteMapping("/api/chipdt/{id}")
    public void delete(@PathVariable("id") int id) {
        chipDTReponsitory.deleteById(id);
    }

}
