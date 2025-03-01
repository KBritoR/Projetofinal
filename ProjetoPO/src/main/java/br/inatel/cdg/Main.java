package br.inatel.cdg;

import java.util.Scanner;

public class Main {

    public static void main(String[] Args){

        Database data = new Database();

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        Hospede hos = new Hospede();
        boolean aux = true;

        while(aux){

            System.out.println("Bem vindo ao Hotel-List!");
            System.out.println("1 - Cadastrar hotel");
            System.out.println("2 - Cadastrar Hospede");
            System.out.println("3 - Cadastrar Empregado");
            System.out.println("4 - Listar Hoteis");
            System.out.println("5 - Fazer check-in");
            System.out.println("6 - Lista de pessoas em Hoteis");
            System.out.println("7 - Fazer check-out");
            System.out.println("8 - Sair");
            System.out.println("");
            int menu = sc.nextInt();

            switch(menu){
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o nome do Hotel: ");
                    hotel.setNome(sc.nextLine());
                    System.out.println("Quantos quartos o hotel possui?");
                    hotel.setQntQuartos(sc.nextInt());
                    data.insertHotel(hotel);
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Entre com o nome do hospede: ");
                    String nome = sc.nextLine();
                    System.out.println("Entre com o CPF do hospede: ");
                    String cpf = sc.nextLine();
                    System.out.println("Entre com a idade do hospede: ");
                    int idade = sc.nextInt();
                    Pessoa hospede = new Hospede(nome, cpf,"Hospede",idade);
                    data.insertPessoa(hospede);
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Entre com o nome do empregado: ");
                    String n = sc.nextLine();
                    System.out.println("Entre com o CPF do empregado: ");
                    String cpf1 = sc.nextLine();
                    System.out.println("Entre com a idade do empregado: ");
                    int i = sc.nextInt();
                    Pessoa empregado = new Empregado(n, cpf1,"Empregado",i);
                    data.insertPessoa(empregado);
                    break;
                case 4:
                    System.out.println("Hoteis disponiveis: ");
                    data.researchHotel().forEach(h -> {
                        System.out.println("ID: " + h.getQntQuartos());
                        System.out.println("Nome Hotel: " + h.getNome());
                        System.out.println("Quartos: " + h.getQntQuartos());
                    });

                    break;
                case 5:
                    System.out.println("Insira o id do Hospede e o id do Hotel: ");
                    data.insertPHotel(sc.nextInt(),sc.nextInt());
                    hos.fazCheckIn();
                    break;
                case 6:
                    System.out.println("Pessoas para check-in: ");
                    data.researchHospede().forEach(p -> {
                        System.out.println("ID: " + p.getIdPessoa());
                        System.out.println("Nome: " + p.getNome());
                        System.out.println("CPF: " + p.getCpf());
                        System.out.println("Ocupação: " + p.getOcupacao());
                        System.out.println("Idade: " + p.getIdade());
                    });
                    break;
                case 7:
                    System.out.println("Insira o ID de quem ira fazer check-out");
                    data.deletePessoa(sc.nextInt());
                    hos.fazCheckOut();
                    break;

                case 8:
                    aux = false;
                    break;
            }

        }


    }
}