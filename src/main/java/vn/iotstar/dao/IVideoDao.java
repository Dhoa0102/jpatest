package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Video;


public interface IVideoDao {
	void insert(Video video);
	void update(Video video);
	void delete(int videoid) throws Exception;
	Video findById(int videoid);
	List<Video> findAll();
	List<Video> findByVideoName(String tittle);
	List<Video> findAll(int page,int pagesize);
	int count();
}
