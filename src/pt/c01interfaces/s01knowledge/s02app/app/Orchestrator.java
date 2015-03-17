package pt.c01interfaces.s01knowledge.s02app.app;

import java.util.Scanner;
import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.impl.Statistics;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;
import pt.c01interfaces.s01knowledge.s01base.inter.IStatistics;
import pt.c01interfaces.s01knowledge.s02app.actors.Enquirer;
import pt.c01interfaces.s01knowledge.s02app.actors.Responder;
import pt.c02classes.s01knowledge.s02app.app.OrchestratorInteractive;

public class Orchestrator
{
	public static void main(String[] args)
	{
		
		IBaseConhecimento base = new BaseConhecimento();
		
		String[] listaAnimais = base.listaNomes();
	
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		
//		leitura da escolha do jogo desejado
		Scanner scanner = new Scanner(System.in);
		System.out.println("Para jogar Animals, digite A e para jogar Maze, digite M");
		String tString = scanner.next();
		char jogo = tString.charAt(0);
		
		switch (jogo) {
			case 'A' :
				for (int animal = 0; animal < listaAnimais.length; animal++) {
					System.out.println("Enquirer com " + listaAnimais[animal] + "...");
					stat = new Statistics();
					enq = new Enquirer();
					resp = new Responder(stat, listaAnimais[animal]);
					enq.connect(resp);
					System.out.println("----------------------------------------------------------------------------------------\n");
		        }	
				break;
			case 'M' :		
		}
	}
}
