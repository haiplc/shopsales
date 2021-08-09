package shopsale.com.asmspringboot.Reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shopsale.com.asmspringboot.Model.OrderItems;

public interface OrderItemsReponsitory extends JpaRepository<OrderItems, Integer> {
	@Query(value = "select * from order_items where order_id = :order_id", nativeQuery = true)
	List<OrderItems> findOrderItems(@Param(value = "order_id") int order_id);
}
