package com.rbservicios.DemoSpring.services;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
