package sprint02ex03.entities;

import org.apache.commons.lang3.StringUtils;

public class Mensagem {

    private static int calculaOcorrenciasEncontradas(String mensagem, String pattern){
        return StringUtils.countMatches(mensagem, pattern);
    }


    public static void validaSentimento(String mensagem) {
        mensagem = mensagem.replace(')', '*').replace('(','&');
        int contaTriste = calculaOcorrenciasEncontradas(mensagem, ":&");
        int contaFeliz = calculaOcorrenciasEncontradas(mensagem, ":*");
        if (contaFeliz == contaTriste) {
            System.out.println("Neutro - " + contaFeliz + " - " + contaTriste);
        } else if(contaFeliz > contaTriste) {
            System.out.println("Divertido - " + contaFeliz + " - " + contaTriste);
        } else {
            System.out.println("Chateado - " + contaFeliz + " - " + contaTriste);
        }
    }
}
