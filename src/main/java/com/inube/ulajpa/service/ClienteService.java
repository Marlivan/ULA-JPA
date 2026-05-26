package com.inube.ulajpa.service;

import com.inube.ulajpa.model.ClienteModel;
import com.inube.ulajpa.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.inube.ulajpa.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteModel guardar(ClienteModel cliente){

        return repository.save(cliente);
    }

    public List<ClienteModel> listar(){

        return repository.findByEstado(CODEPOS);
    }

    public ClienteModel buscarPorId(Integer id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                MSG15));
    }

    public ClienteModel actualizar(Integer id,
                              ClienteModel request){

        ClienteModel cliente = buscarPorId(id);

        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setTelefono(request.getTelefono());
        cliente.setCorreo(request.getCorreo());

        return repository.save(cliente);
    }

    public void eliminar(Integer id){

        ClienteModel cliente = buscarPorId(id);

        cliente.setEstado(CODENEG);

        repository.save(cliente);
    }
}
