package shopsale.com.asmspringboot.Model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProppertyModel {
    String propName;
    String name;
    List<Property> data;
}
