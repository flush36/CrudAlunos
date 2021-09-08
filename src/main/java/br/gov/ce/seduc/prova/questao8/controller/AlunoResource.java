package br.gov.ce.seduc.prova.questao8.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.gov.ce.seduc.prova.questao8.entity.Aluno;
import br.gov.ce.seduc.prova.questao8.service.AlunoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "seduc/aluno")
@AllArgsConstructor
public class AlunoResource {


	private final AlunoService alunoService;
	
	@PostMapping
	public ResponseEntity<Aluno> salvar(@Valid @RequestBody Aluno aluno) {
		return ResponseEntity.ok(alunoService.salvar(aluno));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		alunoService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		alunoService.deletar(id);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Integer id, @RequestBody Aluno aluno) {
		alunoService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(alunoService.atualizar(id, aluno));
	}
	
	@GetMapping("buscar-todos")
	public ResponseEntity<List<Aluno>> buscarTodos() {
		return ResponseEntity.ok(alunoService.listar());
	}
	
	@GetMapping("buscar-por-matricula")
	public ResponseEntity<Aluno> buscarAlunoPorMatricula(@RequestParam("matricula") String matricula) {
		 Aluno aluno = alunoService.buscarPorMatricula(matricula)
				 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
		 return ResponseEntity.ok(aluno);
	}
	
	
}
