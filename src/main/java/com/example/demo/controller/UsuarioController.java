package com.example.demo.controller;

import com.example.demo.export.ExcelReportService;
import com.example.demo.export.PdfReportService;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PdfReportService pdfReportService;

    @Autowired
    private ExcelReportService excelReportService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/listar";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/reporte-pdf")
    public void exportarListaUsuariosPdf(HttpServletResponse response) throws DocumentException, IOException {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        pdfReportService.exportarListaUsuariosPdf(response, usuarios);
    }

    @GetMapping("/reporte-excel")
    public void exportarListaUsuariosExcel(HttpServletResponse response) throws IOException {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        excelReportService.exportarListaUsuariosExcel(response, usuarios);
    }
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarios/form";
        } else {
            return "redirect:/usuarios";
        }
    }
}
