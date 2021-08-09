package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.RamSanPham;

public interface RamDTReponsitory extends JpaRepository<RamSanPham, Integer> {

}
