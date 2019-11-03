package com.example.comic.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.comic.model.Tap;
import com.example.comic.model.Truyen;
import com.example.comic.reponsitory.TapReponsitory;
import com.example.comic.reponsitory.TruyenReponsitory;

@RestController
@RequestMapping("/api")
public class TapController {
	
	@Autowired
	private TapReponsitory tapre;
	
	@Autowired
	private TruyenReponsitory truyenr;
	
	@GetMapping("/taps")
	public List<Tap> getAllTap(){
		return tapre.findAll();
	}
	@PostMapping("/truyens/{id}/taps")
	public Tap addTap(@PathVariable("id") long id , @Valid @RequestBody Tap tap) {
		Optional<Truyen> truyen = truyenr.findById(id);
		if(!truyen.isPresent()) {
			return null;
		}
		tap.setTruyen(truyen.get());
		return tapre.save(tap);
	}
	
	
}
