package co.edu.unbosque.Servlet;
import java.io.*;
import java.util.List;

import co.edu.unbosque.Datos.DatosUsuario;
import co.edu.unbosque.Operaciones.AgregarUsuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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

        List<DatosUsuario> users = getUsers().get();

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