package com.reservas.CruzDelSur.service;

import com.reservas.CruzDelSur.entity.Usuario;
import com.reservas.CruzDelSur.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Inyectamos el encriptador que configuramos en SecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        // Encriptar la contraseña plana antes de enviarla a la base de datos
        String hash = passwordEncoder.encode(usuario.getContrasena_hash());
        usuario.setContrasena_hash(hash);

        return repository.save(usuario);
    }

    @Transactional
    public Usuario actualizar(Integer id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombres(usuarioActualizado.getNombres());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setRol(usuarioActualizado.getRol());

        // Solo actualizar y encriptar la contraseña si el usuario envía una nueva
        if (usuarioActualizado.getContrasena_hash() != null && !usuarioActualizado.getContrasena_hash().isEmpty()) {
            String hash = passwordEncoder.encode(usuarioActualizado.getContrasena_hash());
            usuarioExistente.setContrasena_hash(hash);
        }

        return repository.save(usuarioExistente);
    }

    @Transactional
    public void eliminar(Integer id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        repository.delete(usuario);
    }
}
