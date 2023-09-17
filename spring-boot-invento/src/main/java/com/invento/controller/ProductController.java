package com.invento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invento.entity.Product;
import com.invento.service.ProductServiceImpl;

// This controller will talk to the service class
// For that we need to inject service in controller class
// @RestController is the combination of @Controller and @ResponseBody
// @ResponseBody used for serialization (Java object to JSON object)
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Cross-Origin Resource Sharing (CORS) issue
public class ProductController {

	@Autowired // Inject 'ProductService' in 'ProductController' using @Autowirted annotation
	private ProductServiceImpl service;

	// We will return the 'Product' object
	// We will pass method argument as 'Product' object
	// This is the post method and method argument is our request body
	// Thus we need to annotate it as @RequestBody
	// So that input JSON can be passed to 'Product' object
	// This is post method so add annotation @PostMapping and give URL
	// @RequestBody used for deserialization (JSON object to Java object)
	// Convert data coming in JSON format into Java object
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product); // We can directly call 'service'
	}

	// ResponseEntity is the return type
	@PostMapping("/addProduct2")
	public ResponseEntity<?> addProduct2(@RequestBody Product product) {
		return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED); // Create a ResponseEntity with a
																						// body and status code.
	}

	// Similarly We have one more method to save list of 'Product' object
	// This is post method so add annotation @PostMapping and give URL
	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return service.saveProducts(products); // We can directly call 'service'
	}

	// Similarly we will write for get API
	// This is get API for that we need to annotate @GetMapping
	@GetMapping("/getAllProducts")
	public List<Product> findAllProducts() {
		return service.getProducts(); // We can directly return from 'service'
	}

	@GetMapping("/getAllProducts2")
	public ResponseEntity<?> findAllProducts2() {
		return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
	}

	// This is get API for that we need to annotate @GetMapping
	// We need to pass method argument as part of request URL
	// For that use @PathVariable, we can also use @RequestParam annotation
	// @PathVariables extract values from the URL path
	// @RequestParams extract values from the query string
	// If you use @PathVariable, then you must add '{id}' as part of request URL
	// Else you will get 404 error
	@GetMapping("/getProductById/{id}")
	public Product findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}

	@GetMapping("/getProductById2/{id}")
	public ResponseEntity<?> findProductById2(@PathVariable int id) {
		return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
	}

	// @GetMapping("/getProductById")
	// public Product findProductById(@RequestParam(name = "id") int productId) {
	// return service.getProductById(productId);
	// }
	// http://localhost:8080/getProductById?id=123

	// This is get API for that we need to annotate @GetMapping
	// Any input you want to pass as request URL then use @PathVariable annotation
	@GetMapping("/getProductByName/{name}")
	public Product findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}

	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product); // We can directly call 'service'
	}

	@PutMapping("/updateProduct2")
	public ResponseEntity<?> updateProduct2(@RequestBody Product product) {
		return new ResponseEntity<>(service.updateProduct(product), HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

	@DeleteMapping("/deleteProduct2/{id}")
	public ResponseEntity<?> deleteProduct2(@PathVariable int id) {
		return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.OK);
	}
}
