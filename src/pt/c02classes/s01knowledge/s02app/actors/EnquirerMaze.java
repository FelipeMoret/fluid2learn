package pt.c02classes.s01knowledge.s02app.actors;

import java.util.ArrayList;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	// Declaração do vetor que guardará as coordenadas já visitadas
	ArrayList<String> coordenadas = new ArrayList<String>();
	
	// Função recursiva responsável por achar a saída do labirinto
	public boolean findaway(String move, int posx, int posy) {
		
		// Variáveis booleanas que indicam se um movimento para dada direção foi possível ou não
		boolean north = false, west = false, east = false, south = false, Final = false;
		
		// Adicionando a posição atual no vetor de coordenadas
		coordenadas.add(posx +  ";" + posy);		
			
		// Se chegamos na saida
		if(responder.ask("aqui").equalsIgnoreCase("saida")) {
			return true;
		}
		
		// Condicional responsável pela movimentação para o norte
		if(north == false && west == false && east == false && south == false) { 
			if(((responder.ask("norte").equalsIgnoreCase("passagem")) || (responder.ask("norte").equalsIgnoreCase("saida"))) && (!((move).equalsIgnoreCase("sul")))) {
				if(!(coordenadas.contains(posx + ";" + posy + 1))) {
					responder.move("norte");
					north = findaway("norte", posx, posy + 1);
					if(north == false)
						responder.move("sul");
				}
			} 
		}
	
		// Condicional responsável pela movimentação para o oeste
		if(north == false && west == false && east == false && south == false){
			if(((responder.ask("oeste").equalsIgnoreCase("passagem")) || (responder.ask("oeste").equalsIgnoreCase("saida"))) && (!((move).equalsIgnoreCase("leste")))) {
				if(!(coordenadas.contains(posx + (-1) + ";" + posy))) {
					responder.move("oeste");
					west = findaway("oeste", posx - 1, posy);
					if(west == false)
						responder.move("leste");
				}
			} 
		}
		
		// Condicional responsável pela movimentação para o leste
		if(north == false && west == false && east == false && south == false){
			if(((responder.ask("leste").equalsIgnoreCase("passagem")) || (responder.ask("leste").equalsIgnoreCase("saida"))) && (!((move).equalsIgnoreCase("oeste")))) {
				if(!(coordenadas.contains(posx + 1 + ";" + posy))) {
					responder.move("leste");
					east = findaway("leste", posx + 1, posy);
					if(east == false)
						responder.move("oeste");
				}
			}
		}
		
		// Condicional responsável pela movimentação para o sul
		if(north == false && west == false && east == false && south == false){
			if(((responder.ask("sul").equalsIgnoreCase("passagem")) || (responder.ask("sul").equalsIgnoreCase("saida"))) && (!((move).equalsIgnoreCase("norte")))) {
				if(!(coordenadas.contains(posx + ";" + posy + (-1)))) {
					responder.move("sul");
					south = findaway("sul", posx, posy - 1);
					if(south == false)
						responder.move("norte");
				}
			} 
		}
			
		
		// Verificando se houve algum movimento bem sucedido
		Final = (north || west || east || south);
		
		return (Final);
		
	}
	
	public boolean discover() {
		// Caso seja encontrada a saida
		if(findaway("inicial", 0,0) == true) {
			System.out.println("Parabens, voce encontrou a saida");
			
			return true;
		}
		//Casi não seja encontrada a saida
		return false;
	}	
}
