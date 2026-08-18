package com.f1proyect.dominio.puertos.out;

import com.f1proyect.dominio.modelos.Piloto;
import java.util.List;
import java.util.Optional;

public interface PilotoRepositorio {
    Piloto guardar(Piloto piloto);
    Optional<Piloto> buscarPorId(int id);
    List<Piloto> obtenerTodos(); // Verificar que este método exista con este nombre exacto
    void eliminar(int id);
}
/* define qué operaciones de datos necesita la aplicación
 para gestionar pilotos (crear/actualizar, buscar, listar y eliminar),
 sin importar si en el futuro se guardan en un HashMap en memoria,
  un archivo JSON o una base de datos SQL.
  
  
  -- El punto de usar Optional<Piloto> en la búsqueda por ID es 
  -- evitar el famoso NullPointerException (error de puntero nulo).*/ 