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
@Entity(name = "tuongthichpk")
public class TuongThichPhuKien {
    @Id
    @GeneratedValue
    @Column(name = "tuongthich_id")
    int tuongthich_id;

    @Size(min = 2, max = 50, message = "Không được để trống! Tối thiểu 2, tối đa 50 kí tự.")
    @Column(name = "tuongthich_name")
    String tuongthich_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tuongthich")
    List<PhuKien> phukien;
}
