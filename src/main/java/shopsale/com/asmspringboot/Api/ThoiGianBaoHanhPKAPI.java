package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.ThoiGianBaoHanhPhuKien;
import shopsale.com.asmspringboot.Reponsitory.ThoiGianBaoHanhReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ThoiGianBaoHanhPKAPI {

    @Autowired
    ThoiGianBaoHanhReponsitory thoiGianBaoHanhReponsitory;

    @GetMapping("/api/thoigianbaohanhpk")
    public List<ThoiGianBaoHanhPhuKien> list() {
        return thoiGianBaoHanhReponsitory.findAll();
    }

    @GetMapping("/api/thoigianbaohanhpk/{id}")
    public ThoiGianBaoHanhPhuKien getById(@PathVariable("id") int id) {
        return thoiGianBaoHanhReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/thoigianbaohanhpk")
    public ThoiGianBaoHanhPhuKien insert(@RequestBody @Validated ThoiGianBaoHanhPhuKien thoigianbaohanh) {
        return thoiGianBaoHanhReponsitory.save(thoigianbaohanh);
    }

    @PutMapping(value = "/api/thoigianbaohanhpk/{id}")
    public ThoiGianBaoHanhPhuKien update(@PathVariable("id") int id,
            @RequestBody ThoiGianBaoHanhPhuKien thoigianbaohanh) {
        return thoiGianBaoHanhReponsitory.save(thoigianbaohanh);
    }

    @DeleteMapping("/api/thoigianbaohanhpk/{id}")
    public void delete(@PathVariable("id") int id) {
        thoiGianBaoHanhReponsitory.deleteById(id);
    }

}
