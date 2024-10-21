import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Metodos {

    // Metodo para establecer conexión con la base de datos
    /**
     * Establece la conexión con la base de datos PostgreSQL.
     *
     * @return un objeto Connection que representa la conexión con la base de datos.
     * Si no se puede conectar, se muestra un mensaje de error y se devuelve null.
     */
    public static Connection conexion() {
        Connection conn = null;
        try {
            String driver = "jdbc:postgresql:";
            String host = "//localhost:";
            String porto = "5432";
            String sid = "postgres";
            String usuario = "postgres";
            String password = "postgres";
            String url = driver + host + porto + "/" + sid;

            conn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.err.println("Erro na conexión: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Inserta un nuevo producto en la tabla 'produtos'.
     *
     * @param codigo         el código del producto.
     * @param descricion     la descripción del producto.
     * @param prezo         el precio del producto.
     * @param dataCaducidade la fecha de caducidad del producto en formato "dd/MM/yyyy".
     */
    public static void insireProduto(String codigo, String descricion, int prezo, String dataCaducidade) {
        // convertimos el string que pasamos a date, mediante la variable fecha:
        LocalDate fecha = null;
        fecha = LocalDate.parse(dataCaducidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String sql = "INSERT INTO produtos (codigo, descricion, prezo, datac) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, descricion);
            pstmt.setInt(3, prezo);
            pstmt.setDate(4, Date.valueOf(fecha));

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Produto inserido correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    //Metodo para listar todos los productos
    /**
     * Lista todos los productos de la tabla 'produtos'.
     */
    public static void listaProdutos() {
        String sql = "SELECT * FROM produtos";
        try (Connection conn = conexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Código: " + rs.getString("codigo"));
                System.out.println("Descripción: " + rs.getString("descricion"));
                System.out.println("Prezo: " + rs.getInt("prezo"));
                System.out.println("Data de Caducidade: " + rs.getDate("datac"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    // Metodo para listar un producto por su código
    /**
     * Lista un producto específico según su código.
     *
     * @param codigo el código del producto a buscar.
     */
    public static void listaProdutoPorCodigo(String codigo) {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";
        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Código: " + rs.getString("codigo"));
                System.out.println("Descripción: " + rs.getString("descricion"));
                System.out.println("Prezo: " + rs.getInt("prezo"));
                System.out.println("Data de Caducidade: " + rs.getDate("datac"));
            } else {
                System.out.println("Non se atopou o produto co código: " + codigo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produto: " + e.getMessage());
        }
    }

    // Metodo para actualizar el precio de un producto
    /**
     * Actualiza el precio de un producto especificado por su código.
     *
     * @param codigo     el código del producto a actualizar.
     * @param novoPrezo  el nuevo precio que se asignará al producto.
     */
    public static void actualizaPre(String codigo, int novoPrezo) {
        String sql = "UPDATE produtos SET prezo = ? WHERE codigo = ?";
        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, novoPrezo);
            pstmt.setString(2, codigo);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Prezo actualizado correctamente.");
            } else {
                System.out.println("Non se atopou o produto co código: " + codigo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao actualizar prezo: " + e.getMessage());
        }
    }

    // Metodo para eliminar un producto por su código
    /**
     * Elimina un producto especificado por su código.
     *
     * @param codigo el código del producto a eliminar.
     */
    public static void eliminaProduto(String codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";
        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Produto eliminado correctamente.");
            } else {
                System.out.println("Non se atopou o produto co código: " + codigo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao eliminar produto: " + e.getMessage());
        }
    }
}
