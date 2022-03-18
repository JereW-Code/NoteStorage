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
  <h1>Search Result</h1>
  <%
    List<Note> notes = (List<Note>) request.getAttribute("result");
    if (!notes.isEmpty())
    {
    %>
    <ul>
      <%
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
     <% }
    } else
    {%>
      <p>Nothing found</p>
  <%}%>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>