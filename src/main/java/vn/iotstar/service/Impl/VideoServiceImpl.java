package vn.iotstar.service.Impl;

import java.util.List;


import vn.iotstar.entity.Video;
import vn.iotstar.service.IVideoService;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDaoImpl;

public class VideoServiceImpl implements IVideoService {
	IVideoDao videoDao=new VideoDaoImpl();
	@Override
	public void insert(Video video) {
		videoDao.insert(video);
		
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
		
	}

	@Override
	public void delete(int videoid) throws Exception {
		videoDao.delete(videoid);
		
	}

	@Override
	public Video findById(int videoid) {
		return videoDao.findById(videoid);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public List<Video> findByVideoName(String videoname) {
		return videoDao.findByVideoName(videoname);
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return videoDao.count();
	}
}
