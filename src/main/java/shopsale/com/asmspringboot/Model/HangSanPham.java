package shopsale.com.asmspringboot.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hangdt")
public class HangSanPham {
	@Id
	@GeneratedValue
	@Column(name = "hang_id")
	int hang_id;

	@Size(min = 2, max = 30, message = "Không được để trống! Tối thiểu 2, tối đa 30 ký tự.")
	@Column(name = "hang_name", unique = true)
	String hang_name;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hang")
	List<SanPhamDT> sanphamdt;
}
