package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.BacklogOperation;
import fr.usmb.m2isc.javaee.comptes.ejb.CommentOperation;
import fr.usmb.m2isc.javaee.comptes.ejb.UserstoryOperation;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;
import fr.usmb.m2isc.javaee.comptes.jpa.Comment;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


/**
 * Servlet utilisee pour transferer de l'argent d'un compte vers un autre compte.
 */
@WebServlet(value = "/comment", name ="comment" )
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CommentOperation ejbComment;

	@EJB
	private UserstoryOperation ejbUserstory;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		Userstory userstory = ejbUserstory.getOne(id);


		if(action != null) {

					int idComment = Integer.parseInt(request.getParameter("idComment"));
					int index = -1;
					for(int i = 0 ; i < userstory.getComments().size(); i++){
						if(userstory.getComments().get(i).getId() == idComment){
							index = i;
							break;
						}
					}
					userstory.getComments().remove(index);
					ejbUserstory.updateOne(userstory);


					request.setAttribute("userstory", userstory);
					request.getRequestDispatcher("/userstory.jsp").forward(request, response);

		}
		else{
			request.setAttribute("userstory", userstory);
			request.getRequestDispatcher("/userstory.jsp").forward(request, response);
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		Userstory userstory = ejbUserstory.getOne(id);
		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setDescription(description);
		comment.setUserstory(userstory);
		comment.setOwner(request.getRemoteUser());
		comment = ejbComment.createOne(comment);
		userstory.getComments().add(comment);
		ejbUserstory.updateOne(userstory);

		userstory = ejbUserstory.getOne(id);

		request.setAttribute("userstory", userstory);
		request.getRequestDispatcher("/userstory.jsp").forward(request, response);
	}



}
