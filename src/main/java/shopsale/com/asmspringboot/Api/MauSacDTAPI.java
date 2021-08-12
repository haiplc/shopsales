package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.MauSacSanPham;
import shopsale.com.asmspringboot.Reponsitory.MauSacDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MauSacDTAPI {

    @Autowired
    MauSacDTReponsitory mauSacDTReponsitory;

    @GetMapping("/api/mausacdt")
    public List<MauSacSanPham> list() {
        return mauSacDTReponsitory.findAll();
    }

    @GetMapping("/api/mausacdt/{id}")
    public MauSacSanPham getById(@PathVariable("id") int id) {
        return mauSacDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/mausacdt")
    public MauSacSanPham insert(@RequestBody @Validated MauSacSanPham mausacsp) {
        return mauSacDTReponsitory.save(mausacsp);
    }

    @PutMapping(value = "/api/mausacdt/{id}")
    public MauSacSanPham update(@PathVariable("id") int id, @RequestBody MauSacSanPham mausacsp) {
        return mauSacDTReponsitory.save(mausacsp);
    }

    @DeleteMapping("/api/mausacdt/{id}")
    public void delete(@PathVariable("id") int id) {
        mauSacDTReponsitory.deleteById(id);
    }

}
