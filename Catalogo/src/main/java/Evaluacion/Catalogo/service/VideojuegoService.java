package Evaluacion.Catalogo.service;
import Evaluacion.Catalogo.model.Videojuego;
import Evaluacion.Catalogo.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository repository;

    public List <Videojuego> listarVideojuegos(){
        return repository.findAll();
    }

    public Videojuego buscarPorID(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Videojuego no encoontrado"));

    }

    public Videojuego crearVideojuego(Videojuego videojuego){
        return repository.save(videojuego);
    }
    public void eliminarVideojuego (Long id){
        repository.deleteById(id);
    }
}
