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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String username = request.getParameter("name");
        String password = request.getParameter("password");

        AgregarUsuario uService = new AgregarUsuario();
        uService.setRuta(getServletContext().getRealPath("") + File.separator +  "WEB-INF/classes" + File.separator + "usuarios.csv");

        List<DatosUsuario> users;
        users = getUsers().get();

        DatosUsuario userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()) )
                .findFirst()
                .orElse(null);

       if (userFounded != null) {
            request.setAttribute("role", userFounded.getRole());
            request.setAttribute("username", userFounded.getUsername());
           System.out.println(userFounded.getRole());

            if (userFounded.getRole().equals("Artista")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./artistas.html");
                dispatcher.forward(request, response);
            } else if (userFounded.getRole().equals("Comprador")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./piezas.html");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("./401.html");
        }




    }
}