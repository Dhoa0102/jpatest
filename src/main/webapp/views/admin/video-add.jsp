<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
	<label for="fname">Description</label><br>
	<input type="text" id="description" name="description" value="${video.description}"><br>
	<label for="fname">Tittle</label><br>
	<input type="text" id="tittle" name="tittle" value="${video.tittle}"><br>
	<label for="fname">Views</label><br>
	<input type="text" id="views" name="views" value="${video.views}"><br>
	<label for="fname">Category Id</label><br>
	<input type="text" id="categoryId" name="categoryId" value="${video.category.categoryId}"><br>
	
	<label for="lname">Poster</label><br>
		
			<img height="150" width="200" src="${imgUrl}" />
	<input type="file" id="poster" name="poster"><br>
	<p>Active</p>
	<input type="radio" id="acton" name="active" value="1" checked >
	<label for="html">Dang hoat dong</label><br>
	<input type="radio" id="actoff" name="active" value="0" >
	<label for="css">Khoa</label><br>
	<input type="submit" value="insert">
</form>