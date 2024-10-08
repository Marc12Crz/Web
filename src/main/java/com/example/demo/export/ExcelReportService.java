package com.example.demo.export;

import com.example.demo.model.Adopcion;
import com.example.demo.model.FormularioAdopcion;
import com.example.demo.model.Usuario;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelReportService {

    // Método para exportar la lista de usuarios (ya proporcionado)
    public void exportarListaUsuariosExcel(HttpServletResponse response, List<Usuario> usuarios) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Usuarios");
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Nombre");
        header.createCell(2).setCellValue("Correo");
        header.createCell(3).setCellValue("Dirección");
        header.createCell(4).setCellValue("Teléfono");

        int rowNum = 1;
        for (Usuario usuario : usuarios) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(usuario.getIdUsuario());
            row.createCell(1).setCellValue(usuario.getNombre());
            row.createCell(2).setCellValue(usuario.getCorreo());
            row.createCell(3).setCellValue(usuario.getDireccion());
            row.createCell(4).setCellValue(usuario.getTelefono());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    // Método para exportar la lista de adopciones
    public void exportarListaAdopcionesExcel(HttpServletResponse response, List<Adopcion> adopciones) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=adopciones.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Adopciones");
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("ID Usuario");
        header.createCell(2).setCellValue("ID Mascota");
        header.createCell(3).setCellValue("Fecha de Solicitud");
        header.createCell(4).setCellValue("Estado");

        int rowNum = 1;
        for (Adopcion adopcion : adopciones) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(adopcion.getIdAdopcion());
            row.createCell(1).setCellValue(adopcion.getIdUsuario());
            row.createCell(2).setCellValue(adopcion.getIdMascota());
            row.createCell(3).setCellValue(adopcion.getFechaSolicitud().toString());
            row.createCell(4).setCellValue(adopcion.getEstadoSolicitud());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
    public void exportarListaFormulariosExcel(HttpServletResponse response, List<FormularioAdopcion> formularios) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=formularios.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Formularios");
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("ID Albergue");
        header.createCell(2).setCellValue("Pregunta");

        int rowNum = 1;
        for (FormularioAdopcion formulario : formularios) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(formulario.getIdFormulario());
            row.createCell(1).setCellValue(formulario.getIdAlbergue());
            row.createCell(2).setCellValue(formulario.getPregunta());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
