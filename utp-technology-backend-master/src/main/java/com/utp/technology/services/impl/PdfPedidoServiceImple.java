package com.utp.technology.services.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.utp.technology.services.PdfPedidoService;
import com.utp.technology.services.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfPedidoServiceImple implements PdfPedidoService {

  private final PedidoService pedidoService;

  @Override
  public byte[] generarComprobantePdf(Integer pedidoId) {

    var pedidoOpt = this.pedidoService.findById(pedidoId);

    if (pedidoOpt.isEmpty()) {
      return null;
    }

    var detalles = this.pedidoService.listPedidoDetalles(pedidoId);

    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      PdfWriter writer = new PdfWriter(byteArrayOutputStream);
      PdfDocument pdfDocument = new PdfDocument(writer);
      Document document = new Document(pdfDocument);
      document.setLeftMargin(25);
      document.setRightMargin(25);

      String nro = String.format("%05d", pedidoOpt.get().getId());
      Paragraph titulo = new Paragraph("COMPROBANTE N° " + nro).setTextAlignment(TextAlignment.CENTER);
      document.add(titulo);

      // Fecha / Cliente
      var fechaP = new Paragraph("Fecha: " + pedidoOpt.get().getFecha().toString())
          .setTextAlignment(TextAlignment.LEFT);
      document.add(fechaP);

      var clienteP = new Paragraph("Cliente: " + pedidoOpt.get().getCliente().getNombre())
          .setTextAlignment(TextAlignment.LEFT);
      document.add(clienteP);

      var separatorP = new Paragraph(
          "----------------------------------------------------------------------------------------------------------------------------------------")
          .setTextAlignment(TextAlignment.LEFT);
      document.add(separatorP);

      Table table = new Table(5);
      table.setWidth(UnitValue.createPercentValue(100));

      table.addHeaderCell(new Paragraph("#").setTextAlignment(TextAlignment.CENTER));
      table.addHeaderCell(new Paragraph("Producto").setTextAlignment(TextAlignment.CENTER));
      table.addHeaderCell(new Paragraph("Precio").setTextAlignment(TextAlignment.CENTER));
      table.addHeaderCell(new Paragraph("Cantidad").setTextAlignment(TextAlignment.CENTER));
      table.addHeaderCell(new Paragraph("Total Detalle").setTextAlignment(TextAlignment.CENTER));

      AtomicInteger index = new AtomicInteger(0);
      List<String[]> list = detalles.stream().map(detalle -> {
        Integer currentIndex = index.incrementAndGet();
        return new String[] {
            currentIndex.toString(), // #
            detalle.getProducto(), // Producto
            "S/ " + detalle.getPrecioUnitario().toString(), // Precio
            detalle.getCantidad().toString(), // Cantidad
            "S/ " + detalle.getSubtotal().toString(), // Total detalle
        };
      }).toList();

      for (var item : list) {
        table.addCell(new Paragraph(item[0]).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Paragraph(item[1]));
        table.addCell(new Paragraph(item[2]).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Paragraph(item[3]).setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Paragraph(item[4]).setTextAlignment(TextAlignment.CENTER));
      }

      document.add(table);

      document.add(separatorP);

      // TOTAL
      Double total = detalles.stream().reduce(0.0, (sum, detalle) -> {
        return sum + detalle.getSubtotal();
      }, Double::sum);
      var totalP = new Paragraph("Total: " + total).setTextAlignment(TextAlignment.RIGHT);
      document.add(totalP);

      document.close();

      return byteArrayOutputStream.toByteArray();
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

}
