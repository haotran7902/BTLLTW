package com.samsung.ltw.entity.expand;

import com.samsung.ltw.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookExpand {
	
	private Book book;
	private String avgStar;
}
