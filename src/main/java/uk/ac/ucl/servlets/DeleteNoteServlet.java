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

@WebServlet("/noteDB/Delete")
public class DeleteNoteServlet extends HttpServlet
{
    //called by editNote.jsp
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String id = request.getParameter("note-id");

        Model model = ModelFactory.getModel(getServletContext().getRealPath(""));
        model.updateNote(id, "", "DEL");

        response.sendRedirect("/noteList");

    }

}
