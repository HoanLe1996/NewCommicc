package com.example.comic.reponsitory;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.comic.model.TheLoai;
import com.example.comic.model.Truyen;

@Repository
public interface TheLoaiReponsitory extends JpaRepository<TheLoai, Long>{
	@Query(value = "select tl from TheLoai tl  where tl.ten_theloai like :name%" ,nativeQuery = false)
	List<TheLoai> getTheLoaiTheoTen(@Param("name") String name) ;

	@Query(value = "Select * from The_Loai tl join Truyen_TheLoai ttl on tl.id =ttl.theloai_id join Truyen t on ttl.truyen_id = t.id where t.ten_truyen = :namet ",nativeQuery = true)
	List<TheLoai> getTLbyTenTruyen(String namet);
	
}
