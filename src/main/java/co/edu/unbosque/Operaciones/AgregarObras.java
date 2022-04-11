package co.edu.unbosque.Operaciones;

import co.edu.unbosque.Servlet.ObrasArteServlet;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String ruta_Archivo = getServletContext().getRealPath("") + File.separator+RUTA_SUBIDA;
        File DirSubida = new File(ruta_Archivo);

        List<String> files = new ArrayList<String>();
        for (File file : DirSubida.listFiles()){
            files.add(RUTA_SUBIDA+File.separator+file.getName());

        }
        for (int i=0; i< new ServicioObras().getArrayObras().size();i++){

        }

        System.out.println(files.toString());
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(files));



    }

}
