package shopsale.com.asmspringboot.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Orders {
	@Id
	@GeneratedValue
	int id;

	@NotNull
	Date created = new Date((new java.util.Date()).getTime());

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_id"))
	Users user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	List<OrderItems> order_items;

	@NotBlank(message = "Địa chỉ nhận hàng không được để trống!")
	String address;

	@NotBlank(message = "Số điện thoại không được để trống!")
	String phoneNumber;

	@NotBlank(message = "Tên khách hàng không được để trống!")
	@Size(min = 5, max = 50, message = "Tên khách hàng tối thiểu 5, tối đa 50 ký tự!")
	String name;

	// @Pattern(regexp = "")
	@Column(name = "email")
	String email;

	@Column(name = "total")
	double total = 0;

	public String getDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		return formatter.format(getCreated());
	}

}
