package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.ChipSanPham;

public interface ChipDTReponsitory extends JpaRepository<ChipSanPham, Integer> {
}
