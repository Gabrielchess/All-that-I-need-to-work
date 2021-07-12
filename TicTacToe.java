import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class TicTacToe {

	static ArrayList<Integer> posicaoJogador = new ArrayList<Integer>();
	static ArrayList<Integer> posicaoCPU = new ArrayList<Integer>();
	
	public static void main(String[] args) {

		char [][] tabuleiro = {{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		
		printTabuleiro(tabuleiro);

		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Escolha sua jogada (1-9):");
			int posJogador = scan.nextInt();
			
			while(posicaoJogador.contains(posJogador) || posicaoCPU.contains(posJogador)) {
				System.out.println("Posição ocupada, escolha outra jogada.");
				posJogador = scan.nextInt();
			}
						
			lugarPeca(tabuleiro, posJogador, "player");
			
			String result = checkWinner();
			if(result.length() > 0){
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int posCPU = rand.nextInt(9)+1;
			while(posicaoJogador.contains(posCPU) || posicaoCPU.contains(posCPU)) {
				posCPU = rand.nextInt(9)+1;
			}
			
			lugarPeca(tabuleiro, posCPU, "cpu");
	
			printTabuleiro(tabuleiro);
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
	}
	
	public static void lugarPeca(char[][] tabuleiro, int pos, String user) {
		
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			posicaoJogador.add(pos);
		}	else if(user.equals("cpu")) {
			symbol = '0';
			posicaoCPU.add(pos);
		}
		
		
		switch(pos) {
		case 1:
			tabuleiro[0][0] = symbol;
			break;
		case 2:
			tabuleiro[0][2] = symbol;
			break;
		case 3:
			tabuleiro[0][4] = symbol;
			break;
		case 4:
			tabuleiro[2][0] = symbol;
			break;
		case 5:			
			tabuleiro[2][2] = symbol;
			break;
		case 6:
			tabuleiro[2][4] = symbol;
			break;
		case 7:
			tabuleiro[4][0] = symbol;
			break;
		case 8:
			tabuleiro[4][2] = symbol;
			break;
		case 9:
			tabuleiro[4][4] = symbol;
			break;
		default:
			break;
		}
	}
	
	public static String checkWinner() {
		
		List linhaSuperior = Arrays.asList(1,2,3);
		List linhaMedia = Arrays.asList(4,5,6);
		List linhaInferior = Arrays.asList(7,8,9);
		List colEsquerda = Arrays.asList(1,4,7);
		List colMedia = Arrays.asList(2,5,8);
		List colDireita = Arrays.asList(3,6,9);
		List primeiraDiagonal = Arrays.asList(1,5,9);
		List segundaDiagonal = Arrays.asList(7,5,3);
		
		List<List> vencedor = new ArrayList<List>();
		vencedor.add(linhaSuperior);
		vencedor.add(linhaMedia);
		vencedor.add(linhaInferior);
		vencedor.add(colEsquerda);
		vencedor.add(colMedia);
		vencedor.add(colDireita);
		vencedor.add(primeiraDiagonal);
		vencedor.add(segundaDiagonal);
		
		for(List l : vencedor) {
			if(posicaoJogador.containsAll(l)) {
				return "Parabens, você ganhou!";
			} else if (posicaoCPU.containsAll(l)) {
				return "Computador venceu";
			} else if (posicaoJogador.size()+posicaoCPU.size() == 9) {
				return "Empate!";
			}
		}
		return "";
	}
	
	public static void printTabuleiro(char[][] tabuleiro) {
		for(char[] row : tabuleiro) {
			for(char c : row) {
				System.out.print(c);	
			}
			System.out.println();
		}
	}

}
