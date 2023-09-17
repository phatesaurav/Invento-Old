package com.invento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invento.entity.Product;
import com.invento.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	// All the arguments passed in each method are coming from front-end
	
	// This 'ProductService' will talk to the 'ProductRepository'
	// So we need to inject this repository in the service
	// We will create object of it
	@Autowired // We can inject using @Autowired annotation
	private ProductRepository repository;

	// We will write all the http method i.e. get, post, put and delete

	// We will write post method where we can save 'Product' object to the database
	// This method will take argument as 'Product' object and it will save it
	public Product saveProduct(Product product) { // Give 'Product' object as argument
		return repository.save(product); // This is the in-built method given by JPA Repository
	}

	// We will write post method
	// To save list of 'Product' at the same time we will add one more method
	public List<Product> saveProducts(List<Product> products) { // Give list of 'Product' as arguments
		return repository.saveAll(products); // To save list of 'Product' use 'saveAll' method given by spring data JPA
	}

	// We will write get method to fetch 'Product' object from database
	// First we will fetch list of 'Product' object
	public List<Product> getProducts() {
		return repository.findAll();
	}

	// To fetch single 'Product' object based on some field write separate method
	// We need to pass id as argument
	// Change method return type to single 'Product' object
	// Because based on id we can get only one product object
	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}

	// To fetch single 'Product' object based on 'name'
	// Pass 'name' as argument
	// We need to write the method manually
	// That's what we learn in spring data JPA, how to customize our query by method
	public Product getProductByName(String name) {
		return repository.findByName(name); // 'findBy' is the prefix and for what which field we need to find is suffix
	}

	// We will write method for delete
	// We will give id as argument, based on id we want to delete it
	public String deleteProduct(int id) {
//		repository.deleteById(id);
//		return "product removed with id: " + id;
		Product existingProduct = repository.findById(id).orElse(null);
		if(existingProduct != null) {
			repository.deleteById(id);
			return "product removed with id: " + id;
		}
		return "Something Went Wrong";
	}

	// We will return 'Product' object after update
	// Give 'Product' object which you want to update as argument
	// 'id' cannot be modified, it will always be read-only field
	// So first get the existing product available in our database then modify it
	// For that write variable 'existingProduct'
	// Because there is no update method in spring data JPA
	// All you need to play with save method
	public Product updateProduct(Product product) {

		// So first based on the 'id' fetch the existing product present in database
		// Then the new 'Product' value which we will give as part of postman request
		// That all the field we will populated to this 'existingProduct' object to
		// update 'Product'
		Product existingProduct = repository.findById(product.getId()).orElse(null); // getId() getter given by lombok
		existingProduct.setName(product.getName());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		return repository.save(existingProduct); // Save this 'existingProduct' object after setting the value
	}
}
