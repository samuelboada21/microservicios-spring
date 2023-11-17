
package com.carro.service.repository;

import com.carro.service.entidades.Carro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel
 */
@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{
    List<Carro> findByUsuarioId(int usuarioId);
}
