package br.com.systemsgs.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.systemsgs.dto.ModelClienteConsumerDTO;

@FeignClient(name = "client-consumer", url = "localhost:8888/api/v1/clientes/")
public interface ClienteConsumerClient {
	
	@GetMapping(value = "/listaTodos")
	List<ModelClienteConsumerDTO> listaTodosClientes();

}