package ec.webmarket.restful.service;

import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String registrarUsuario(Usuario usuario) {
        // Validar si el usuario ya existe
        Usuario usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
        if (usuarioExistente != null) {
            return "Error: El nombre de usuario " + usuario.getUsuario() + " ya está en uso";
        }

        // Validar tipo de usuario
        if (usuario.getTipo() == null || (!usuario.getTipo().equals("PACIENTE") && !usuario.getTipo().equals("ODONTOLOGO"))) {
            return "Error: El tipo de usuario debe ser PACIENTE u ODONTOLOGO";
        }

        // Validar que la contraseña no esté vacía
        if (usuario.getClave() == null || usuario.getClave().trim().isEmpty()) {
            return "Error: La contraseña no puede estar vacía";
        }

        usuarioRepository.save(usuario);
        return "Usuario " + usuario.getUsuario() + " registrado exitosamente";
    }

    public String autenticar(String nombreUsuario, String clave) {
        Usuario usuario = usuarioRepository.findByUsuario(nombreUsuario);
        if (usuario == null) {
            return "Error: Usuario " + nombreUsuario + " no encontrado";
        }
        
        if (!usuario.getClave().equals(clave)) {
            return "Error: Contraseña incorrecta para el usuario " + nombreUsuario;
        }
        
        return "Login exitoso para el usuario " + nombreUsuario + " (Tipo: " + usuario.getTipo() + ")";
    }

    public String actualizarClave(String nombreUsuario, String claveAntigua, String claveNueva) {
        Usuario usuario = usuarioRepository.findByUsuario(nombreUsuario);
        if (usuario == null) {
            return "Error: Usuario " + nombreUsuario + " no encontrado";
        }
        
        // Validar contraseña antigua
        if (!usuario.getClave().equals(claveAntigua)) {
            return "Error: La contraseña actual es incorrecta para el usuario " + nombreUsuario;
        }

        // Validar que la nueva contraseña no esté vacía
        if (claveNueva == null || claveNueva.trim().isEmpty()) {
            return "Error: La nueva contraseña no puede estar vacía";
        }

        // Validar que la nueva contraseña no sea igual a la antigua
        if (claveAntigua.equals(claveNueva)) {
            return "Error: La nueva contraseña debe ser diferente a la actual";
        }
        
        usuario.setClave(claveNueva);
        usuarioRepository.save(usuario);
        return "Contraseña actualizada exitosamente para el usuario " + nombreUsuario + ". La nueva contraseña es: " + claveNueva;
    }

    public String asignarRol(Long id, String tipo) {
        // Validar tipo de usuario
        if (tipo == null || (!tipo.equals("PACIENTE") && !tipo.equals("ODONTOLOGO"))) {
            return "Error: El tipo debe ser PACIENTE u ODONTOLOGO";
        }

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return "Error: No se encontró usuario con ID " + id;
        }

        // Validar que el nuevo rol sea diferente al actual
        if (tipo.equals(usuario.getTipo())) {
            return "Error: El usuario ya tiene asignado el rol " + tipo;
        }

        usuario.setTipo(tipo);
        usuarioRepository.save(usuario);
        return "Rol actualizado exitosamente. Usuario " + usuario.getUsuario() + " ahora es " + tipo;
    }

    public String recuperarClave(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsuario(nombreUsuario);
        if (usuario == null) {
            return "Error: Usuario " + nombreUsuario + " no encontrado";
        }

        // Aquí podrías generar una contraseña temporal o enviar un correo
        String claveTemporal = generarClaveTemporal();
        usuario.setClave(claveTemporal);
        usuarioRepository.save(usuario);
        
        return "Se ha generado una nueva contraseña temporal para el usuario " + nombreUsuario + 
               ". Su nueva contraseña es: " + claveTemporal;
    }

    private String generarClaveTemporal() {
        // Generar una contraseña aleatoria de 8 caracteres
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = (int)(caracteres.length() * Math.random());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }

    public Usuario findByUsuario(String nombreUsuario) {
        return usuarioRepository.findByUsuario(nombreUsuario);
    }
}