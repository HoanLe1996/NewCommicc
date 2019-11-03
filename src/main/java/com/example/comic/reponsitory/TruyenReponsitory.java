package com.example.comic.reponsitory;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.comic.model.TheLoai;
import com.example.comic.model.Truyen;

@Repository
public interface TruyenReponsitory extends JpaRepository<Truyen, Long> {
//	
//	@Query(value = "Select * from Truyen t join Truyen_The_Loai ttl on t.id =ttl.id_truyen join The_Loai tl on ttl.id_theloai = tl.id where tl.ten_theloai = :name ",nativeQuery = true)
//	List<Truyen> getDSTByTheLoai(@Param("name") String name) ;
//	
	
	

	
}
