package com.example.comic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.comic.model.Tap;

@Repository
public interface TapReponsitory extends JpaRepository<Tap, Long>{

}
