package shopsale.com.asmspringboot.Service;

import shopsale.com.asmspringboot.Model.GioHang;

public interface GioHangService {

	public GioHang getGioHang();

	public double getTotal();

	public void themSanPham(int sanpham_id);

	public void truSanPham(int sanpham_id);

	public void xoaSanPham(int sanpham_id);
}
