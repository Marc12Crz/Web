package com.example.demo.controller;

import com.example.demo.export.ExcelReportService;
import com.example.demo.export.PdfReportService;
import com.example.demo.model.Adopcion;
import com.example.demo.repository.AdopcionRepository;
import com.example.demo.service.AdopcionService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @Autowired
    private PdfReportService pdfReportService;

    @Autowired
    private ExcelReportService excelReportService;
    @Autowired
    private AdopcionRepository adopcionRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("adopciones", adopcionService.listarAdopciones());
        return "adopciones/listar";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("adopcion", new Adopcion());
        return "adopciones/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Adopcion adopcion) {
        adopcionService.guardarAdopcion(adopcion);
        return "redirect:/adopciones";
    }

    @GetMapping("/reporte-pdf")
    public void exportarListaAdopcionesPdf(HttpServletResponse response) throws DocumentException, IOException {
        List<Adopcion> adopciones = adopcionService.listarAdopciones();
        pdfReportService.exportarListaAdopcionesPdf(response, adopciones);
    }

    @GetMapping("/reporte-excel")
    public void exportarListaAdopcionesExcel(HttpServletResponse response) throws IOException {
        List<Adopcion> adopciones = adopcionService.listarAdopciones();
        excelReportService.exportarListaAdopcionesExcel(response, adopciones);
    }
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Adopcion adopcion = adopcionService.obtenerAdopcionPorId(id);
        if (adopcion != null) {
            model.addAttribute("adopcion", adopcion);
            return "adopciones/form";
        } else {
            return "redirect:/adopciones";
        }
    }
    public Adopcion obtenerAdopcionPorId(Long id) {
        return adopcionRepository.findById(id).orElse(null);
    }
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        adopcionService.eliminarAdopcion(id);
        return "redirect:/adopciones";
    }

}
