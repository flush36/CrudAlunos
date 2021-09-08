package br.gov.ce.seduc.prova.questao8.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.ce.seduc.prova.questao8.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

	Optional<Aluno> findByMatricula(String matricula);
}
