package factory;

import dao.ProductoDao;
import dao.ProductoDaoImpl;

public class RespositoryFactory {

    //Por ahora solo tenemos una conexion a Oracle
    public static ProductoDao getImagenRepository() {
        return new ProductoDaoImpl();
    }
}
