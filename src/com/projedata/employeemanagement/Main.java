package com.projedata.employeemanagement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    	
        // 3.1 – Inserir todos os funcionários
    	
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Remover o funcionário “João”
        
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com formatação
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = new DecimalFormat("#,##0.00");

        System.out.println("Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Data Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                    ", Salário: " + numberFormat.format(funcionario.getSalario()) +
                    ", Função: " + funcionario.getFuncao());
        }

        // 3.4 – Aumento de 10% no salário
        
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10"))));

        // 3.5 – Agrupar funcionários por função
        
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(funcionario -> System.out.println("  Nome: " + funcionario.getNome()));
        });

        // 3.8 – Imprimir funcionários que fazem aniversário no mês 10 e 12
        
        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.getDataNascimento().format(dateFormatter)));

        // 3.9 – Imprimir o funcionário com a maior idade
        
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();

        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\nFuncionário mais velho: Nome: " + maisVelho.getNome() + ", Idade: " + idade + " anos");

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        
        System.out.println("\nFuncionários por ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome()));

        // 3.11 – Imprimir o total dos salários dos funcionários
        
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + numberFormat.format(totalSalarios));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em termos de salários mínimos:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + funcionario.getNome() + ", Salários Mínimos: " + salariosMinimos);
        });
        
    }
}