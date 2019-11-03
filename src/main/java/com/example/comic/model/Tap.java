package com.example.comic.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table( uniqueConstraints=@UniqueConstraint(columnNames={"ten_tap", "id_truyen"}))
public class Tap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "ten_tap")
	private String tenTap;

	@Lob
//	@NotBlank
	@Column(name = "noi_dung")
	private String noiDung;

	@Column(name = "ngay_capnhat", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date ngayCapnhat;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //tức là mặc định không lấy ra các đối tượng liên quan nhưng bên trong transaction,
	@JoinColumn(name = "id_truyen")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Truyen truyen;

	public Tap(@NotBlank String tenTap, @NotBlank String noiDung, Date ngayCapnhat) {
		super();
		this.tenTap = tenTap;
		this.noiDung = noiDung;
		this.ngayCapnhat = ngayCapnhat;
	}

	public Tap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenTap() {
		return tenTap;
	}

	public void setTenTap(String tenTap) {
		this.tenTap = tenTap;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getNgayCapnhat() {
		return ngayCapnhat;
	}

	public void setNgayCapnhat(Date ngayCapnhat) {
		this.ngayCapnhat = ngayCapnhat;
	}

	public void setTruyen(Truyen truyen) {
		this.truyen = truyen;
	}

}
