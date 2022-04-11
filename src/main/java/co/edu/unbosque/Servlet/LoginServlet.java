package co.edu.unbosque.Servlet;

import co.edu.unbosque.Datos.DatosUsuario;
import co.edu.unbosque.Operaciones.AgregarUsuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static co.edu.unbosque.Operaciones.AgregarUsuario.getUsers;

@WebServlet(name = "login", value = "/login")

class LoginServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        AgregarUsuario uService = new AgregarUsuario();
        uService.setRuta(getServletContext().getRealPath("") + File.separator + "resources" + File.separator + "users.csv");

        List<DatosUsuario> users;
        users = getUsers().get();

        DatosUsuario userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()) && role.equals(user.getRole()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {
            request.setAttribute("role", userFounded.getRole());
            request.setAttribute("coins", userFounded.getCoins());
            request.setAttribute("username", userFounded.getUsername());

            if (role.equals("artista")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./artistas.html");
                dispatcher.forward(request, response);
            } else if (role.equals("comprador")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("./401.html");
        }
    }
}