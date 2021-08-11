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
@Entity(name = "loaidt")
public class LoaiSanPham {
	@Id
	@GeneratedValue
	@Column(name = "loai_id")
	int loai_id;

	@Size(min = 5, max = 30, message = "Không được để trống! Tối thiểu 5, tối đa 30 ký tự.")
	@Column(name = "loai_name", unique = true)
	String loai_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loai")
	List<SanPhamDT> sanphamdt;
}
