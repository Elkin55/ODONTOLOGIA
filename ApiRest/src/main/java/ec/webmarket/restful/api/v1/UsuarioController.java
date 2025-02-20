package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody Usuario usuario) {
        usuarioService.autenticar(usuario);
        return ResponseEntity.ok("Login exitoso");
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<String> asignarRol(@PathVariable Long id, @RequestParam String tipo) {
        usuarioService.asignarRol(id, tipo);
        return ResponseEntity.ok("Rol asignado correctamente");
    }

    @PutMapping("/{id}/clave")
    public ResponseEntity<String> actualizarClave(@PathVariable Long id, @RequestBody String nuevaClave) {
        usuarioService.actualizarClave(id, nuevaClave);
        return ResponseEntity.ok("Contraseña actualizada exitosamente");
    }

    @PostMapping("/recuperar-clave")
    public ResponseEntity<String> recuperarClave(@RequestParam String usuario) {
        return ResponseEntity.ok("Se ha iniciado el proceso de recuperación de contraseña");
    }
}