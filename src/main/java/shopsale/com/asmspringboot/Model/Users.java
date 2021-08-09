package shopsale.com.asmspringboot.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class Users {
	// id,ten,sdt,tendn,mk, image, chucvu
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	int user_id;

	@Size(min = 3, max = 50, message = "Không được để trống! Tối thiểu 3, tối đa 50 ký tự.")
	@Column(name = "user_name")
	String user_name;

	@NotNull(message = "Không được để trống!")
	@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Sai định dạng Số điện thoại")
	@Size(min = 10, max = 10, message = "Số điện thoại có 10 ký tự.")

	@Column(name = "user_sdt")
	String user_sdt;

	@NotNull(message = "Không được để trống!")
	@Size(min = 5, max = 50, message = "Không được để trống! Tối thiểu 5, tối đa 50 ký tự.")
	@Column(name = "user_tendangnhap")
	String user_tendangnhap;

	@Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", message = "Sai định dạng Email")
	@Size(min = 15, max = 100, message = "Email tối thiểu 15, tối đa 100 ký tự.")
	@Column(name = "user_email")
	String user_email;

	@NotNull(message = "Không được để trống!")
	@Size(min = 3, max = 50, message = "Không được để trống! Tối thiểu 3, tối đa 50 ký tự.")
	@Column(name = "user_matkhau")
	String user_matkhau;

	@Column(name = "user_image")
	String user_image;

	@Column(name = "user_chucvu")
	String user_chucvu;

	@Column(name = "address")
	String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	List<Orders> order;
}
