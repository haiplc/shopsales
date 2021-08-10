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
@Entity(name = "thuonghieupk")
public class ThuongHieuPhuKien {
    @Id
    @GeneratedValue
    @Column(name = "thuonghieu_id")
    int thuonghieu_id;

    @Size(min = 2, max = 50, message = "Không được để trống! Tối thiểu 2, tối đa 50 kí tự.")
    @Column(name = "thuonghieu_name")
    String thuonghieu_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thuonghieu")
    List<PhuKien> phukien;
}
