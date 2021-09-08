package br.gov.ce.seduc.prova.questao7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		
		lista1.add("Java");
		lista1.add("Phyton");
		lista1.add("C#");
		lista1.add("SQL");
		
		lista2.add("Java");
		lista2.add("C#");
		lista2.add("Ruby");
		lista2.add("Delphi");
		
		System.out.println(concatLists(lista1, lista2));
	}
	
	public static List<String> concatLists(List<String> lista1, List<String> lista2) {
		return Stream.of(lista1, lista2)
				.flatMap(List::stream)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
	
}
