package co.edu.unbosque.Operaciones;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="listFiles" , value = "/list-Files")
public class AgregarObras extends HttpServlet {

    private String Ruta_Subida= "subidos";

    public void DoGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/jason");

        String ruta_Archivo = getServletContext().getRealPath("") + File.separator+Ruta_Subida;
        File DirSubida = new File(ruta_Archivo);

        List<String> files = new ArrayList<>();
        for (File file : DirSubida.listFiles()){
            files.add(File.separator+Ruta_Subida+file.getName());
        }
        PrintWriter out = response.getWriter();
        // out.println(new Gson());

    }

}

