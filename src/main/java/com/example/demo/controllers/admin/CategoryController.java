package com.example.demo.controllers.admin;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired(required = false) 
	private CategoryService categoryService;
	@GetMapping("/category")
	public String index(Model model,@Param("keyWord") String keyWord,@RequestParam(name="pageNo",defaultValue="1") Integer pageNo) {
		//List<Category> list=this.categoryService.getAll();
		Page<Category> list=this.categoryService.getAll(pageNo);
		if(keyWord !=null) {
			list=this.categoryService.searchCategory(keyWord,pageNo);
			model.addAttribute("keyWord",keyWord);
		}
		model.addAttribute("totalPage",list.getTotalPages());
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("list",list);
		return "admin/category/index";
	}
	
	@GetMapping("/delete-category/{id}")
	public String remove(Model model,@PathVariable("id") Integer id) {
		if(this.categoryService.delete(id)) {
			return "redirect:/admin/category";
		}
		else {
			return "redirect:/admin/category";
		}
	}
	
	@GetMapping("/edit-category/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Category category=this.categoryService.findById(id);
		model.addAttribute("category",category);
		return "admin/category/edit";
	}
	@PostMapping("/edit-category")
	public String edit(@ModelAttribute("category") Category category) {
		if(this.categoryService.create(category)) {
			return "redirect:/admin/category";
		}
		else {
			return "redirect:/admin/edit-category";
		}
	}
	
	@GetMapping("/add-category")
	public String add(Model model) {
		Category category =new Category();
		category.setCategoryStatus(true);
		model.addAttribute("category",category);
		return "admin/category/add";
	}
	
	@PostMapping("/add-category")
	public String save(@ModelAttribute("category") Category category) {
		if(this.categoryService.create(category)) {
			return "redirect:/admin/category";
		}
		else {
			return "redirect:/admin/add-category";
		}
	}
	
}
