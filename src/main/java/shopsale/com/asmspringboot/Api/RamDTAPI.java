package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.RamSanPham;
import shopsale.com.asmspringboot.Reponsitory.RamDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RamDTAPI {

    @Autowired
    RamDTReponsitory ramDTReponsitory;

    @GetMapping("/api/ramdt")
    public List<RamSanPham> list() {
        return ramDTReponsitory.findAll();
    }

    @GetMapping("/api/ramdt/{id}")
    public RamSanPham getById(@PathVariable("id") int id) {
        return ramDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/ramdt")
    public RamSanPham insert(@RequestBody @Validated RamSanPham ramsp) {
        return ramDTReponsitory.save(ramsp);
    }

    @PutMapping(value = "/api/ramdt/{id}")
    public RamSanPham update(@PathVariable("id") int id, @RequestBody RamSanPham ramsp) {
        return ramDTReponsitory.save(ramsp);
    }

    @DeleteMapping("/api/ramdt/{id}")
    public void delete(@PathVariable("id") int id) {
        ramDTReponsitory.deleteById(id);
    }

}
