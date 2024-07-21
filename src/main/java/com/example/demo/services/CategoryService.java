package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;

import java.lang.Boolean;

public interface CategoryService {
	List<Category> getAll();
	Boolean create(Category category);
	Boolean update(Category category);
	Category findById(Integer id);
	Boolean delete(Integer id);
	List<Category> searchCategory(String keyWord);
	Page<Category> getAll(Integer pageNo);
	Page<Category> searchCategory(String keyWord,Integer pageNo);
}
