package vn.iotstar.service.Impl;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;

public class CategoryServices implements ICategoryService{
	ICategoryDao cateDao=new CategoryDaoImpl();
	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);		
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<Category> findByCategoryName(String catename) {
		return cateDao.findByCategoryName(catename);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return cateDao.count();
	}

}
