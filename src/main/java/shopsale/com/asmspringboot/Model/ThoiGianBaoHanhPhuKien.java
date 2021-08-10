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
@Entity(name = "thoigianbaohanhpk")
public class ThoiGianBaoHanhPhuKien {
    @Id
    @GeneratedValue
    @Column(name = "thoigianbaohanh_id")
    int thoigianbaohanh_id;

    @Size(min = 2, max = 50, message = "Không được để trống! Tối thiểu 2, tối đa 50 kí tự.")
    @Column(name = "thoigianbaohanh_name")
    String thoigianbaohanh_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thoigianbaohanh")
    List<PhuKien> phukien;
}
