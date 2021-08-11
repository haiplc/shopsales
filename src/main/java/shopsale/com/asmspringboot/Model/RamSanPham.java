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
@Entity(name = "ramdt")
public class RamSanPham {
	@Id
	@GeneratedValue
	@Column(name = "ram_id")
	int ram_id;

	@Size(min = 3, max = 20, message = "Không được để trống! Tối thiểu 3, tối đa 20 ký tự.")
	@Column(name = "ram_name", unique = true)
	String ram_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ram")
	List<SanPhamDT> sanphamdt;
}
