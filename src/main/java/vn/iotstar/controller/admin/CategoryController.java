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
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.Impl.CategoryServices;
import vn.iotstar.utils.Constant;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/edit", "/admin/category/update","/admin/category/insert",
		"/admin/category/add","/admin/category/delete","/admin/category/search"})
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService= new CategoryServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url= req.getRequestURI();
		if(url.contains("categories"))
		{
			List<Category> list=cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if(url.contains("edit"))
		{
			int id=Integer.parseInt(req.getParameter("id"));
			Category category=cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if(url.contains("add"))
		{
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		else if(url.contains("delete")){
			int id=Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url= req.getRequestURI();
		if(url.contains("admin/category/update")) {
			int categoryid=Integer.parseInt(req.getParameter("categoryid"));
			String categoryname=req.getParameter("categoryname");
			int status=Integer.parseInt(req.getParameter("status"));

			Category category=new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			if(status==0)
				category.setStatus(false);
			else
				category.setStatus(true);
			// Lưu hình cũ
			Category cateOld=cateService.findById(categoryid);
			String fileOld=cateOld.getImages();
			
			// images config
			String fname="";
			String uploadPath= Constant.UPLOAD_DIRECTORY;
			File uploadDir= new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part=req.getPart("images");
				if(part.getSize()>0) {
					String filename= Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index=filename.lastIndexOf(".");
					String ext =filename.substring(index+1);
					fname=System.currentTimeMillis()+"."+ext;
					//upload
					part.write(uploadPath + "/" + fname);
					category.setImages(fname);
				}else{
					category.setImages(fileOld);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
		else 
			if(url.contains("admin/category/insert")) {
				String categoryname=req.getParameter("categoryname");
				int status=Integer.parseInt(req.getParameter("status"));
				
				Category category=new Category();
				category.setCategoryname(categoryname);
				if(status==0)
					category.setStatus(false);
				else 
					category.setStatus(true);
				String fname="";
				String uploadPath= Constant.UPLOAD_DIRECTORY;
				File uploadDir= new File(uploadPath);
				if(!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				try {
					Part part=req.getPart("images");
					System.out.println("asfadssadasbbbbbbbbbbbbbbbbbbbbb");
					System.out.print(part.getSize());
					if(part.getSize()>0) {
						String filename= Paths.get(part.getSubmittedFileName()).getFileName().toString();
						// đổi tên file
						int index=filename.lastIndexOf(".");
						String ext =filename.substring(index+1);
						fname=System.currentTimeMillis()+"."+ext;
						//upload
						part.write(uploadPath + "/" + fname);
						System.out.println("asfadssadasaaaaaaaaaaaaa");
						System.out.println(fname);
						category.setImages(fname);
					}else{
						category.setImages("avatar.jpg");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				cateService.insert(category);
				resp.sendRedirect(req.getContextPath()+"/admin/categories");
			}
	}
}
