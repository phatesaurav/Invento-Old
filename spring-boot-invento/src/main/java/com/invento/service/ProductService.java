package com.invento.service;

import java.util.List;

import com.invento.entity.Product;

public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public List<Product> saveProducts(List<Product> products);
	
	public List<Product> getProducts();
	
	public Product getProductById(int id);
	
	public Product getProductByName(String name);
	
	public String deleteProduct(int id);

}
