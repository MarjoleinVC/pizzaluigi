package be.vdab.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzasServlet
 */
@WebServlet("/pizzas.htm")
public class PizzasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private final PizzaDAO pizzaDAO = new PizzaDAO();

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute("pizzasRequests",
				new AtomicInteger());
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Map<Long, Pizza> pizzas = new LinkedHashMap<>(); pizzas.put(12L, new
		 * Pizza(12, "Prosciutto", new BigDecimal(4), true)); pizzas.put(14L,
		 * new Pizza(14, "Margherita", new BigDecimal(5), false));
		 * pizzas.put(17L, new Pizza(17, "Calzone", new BigDecimal(4), false));
		 * pizzas.put(23L, new Pizza(23, "Fungi & Olive", new BigDecimal(5),
		 * false)); request.setAttribute("pizzas", pizzas);
		 * request.getRequestDispatcher(VIEW).forward(request, response);
		 */
		((AtomicInteger) this.getServletContext()
				.getAttribute("pizzasRequests")).incrementAndGet();
		Iterable<Pizza> pizzas = pizzaDAO.findAll();
		String pizzaFotosPad = this.getServletContext().getRealPath(
				"/pizzafotos");
		Set<Long> pizzaIdsMetFoto = new HashSet<>();
		for (Pizza pizza : pizzas) {
			File file = new File(String.format("%s/%d.jpg", pizzaFotosPad,
					pizza.getId()));
			if (file.exists()) { // is er foto voor deze pizza ?
				pizzaIdsMetFoto.add(pizza.getId());
			}
		}
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("pizzaIdsMetFoto", pizzaIdsMetFoto);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}