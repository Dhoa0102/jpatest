<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách Video theo Danh mục</title>
    <style>
        .category {
            margin-top: 20px;
            border-bottom: 1px solid #ccc;
        }
        .video {
            display: flex;
            margin-top: 10px;
        }
        .poster {
            width: 150px;
            height: 100px;
            background-color: #f0f0f0;
            margin-right: 10px;
        }
        .video-details {
            flex: 1;
        }
    </style>
</head>
<body>
<form action=""></form>
<h1>Danh sách Video theo Danh mục</h1>

<!-- Duyệt qua từng danh mục -->
<c:forEach items="${listcate}" var="cate" varStatus="STT"">
    <div class="category">
        <h2>${cate.categoryname}</h2>

        <!-- Kiểm tra nếu danh mục có video nào không -->
        <c:choose>
            <c:when test="${not empty category.videos}">
                <!-- Duyệt qua từng video trong danh mục -->
                <c:forEach var="video" items="${cate.videos}">
                    <div class="video">
                        <div class="poster">
                            <img src="${video.poster}" alt="${video.tittle}" style="width:100%; height:100%;">
                        </div>
                        <div class="video-details">
                            <h3>${video.tittle}</h3>
                            <p><strong>Lượt xem:</strong> ${video.views}</p>
                            <p>${video.description}</p>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>Không có video nào trong danh mục này.</p>
            </c:otherwise>
        </c:choose>
    </div>
</c:forEach>

</body>
</html>
