package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<String> autenticar(@RequestBody Map<String, String> credenciales) {
        String usuario = credenciales.get("usuario");
        String clave = credenciales.get("clave");
        String resultado = usuarioService.autenticar(usuario, clave);
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/actualizar-clave")
    public ResponseEntity<String> actualizarClave(@RequestBody Map<String, String> datos) {
        String usuario = datos.get("usuario");
        String claveAntigua = datos.get("claveAntigua");
        String claveNueva = datos.get("claveNueva");
        String resultado = usuarioService.actualizarClave(usuario, claveAntigua, claveNueva);
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<String> asignarRol(@PathVariable Long id, @RequestBody Map<String, String> datos) {
        String tipo = datos.get("tipo");
        Usuario usuario = usuarioService.asignarRol(id, tipo);
        if (usuario != null) {
            return ResponseEntity.ok("Rol asignado correctamente");
        }
        return ResponseEntity.ok("Usuario no encontrado");
    }
}