package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.Users;

public interface ThanhToanGioHangReponsitory extends JpaRepository<Users, Integer> {

}
