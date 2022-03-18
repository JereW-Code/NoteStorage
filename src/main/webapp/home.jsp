<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">

    <nav>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/editNote">New Note</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/noteList">View the Note List</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/search">Search Notes</a>
            </li>
        </ul>
    </nav>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>