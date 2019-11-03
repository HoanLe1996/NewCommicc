package com.example.comic.model;

import java.util.List;
import java.util.Objects;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "ten_theloai" }))
public class TheLoai {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String ten_theloai;

	/*
	 * @OneToMany(mappedBy = "theloai") private List<TruyenTheLoai> ttl;
	 */
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
		name = "truyen_theloai",
		joinColumns = @JoinColumn(name = "theloai_id"),
		inverseJoinColumns = @JoinColumn(name = "truyen_id"))
	private List<Truyen> truyens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTen_theloai() {
		return ten_theloai;
	}

	public void setTen_theloai(String ten_theloai) {
		this.ten_theloai = ten_theloai;
	}

	

	public List<Truyen> getTruyens() {
		return truyens;
	}

	public void setTruyens(List<Truyen> truyens) {
		this.truyens = truyens;
	}

	public TheLoai() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TheLoai(@NotBlank String ten_theloai, List<Truyen> truyens) {
		super();
		this.ten_theloai = ten_theloai;
		this.truyens = truyens;
	}

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.ten_theloai);
        return hash;
        
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheLoai other = (TheLoai) obj;
		if (!Objects.equals(this.ten_theloai, other.ten_theloai)) {
            return false;
        }
		return true;
	}
	
	


}
