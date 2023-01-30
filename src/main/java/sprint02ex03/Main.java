package sprint02ex03;

import sprint02ex03.entities.Mensagem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma frase: \n-> ");
        String frase = scanner.nextLine();
        Mensagem.validaSentimento(frase);
    }
}

