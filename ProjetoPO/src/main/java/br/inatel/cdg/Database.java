package br.inatel.cdg;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private static final String HOTEL_FILE = "hotels.csv";
    private static final String PESSOA_FILE = "pessoas.csv";
    private static final String PESSOA_HOTEL_FILE = "pessoas_hoteis.csv";

    private boolean check = false;

    public boolean insertHotel(Hotel h) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOTEL_FILE, true))) {
            writer.write(h.getNome() + "," + h.getQntQuartos() + "\n");
            check = true;
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de hotel: " + e.getMessage());
            check = false;
        }
        return check;
    }

    public boolean insertPessoa(Pessoa p) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PESSOA_FILE, true))) {
            writer.write(p.getNome() + "," + p.getCpf() + "," + p.getOcupacao() + "," + p.getIdade() + "\n");
            check = true;
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de pessoa: " + e.getMessage());
            check = false;
        }
        return check;
    }

    public boolean insertPHotel(int idPessoa, int idHotel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PESSOA_HOTEL_FILE, true))) {
            writer.write(idPessoa + "," + idHotel + "\n");
            check = true;
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de pessoas_hoteis: " + e.getMessage());
            check = false;
        }
        return check;
    }

    public ArrayList<Hotel> researchHotel() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HOTEL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Hotel h = new Hotel();
                h.setNome(data[0]);
                h.setQntQuartos(Integer.parseInt(data[1]));
                hotels.add(h);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo de hotel: " + e.getMessage());
        }
        return hotels;
    }

    public ArrayList<Pessoa> researchHospede() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PESSOA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Pessoa p = new Pessoa();
                p.setNome(data[0]);
                p.setCpf(data[1]);
                p.setOcupacao(data[2]);
                p.setIdade(Integer.parseInt(data[3]));
                pessoas.add(p);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo de pessoa: " + e.getMessage());
        }
        return pessoas;
    }

    public boolean deletePessoa(int id) {
        boolean check = false;
        ArrayList<String> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PESSOA_HOTEL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int idPessoa = Integer.parseInt(data[0]);
                if (idPessoa == id) {
                    check = true; // Pessoa encontrada e marcada para exclusão
                    continue; // Pula a linha para não adicioná-la à lista de linhas
                }
                linhas.add(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo de pessoa: " + e.getMessage());
            return false; // Retorna false se houver erro na leitura do arquivo
        }

        // Escreve as linhas restantes de volta no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PESSOA_HOTEL_FILE))) {
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de pessoa: " + e.getMessage());
            return false; // Retorna false se houver erro na escrita do arquivo
        }

        return check; // Retorna true se a pessoa foi excluída com sucesso
    }
}
