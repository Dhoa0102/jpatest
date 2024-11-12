package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IVideoService;
import vn.iotstar.service.Impl.CategoryServices;
import vn.iotstar.service.Impl.VideoServiceImpl;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/edit", "/admin/video/update","/admin/video/insert",
		"/admin/video/add","/admin/video/delete","/admin/video/search","/admin/video/detail","/admin/video/categoryvideo"})
public class VideoController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public IVideoService videoService=new VideoServiceImpl();
	public ICategoryService cateService= new CategoryServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url= req.getRequestURI();
		if(url.contains("videos"))
		{
			List<Video> list=videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
		else if(url.contains("edit"))
		{
			int id=Integer.parseInt(req.getParameter("id"));
			Video video=videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
		else if(url.contains("categoryvideo"))
		{
			List<Category> list=cateService.findAll();
			req.setAttribute("listcate", list);
			int id=Integer.parseInt(req.getParameter("id"));
			Video video=videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/CategoryVideoList.jsp").forward(req, resp);
		}
		
		else if(url.contains("detail"))
		{
			int id=Integer.parseInt(req.getParameter("id"));
			Video video=videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-detail.jsp").forward(req, resp);
		}
		else if(url.contains("add"))
		{
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		}
		else if(url.contains("delete")){
			int id=Integer.parseInt(req.getParameter("id"));
			try {
				videoService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath()+"/admin/videos");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url= req.getRequestURI();
		if(url.contains("admin/video/update")) {
			int videoid=Integer.parseInt(req.getParameter("videoid"));
			String description=req.getParameter("description");
			String tittle=req.getParameter("tittle");
			int views=Integer.parseInt(req.getParameter("views"));
			int categoryId=Integer.parseInt(req.getParameter("categoryId"));
			Category category=cateService.findById(categoryId);
			int active=Integer.parseInt(req.getParameter("active"));
			Video video=new Video();
			video.setVideoId(videoid);
			video.setDescription(description);
			video.setTittle(tittle);
			video.setViews(views);
			video.setCategory(category);
			if(active==0)
				video.setActive(false);
			else
				video.setActive(true);
			// Lưu hình cũ
			Video videoOld=videoService.findById(videoid);
			String fileOld=videoOld.getPoster();
			
			// images config
			String fname="";
			String uploadPath= Constant.UPLOAD_DIRECTORY;
			File uploadDir= new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part=req.getPart("poster");
				if(part.getSize()>0) {
					String filename= Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index=filename.lastIndexOf(".");
					String ext =filename.substring(index+1);
					fname=System.currentTimeMillis()+"."+ext;
					//upload
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				}else{
					video.setPoster(fileOld);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			videoService.update(video);
			resp.sendRedirect(req.getContextPath()+"/admin/videos");
		}
		else 
			if(url.contains("admin/video/insert")) {
				String description=req.getParameter("description");
				String tittle=req.getParameter("tittle");
				int views=Integer.parseInt(req.getParameter("views"));
				int active=Integer.parseInt(req.getParameter("active"));
				int id=Integer.parseInt(req.getParameter("categoryId"));
				Category category=cateService.findById(id);
				Video video=new Video();
				video.setDescription(description);
				video.setTittle(tittle);
				video.setCategory(category);
				video.setViews(views);
				if(active==0)
					video.setActive(false);
				else 
					video.setActive(true);
				String fname="";
				String uploadPath= Constant.UPLOAD_DIRECTORY;
				File uploadDir= new File(uploadPath);
				if(!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				try {
					Part part=req.getPart("poster");
					if(part.getSize()>0) {
						String filename= Paths.get(part.getSubmittedFileName()).getFileName().toString();
						// đổi tên file
						int index=filename.lastIndexOf(".");
						String ext =filename.substring(index+1);
						fname=System.currentTimeMillis()+"."+ext;
						//upload
						part.write(uploadPath + "/" + fname);
						video.setPoster(fname);
					}else{
						video.setPoster("avatar.jpg");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(video.toString());
				videoService.insert(video);
				resp.sendRedirect(req.getContextPath()+"/admin/videos");
			}
			else 
				if(url.contains("admin/video/search")) {
					String searchname=req.getParameter("searchname");
					List<Video> list=videoService.findByVideoName(searchname);
					req.setAttribute("listvideo", list);
					req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
				}
	}
}
