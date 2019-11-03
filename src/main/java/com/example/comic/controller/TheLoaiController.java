package com.example.comic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.comic.model.TheLoai;
import com.example.comic.reponsitory.TheLoaiReponsitory;
import com.example.comic.util.ResponseUtil;
import com.sun.xml.txw2.Document;

@RestController
@RequestMapping("/api")
public class TheLoaiController {

	@Autowired
	private TheLoaiReponsitory tlreponsitory;

	@GetMapping("/theloais")
	public List<TheLoai> getAllTL() {
		return tlreponsitory.findAll();
	}

	@PostMapping("/theloais")
	public TheLoai addTL(@Valid @RequestBody TheLoai theloai) {
		return tlreponsitory.save(theloai);
	}

	@GetMapping(value = "/theloais/ten", produces = "application/json")
	public String getTheLoaitheoten(@RequestParam("name") String name) {
		try {
			List<TheLoai> tl = tlreponsitory.getTheLoaiTheoTen(name);
			if (tl.isEmpty()) {
				return ResponseUtil.NotFound();
			} else {
				return ResponseUtil.success(ResponseUtil.returnListTL(tl));
			}
		} catch (Exception e) {
			return ResponseUtil.SeverError();
		}

	}
	
	@GetMapping(value = "/theloais/tentruyen", produces = "application/json" )
	public String getTheLoaitheotentruyen(@RequestParam("namet") String namet) {
		try {
			List<TheLoai> tl = tlreponsitory.getTLbyTenTruyen(namet);
			if (tl.isEmpty()) {
				return ResponseUtil.NotFound();
			} else {
				return ResponseUtil.success(ResponseUtil.returnListTL(tl));
			}
		} catch (Exception e) {
			return ResponseUtil.SeverError();
		}

	}

	@GetMapping("/categories")
	public List<String> getTheLoaiURL() throws IOException {
		org.jsoup.nodes.Document doc = Jsoup.connect("https://truyenfull.vn/").get();
		System.out.println("Title:" + doc.title());
		List<String> l_e = new ArrayList<>();
		Elements categories = doc.select("div.row > div.col-md-4 > ul > li > a");
		for (Element category : categories) {
			System.out.println("Categori name :" + category.text());
			System.out.println("link" + category.attr("href"));
			l_e.add(category.text());
		}
		return l_e;
	}
	
	@PostMapping("/categories")
	public void ThemTL() throws IOException {
		org.jsoup.nodes.Document doc = Jsoup.connect("https://truyenfull.vn/").get();
		System.out.println("Title:" + doc.title());
		List<TheLoai> the_loai = new ArrayList<>();
		Elements categories = doc.select("div.row > div.col-md-4 > ul > li > a");
		for (Element category : categories) {
			System.out.println("Categori name :" + category.text());
			System.out.println("link" + category.attr("href"));;
			TheLoai db = new TheLoai();
			db.setTen_theloai(category.text());
			the_loai.add(db);
		}
		tlreponsitory.saveAll(the_loai);

	}

}
