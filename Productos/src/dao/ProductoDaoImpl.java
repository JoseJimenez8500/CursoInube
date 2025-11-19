package dao;

import config.OracleConnection;
import model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public void borrar(String idProducto) {
        String sqlBorrar = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTOS = ?";
        try (Connection conn = OracleConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlBorrar)
        ){
            //Asignamos los valores de producto a los parametros de consulta (Delete)
            ps.setString(1, idProducto);

            //ejecuta el borrado en la base de datos
            int filas = ps.executeUpdate();

            System.out.println("Producto borrado en Oracle: " + idProducto);
        }catch (SQLException e) {
            //Captura errores relacionados con la base de datos
            System.out.println("Error al borrar en Oracle: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sqlLista = "SELECT ID_PRODUCTOS, NOMBRE, PRECIO, FECHA_ALTA FROM PRODUCTOS";
        try (Connection conn = OracleConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlLista)
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                );
                productos.add(p);
            }

            System.out.println("Lista de productos guardados en Oracle");
        }catch (SQLException e) {
            //Captura errores relacionados con la base de datos
            System.out.println("Error al listar los productos " + e.getMessage());
        }
        return productos;
    }
}
