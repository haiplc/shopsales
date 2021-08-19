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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sanphamdt")
public class SanPhamDT {
	@Id
	@GeneratedValue
	@Column(name = "sanpham_id")
	int sanpham_id;

	@NotBlank(message = "Không được để trống!")
	@Column(name = "sanpham_name")
	String sanpham_name;

	@ManyToOne
	@JoinColumn(name = "loai_id", nullable = true, foreignKey = @ForeignKey(name = "loai_name"))
	LoaiSanPham loai;

	@ManyToOne
	@JoinColumn(name = "hang_id", nullable = true, foreignKey = @ForeignKey(name = "hang_name"))
	HangSanPham hang;

	@ManyToOne
	@JoinColumn(name = "chip_id", nullable = true, foreignKey = @ForeignKey(name = "chip_name"))
	ChipSanPham chip;

	@ManyToOne
	@JoinColumn(name = "ram_id", nullable = true, foreignKey = @ForeignKey(name = "ram_name"))
	RamSanPham ram;

	@ManyToOne
	@JoinColumn(name = "manhinh_id", nullable = true, foreignKey = @ForeignKey(name = "manhinh_name"))
	ManHinhSanPham manhinh;

	@ManyToOne
	@JoinColumn(name = "bonho_id", nullable = true, foreignKey = @ForeignKey(name = "bonho_name"))
	BoNhoSanPham bonho;

	@ManyToOne
	@JoinColumn(name = "mausac_id", nullable = true, foreignKey = @ForeignKey(name = "mausac_name"))
	MauSacSanPham mausac;

	@NotNull(message = "Không được để trống!")
	@Column(name = "sanpham_giaban")
	Long sanpham_giaban;

	@NotNull(message = "Không được để trống!")
	@Column(name = "sanpham_soluong")
	Long sanpham_soluong;

	// id, ten, loai, hang, chip, ram, manhinh,
	// bonho, mausac, giaban, soluong, anh 1, anh 2, url,
	// lienhe, mota, ngaytao
	@NotNull(message = "Không được để trống!")
	@Pattern(regexp = "(http://|https://|/|../)[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\/[a-zA-Z0-9-]*)+(.jpg|.png)", message = "Sai định dạng ảnh! Mời nhập lại!")
	@Column(name = "sanpham_anh1")
	@Size(max = 400)
	String sanpham_anh1;

	@NotNull(message = "Không được để trống!")
	@Pattern(regexp = "(http://|https://|/|../)[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\/[a-zA-Z0-9-]*)+(.jpg|.png)", message = "Sai định dạng ảnh! Mời nhập lại!")
	@Column(name = "sanpham_anh2")
	@Size(max = 400)
	String sanpham_anh2;

	@Column(name = "sanpham_url")
	String sanpham_url;

	@Size(min = 5, max = 500, message = "Không được để trống! Tối thiểu 5, tối đa 500 kí tự.")
	@Column(name = "sanpham_lienhe")
	String sanpham_lienhe;

	@Size(min = 5, max = 900, message = "Không được để trống! Tối thiểu 5, tối đa 900 kí tự.")
	@Column(name = "sanpham_mota")
	String sanpham_mota;

	@Column(name = "created_date")
	Date createdDate = new Date((new java.util.Date()).getTime());

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SanPhamDT) {
			SanPhamDT cSanPham = (SanPhamDT) obj;
			return this.sanpham_id == cSanPham.getSanpham_id();
		}
		return false;

	}

	@Override
	public int hashCode() {
		return sanpham_id;
	}

	public String getDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(createdDate);
	}

}
