<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>
    <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">


<html>
<body>
<div class="main">
    <h1>Search</h1>
    <form method="POST" action="${pageContext.request.contextPath}/runsearch">
        <input type="text" name="searchstring" placeholder="Enter search keyword here"/>
        <input type="submit" value="Search"/>
    </form>
</div>
</body>
</html>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>