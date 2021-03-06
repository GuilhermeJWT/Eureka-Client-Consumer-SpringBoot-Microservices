package br.com.systemsgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.client.ClienteConsumerClient;
import br.com.systemsgs.dto.ModelClienteConsumerDTO;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping(value = "/api/consumer")
public class ConsumerController {
	
	@Autowired
	private ClienteConsumerClient clienteConsumerClient;
	
	@PostMapping(value = "/salvar")
	public String salvaCliente(@RequestBody ModelClienteConsumerDTO modelClienteConsumerDTO){
		clienteConsumerClient.salvaCliente(modelClienteConsumerDTO);
		
		return "Opa! Salvou o Cliente com Sucesso na Api Payment-Service";
	}
	
	@GetMapping(value = "/listar")
	public List<ModelClienteConsumerDTO> listTodosClientes(){
		try {
			return clienteConsumerClient.listaTodosClientes();
		}catch(FeignClientException exception) {
			exception.printStackTrace();
			return new ArrayList<ModelClienteConsumerDTO>();
		}
	}
	
	@GetMapping(value = "/pesquisaPorId/{id}")
	public ResponseEntity<ModelClienteConsumerDTO> pesquisaPorId(@PathVariable("id") Long id) {
		ModelClienteConsumerDTO clientePesquisado = clienteConsumerClient.pesquisaPorId(id);
		
		return ResponseEntity.ok(clientePesquisado);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	public String deletarCliente(@PathVariable("id") Long id) {
		clienteConsumerClient.deletaCliente(id);
		
		return "Cliente Removido com Sucesso na Api Payment-Service";
	}
	
}
