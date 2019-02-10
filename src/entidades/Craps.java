package entidades;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Craps {
	
	//cria gerador de numeros aleatórios para uso no método rollDice
	public static final Random randomNumbers = new Random();
	
	//enumeração com constantes que representam o status do jogo
	private enum Status {CONTINUE,WON,LOST};
	
	private double bankBalance = 1000;
	//constantes que representam lançamentos comuns dos dados
	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
	private static final int BOX_CARS = 12;
	
	//joga uma partidade de craps
	public void play() {
		Locale.setDefault(Locale.US);
		int mypoint = 0; //pontos se não ganhar ou perder na primeira rolagem
		Status gameStatus; //pode conter CONTINUE,LOST,WON
		
		int sumOfDice = rollDice();//primeira rolagem dos dados
		
		//determina status do jogo e a pontuação com base no primeiro lançamento
		
		switch(sumOfDice) {
		case SEVEN:
		case YO_LEVEN:
		gameStatus = Status.WON;
		break;
		case SNAKE_EYES:
		case TREY:
		case BOX_CARS:
		gameStatus = Status.LOST;
		break;
		default:
		gameStatus = Status.CONTINUE;
		mypoint = sumOfDice;
		System.out.printf("\npoint is %d",mypoint);
		}
		while(gameStatus == Status.CONTINUE) {
			sumOfDice = rollDice();
			if(sumOfDice == mypoint)
				gameStatus = Status.WON;
			else if (sumOfDice == SEVEN)
				gameStatus = Status.LOST;
		}
		if(gameStatus == Status.WON) {
			bankBalance +=100;
			System.out.printf("\nPlayer wins");
			System.out.printf("\n%.2f",bankBalance);
			}
		else {
			bankBalance -=100;
			if(bankBalance == 0) {
				System.out.println("descupe você faliu!");
				System.out.printf("\nPlayer loses");}
			System.out.printf("\n%.2f",bankBalance);
			}}
		
	public int rollDice() {
		int face1 = 1+ randomNumbers.nextInt(6);
		int face2 = 1+ randomNumbers.nextInt(6);
		
		int sum = face1+face2;
		System.out.printf("\njogador jogou %d e %d sum = %d",face1,face2,sum);
		return sum;}
	
	public void Balance() {
		Scanner teclado =new Scanner(System.in);
		double wager = teclado.nextDouble();
		while(wager < bankBalance) {
			System.out.printf("valor inferior.Insira novamente:");
			wager = teclado.nextDouble();}
		bankBalance = wager;
		play();
		teclado.close();
	}

	}
	


