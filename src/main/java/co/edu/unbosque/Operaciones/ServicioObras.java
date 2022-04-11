package co.edu.unbosque.Operaciones;

import co.edu.unbosque.Datos.DatosObrasArte;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ServicioObras {

    private ArrayList<DatosObrasArte> ArrayObras = new ArrayList<DatosObrasArte>();

    public void crearObra(String titulo, int precio, File rutaImag) throws IOException {
        DatosObrasArte obra = new DatosObrasArte(titulo,precio,rutaImag);
        ArrayObras.add(obra);
        CSVWriter writer = new CSVWriter(new FileWriter("Obras.csv"));
        for (int i = 0; i<ArrayObras.size(); i++){

            System.out.println("Entro a intentar escribir");
            String[] linea = new String[3];
            linea[0] = ArrayObras.get(i).getTitulo();

            linea[1]= String.valueOf(ArrayObras.get(i).getPrecio());

            linea[2]= String.valueOf(ArrayObras.get(i).getImagen());
            System.out.println(linea.toString());
            writer.writeNext(linea);
        }
        System.out.println(ArrayObras.toString()+"en servicio obras.");
        writer.close();
    }

    public ArrayList<DatosObrasArte> getArrayObras() {
        return ArrayObras;
    }
}
