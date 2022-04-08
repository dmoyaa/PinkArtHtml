package co.edu.unbosque.Servlet;

import co.edu.unbosque.Datos.DatosObrasArte;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ObrasArteServlet", value = "/upload-file")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
    maxFileSize = 10024 * 10024 * 5,
    maxRequestSize = 1024 * 1024 * 5 * 5)
public class ObrasArteServlet extends HttpServlet {

    private String RUTA_SUBIDA= "subidos";
    private ArrayList<DatosObrasArte> datosObrasArteArrayList = new ArrayList<DatosObrasArte>();

    public void init() {

    }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws   IOException{
        System.out.println("Title: "+request.getParameter("title")+"\n" +
                "Price: "+request.getParameter("precio"));
        String t = request.getParameter("title");
        int p = Integer.parseInt(request.getParameter("precio")) ;

        String rutaArchivo = getServletContext().getRealPath("")+ File.separator+RUTA_SUBIDA;
        File DirArchivo = new File(rutaArchivo);



        if(!DirArchivo.exists()) DirArchivo.mkdir();
        File imagen = null;
        try{
            for ( Part part : request.getParts()){
                String NombreArchivo = part.getSubmittedFileName();
                part.write(DirArchivo+File.separator+NombreArchivo);
                imagen=new File(RUTA_SUBIDA+File.separator+part.getSubmittedFileName());
            }
        }
        catch (ServletException e) {
            e.printStackTrace();
        }
        DatosObrasArte obra = new DatosObrasArte(t,p,imagen);
        datosObrasArteArrayList.add(obra);
       System.out.println(datosObrasArteArrayList.toString());
       response.sendRedirect("./prueba.html");
   }

    public ArrayList<DatosObrasArte> getDatosObrasArteArrayList() {
        return datosObrasArteArrayList;
    }
}
