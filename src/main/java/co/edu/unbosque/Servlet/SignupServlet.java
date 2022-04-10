package co.edu.unbosque.Servlet;

import co.edu.unbosque.Datos.DatosUsuario;
import co.edu.unbosque.Operaciones.AgregarUsuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        if (role.equals("artista")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./artistas.html");
            dispatcher.forward(req, resp);
        } else if (role.equals("comprador")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./");
            dispatcher.forward(req, resp);
        }
    }
}