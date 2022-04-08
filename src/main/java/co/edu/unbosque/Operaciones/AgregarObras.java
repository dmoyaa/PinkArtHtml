package co.edu.unbosque.Operaciones;

import co.edu.unbosque.Datos.DatosObrasArte;
import co.edu.unbosque.Datos.DatosUsuario;
import co.edu.unbosque.Servlet.ObrasArteServlet;
import com.google.gson.Gson;

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

@WebServlet(name="AgregarObras" , value = "AgregarObras")
public class AgregarObras extends HttpServlet {

    private String RUTA_SUBIDA= "subidos";
    private ObrasArteServlet obras = new ObrasArteServlet();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        System.out.println("lo intenté pero estoy pendejo");

        String ruta_Archivo = getServletContext().getRealPath("") + File.separator+RUTA_SUBIDA;
        File DirSubida = new File(ruta_Archivo);

        List<String> files = new ArrayList<String>();
        for (File file : DirSubida.listFiles()){
            files.add(RUTA_SUBIDA+File.separator+file.getName());

        }

        System.out.println(files.toString());
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(files));



    }

}
