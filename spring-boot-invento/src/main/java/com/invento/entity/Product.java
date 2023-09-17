package com.invento.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// As we added 'lombok' no need to add getter, setter manually
// We can directly add 'lombok' provided annotation
@Data // Generates @Getter, @Setter,
		// @RequiredArgsConstructor
		// (constructor that takes in only final or @NonNull fields as arguments),
		// @ToString,
		// @EqualsAndHashCode
@AllArgsConstructor // Generates a constructor that takes in all fields in the class as arguments
@NoArgsConstructor // Generates a no-args constructor
@Entity // As this Product is our 'Model' name i.e. 'Entity' class
@Table(name = "PRODUCT_TBL") // If you will not specify the table name JPA is smart enough,
								// It can create table name with the class name
public class Product {

	@Id // Specify the primary key
		// If you want to change the column name, you can add @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) // To auto generate id //This is generator given by the hibernate
	private int id;
	private String name;
	private int quantity;
	private double price;
}
