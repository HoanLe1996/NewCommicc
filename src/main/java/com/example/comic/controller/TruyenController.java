package com.example.comic.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.comic.model.Tap;
import com.example.comic.model.TheLoai;
import com.example.comic.model.Truyen;
import com.example.comic.reponsitory.TapReponsitory;
import com.example.comic.reponsitory.TheLoaiReponsitory;
import com.example.comic.reponsitory.TruyenReponsitory;
import com.example.comic.selector.TruyenSelector;
import com.example.comic.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class TruyenController {

	@Autowired
	private TheLoaiReponsitory theloaire;
	@Autowired
	private TruyenReponsitory truyenre;
	@Autowired
	private TapReponsitory tapre;
	@Autowired
	private TruyenSelector truyense;

	private static final String folders = "D:/image";

	@RequestMapping("/truyens")
	public List<Truyen> getAll() {
		return truyenre.findAll();
	}

	@PostMapping("/truyens")
	public Truyen InsertTruyen(@Valid @RequestBody Truyen truyen) {
		return truyenre.save(truyen);
	}

	@RequestMapping(value = "/truyens/{id}", method = RequestMethod.GET, produces = "application/json")
	public String getTruyenById(@PathVariable("id") Long id) {
		try {
			Optional<Truyen> truyen = truyenre.findById(id);
			if (truyen.isPresent()) {
				return ResponseUtil.success(ResponseUtil.returnTruyen(truyen.get()));
			} else {
				return ResponseUtil.NotFound();
			}
		} catch (Exception e) {
			return ResponseUtil.SeverError();
		}
	}

	@GetMapping("/truyensurl")
	public List<Truyen> getTruyenURL() throws IOException {
		List<Truyen> l_t = new ArrayList<>();
		Set<TheLoai> l_theloai = new HashSet<>();
		List<Tap> list_tap = new ArrayList<>();

		for (int i = 1; i < 2; i++) {
			org.jsoup.nodes.Document doc = Jsoup.connect("https://truyenfull.vn/danh-sach/truyen-moi/trang-" + i).get();
			System.out.println("https://truyenfull.vn/danh-sach/truyen-moi/trang-" + i);
			System.out.println("Title:" + doc.title());
			Elements comics = doc.select(truyense.truyen());
			// Elements comics = doc.select("div > ul.pagination.pagination-sm > li > a");
			for (Element comic : comics) {
				System.out.println("comics name :" + comic.text());
				System.out.println("link" + comic.attr("href"));
				org.jsoup.nodes.Document doc2 = Jsoup.connect(comic.attr("href")).get();
				System.out.println("link conenect :" + comic.attr("href"));
				Elements tacgia = doc2.select(truyense.tacgia());
				Elements trangthai = doc2.select(truyense.danhgia());
				Elements theloai = doc2.select(truyense.theloai());
				for (Element t_l : theloai) {

					TheLoai tl = new TheLoai();
					tl.setTen_theloai(t_l.attr("title"));
					l_theloai.add(tl);
				}
				Elements nguon = doc2.select(truyense.nguon());
				Elements mota = doc2.select(truyense.mota());
				Elements danhgia = doc2.select(truyense.danhgia());
				Elements luotxem = doc2.select(truyense.luotxem());
				// get and download images
				Elements image = doc2.select(truyense.image());
				String src = image.attr("src");
				String src1 = src.substring(src.lastIndexOf("/") + 1);
				System.out.println("---------:" + src);
				// lấy dữ liệu image.
				// downloadImage(src);
				// Lấy tập truyện
				for (int j = 1; j < 2; j++) {
					org.jsoup.nodes.Document doc_chuong = Jsoup
							.connect(comic.attr("href") + "trang-" + i + "/#list-chapter").get();
					Elements chuongtruyen = doc_chuong.select(truyense.chuongtruyen());
					for (Element chuong : chuongtruyen) {
						System.out.println("Lay title:" + chuong.attr("title"));
						System.out.println("Lay href :" + chuong.attr("href"));
						// Lấy nội dung truyện
						Document doc_noidung = Jsoup.connect(chuong.attr("href")).get();
						Elements noidung_tap = doc_noidung.select(truyense.noidungchuongtruyen());

						Tap tap = new Tap();
						tap.setTenTap(chuong.attr("title"));
						tap.setNoiDung(noidung_tap.text());
						list_tap.add(tap);
					}
				}

				Truyen comicc = new Truyen();
				comicc.setTenTruyen(comic.text());
				comicc.setTacGia(tacgia.text());
				comicc.setTrangThai(trangthai.text());
				comicc.setMoTa(mota.text());
				comicc.setNguon(nguon.text());
				comicc.setDanhGia(Double.parseDouble(danhgia.text()));
				comicc.setLuotXem(Integer.parseInt(luotxem.text()));
				comicc.setHinhAnh(src1);
				comicc.setChuongTruyen(list_tap);
				comicc.setTheloais(l_theloai);

				l_t.add(comicc);
			}
		}
		return l_t;
	}

	@PostMapping("/add-truyen-url")
	public String addTruyenU() throws IOException {
		// List<TheLoai> allTheLoai = theloaire.findAll();
		Set<TheLoai> l_theloai = new HashSet<>();
		List<Truyen> l_t = new ArrayList<>();
		List<Tap> list_tap = new ArrayList<>();

		for (int i = 1; i < 2; i++) {
			org.jsoup.nodes.Document doc = Jsoup.connect("https://truyenfull.vn/danh-sach/truyen-moi/trang-" + i).get();
			System.out.println("https://truyenfull.vn/danh-sach/truyen-moi/trang-" + i);
			System.out.println("Title:" + doc.title());
			Elements comics = doc
					.select("div.list.list-truyen.col-xs-12 > div.row > div.col-xs-7 > div > h3.truyen-title > a");
			// Elements comics = doc.select("div > ul.pagination.pagination-sm > li > a");
			for (Element comic : comics) {

				System.out.println("comics name :" + comic.text());
				System.out.println("link" + comic.attr("href"));
				org.jsoup.nodes.Document doc2 = Jsoup.connect(comic.attr("href")).get();
				System.out.println("link conenect :" + comic.attr("href"));
				Elements tacgia = doc2.select("div.info > div:first-child > a");
				Elements trangthai = doc2.select("div.info > div:last-child > span");
				Elements theloai = doc2.select("div.info > div:nth-child(2) > a");
				for (Element t_l : theloai) {
					TheLoai tl = new TheLoai();
					tl.setTen_theloai(t_l.attr("title"));
					System.out.println(t_l.attr("title"));
					l_theloai.add(tl);
				}

				Elements nguon = doc2.select("div.info > div:nth-child(3) > span");
				Elements mota = doc2.select("div.col-xs-12.col-sm-8.col-md-8.desc > div.desc-text");
				Elements danhgia = doc2.select("div.small > em > strong:first-child > span");
				Elements luotxem = doc2.select("div.small > em > strong:last-child > span");
				// get and download images
				Elements image = doc2.select("div.books > div.book > img");
				String src = image.attr("src");
				String src1 = src.substring(src.lastIndexOf("/") + 1);
				System.out.println("---------:" + src);
				// lấy dữ liệu image.
				// downloadImage(src);
				// Lấy tập truyện
				for (int j = 1; j < 2; j++) {
					org.jsoup.nodes.Document doc_chuong = Jsoup
							.connect(comic.attr("href") + "trang-" + i + "/#list-chapter").get();
					Elements chuongtruyen = doc_chuong
							.select("div.row > div.col-xs-12.col-sm-6.col-md-6 > ul.list-chapter > li > a");
					for (Element chuong : chuongtruyen) {
						System.out.println("Lay title:" + chuong.attr("title"));
						System.out.println("Lay href :" + chuong.attr("href"));
						// Lấy nội dung truyện
						Document doc_noidung = Jsoup.connect(chuong.attr("href")).get();
						Elements noidung_tap = doc_noidung.select("div.chapter-c");

						Tap tap = new Tap();

						tap.setTenTap(chuong.attr("title"));
						tap.setNoiDung(noidung_tap.text() + " ");
						list_tap.add(tap);
					}
				}

				Truyen comicc = new Truyen();
				comicc.setTenTruyen(comic.text());
				comicc.setTacGia(tacgia.text());
				comicc.setTrangThai(trangthai.text());
				comicc.setMoTa(mota.text());
				comicc.setNguon(nguon.text());
				if (danhgia.text().equals("")) {
					comicc.setDanhGia(0.0);
				} else {
					comicc.setDanhGia(Double.parseDouble(danhgia.text()));
				}
				if (luotxem.text().equals("")) {
					comicc.setLuotXem(0);
				} else {
					comicc.setLuotXem(Integer.parseInt(luotxem.text()));
				}

				comicc.setHinhAnh(src1);

				comicc.setChuongTruyen(list_tap);
				comicc.setTheloais(l_theloai);

				l_t.add(comicc);

			}
		}
		truyenre.saveAll(l_t);

		return "Thanh cong";
	}

	private static void downloadImage(String strImageURL) {

		// get file name from image path
		String strImageName = strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

		System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

		try {

			// open the stream from URL
			URL url = new URL(strImageURL); // Tạo đường dẫn url
			URLConnection urlConn = url.openConnection(); // mỡ 1 kết nối

			urlConn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
			System.setProperty("http.agent", "Chrome"); // Server returned HTTP response code: 403 for
			InputStream in = urlConn.getInputStream(); // trả về 1 luồng đầu vào đọc từ kết nối mỡ này

			byte[] buffer = new byte[4096];
			int n;

			OutputStream os = new FileOutputStream("D:/image" + "/" + strImageName);

			// write bytes to the output stream
			while ((n = in.read(buffer)) != -1) {
				os.write(buffer, 0, n);
			}

			// close the stream
			os.close();

			System.out.println("Image saved");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
