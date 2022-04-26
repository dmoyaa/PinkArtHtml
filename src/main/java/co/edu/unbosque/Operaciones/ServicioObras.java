package co.edu.unbosque.Operaciones;

import co.edu.unbosque.Datos.DatosObrasArte;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioObras {

    private ArrayList<DatosObrasArte> ArrayObras = new ArrayList<DatosObrasArte>();

    private static String ruta = "Obras.csv";

    public static Optional<List<DatosObrasArte>> getObras() throws IOException {
        List<DatosObrasArte> obrasArtes;
        System.out.printf(ruta);
        try (InputStream is = new FileInputStream(ruta)) {

            MappingStrategy Column;
            HeaderColumnNameMappingStrategy<DatosObrasArte> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(DatosObrasArte.class);
            Column = strategy;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                CsvToBean<DatosObrasArte> csvToBean = new CsvToBeanBuilder<DatosObrasArte>(br)
                        .withType(DatosObrasArte.class)
                        .withMappingStrategy(Column)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                obrasArtes = csvToBean.parse();
            }
        }
        return Optional.of(obrasArtes);
    }

    public void crearObra(String titulo, int precio, File rutaImag, String path,boolean append) throws IOException {
        DatosObrasArte obra = new DatosObrasArte(titulo,precio,rutaImag);
        String newLine = "\n"+titulo+","+precio+","+rutaImag;
        ArrayObras.add(obra);
        FileOutputStream os = new FileOutputStream(path  + "WEB-INF/classes" + File.separator + "Obras.csv", append);
        os.write(newLine.getBytes());
        os.close();

        System.out.println(ArrayObras.toString()+"en servicio obras.");
    }

    public ArrayList<DatosObrasArte> getArrayObras() {
        return ArrayObras;
    }
}
