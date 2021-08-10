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
@Entity(name = "chatlieupk")
public class ChatLieuPhuKien {
    @Id
    @GeneratedValue
    @Column(name = "chatlieu_id")
    int chatlieu_id;

    @Size(min = 2, max = 20, message = "Không được để trống! Tối thiểu 2, tối đa 20 kí tự.")
    @Column(name = "chatlieu_name")
    String chatlieu_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatlieu")
    List<PhuKien> phukien;
}
