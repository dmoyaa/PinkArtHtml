package co.edu.unbosque.Servlet;

import co.edu.unbosque.Operaciones.AgregarUsuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String nombre = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            System.out.println(getServletContext().getRealPath("") + File.separator);
            new AgregarUsuario().crearUsuario(nombre, password, role, getServletContext().getRealPath("") + File.separator, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("username", nombre);
        req.setAttribute("coins", "0");

        if (role.equals("Artista")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./artistas.html");
            dispatcher.forward(req, resp);
        } else if (role.equals("Comprador")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./piezas.html");
            dispatcher.forward(req, resp);
        }
    }
}