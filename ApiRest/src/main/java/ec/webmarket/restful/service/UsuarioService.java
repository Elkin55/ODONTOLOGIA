package ec.webmarket.restful.service;

import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Aquí iría la lógica de validación y registro
        return usuarioRepository.save(usuario);
    }

    public String autenticar(Usuario usuario) {
        // Aquí iría la lógica de autenticación
        return "autenticado";
    }

    public Usuario asignarRol(Long id, String tipo) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setTipo(tipo);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarClave(Long id, String nuevaClave) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setClave(nuevaClave);
        return usuarioRepository.save(usuario);
    }

    public void recuperarClave(String usuario) {
        // Aquí iría la lógica de recuperación de contraseña
    }
}