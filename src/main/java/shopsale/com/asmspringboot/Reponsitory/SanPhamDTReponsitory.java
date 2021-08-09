package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shopsale.com.asmspringboot.Model.SanPhamDT;

public interface SanPhamDTReponsitory extends JpaRepository<SanPhamDT, Integer> {
	@Query(value = "select * from sanphamdt where sanpham_name ~* ?1", nativeQuery = true)
	Page<SanPhamDT> findByNameContaining(String sanpham_name, Pageable pager);

	//// admin
	// giam dan
	@Query(value = "select * from sanphamdt ORDER BY sanpham_id DESC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByIdGiam(String sanpham_id, Pageable pager);

	@Query(value = "select * from sanphamdt ORDER BY sanpham_name DESC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByNameGiam(String sanpham_name, Pageable pager);

	@Query(value = "SELECT * from sanphamdt ORDER BY created_date DESC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByDateGiam(String created_date, Pageable pager);

	@Query(value = "select * from sanphamdt ORDER BY sanpham_soluong DESC", nativeQuery = true)
	Page<SanPhamDT> SortProductsBySoLuongGiam(String sanpham_soluong, Pageable pager);

	@Query(value = "select * from sanphamdt ORDER BY sanpham_giaban DESC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByGiaBanGiam(String sanpham_giaban, Pageable pager);

	// tang dan
	@Query(value = "select * from sanphamdt ORDER BY sanpham_soluong ASC", nativeQuery = true)
	Page<SanPhamDT> SortProductsBySoLuongTang(String sanpham_soluong, Pageable pager);

	@Query(value = "select * from sanphamdt ORDER BY sanpham_id ASC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByIdTang(String sanpham_id, Pageable pager);

	@Query(value = "select * from sanphamdt ORDER BY sanpham_name ASC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByNameTang(String sanpham_name, Pageable pager);

	@Query(value = "SELECT * from sanphamdt ORDER BY created_date ASC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByDateTang(String created_date, Pageable pager);

	@Query(value = "select * from sanphamdt ORDER BY sanpham_giaban ASC", nativeQuery = true)
	Page<SanPhamDT> SortProductsByGiaBanTang(String sanpham_giaban, Pageable pager);

	/// client
	// search home : hang, orders, key
	@Query(value = "select * from sanphamdt where sanpham_name ~* :key  and sanpham_giaban >= :min and sanpham_giaban < :max and (CASE WHEN :hang > 0 THEN hang_id = :hang ELSE true END)", nativeQuery = true)
	Page<SanPhamDT> SearchProductsOrder(@Param(value = "key") String key, @Param(value = "hang") int hang,
			@Param(value = "min") int min, @Param(value = "max") int max, Pageable pager);

}
