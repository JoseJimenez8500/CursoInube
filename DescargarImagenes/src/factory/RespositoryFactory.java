package factory;

import repository.ImagenRepository;
import repository.ImagenRepositoryImpl;

public class RespositoryFactory {

    //Por ahora solo tenemos una conexion a Oracle
    public static ImagenRepository getImagenRepository() {
        return new ImagenRepositoryImpl();
    }
}
