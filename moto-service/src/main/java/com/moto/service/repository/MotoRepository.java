
package com.moto.service.repository;

import com.moto.service.entidades.Moto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel
 */
@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer>{
    List<Moto> findByUsuarioId(int usuarioId);
}
