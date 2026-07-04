import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NumeroDAO {


    public void guardar(Numero numero) {

        String sql =
                "INSERT INTO numeros(numero,resultado) VALUES(?,?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, numero.getNumero());
            ps.setDouble(2, numero.getResultado());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarDatos() {
        String sql =
                "SELECT * FROM numeros";

        try(Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println();
            System.out.println("========== DATOS EN MYSQL ==========");

            while(rs.next()) {
                System.out.println(

                                "ID: " + rs.getInt("id") +
                                " | Numero: " + rs.getDouble("numero") +
                                " | Resultado: " + rs.getDouble("resultado")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
