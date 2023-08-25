import entities.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        List<Produto> lista = new ArrayList<>();

        System.out.print("Digite o caminho do arquivo CSV: ");
        String caminhoArquivo = sc.nextLine().strip();
        System.out.println();

        File arquivo = new File(caminhoArquivo);
        String pasta = arquivo.getParent();

        boolean pastaCriada = new File(pasta + "\\out").mkdir();

        String arquivoDestino = pasta + "\\out\\summary.CSV";


        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String itemCsv = br.readLine();

            while (itemCsv != null) {
                String[] campo = itemCsv.split(",");


                String nome = campo[0];
                double preco = Double.parseDouble(campo[1]);
                int quantidade = Integer.parseInt(campo[2]);

                lista.add(new Produto(nome, preco, quantidade));

                itemCsv = br.readLine();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDestino))) {
                for (Produto x : lista) {
                    bw.write(x.getNome() + ", " + String.format(" R$%.2f", x.total()));
                    bw.newLine();
                }

                System.out.println("Arquivo criado");

            } catch (IOException e) {
                System.out.println("ERROR! " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("ERROR! " + e.getMessage());
        } catch (IndexOutOfBoundsException e){
            System.out.println("ERROE! " + e.getMessage());
        }

        sc.close();
    }
}