package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Video;

public class VideoDaoImpl implements IVideoDao{

	@Override
	public void insert(Video video) {
		EntityManager enma=JPAConfig.getEntityManager();
		EntityTransaction trans=enma.getTransaction();
		try {
			trans.begin();
			enma.persist(video);//insert
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
		
	}

	@Override
	public void update(Video video) {
		EntityManager enma=JPAConfig.getEntityManager();
		EntityTransaction trans=enma.getTransaction();
		try {
			trans.begin();
			enma.merge(video);//update
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
		
	}

	@Override
	public void delete(int videoid) throws Exception {
		EntityManager enma=JPAConfig.getEntityManager();
		EntityTransaction trans=enma.getTransaction();
		try {
			trans.begin();
			Video video=enma.find(Video.class, videoid);
			if(video!=null) {
				enma.remove(video);
			}else {
				throw new Exception("Khong tim thay");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
		
	}

	@Override
	public Video findById(int videoid) {
		EntityManager enma=JPAConfig.getEntityManager();
		Video video=enma.find(Video.class, videoid);
		return video;
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma=JPAConfig.getEntityManager();
		TypedQuery<Video> query=enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideoName(String tittle) {
		EntityManager enma=JPAConfig.getEntityManager();
		String jpql="select v from Video v where v.tittle like :tittle";
		TypedQuery<Video> query=enma.createQuery(jpql, Video.class);
		query.setParameter("tittle", "%"+tittle+"%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select count(v) from Video v";
		Query query= enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
