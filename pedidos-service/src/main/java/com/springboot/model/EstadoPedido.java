package com.springboot.model;

public enum EstadoPedido {
    PENDIENTE,  // Cuando el pedido es creado pero aún no procesado
    EN_PROCESO,  // Cuando el pedido está siendo procesado
    ENTREGADO,  // Cuando el pedido ha sido entregado
    CANCELADO,  // Si el pedido fue cancelado
    COMPLETADO  // Si el pedido ha sido completado
}