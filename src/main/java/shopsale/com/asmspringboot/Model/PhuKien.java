package shopsale.com.asmspringboot.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phukien")
public class PhuKien {
	@Id
	@GeneratedValue
	@Column(name = "phukien_id")
	int phukien_id;

	@Size(max = 100, min = 5, message = "Không được để trống! Tối thiểu 5, tối đa 100 kí tự.")
	@Column(name = "phukien_name")
	String phukien_name;

	@ManyToOne
	@JoinColumn(name = "chatlieu_id", nullable = true, foreignKey = @ForeignKey(name = "chatlieu_name"))
	ChatLieuPhuKien chatlieu;

	@ManyToOne
	@JoinColumn(name = "loaip_id", nullable = true, foreignKey = @ForeignKey(name = "loaip_name"))
	LoaiPhuKien loaip;

	@ManyToOne
	@JoinColumn(name = "tuongthich_id", nullable = true, foreignKey = @ForeignKey(name = "tuongthich_name"))
	TuongThichPhuKien tuongthich;

	@ManyToOne
	@JoinColumn(name = "thoigianbaohanh_id", nullable = true, foreignKey = @ForeignKey(name = "thoigianbaohanh_name"))
	ThoiGianBaoHanhPhuKien thoigianbaohanh;

	@ManyToOne
	@JoinColumn(name = "thuonghieu_id", nullable = true, foreignKey = @ForeignKey(name = "thuonghieu_name"))
	ThuongHieuPhuKien thuonghieu;

	@ManyToOne
	@JoinColumn(name = "xuatxu_id", nullable = true, foreignKey = @ForeignKey(name = "xuatxu_name"))
	XuatXuPhuKien xuatxu;

	@ManyToOne
	@JoinColumn(name = "mausac_id", nullable = true, foreignKey = @ForeignKey(name = "mausac_name"))
	MauSacSanPham mausac;

	@NotNull(message = "Không được để trống!")
	@Column(name = "phukien_giaban")
	Long phukien_giaban;

	@NotNull(message = "Không được để trống!")
	@Column(name = "phukien_soluong")
	Long phukien_soluong;

	// id, ten, loai, hang, tuongthich, ram, manhinh,
	// bonho, mausac, giaban, soluong, anh 1, anh 2, url,
	// lienhe, mota, ngaytao
	@NotNull(message = "Không được để trống!")
	// @Pattern(regexp =
	// "(http://|https://|/|../)[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\/[a-zA-Z0-9-]*)+(.jpg|.png)",
	// message = "Sai định dạng ảnh! Mời nhập lại!")
	@Column(name = "phukien_anh1")
	@Size(max = 400)
	String phukien_anh1;

	@NotNull(message = "Không được để trống!")
	// @Pattern(regexp =
	// "(http://|https://|/|../)[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\/[a-zA-Z0-9-]*)+(.jpg|.png)",
	// message = "Sai định dạng ảnh! Mời nhập lại!")
	@Column(name = "phukien_anh2")
	@Size(max = 400)
	String phukien_anh2;

	@Size(min = 5, max = 900, message = "Không được để trống! Tối thiểu 5, tối đa 900 kí tự.")
	@Column(name = "phukien_tinhnang1")
	String phukien_tinhnang1;

	@Size(min = 5, max = 900, message = "Không được để trống! Tối thiểu 5, tối đa 900 kí tự.")
	@Column(name = "phukien_tinhnang2")
	String phukien_tinhnang2;

	@Column(name = "created_date")
	Date createdDate = new Date((new java.util.Date()).getTime());

	@Size(max = 4, min = 4, message = "Không được để trống! Năm có 4 kí tự số.")
	@Column(name = "phukien_namsx")
	String phukien_namsx;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PhuKien) {
			PhuKien cSanPham = (PhuKien) obj;
			return this.phukien_id == cSanPham.getPhukien_id();
		}
		return false;

	}

	@Override
	public int hashCode() {
		return phukien_id;
	}

	public String getDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(createdDate);
	}

}
