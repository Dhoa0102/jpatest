<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/video/update " method="post" enctype="multipart/form-data">
	<input type="text" id="videoid" name="videoid" hidden="hidden" value="${video.videoId}"><br>
	<label for="fname">Description</label><br>
	<input type="text" id="description" name="description" value="${video.description}"><br>
	<label for="fname">Tittle</label><br>
	<input type="text" id="tittle" name="tittle" value="${video.tittle}"><br>
	<label for="fname">Views</label><br>
	<input type="text" id="views" name="views" value="${video.views}"><br>
	<label for="fname">CategoryId</label><br>
	<input type="text" id="categoryId" name="categoryId" value="${video.category.categoryId}"><br>
	
	<label for="lname">Poster</label><br>
		
			<img height="150" width="200" src="${imgUrl}" />
	<input type="file" id="poster" name="poster"><br>
	<p>Active</p>
	<<input type="radio" id="acton" name="active" value="1" ${video.active==true?'checked':'' }>
	<label for="html">Dang hoat dong</label><br>
	<input type="radio" id="actoff" name="active" value="0" ${cate.active==false?'checked':'' }>
	<label for="css">Khoa</label><br>
	<input type="submit" value="Update">
</form>