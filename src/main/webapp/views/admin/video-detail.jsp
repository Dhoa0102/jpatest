<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta charset="UTF-8">
    <title>Video Detail Page</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #000;
        }
        td {
            padding: 10px;
            border: 1px solid #000;
            vertical-align: top;
        }
        .poster {
            width: 200px; /* Adjust the width of the poster area */
            height: 300px; /* Adjust the height of the poster area */
            background-color: #f0f0f0;
            text-align: center;
        }
        .description {
            width: 100%;
            border-top: 1px solid #000;
        }
    </style>
</head>
<body>

<h2>Chi tiết video</h2>

<form action="${pageContext.request.contextPath}/admin/video/detail " method="get" enctype="multipart/form-data">
	<table>
	    <tr>

	        <c:if test="${video.poster.substring(0,5) == 'https' }">
				<c:url value="${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${video.poster.substring(0,5) != 'https' }">
				<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>
	        <td class="poster"><img height="150" width="200" src="${imgUrl}" /></td>
	        <td>
	            <p><strong>Tiêu đề:</strong>${video.tittle} </p>
	            <p><strong>Mã video:</strong> ${video.videoId}</p>
	            <p><strong>Category name:</strong> ${video.category.categoryname}</p>
	            <p><strong>View:</strong> ${video.views}</p>
	            <p><strong>Share:</strong> 10</p>
	            <p><strong>Like:</strong> 10</p>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" class="description">${video.description}</td>
	    </tr>
	</table>
	<a href="<c:url value='/admin/videos'/>">Back</a>
</form>



</body>
</html>