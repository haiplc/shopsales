package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.TuongThichPhuKien;
import shopsale.com.asmspringboot.Reponsitory.TuongThichPKReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TuongThichPKAPI {

    @Autowired
    TuongThichPKReponsitory tuongThichPKReponsitory;

    @GetMapping("/api/tuongthichpk")
    public List<TuongThichPhuKien> list() {
        return tuongThichPKReponsitory.findAll();
    }

    @GetMapping("/api/tuongthichpk/{id}")
    public TuongThichPhuKien getById(@PathVariable("id") int id) {
        return tuongThichPKReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/tuongthichpk")
    public TuongThichPhuKien insert(@RequestBody @Validated TuongThichPhuKien tuongthich) {
        return tuongThichPKReponsitory.save(tuongthich);
    }

    @PutMapping(value = "/api/tuongthichpk/{id}")
    public TuongThichPhuKien update(@PathVariable("id") int id, @RequestBody TuongThichPhuKien tuongthich) {
        return tuongThichPKReponsitory.save(tuongthich);
    }

    @DeleteMapping("/api/tuongthichpk/{id}")
    public void delete(@PathVariable("id") int id) {
        tuongThichPKReponsitory.deleteById(id);
    }

}
