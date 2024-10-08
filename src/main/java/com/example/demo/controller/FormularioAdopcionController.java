package com.example.demo.controller;

import com.example.demo.export.ExcelReportService;
import com.example.demo.export.PdfReportService;
import com.example.demo.model.FormularioAdopcion;
import com.example.demo.service.FormularioAdopcionService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/formularios")
public class FormularioAdopcionController {

    @Autowired
    private FormularioAdopcionService formularioAdopcionService;

    @Autowired
    private PdfReportService pdfReportService;

    @Autowired
    private ExcelReportService excelReportService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("formularios", formularioAdopcionService.listarFormularios());
        return "formularios/listar";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("formulario", new FormularioAdopcion());
        return "formularios/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute FormularioAdopcion formulario) {
        formularioAdopcionService.guardarFormulario(formulario);
        return "redirect:/formularios";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        formularioAdopcionService.eliminarFormulario(id);
        return "redirect:/formularios";
    }

    @GetMapping("/reporte-pdf")
    public void exportarListaFormulariosPdf(HttpServletResponse response) throws DocumentException, IOException {
        List<FormularioAdopcion> formularios = formularioAdopcionService.listarFormularios();
        pdfReportService.exportarListaFormulariosPdf(response, formularios);
    }

    @GetMapping("/reporte-excel")
    public void exportarListaFormulariosExcel(HttpServletResponse response) throws IOException {
        List<FormularioAdopcion> formularios = formularioAdopcionService.listarFormularios();
        excelReportService.exportarListaFormulariosExcel(response, formularios);
    }
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        FormularioAdopcion formulario = formularioAdopcionService.obtenerFormularioPorId(id);
        if (formulario != null) {
            model.addAttribute("formulario", formulario);
            return "formularios/form";
        } else {
            return "redirect:/formularios";
        }
    }

}
