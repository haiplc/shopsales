package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.PhuKien;
import shopsale.com.asmspringboot.Reponsitory.PhuKienReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PhuKienAPI {

    @Autowired
    PhuKienReponsitory phuKienReponsitory;

    @GetMapping("/api/phukien")
    public List<PhuKien> list() {
        return phuKienReponsitory.findAll();
    }

    @GetMapping("/api/phukien/{id}")
    public PhuKien getById(@PathVariable("id") int id) {
        return phuKienReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/phukien")
    public PhuKien insert(@RequestBody @Validated PhuKien sanphamphukien) {
        return phuKienReponsitory.save(sanphamphukien);
    }

    @PutMapping(value = "/api/phukien/{id}")
    public PhuKien update(@PathVariable("id") int id, @RequestBody PhuKien sanphamphukien) {
        return phuKienReponsitory.save(sanphamphukien);
    }

    @DeleteMapping("/api/phukien/{id}")
    public void delete(@PathVariable("id") int id) {
        phuKienReponsitory.deleteById(id);
    }

}
