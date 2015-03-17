package pt.c02classes.s01knowledge.s02app.actors;

import java.util.ArrayList;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	// Declara��o do vetor que guardar� as coordenadas j� visitadas
	ArrayList<String> coordenadas = new ArrayList<String>();
	
	// Fun��o recursiva respons�vel por achar a sa�da do labirinto
	public boolean findaway(String move, int posx, int posy) {
		
		// Vari�veis booleanas que indicam se um movimento para dada dire��o foi poss�vel ou n�o
		boolean north = false, west = false, east = false, south = false, Final = false;
		
		// Adicionando a posi��o atual no vetor de coordenadas
		coordenadas.add(posx +  ";" + posy);		
			
		// Se chegamos na saida
		if(responder.ask("aqui").equalsIgnoreCase("saida")) {
			return true;
		}
		
		// Condicional respons�vel pela movimenta��o para o norte
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
	
		// Condicional respons�vel pela movimenta��o para o oeste
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
		
		// Condicional respons�vel pela movimenta��o para o leste
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
		
		// Condicional respons�vel pela movimenta��o para o sul
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
		//Casi n�o seja encontrada a saida
		return false;
	}	
}
