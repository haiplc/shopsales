package shopsale.com.asmspringboot.Model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class GioHang {
	private Map<SanPhamDT, Integer> chiTietGioHang = new HashMap<SanPhamDT, Integer>();

	public Map<SanPhamDT, Integer> getChiTietGioHang() {
		return chiTietGioHang;
	}

	public void setChiTietGioHang(Map<SanPhamDT, Integer> chiTietGioHang) {
		this.chiTietGioHang = chiTietGioHang;
	}
}
