package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

import java.util.*;
	
public class Enquirer implements IEnquirer{

	IResponder responder;
	
	IObjetoConhecimento obj;
	
	public void connect(IResponder responder)
	{
//    	declaracao de variaveis auxiliares
		int i = 0, j = 0;
		int repitida = 0, percorre = 0;	
		
        IBaseConhecimento bc = new BaseConhecimento();
        
//    	vetor que contem os nomes dos animais dos arquivos .txt
        
        String[] names = bc.listaNomes();
		
//    	vetores de armazenamesnto de perguntas e respostas
		
		ArrayList<String> questions = new ArrayList<String>();
		ArrayList<String> answers = new ArrayList<String>();
		
//    	o obj vai sendo carregado com os animais dos arquivos ao longo do programa
		obj = bc.recuperaObjeto(names[i]);
		
		IDeclaracao decl = obj.primeira();
		
//    	loop que roda ate ser encontrado o animal, o que ocorre com o fim de declaracoes
		while (decl != null) {
			
			String pergunta = decl.getPropriedade();
			String respostaEsperada = decl.getValor();
			String resposta;
			
//    		checando se a pergunta em questao ja foi feita
			repitida = 0;
			for(percorre = 0; (percorre < j) && (repitida == 0); percorre++) {
				if(pergunta.equals(questions.get(percorre)))
					repitida = 1;
			}
			
//    		divisao em dois casos: pergunta repitida ou nao repitida
			if(repitida == 0) {
//    			adiciona a pergunta e a resposta aos vetores, alem de fazer a pergunta
			    questions.add(pergunta);
			    resposta = responder.ask(pergunta);
			    answers.add(resposta);
			    j++;
			}
			else {
//    			obtem a resposta no vetor answers da pergunta ja feita
				percorre--;
				resposta = answers.get(percorre);
			}
			
//    		caso a resposta seja a esperada, parte-se para a proxima pergunta
		    if (resposta.equals(respostaEsperada))
				decl = obj.proxima();
//    		caso contrario, parte-se para as perguntas do proximo animal do vetor nomes
			else {
				i++;
				obj = bc.recuperaObjeto(names[i]);
				decl = obj.primeira();
			}
		}
		
		boolean acertei = responder.finalAnswer(names[i]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

	}
  
}