package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.AgencyOperation;
import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Date;
import java.sql.SQLException;


/**
 * Servlet utilisee pour transferer de l'argent d'un compte vers un autre compte.
 */
@WebServlet(value = "/agency", name ="agency" )
public class AgenciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private AgencyOperation ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgenciesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String name;


		if(action != null) {
			switch (action) {
				case "get":
					name = request.getParameter("name");
					request.setAttribute("agency", ejb.getOne(name));
					request.getRequestDispatcher("/agency.jsp").forward(request, response);
					break;
				case "delete":
					name = request.getParameter("name");
					ejb.deleteOne(name);
					request.setAttribute("agencies", ejb.getAll());
					request.getRequestDispatcher("/agencies.jsp").forward(request, response);
					break;
				default:
					request.setAttribute("agencies", ejb.getAll());
					request.getRequestDispatcher("/agencies.jsp").forward(request, response);
					break;
			}
		}
		else{
			request.setAttribute("agencies", ejb.getAll());
			request.getRequestDispatcher("/agencies.jsp").forward(request, response);
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String name = request.getParameter("name");

		Agency agency = new Agency();
		agency.setName(name);

		ejb.createOne(agency);

		request.setAttribute("agencies", ejb.getAll());
		request.getRequestDispatcher("/agencies.jsp").forward(request, response);
	}



}
