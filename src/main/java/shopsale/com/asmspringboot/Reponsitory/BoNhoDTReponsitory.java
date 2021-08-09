package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.BoNhoSanPham;

public interface BoNhoDTReponsitory extends JpaRepository<BoNhoSanPham, Integer> {

}
