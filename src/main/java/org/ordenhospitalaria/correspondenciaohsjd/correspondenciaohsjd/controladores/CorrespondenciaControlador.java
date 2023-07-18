package org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.modelos.Ci;
import org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.servicios.CiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/correspondencia-api/v1" })
@CrossOrigin(value = { "*" })
public class CorrespondenciaControlador {

    @Autowired
    private CiService ciService;

    @GetMapping("/")
    public ResponseEntity<?> obtenerUsuarios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "proveedor") String order,
            @RequestParam(defaultValue = "true") boolean asc) {
        Map<String, Object> mensaje = new HashMap<>();
        Page<Ci> usuarios = ciService.paginaCi(
                PageRequest.of(pagina, size, Sort.by(order)));
        if (!asc) {
            usuarios = ciService.paginaCi(
                    PageRequest.of(pagina, size, Sort.by(order).descending()));
        }
        mensaje.put("usuarios", usuarios);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/crear-libro-ci")
    public ResponseEntity<?> crearUsuario(@Validated @ModelAttribute Ci ci, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errores = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String campo = fieldError.getField();
                String mensaje = fieldError.getDefaultMessage();

                errores.computeIfAbsent(campo, key -> new ArrayList<>()).add(mensaje);
            }
            return ResponseEntity.badRequest().body(Collections.singletonMap("errores", errores));
        }

        Map<String, String> mensaje = new HashMap<>();
        try {
            ciService.crearCi(ci);
            mensaje.put("mensaje", "Usuario creado exitosamente");
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            mensaje.clear();
            mensaje.put("error", "Error al crear el usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }
    }

}
