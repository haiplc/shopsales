package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.ManHinhSanPham;

public interface ManHinhDTReponsitory extends JpaRepository<ManHinhSanPham, Integer> {
}
