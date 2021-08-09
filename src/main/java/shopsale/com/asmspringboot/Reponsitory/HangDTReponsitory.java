package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.HangSanPham;

public interface HangDTReponsitory extends JpaRepository<HangSanPham, Integer> {
}
