package br.gov.ce.seduc.prova.questao8.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.ce.seduc.prova.questao8.entity.Aluno;
import br.gov.ce.seduc.prova.questao8.exception.custom.BusinessException;
import br.gov.ce.seduc.prova.questao8.repository.AlunoRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

	private final AlunoRepository alunoRepository;
	
	public Optional<Aluno> getById(Integer id) {
		return alunoRepository.findById(id);
	}
	
	public Aluno salvar(Aluno aluno) {
		Optional<Aluno> buscarPorMatricula = buscarPorMatricula(aluno.getMatricula());
		if(buscarPorMatricula.isPresent())
			throw new BusinessException("Já existe um aluno para esta matricula.");
		
		return alunoRepository.save(aluno);
	}
	
	public Aluno atualizar(Integer id, Aluno aluno) throws NotFoundException {
		validarPacienteExiste(id);
		aluno.setId(id);
		return alunoRepository.save(aluno);
	}
	
	public void deletar(Integer id) throws NotFoundException {
		validarPacienteExiste(id);
		alunoRepository.deleteById(id);
	}
	
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}
	
	public Optional<Aluno> buscarPorMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	private void validarPacienteExiste(Integer id) throws NotFoundException {
		getById(id).orElseThrow(() ->  new NotFoundException("Paciente com id: " + id + " não encontrado."));
	}
}
