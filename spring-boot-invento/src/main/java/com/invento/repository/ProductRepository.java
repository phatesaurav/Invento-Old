package com.invento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invento.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	// Specify the 'Model' name as first argument, 'Model' name is our 'Product'
	// Then the second argument specify data type of primary key which is 'Integer'
	// 'JpaRepository' will have all the in-built method related to crud example

	Product findByName(String name);

}

// So, we created our 'Entity' and corresponding 'Repository'
// Now, we need to write some logic to connect our database
// If you are using spring boot application, you no need to write any additional logic for database connection
// All the database related properties you can add in application.properties file
