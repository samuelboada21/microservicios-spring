
package com.usuario.service.repositorios;

import com.usuario.service.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
}
