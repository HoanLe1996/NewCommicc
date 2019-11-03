package com.example.comic.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "ngaytao", "ngaycapnhat" }, allowGetters = true)
public class Truyen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "ten_truyen", unique = true)
	private String tenTruyen;
	
	//@NotBlank
	private String tacGia;

	private String nguon;
	
	@Lob
	private String moTa;
	
	@Column(name = "trang_thai")
	private String trangThai;
	/*
	 * @NotNull
	 * 
	 * @Min(value = 0)
	 * 
	 * @Max(value = 10)
	 */
	@Column(name = "danh_gia")
	@NotNull
	private double danhGia;
	
	/*
	 * @NotNull
	 * 
	 * @Min(value= 0)
	 */
	 @Column(name = "luot_xem")
	private int luotXem;
	
	//@NotBlank
	@Column(name = "hinh_anh")
	private String hinhAnh;
	
    @Column(nullable = false, updatable = false,name = "ngay_tao")
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Date ngayTao;
	
	@Column( name = "ngay_capnhat")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date ngayCapnhat;

	@OneToMany(mappedBy = "truyen",cascade = CascadeType.ALL)
	private List<Tap> chuongTruyen;

	/*
	 * @OneToMany(mappedBy = "truyen") private List<TruyenTheLoai> ttl;
	 */
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER) // Lấy các đối tượng liên quan trong database
    @JoinTable(
            name = "truyen_theloai",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "theloai_id"))
    private Set<TheLoai> theloais = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public String getNguon() {
		return nguon;
	}
	
	
	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public void setNguon(String nguon) {
		this.nguon = nguon;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	
	public double getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(double danhGia) {
		this.danhGia = danhGia;
	}

	public int getLuotXem() {
		return luotXem;
	}

	public void setLuotXem(int luotXem) {
		this.luotXem = luotXem;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgayCapnhat() {
		return ngayCapnhat;
	}

	public void setNgayCapnhat(Date ngayCapnhat) {
		this.ngayCapnhat = ngayCapnhat;
	}

	
	

	public String getTenTruyen() {
		return tenTruyen;
	}

	public void setTenTruyen(String tenTruyen) {
		this.tenTruyen = tenTruyen;
	}

	
	public Truyen(@NotBlank String tenTruyen, String tacGia, String nguon, String moTa, String trangThai,
			double danhGia, int luotXem, String hinhAnh, Date ngayTao, Date ngayCapnhat, List<Tap> chuongTruyen,
			Set<TheLoai> theloais) {
		super();
		this.tenTruyen = tenTruyen;
		this.tacGia = tacGia;
		this.nguon = nguon;
		this.moTa = moTa;
		this.trangThai = trangThai;
		this.danhGia = danhGia;
		this.luotXem = luotXem;
		this.hinhAnh = hinhAnh;
		this.ngayTao = ngayTao;
		this.ngayCapnhat = ngayCapnhat;
		this.chuongTruyen = chuongTruyen;
		this.theloais = theloais;
	}

	public Set<TheLoai> getTheloais() {
		return theloais;
	}

	public void setTheloais(Set<TheLoai> theloais) {
//		if(this.theloais == null) {
//			this.theloais = theloais;
//		}
//		else if(this.theloais != theloais) {
//			this.theloais.clear();
//			if(theloais !=null)
//				this.theloais.addAll(theloais);
//		}
		this.theloais=theloais;
	}

	public List<Tap> getChuongTruyen() {
		return chuongTruyen;
	}

	public void setChuongTruyen(List<Tap> chuongTruyen) {
		this.chuongTruyen = chuongTruyen;
	}

	public Truyen() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
