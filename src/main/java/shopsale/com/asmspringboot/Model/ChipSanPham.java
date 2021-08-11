package shopsale.com.asmspringboot.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chipdt")
public class ChipSanPham {
	@Id
	@GeneratedValue
	@Column(name = "chip_id")
	int chip_id;

	@Size(min = 5, max = 30, message = "Không được để trống! Tối thiểu 5, tối đa 30 ký tự.")
	@Column(name = "chip_name", unique = true)
	String chip_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chip")
	List<SanPhamDT> sanphamdt;

}
