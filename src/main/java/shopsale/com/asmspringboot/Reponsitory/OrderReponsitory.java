package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import shopsale.com.asmspringboot.Model.Orders;

public interface OrderReponsitory extends JpaRepository<Orders, Integer> {

}
