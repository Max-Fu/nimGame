import java.util.*;
import java.util.Scanner;

class nimGame {
	public static void main(String[] args) {
		//***Should be less than ten lines
		//Move the following into separate functions
		Scanner sc = new Scanner(System.in);
		//input initialization
		System.out.println("Welcome to the name of Nim.\nAnd this is a variation of it.");
		//Welcome sentenses
		System.out.println("Please type \"yes\" if you want to go first, and type \"no\" if you want to go second");
		String firstOrSecond = sc.next();
		//Ask the user to go first or second
		boolean firstOrSecondChecker = true;
		if (firstOrSecond.equalsIgnoreCase("yes")) {
			System.out.println("You will go first");
			firstOrSecondChecker = true;
		} else if (firstOrSecond.equalsIgnoreCase("no")){
			System.out.println("You will go second");
			firstOrSecondChecker = false;
		} else {
			System.out.println("Error: wrong input, please re-enter your input");
			firstOrSecond = sc.next();
			while (firstOrSecond.equalsIgnoreCase("yes")||firstOrSecond.equalsIgnoreCase("no")) {
				System.out.println("Error: wrong input, please re-enter your input");
				firstOrSecond = sc.next();
			} 
			if (firstOrSecond.equalsIgnoreCase("yes")) {
				System.out.println("You will go first");
				firstOrSecondChecker = true;
			} else if (firstOrSecond.equalsIgnoreCase("no")){
				System.out.println("You will go second");
				firstOrSecondChecker = false;
		}}
		//First or second legitimacy checker: whether or not the input is valid
		System.out.println("How many candies do you want to have in this game? (Please give whole numbers)");
		int numberOfCandies = sc.nextInt();
		//Ask the user for the total number of candies that can be used in this game
		System.out.println("You want to have " + numberOfCandies + " in this game");
		System.out.println("What is the maxium number of candies you can pull out at once? (it should be smaller than a quarter of the number you give previously)");
		//Ask the user to give the maximum number of candies: ****As the user again if the number of candies is larger than the number of pulls 
		int numberOfPulls = sc.nextInt();
		System.out.println("The number of candies that can be pull out every time is " + numberOfPulls);
		int leftOver = numberOfCandies;
		if (firstOrSecondChecker) {
			System.out.println("How many do you want to get? Give a number");
			int input = sc.nextInt();
			leftOver -= input;
			while (leftOver>=1) {
				//the program below can definitely be functionalized. 
				if (leftOver > 2) {
					int pullOut = calculatePulls(leftOver, numberOfPulls);
					if (pullOut==1){
						System.out.println("I will take "+ pullOut + " candy");
					} else {
						System.out.println("I will take " + pullOut + " candies");
					} 
					leftOver -= pullOut;
					if (leftOver==1) {
						System.out.println("There is only one candy left. You lost!");
						break;
					} else {
						System.out.println("There are " + leftOver + " candies left. How many do you want to get?");
						int take = sc.nextInt();
						if (take > numberOfPulls) {
							System.out.println("You took more than you can. RETAKE");
							take = sc.nextInt();
						} 
						leftOver -= take;
					}
				} else if (leftOver == 2) {
					System.out.println("I will take 1 candy, you lost!");
					break;
				} else {
					System.out.println("You won the game! Congratulation!");
					break;
				} 	
			}
		} else {
			while (leftOver>=1) {
				if (leftOver > 2) {
					int pullOut = calculatePulls(leftOver, numberOfPulls);
					if (pullOut==1){
						System.out.println("I will take "+ pullOut + " candy");
					} else {
						System.out.println("I will take " + pullOut + " candies");
					} 
					leftOver -= pullOut;
					if (leftOver==1) {
						System.out.println("There is only one candy left. You lost!");
						break;
					} else {
						System.out.println("There are " + leftOver + " candies left. How many do you want to get?");
						int take = sc.nextInt();
						if (take > numberOfPulls) {
							System.out.println("You took more than you can. RETAKE");
							take = sc.nextInt();
						} 
						leftOver -= take;
					}
				} else if (leftOver == 2) {
					System.out.println("I will take 1 candy, you lost!");
					break;
				} else {
					System.out.println("You won the game! Congratulation!");
					break;
				} 	
			}
		}
	}
	public static int calculatePulls(int leftOver, int numberOfPulls) { 
		int baseOfDecision = leftOver % (numberOfPulls+1);
		int pullOut = 1;
		//if baseOfDecision is 1, then the program shall randomly choose a number between 1 and the numberOfCandies and wait for the next pull, since any change at this point will produce the same result. 
		if (baseOfDecision == 1) {
			pullOut = new Random().nextInt(numberOfPulls)+1;
		} else if (baseOfDecision == 0) {
			pullOut = 4;
		} else {
			pullOut = baseOfDecision-1;
			//move the user to a false spot
		}
		return pullOut;
	}
}
//number of pulls has something wrong: if I take 5, still permitted even though the safety valve is on
//functionize the program: how to functionalize the program then? Don't know how to use public static void to return multiple const 
//Another large function: ask the user whether or not they want to start the game again. 