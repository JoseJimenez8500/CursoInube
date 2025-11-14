package dao;

import config.OracleConnection;
import model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProductoDaoImpl implements ProductoDao {


    /**
     * Metodo privado que gurada los metadatos del producto en la base de datos Oracle
     *
     * @param producto Objeto producto con la informacion a almacenar
     */
    @Override
    public void guardar(Producto producto) {
        String sqlGuardar = "INSERT INTO PRODUCTOS (ID_PRODUCTOS, NOMBRE, PRECIO, FECHA_ALTA) VALUES (?, ?, ?, ?)";
        try (Connection conn = OracleConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlGuardar)
        ){
            //Asignamos los valores deproducto a los parametros de consulta (Insert)
            ps.setString(1, producto.getIdProducto());
            ps.setString(2, producto.getNombreProducto());
            ps.setDouble(3, producto.getPrecio());
            ps.setDate(4, producto.getFechaCreacion());

            //ejecuta la insercion en la base de datos
            ps.executeUpdate();

            System.out.println("Producto guardado en Oracle: " + producto.getNombreProducto());
        }catch (SQLException e) {
            //Captura errores relacionados con la base de datos
            System.out.println("Error al guardar en Oracle: " + e.getMessage());
        }
    }
}
