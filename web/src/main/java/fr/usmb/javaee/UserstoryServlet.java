package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.AgencyOperation;
import fr.usmb.m2isc.javaee.comptes.ejb.BacklogOperation;
import fr.usmb.m2isc.javaee.comptes.ejb.UserstoryOperation;
import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Servlet utilisee pour transferer de l'argent d'un compte vers un autre compte.
 */
@WebServlet(value = "/userstory", name ="userstory" )
public class UserstoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BacklogOperation ejbBacklog;

	@EJB
	private UserstoryOperation ejbUserstory;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserstoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		int id;
		Backlog backlog = null;
		int idUserstory;

		if(action != null) {
			switch (action) {
				case "get":
					idUserstory = Integer.parseInt(request.getParameter("idUserstory"));
					request.setAttribute("userstory", ejbUserstory.getOne(idUserstory));
					request.getRequestDispatcher("/userstory.jsp").forward(request, response);
					break;
				case "delete":

					id = Integer.parseInt(request.getParameter("id"));
					backlog = ejbBacklog.getOne(id);
					idUserstory = Integer.parseInt(request.getParameter("idUserstory"));

					int index = -1;
					for(int i = 0 ; i < backlog.getUserstories().size(); i++){
						if(backlog.getUserstories().get(i).getId() == idUserstory){
							index = i;
							break;
						}
					}
					backlog.getUserstories().remove(index);
					ejbBacklog.updateOne(backlog);
					//ejbUserstory.deleteOne(idUserstory);

					request.setAttribute("backlog", backlog);
					request.getRequestDispatcher("/backlog.jsp").forward(request, response);
					break;
				default:
					request.setAttribute("backlog", backlog);
					request.getRequestDispatcher("/backlog.jsp").forward(request, response);
					break;
			}
		}
		else{

			id = Integer.parseInt(request.getParameter("id"));
			backlog = ejbBacklog.getOne(id);
			request.setAttribute("backlog", backlog);
			request.getRequestDispatcher("/backlog.jsp").forward(request, response);
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");

		Backlog backlog = ejbBacklog.getOne(id);
		Userstory userstory = new Userstory();
		userstory.setDate(new Date());
		userstory.setTitle(title);
		userstory.setBacklog(backlog);
		//ejbUserstory.createOne(userstory);

		backlog.getUserstories().add(userstory);

		ejbBacklog.updateOne(backlog);

		backlog = ejbBacklog.getOne(id);


		request.setAttribute("backlog", backlog);
		request.getRequestDispatcher("/backlog.jsp").forward(request, response);
	}



}
