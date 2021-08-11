package shopsale.com.asmspringboot.Reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shopsale.com.asmspringboot.Model.PhuKien;

public interface PhuKienReponsitory extends JpaRepository<PhuKien, Integer> {
	@Query(value = "select * from phukien where phukien_name ~* ?1", nativeQuery = true)
	Page<PhuKien> findByNameContaining(String phukien_name, Pageable pager);

	//// admin
	// giam dan
	@Query(value = "select * from phukien ORDER BY phukien_id DESC", nativeQuery = true)
	Page<PhuKien> SortProductsByIdGiam(String phukien_id, Pageable pager);

	@Query(value = "select * from phukien ORDER BY phukien_name DESC", nativeQuery = true)
	Page<PhuKien> SortProductsByNameGiam(String phukien_name, Pageable pager);

	@Query(value = "SELECT * from phukien ORDER BY created_date DESC", nativeQuery = true)
	Page<PhuKien> SortProductsByDateGiam(String created_date, Pageable pager);

	@Query(value = "select * from phukien ORDER BY phukien_soluong DESC", nativeQuery = true)
	Page<PhuKien> SortProductsBySoLuongGiam(String phukien_soluong, Pageable pager);

	@Query(value = "select * from phukien ORDER BY phukien_giaban DESC", nativeQuery = true)
	Page<PhuKien> SortProductsByGiaBanGiam(String phukien_giaban, Pageable pager);

	// tang dan
	@Query(value = "select * from phukien ORDER BY phukien_soluong ASC", nativeQuery = true)
	Page<PhuKien> SortProductsBySoLuongTang(String phukien_soluong, Pageable pager);

	@Query(value = "select * from phukien ORDER BY phukien_id ASC", nativeQuery = true)
	Page<PhuKien> SortProductsByIdTang(String phukien_id, Pageable pager);

	@Query(value = "select * from phukien ORDER BY phukien_name ASC", nativeQuery = true)
	Page<PhuKien> SortProductsByNameTang(String phukien_name, Pageable pager);

	@Query(value = "SELECT * from phukien ORDER BY created_date ASC", nativeQuery = true)
	Page<PhuKien> SortProductsByDateTang(String created_date, Pageable pager);

	@Query(value = "select * from phukien ORDER BY phukien_giaban ASC", nativeQuery = true)
	Page<PhuKien> SortProductsByGiaBanTang(String phukien_giaban, Pageable pager);

	/// client
	// search home : hang, orders, key
	@Query(value = "select * from phukien where phukien_name ~* :key and (CASE WHEN :loaip > 0 THEN loaip_id = :loaip ELSE true END) and (CASE WHEN :thuonghieu > 0 THEN thuonghieu_id = :thuonghieu ELSE true END)", nativeQuery = true)
	Page<PhuKien> SearchProductsOrder(@Param(value = "key") String key, @Param(value = "loaip") int loaip,
			@Param(value = "thuonghieu") int thuonghieu, Pageable pager);

}
