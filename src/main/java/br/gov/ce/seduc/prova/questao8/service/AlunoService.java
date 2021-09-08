package br.gov.ce.seduc.prova.questao8.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.gov.ce.seduc.prova.questao8.entity.Aluno;
import br.gov.ce.seduc.prova.questao8.repository.AlunoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

	private final AlunoRepository alunoRepository;
	

	private final ModelMapper modelMapper;
	
	public Optional<Aluno> getById(Integer id) {
		return alunoRepository.findById(id);
	}
	
	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public Aluno atualizar(Integer id, Aluno aluno) {
		Aluno alunoBD = alunoRepository.findById(id).get();
		
		Aluno atualizado = modelMapper.map(aluno, Aluno.class);
		atualizado.setId(alunoBD.getId());
		return alunoRepository.save(atualizado);
	}
	
	public void deletar(Integer id) {
		alunoRepository.deleteById(id);
	}
	
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}
	
	public Optional<Aluno> buscarPorMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
}
