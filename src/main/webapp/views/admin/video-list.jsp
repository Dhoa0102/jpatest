<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<a href="${pageContext.request.contextPath}/admin/home ">Back Home</a>
<a href="${pageContext.request.contextPath}/admin/video/add ">Add Video</a>
<form action="${pageContext.request.contextPath}/admin/video/search" method="post">
	
	<input type="text" id="videoSearch" name="searchname"  placeholder="Search for video" title="Type in a tittle">
	<input type="submit" value="search">

</form>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Poster</th>
		<th>Description</th>
		<th>Tittle</th>
		<th>Views</th>
		<th>Category Name</th>
		<th>Active</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT" >
		<tr>
			<td>${STT.index+1 }</td>
			<c:if test="${video.poster.substring(0,5) == 'https' }">
				<c:url value="${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${video.poster.substring(0,5) != 'https' }">
				<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>
		
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${video.description}</td>
			<td>${video.tittle}</td>
			<td>${video.views}</td>
			<td>${video.category.categoryname}</td>
			<td>
				<c:if test="${cate.active==true }">
					<span>Hoạt động</span>
				</c:if>
				<c:if test="${cate.active!=true }">
					<span>Khóa</span>
				</c:if>
				
			</td>
			<td>
				<a href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Sửa</a>
			 	<a href="<c:url value='/admin/video/delete?id=${video.videoId }'/>">Xóa</a>
				<a href="<c:url value='/admin/video/detail?id=${video.videoId }'/>">chi tiết</a>
			</td>
		</tr>
	</c:forEach>
</table>