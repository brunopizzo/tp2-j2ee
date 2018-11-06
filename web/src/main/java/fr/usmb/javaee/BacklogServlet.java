package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.AgencyOperation;
import fr.usmb.m2isc.javaee.comptes.ejb.BacklogOperation;
import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;

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
@WebServlet(value = "/backlog", name ="backlog" )
public class BacklogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private AgencyOperation ejbAgency;

	@EJB
	private BacklogOperation ejbBacklog;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BacklogServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String name = request.getParameter("name");
		Agency agency = ejbAgency.getOne(name);


		if(action != null) {
			switch (action) {
				case "delete":
					int id = agency.getBacklog().getId();
					agency.setBacklog(null);
					ejbAgency.updateOne(agency);
					ejbBacklog.deleteOne(id);
					request.setAttribute("agency", agency);
					request.getRequestDispatcher("/agency.jsp").forward(request, response);
					break;
				default:
					request.setAttribute("agency", agency);
					request.getRequestDispatcher("/agency.jsp").forward(request, response);
					break;
			}
		}
		else{
			request.setAttribute("agency", agency);
			request.getRequestDispatcher("/agency.jsp").forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int priority = Integer.parseInt(request.getParameter("priority"));
		int estimate = Integer.parseInt(request.getParameter("estimate"));

		Agency agency = ejbAgency.getOne(name);
		Backlog backlog = new Backlog();
		backlog.setDate(new Date());
		backlog.setDescription(description);
		backlog.setPriority(priority);
		backlog.setEstimate(estimate);
		//backlog.setAgency(agency);
		//ejbBacklog.createOne(backlog);

		agency.setBacklog(backlog);

		ejbAgency.updateOne(agency);

		agency = ejbAgency.getOne(name);


		request.setAttribute("agency", agency);
		request.getRequestDispatcher("/agency.jsp").forward(request, response);
	}



}
