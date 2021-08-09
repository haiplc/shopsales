package shopsale.com.asmspringboot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ForeignKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_items")
public class OrderItems {
	@Id
	@GeneratedValue
	int id;

	@ManyToOne
	@JoinColumn(name = "sanpham_id", nullable = false, foreignKey = @ForeignKey(name = "sanpham_orderitems"))
	SanPhamDT sanpham;

	int amount;
	double total;
	String name;
	double price;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "order_orderitems"))
	Orders order;
}
