package com.utp.technology.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.technology.http.dto.pedido.ListDetallePedidoDto;
import com.utp.technology.http.dto.pedido.ListPedidoAdmin;
import com.utp.technology.http.dto.pedido.ListPedidoCliente;
import com.utp.technology.http.dto.pedido.StorePedidoDto;
import com.utp.technology.model.Cliente;
import com.utp.technology.model.Comprobante;
import com.utp.technology.model.DetallePedido;
import com.utp.technology.model.Pedido;
import com.utp.technology.model.Producto;
import com.utp.technology.model.Usuario;
import com.utp.technology.repository.ClienteRepository;
import com.utp.technology.repository.ComprobanteRepository;
import com.utp.technology.repository.PedidoDetalleRepository;
import com.utp.technology.repository.PedidoRepository;
import com.utp.technology.repository.ProductoRepository;
import com.utp.technology.security.JwtUsuario;
import com.utp.technology.services.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

  private final ClienteRepository clienteRepository;

  private final ComprobanteRepository comprobanteRepository;

  private final PedidoRepository pedidoRepository;

  private final ProductoRepository productoRepository;

  private final PedidoDetalleRepository pedidoDetalleRepository;

  @Override
  public Optional<Pedido> findById(Integer id) {
    return this.pedidoRepository.findById(id);
  }

  @Override
  public Page<ListPedidoAdmin> listProductoAdmin(PageRequest pageReq) {
    return this.pedidoRepository.listProductoAdmin(pageReq);
  }

  @Override
  public Page<ListPedidoCliente> listProductoCustomer(Integer usuarioId, PageRequest pageReq) {
    return this.pedidoRepository.listProductoCustomer(usuarioId, pageReq);
  }

  @Override
  @Transactional
  public void guardarPedido(StorePedidoDto pedidoData, JwtUsuario jwtUsuario) {
    Usuario usuario = new Usuario();
    usuario.setId(jwtUsuario.getId());

    Optional<Cliente> cliente = this.clienteRepository.findByUsuario(usuario);
    List<Integer> idsProductos = pedidoData.getDetalles().stream().map(producto -> producto.getIdProducto()).toList();
    List<Producto> listProductos = this.productoRepository.findAllByIdIn(idsProductos);

    if (cliente.isEmpty()) {
      throw new IllegalArgumentException("No se encontró el cliente");
    }
    Pedido pedido = new Pedido();
    pedido.setCliente(cliente.get());
    pedido.setUsuario(usuario);
    pedido.setFecha(new Date());
    pedido.setEstado("Pendiente");

    this.pedidoRepository.save(pedido);

    List<DetallePedido> detalles = pedidoData.getDetalles().stream().map(item -> {
      DetallePedido detalle = new DetallePedido();

      var productoOpt = listProductos.stream().filter(producto -> producto.getId().equals(item.getIdProducto()))
          .findFirst();
      // Validando que el producto exista
      if (productoOpt.isEmpty()) {
        throw new IllegalArgumentException("No se encontró el producto");
      }
      // Validando stock
      if (item.getCantidad().compareTo(productoOpt.get().getStock()) > 0) {
        throw new IllegalArgumentException(
            "El producto " + productoOpt.get().getNombre() + " no tiene suficiente stock");
      }
      // Descontando stock
      productoOpt.get().setStock(productoOpt.get().getStock() - item.getCantidad());
      this.productoRepository.save(productoOpt.get());

      detalle.setPedido(pedido);
      detalle.setProducto(productoOpt.get());
      detalle.setCantidad(item.getCantidad());

      // Se va a actualizar mas adelante
      detalle.setPrecioUnitario(0D);
      return detalle;
    }).toList();

    this.pedidoDetalleRepository.saveAll(detalles);
    this.pedidoRepository.actualizarPrecioUnitarioDetalles(pedido.getId());
  }

  @Override
  public List<ListDetallePedidoDto> listPedidoDetalles(Integer pedidoId) {
    return this.pedidoDetalleRepository.listPedidoDetalles(pedidoId);
  }

  @Override
  public void actualizar(Pedido pedido) {
    this.pedidoRepository.save(pedido);
  }

  @Override
  @Transactional
  public void pagar(Pedido pedido) {
    var detalles = this.pedidoDetalleRepository.listPedidoDetalles(pedido.getId());
    Double total = detalles.stream().reduce(0.00, (sum, detalle) -> sum + detalle.getSubtotal(), Double::sum);
    Comprobante comprobante = new Comprobante();
    comprobante.setTipoComprobante("Boleta");
    comprobante.setFechaEmision(new Date());
    comprobante.setPedido(pedido);
    comprobante.setTotal(total);
    this.pedidoRepository.save(pedido);
    this.comprobanteRepository.save(comprobante);
  }

}
