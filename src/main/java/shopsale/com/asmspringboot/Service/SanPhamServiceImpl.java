package shopsale.com.asmspringboot.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopsale.com.asmspringboot.Model.GioHang;
import shopsale.com.asmspringboot.Model.SanPhamDT;
import shopsale.com.asmspringboot.Reponsitory.SanPhamDTReponsitory;

@Service
public class SanPhamServiceImpl implements GioHangService {
	@Autowired
	private GioHang giohang;

	public GioHang getGioHang() {
		return giohang;
	}

	@Autowired
	SanPhamDTReponsitory sanphamReponsitory;

	@Override
	public void themSanPham(int sanpham_id) {
		SanPhamDT sanpham = sanphamReponsitory.findById(sanpham_id).get();

		if (giohang.getChiTietGioHang().containsKey(sanpham)) {
			int count = giohang.getChiTietGioHang().get(sanpham);
			giohang.getChiTietGioHang().put(sanpham, count + 1);

		} else {
			giohang.getChiTietGioHang().put(sanpham, 1);
		}

	}

	@Override
	public void truSanPham(int sanpham_id) {
		SanPhamDT sanpham = sanphamReponsitory.findById(sanpham_id).get();
		if (giohang.getChiTietGioHang().containsKey(sanpham)) {
			int count = giohang.getChiTietGioHang().get(sanpham);
			giohang.getChiTietGioHang().replace(sanpham, count - 1);
		}
	}

	@Override
	public void xoaSanPham(int sanpham_id) {
		SanPhamDT sanpham = sanphamReponsitory.findById(sanpham_id).get();
		if (giohang.getChiTietGioHang().containsKey(sanpham)) {

			giohang.getChiTietGioHang().remove(sanpham);
		}
	}

	@Override
	public double getTotal() {
		Map<SanPhamDT, Integer> listItems = giohang.getChiTietGioHang();
		double totalOrder = 0;
		for (SanPhamDT dt : listItems.keySet()) {
			totalOrder += ((listItems.get(dt)) * (dt.getSanpham_giaban()));
		}

		return totalOrder;
	}
}
