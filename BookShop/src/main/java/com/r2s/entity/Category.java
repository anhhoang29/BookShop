package com.r2s.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer category_id;
	
	@Column(name = "category_name")
	private String category_name;
	
}
