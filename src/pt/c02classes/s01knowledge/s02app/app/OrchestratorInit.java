package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;
import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;


public class OrchestratorInit {
	
	public static void main(String[] args)
	{
		IBaseConhecimento base = new BaseConhecimento();
		
//		fazendo a leitura da escolha de jogo
		Scanner scanner = new Scanner(System.in);
		System.out.println("Para jogar Animals, digite A e para jogar Maze, digite M");
		String tString = scanner.next();
		char jogo = tString.charAt(0);
		
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		
//		fechando o scanner
		scanner.close();
		
//		switch para a escolha de jogos
		switch (jogo) {
			case 'A' :
				base.setScenario("animals");
				  
				String listaAnimais[] = base.listaNomes();
//		  rodando para todos os animais dos arquivos txt
			    for (int animal = 0; animal < listaAnimais.length; animal++) {
				   System.out.println("Enquirer com " + listaAnimais[animal] + "...");
				   stat = new Statistics();
				   enq = new EnquirerAnimals();
				   resp = new ResponderAnimals(stat, listaAnimais[animal]);
				   enq.connect(resp);
				   enq.discover();
				   System.out.println("----------------------------------------------------------------------------------------\n");
			   }  
				
				break;
			case 'M' :
				System.out.println("Enquirer com Mordor...");
				stat = new Statistics();
				resp = new ResponderMaze(stat, "mordor");
				enq = new EnquirerMaze();
				enq.connect(resp);
				enq.discover();
				System.out.println("----------------------------------------------------------------------------------------\n");
				
				break;
		}
	}
	
}
