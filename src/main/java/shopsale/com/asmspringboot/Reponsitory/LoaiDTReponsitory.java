package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.LoaiSanPham;

public interface LoaiDTReponsitory extends JpaRepository<LoaiSanPham, Integer> {
}
