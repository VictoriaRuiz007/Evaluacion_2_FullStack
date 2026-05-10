package evaluacion.logistica.repository;

import evaluacion.logistica.model.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
}
