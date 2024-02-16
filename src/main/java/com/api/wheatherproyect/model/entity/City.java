package com.api.wheatherproyect.model.entity;


import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "city", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_city", nullable = false)
	private Long idCity;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Double longitud;
	
	@Column(nullable = false)
	private Double latitud;
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Queries> queries;
}
 