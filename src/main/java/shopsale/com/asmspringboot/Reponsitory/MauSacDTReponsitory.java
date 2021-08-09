package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.MauSacSanPham;

public interface MauSacDTReponsitory extends JpaRepository<MauSacSanPham, Integer> {
}
