<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Note" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Notes:</h2>
  <ul>
    <%
      List<Note> notes = (List<Note>) request.getAttribute("notes");

      for (Note note : notes)
      {
        String noteId = note.getTextId();
    %>
      <li>
        <form action="${pageContext.request.contextPath}/noteDB" method="get" class="note-form">
          <label for="label-<%=noteId%>"><%=note.getName()%></label>
          <input id="btn-<%=noteId%>" type="submit" value="Edit">
          <input type="hidden" name="noteId" value="<%=noteId%>">
        </form>
      </li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
