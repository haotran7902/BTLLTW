package com.samsung.ltw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer bill_id;
	
	@Column(name = "user_id")
	private String user_id;
	
	@Column(name = "order_detail_id")
	private String order_detail_id;
	
	@Column(name = "total_cost")
	private String total_cost;
}
