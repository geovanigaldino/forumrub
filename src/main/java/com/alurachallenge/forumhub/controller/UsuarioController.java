package com.alurachallenge.forumhub.controller;


import com.alurachallenge.forumhub.dto.*;
import com.alurachallenge.forumhub.entity.Usuario;
import com.alurachallenge.forumhub.repository.UsuarioRepository;
import com.alurachallenge.forumhub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService service;


    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO dto, UriComponentsBuilder uriComponentsBuilder) {
       var usuario = service.cadastrarUsuario(dto);

       var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UsuarioResponseDTO>  atualizarUsuario(@PathVariable Long id, @RequestBody AtualizarUsuarioRequestDTO dto){
        var usuario = service.atualizar(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<UsuarioResponseDTO>> listarUsuarios(Pageable paginacao){
      var page = service.listarUsuario(paginacao);
      return ResponseEntity.ok(page);
    }




}
