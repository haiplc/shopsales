package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shopsale.com.asmspringboot.Model.HangSanPham;

public interface DMHangDTReponsitory extends JpaRepository<HangSanPham, Integer> {
	@Query(value = "select * from hangdt where hang_name ~* ?1", nativeQuery = true)
	Page<HangSanPham> findByNameHangContaining(String search, Pageable pager);

	// giam dan
	@Query(value = "select * from hangdt ORDER BY hang_id DESC", nativeQuery = true)
	Page<HangSanPham> SortProductsByIdGiam(String hang_id, Pageable pager);

	@Query(value = "select * from hangdt ORDER BY hang_name DESC", nativeQuery = true)
	Page<HangSanPham> SortProductsByNameGiam(String hang_name, Pageable pager);

	// tang dan
	@Query(value = "select * from hangdt ORDER BY hang_id ASC", nativeQuery = true)
	Page<HangSanPham> SortProductsByIdTang(String hang_id, Pageable pager);

	@Query(value = "select * from hangdt ORDER BY hang_name ASC", nativeQuery = true)
	Page<HangSanPham> SortProductsByNameTang(String hang_name, Pageable pager);

}
