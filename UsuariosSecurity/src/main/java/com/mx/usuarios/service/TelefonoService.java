package com.mx.usuarios.service;

import java.util.*;

import com.mx.usuarios.dao.iTelefonoDao;
import com.mx.usuarios.dominio.Telefono;
import com.mx.usuarios.dto.TelefonoRequestDTO;
import com.mx.usuarios.dto.TelefonoResponseDTO;
import com.mx.usuarios.exceptions.TelefonoDataInvalidException;
import com.mx.usuarios.exceptions.TelefonoDuplicadoException;
import com.mx.usuarios.exceptions.TelefonoNotFoundException;
import org.modelmapper.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class TelefonoService {

    @Autowired
    private iTelefonoDao dao;

    @Autowired
    private ModelMapper mapper;

    public List<TelefonoResponseDTO> listar(){
        return dao.findAll().stream().map(tel -> mapper.map(tel, TelefonoResponseDTO.class)).toList();
    }

    public TelefonoResponseDTO guardar(TelefonoRequestDTO dto) {
        Telefono tel = mapper.map(dto, Telefono.class);

        if(dao.existsById(tel.getId())) {
            throw new TelefonoDuplicadoException("Ese ID ya esta en uso, intenta con otro.");
        }

        if(tel.getPrecio() < 1000) {
            throw new TelefonoDataInvalidException("El precio tiene que ser mayor de $1,000.");
        }

        if(tel.getMarca().equals("")) {
            throw new TelefonoDataInvalidException("La marca es obligatoria");
        }

        if(tel.getCategoria().equals("")) {
            throw new TelefonoDataInvalidException("La categoría es obligatoria");
        }
        return mapper.map(dao.save(tel), TelefonoResponseDTO.class);
    }

    public TelefonoResponseDTO buscar(int id) {
        Telefono tel = dao.findById(id)
                .orElseThrow(() ->
                        new TelefonoNotFoundException("Telefono no encontrado con el id: " + id));

        return mapper.map(tel, TelefonoResponseDTO.class);
    }

    public TelefonoResponseDTO editar(int id, TelefonoRequestDTO dto) {
        Telefono tel = dao.findById(id)
                .orElseThrow(() ->
                        new TelefonoNotFoundException("Telefono no encontrado con el id:" + id));

        if(dto.getPrecio() < 1000) {
            throw new TelefonoDataInvalidException("El precio tiene que ser mayor de $1,000.");
        }

        tel.setMarca(dto.getMarca());
        tel.setCategoria(dto.getCategoria());
        tel.setPrecio(dto.getPrecio());

        return mapper.map(dao.save(tel), TelefonoResponseDTO.class);
    }

    public void eliminar(int id) {
        if(!dao.existsById(id)) {
            throw new TelefonoNotFoundException("Télefono no encontrado con el id: " + id);
        }
        dao.deleteById(id);
    }

    public TelefonoResponseDTO buscarMarca(String marca) {
        Telefono tel = dao.findByMarcaIgnoringCase(marca);
        if(tel==null){
            throw new TelefonoNotFoundException("Telefono no encontrado con la marca: " + marca);
        }
        return mapper.map(tel, TelefonoResponseDTO.class);
    }

    public List<TelefonoResponseDTO> buscarCategoria(String categoria){
        return dao.findByCategoriaIgnoringCase(categoria)
                .stream().map(tel -> mapper.map(tel, TelefonoResponseDTO.class)).toList();
    }
}
