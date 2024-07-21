package com.example.demo.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.StorageService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired 
	private ProductService productService;
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/upload-file")
	public String uploadFile(){
		return "admin/products/uploadFile";
	}
	@PostMapping("/upload-file")
	public String uploadFile1(@RequestParam("file") MultipartFile file){
		this.storageService.store(file);
		return "redirect:/admin/upload-file";
	}
	
	@GetMapping("/product")
	public String index(Model model) {
		List<Product> list=this.productService.getAll();
		model.addAttribute("list",list);
		return "admin/products/index";
	}
	@GetMapping("/add-product")
	public String add(Model model) {
		Product product=new Product();
		
		
		model.addAttribute("product",product);
		
		List<Category> listCate=this.categoryService.getAll();
		model.addAttribute("listCate",listCate);
		return "admin/products/add";
	}

	@PostMapping("/add-product")
	public String save(@ModelAttribute("product") Product product,@RequestParam("file1") MultipartFile file) {
		
		this.storageService.store(file);
		String fileName=file.getOriginalFilename();
		product.setImage(fileName);
		if(this.productService.create(product)) {
			return "redirect:/admin/product";
		}
		else {
			return "redirect:/admin/product";
		}
	}

	@GetMapping("delete-product/{id}")
	public String remove(Model model,@PathVariable("id") Integer id) {
		if(this.productService.delete(id)) {
			return "redirect:/admin/product";
		}
		else {
			return "redirect:/admin/product";
		}
	}
	
	@GetMapping("/edit-product/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Product product =this.productService.findById(id);
		model.addAttribute("product",product);
		List<Category> listCate=this.categoryService.getAll();
		model.addAttribute("listCate",listCate);
		return "admin/products/edit";
	}
	
	@PostMapping("/edit-product")
	public String edit(@ModelAttribute("product") Product product,@RequestParam("file1") MultipartFile file) {
		if(file!=null) {
			this.storageService.store(file);
			String fileName=file.getOriginalFilename();
			product.setImage(fileName);
		}
		
		if(this.productService.create(product)) {
			return "redirect:/admin/product";
		}
		else {
			return "redirect:/admin/products/edit-product";
		}
	}
	
	
}
