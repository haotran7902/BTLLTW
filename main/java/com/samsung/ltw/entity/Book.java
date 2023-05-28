package com.samsung.ltw.entity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.samsung.ltw.service.CommentService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer book_id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "image")
	private String image;

	@Column(name = "description")
	private String description;

	@Column(name = "publish")
	private String publish;

	@Column(name = "price")
	private Integer price;

	@Column(name = "page")
	private Integer page;

	@Column(name = "sold")
	private Integer sold;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

}
