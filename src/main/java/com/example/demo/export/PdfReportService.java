package com.example.demo.export;

import com.example.demo.model.Adopcion;
import com.example.demo.model.FormularioAdopcion;
import com.example.demo.model.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PdfReportService {

    public void exportarListaUsuariosPdf(HttpServletResponse response, List<Usuario> usuarios) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        PdfPTable table = new PdfPTable(5);
        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Correo");
        table.addCell("Dirección");
        table.addCell("Teléfono");

        for (Usuario usuario : usuarios) {
            table.addCell(usuario.getIdUsuario().toString());
            table.addCell(usuario.getNombre());
            table.addCell(usuario.getCorreo());
            table.addCell(usuario.getDireccion());
            table.addCell(usuario.getTelefono());
        }

        document.add(table);
        document.close();
    }

    public void exportarListaAdopcionesPdf(HttpServletResponse response, List<Adopcion> adopciones) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=adopciones.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        PdfPTable table = new PdfPTable(5);
        table.addCell("ID");
        table.addCell("ID Usuario");
        table.addCell("ID Mascota");
        table.addCell("Fecha de Solicitud");
        table.addCell("Estado");

        for (Adopcion adopcion : adopciones) {
            table.addCell(adopcion.getIdAdopcion().toString());
            table.addCell(adopcion.getIdUsuario().toString());
            table.addCell(adopcion.getIdMascota().toString());
            table.addCell(adopcion.getFechaSolicitud().toString());
            table.addCell(adopcion.getEstadoSolicitud());
        }

        document.add(table);
        document.close();
    }
    public void exportarListaFormulariosPdf(HttpServletResponse response, List<FormularioAdopcion> formularios) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=formularios.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        PdfPTable table = new PdfPTable(3); // Número de columnas
        table.addCell("ID");
        table.addCell("ID Albergue");
        table.addCell("Pregunta");

        for (FormularioAdopcion formulario : formularios) {
            table.addCell(formulario.getIdFormulario().toString());
            table.addCell(formulario.getIdAlbergue().toString());
            table.addCell(formulario.getPregunta());
        }

        document.add(table);
        document.close();
    }
}
