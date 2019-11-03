package com.example.comic.selector;

import org.springframework.stereotype.Service;

import com.example.comic.service.TruyenService;
@Service
public class TruyenSelector implements TruyenService {


	@Override
	public String tacgia() {
		// TODO Auto-generated method stub
		return "div.info > div:first-child > a";
	}

	@Override
	public String trangthai() {
		// TODO Auto-generated method stub
		return "div.info > div:last-child > span";
	}

	@Override
	public String theloai() {
		// TODO Auto-generated method stub
		return "div.info > div:nth-child(2) > a";
	}

	@Override
	public String nguon() {
		// TODO Auto-generated method stub
		return "div.info > div:nth-child(3) > span";
	}

	@Override
	public String mota() {
		// TODO Auto-generated method stub
		return "div.col-xs-12.col-sm-8.col-md-8.desc > div.desc-text";
	}

	@Override
	public String danhgia() {
		// TODO Auto-generated method stub
		return "div.small > em > strong:first-child > span";
	}

	@Override
	public String luotxem() {
		// TODO Auto-generated method stub
		return "div.small > em > strong:last-child > span";
	}

	@Override
	public String image() {
		// TODO Auto-generated method stub
		return "div.books > div.book > img";
	}

	@Override
	public String chuongtruyen() {
		// TODO Auto-generated method stub
		return "div.row > div.col-xs-12.col-sm-6.col-md-6 > ul.list-chapter > li > a";
	}

	@Override
	public String noidungchuongtruyen() {
		// TODO Auto-generated method stub
		return "div.chapter-c";
	}

	@Override
	public String truyen() {
		// TODO Auto-generated method stub
		return "div.list.list-truyen.col-xs-12 > div.row > div.col-xs-7 > div > h3.truyen-title > a";
	}

}
