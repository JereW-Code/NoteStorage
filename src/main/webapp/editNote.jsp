<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Edit Note</h2>
    <form action="${pageContext.request.contextPath}/noteDB" method="post" class="note-form">
        <div class="note-form">
            <label for="note-name">Title: </label>
            <input type="text" name="note-name" id="note-name"
                   value="<%=request.getAttribute("name") != null ? request.getAttribute("name") : ""%>"
                   required >
        </div>
        <div class="note-form">
            <label for="note-text">Text:</label>
        </div>
        <div class="note-form">
            <textarea id="note-text" name="note-text" rows="25" cols="150" required><%=request.getAttribute("text") != null ? request.getAttribute("text") : ""%></textarea>
        </div>
        <div class="note-form">
            <label for="note-file">Attachment: </label>
            <input type="file" id="note-file" name="note-file">
        </div>
        <div class="note-form">
            <input type="submit" value="Save">
        </div>
        <input type="hidden" name="note-id" value="<%=request.getAttribute("noteId") != null ? request.getAttribute("noteId") : ""%>">
    </form>
    <form action="${pageContext.request.contextPath}/noteDB/Delete" method="post" class="note-form">
        <div class="note-form">
            <input type="submit" value="Delete">
        </div>
        <input type="hidden" name="note-id" value="<%=request.getAttribute("noteId") != null ? request.getAttribute("noteId") : ""%>">
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>