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
@Entity(name = "mausacdt")
public class MauSacSanPham {
	@Id
	@GeneratedValue
	@Column(name = "mausac_id")
	int mausac_id;

	@Size(min = 2, max = 30, message = "Không được để trống! Tối thiểu 2, tối đa 30 ký tự.")
	@Column(name = "mausac_name", unique = true)
	String mausac_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mausac")
	List<SanPhamDT> sanphamdt;
}
