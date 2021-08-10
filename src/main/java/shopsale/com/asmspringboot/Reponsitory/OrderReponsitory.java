package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shopsale.com.asmspringboot.Model.Orders;

public interface OrderReponsitory extends JpaRepository<Orders, Integer> {
    @Query(value = "select * from orders where phone_number ~* ?1", nativeQuery = true)
    Page<Orders> findByNameOrderContaining(String search, Pageable pager);
}
