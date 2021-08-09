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
@Entity(name = "bonhodt")
public class BoNhoSanPham {
	@Id
	@GeneratedValue
	@Column(name = "bonho_id")
	int bonho_id;

	@NotNull
	@Size(min = 3, max = 10, message = "Không được để trống! Tối thiểu 3, tối đa 10 ký tự.")
	@Column(name = "bonho_name", unique = true)
	String bonho_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bonho")
	List<SanPhamDT> sanphamdt;

}
