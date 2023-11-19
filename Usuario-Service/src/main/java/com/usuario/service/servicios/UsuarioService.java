
package com.usuario.service.servicios;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repositorios.UsuarioRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Samuel
 */
@Service
public class UsuarioService {
    
    @Autowired
    private MotoFeignClient motoFeignClient;
    
    @Autowired
    private CarroFeignClient carroFeignClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://carro-service/carro/usuario/" + usuarioId, List.class);
        return carros;
    }
    
    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://moto-service/moto/usuario/" + usuarioId, List.class);
        return motos;
    }
    
    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }
    
    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto = motoFeignClient.save(moto);
        return nuevoMoto;
    }
    
    public Map<String, Object> getUsuariosAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        
        if(usuario==null){
            resultado.put("Mensaje","El usuario no existe");
        }
        resultado.put("Usuario",usuario);
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put("Carros", "El usuario no tiene carros");
        }else{
            resultado.put("Carros", carros);
        }
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos", "El usuario no tiene motos");
        }else{
            resultado.put("Motos", motos);
        }
        return resultado;
    }
    
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    
    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    
    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
}
