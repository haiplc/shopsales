package shopsale.com.asmspringboot.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manhinhdt")
public class ManHinhSanPham {
	@Id
	@GeneratedValue
	@Column(name = "manhinh_id")
	int manhinh_id;

	@Size(min = 2, max = 10, message = "Không được để trống! Tối thiểu 2, tối đa 10 ký tự.")
	@Column(name = "manhinh_name", unique = true)
	String manhinh_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manhinh")
	List<SanPhamDT> sanphamdt;
}
