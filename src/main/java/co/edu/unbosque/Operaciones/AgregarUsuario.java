package co.edu.unbosque.Operaciones;
import co.edu.unbosque.Datos.DatosUsuario;
import co.edu.unbosque.Datos.DatosUsuario;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


public class AgregarUsuario {

    private static String ruta ="users.csv";

    public static Optional<List<DatosUsuario>> getUsers() throws IOException {
        List<DatosUsuario> users;
        System.out.printf(ruta);
        try (InputStream is = new FileInputStream(ruta)) {

            HeaderColumnNameMappingStrategy<DatosUsuario> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(DatosUsuario.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                CsvToBean<DatosUsuario> csvToBean = new CsvToBeanBuilder<DatosUsuario>(br)
                        .withType(DatosUsuario.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                users = csvToBean.parse();
            }
        }
        return Optional.of(users);
    }

    public void crearUsuario(String username, String password, String role, String path, String s) throws IOException {
        String newLine = username + "," + password + "," + role + "," + "0" + "\n";

        System.out.println(path + File.separator + "resources" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "resources" + File.separator + "users.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }

    public void crearUsuario(String username, String password, String role, String coins, String path, boolean append) throws IOException {
        String newLine = username + "," + password + "," + role + "," + coins + "\n";

        System.out.println(path + File.separator + "resources" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "resources" + File.separator + "users.csv", append);
        os.write(newLine.getBytes());
        os.close();
    }

    public void crearUsuario(String username, String password, String role, String path, boolean append) throws IOException {
        String newLine = username + "," + password + "," + role + "," + "0" + "\n";

        System.out.println(path + File.separator + "resources" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "resources" + File.separator + "users.csv", append);
        os.write(newLine.getBytes());
        os.close();
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}

