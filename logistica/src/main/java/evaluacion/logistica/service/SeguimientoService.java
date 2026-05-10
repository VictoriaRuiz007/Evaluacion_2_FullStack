package evaluacion.logistica.service;

import evaluacion.logistica.exception.NoEncontradoException;
import evaluacion.logistica.model.Seguimiento;
import evaluacion.logistica.repository.SeguimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    public List<Seguimiento> listarSeguimientos() {
        log.info("Iniciando lista de seguimientos...");
        return seguimientoRepository.findAll();
    }

    public Seguimiento buscarPorId(Integer id){
        log.info("ID ingresado:{}, iniciando busqueda... ", id);
        return seguimientoRepository.findById(id)
                .orElseThrow(()-> new NoEncontradoException("Seguimiento no encontrado"));
    }
}
