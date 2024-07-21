package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.repository.*;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return this.categoryRepository.findAll();
	}

	@Override
	public Boolean create(Category category) {
		// TODO Auto-generated method stub
		try {
			  this.categoryRepository.save(category);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public Boolean update(Category category) {
		// TODO Auto-generated method stub
		try {
			  this.categoryRepository.save(category);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Category findById(Integer id) {
		// TODO Auto-generated method stub
		return  this.categoryRepository.findById(id).get();
	}
	
	@Override
	public Page<Category> searchCategory(String keyWord, Integer pageNo) {
		List list=this.searchCategory(keyWord);
		Pageable pageable=PageRequest.of(pageNo-1, 2);
		Integer start=(int)pageable.getOffset();
		Integer end=(int)(pageable.getOffset()+pageable.getPageSize()>list.size()?list.size():pageable.getOffset()+pageable.getPageSize());
		list=list.subList(start, end);
		return new PageImpl<Category> (list,pageable,this.searchCategory(keyWord).size());
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			  this.categoryRepository.deleteById(id);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Category> searchCategory(String keyWord) {
		// TODO Auto-generated method stub
		return this.categoryRepository.searchCategory(keyWord);
	}

	@Override
	public Page<Category> getAll(Integer pageNo) {
		Pageable pageable=PageRequest.of(pageNo-1,2);
		return this.categoryRepository.findAll(pageable);
	}

	

}
