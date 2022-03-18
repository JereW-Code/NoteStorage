package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.Note;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/noteDB")
public class NoteDBServlet extends HttpServlet
{
    //called by editNote.jsp
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String id = request.getParameter("note-id");
        String name = request.getParameter("note-name");
        String text = request.getParameter("note-text");
        File file = new File(request.getParameter("note-file"));
//        System.out.println(name + "\n----------\n\n" + text + "\n-----------\n\n" + file);

        Model model = ModelFactory.getModel(getServletContext().getRealPath(""));
        model.writeNoteFile(id, name, text, file);

        response.sendRedirect("/noteList");

    }

    //called by noteList.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        String noteId = request.getParameter("noteId");

        Model model = ModelFactory.getModel(getServletContext().getRealPath(""));
        Note note = model.getNoteById(noteId);
        request.setAttribute("name", note.getName());
        request.setAttribute("noteId", note.getTextId());
        request.setAttribute("text", model.readTextFile(note.getTextId()));
        request.setAttribute("attachments", note.getAttachments());
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editNote.jsp");
        dispatch.forward(request, response);

    }
}
