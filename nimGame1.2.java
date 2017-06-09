import java.util.*;
import java.util.Scanner;
/*Design document: Decompose your project into at least three parts(for ex. main, computerMove, chooseNumber) explain the functionality 	for each part. Design your method: name, input (parameters), output (return type).
Three Parts of the Project 
1. Main method
-Functionality: use initialGames and calculatePulls method, mainly the place for implementing the methods
-input (parameter): methods: initialGames and calculatePulls
-retern type: determined by the methods
2. initialGames method
-Functionality: (1) tell the player what this game is about (2) ask the player for input (go first or the total number of candies in the pile.)
-input (parameter): no parameter
-return type: void
3. calculatePulls method
-Functionality: calcuate the computer's move after the player moved
-input (parameter): int leftOver, int numberOfPulls
-return type: int
4. startGame method
-Functionality: process the player's response
-input (parameter): int leftOver, int numberOfPulls, Scanner sc
=return type: void 
*/
class nimGame {
	//the following variable are declared as private since they are under the same class, no need to declare them as public 
	private static Scanner sc = new Scanner(System.in); //start scanner and named it as sc
	private static boolean firstOrSecondChecker = true; //save the firstOrSecondChecker for later usage
	private static int leftOver; //declare integer leftOver for later usage: the number of candies left 
	private static int numberOfPulls = 3; //declare the largest number of candies that can be pull out everytime
	private static boolean playAgain = true; //store for later whether or not the user wants to play again 
	public static void initialGames() {
		//input initialization
				System.out.println("Welcome to Max\'s game of Nim.\nAnd this is a variation of it.");
				//Welcome sentenses
				System.out.println("Please type \"yes\" if you want to go first, and type \"no\" if you want to go second");
				String firstOrSecond = sc.next();
				//Ask the user to go first or second
				if (firstOrSecond.equalsIgnoreCase("yes")) { //if the player typed anything that is literally equivalent to "yes"
					System.out.println("You will go first");
					firstOrSecondChecker = true;
				} else if (firstOrSecond.equalsIgnoreCase("no")){//if the player typed anything that is literally equivalent to "no"
					System.out.println("You will go second");
					firstOrSecondChecker = false;
				} else { //catch input error
					System.out.println("Error: wrong input, please re-enter your input");
					firstOrSecond = sc.next();
					while (!(firstOrSecond.equalsIgnoreCase("yes"))&&!(firstOrSecond.equalsIgnoreCase("no"))) {
						System.out.println("Error: wrong input, please re-enter your input");
						firstOrSecond = sc.next();
					} 
					if (firstOrSecond.equalsIgnoreCase("yes")) {
						System.out.println("You will go first");
						firstOrSecondChecker = true;
					} else if (firstOrSecond.equalsIgnoreCase("no")){
						System.out.println("You will go second");
						firstOrSecondChecker = false;
				}}//First or second legitimacy checker: whether or not the input is valid
				int numberOfCandies = 0; //initialize numberOfCandies (total)
				if (firstOrSecondChecker) {
					numberOfCandies = new Random().nextInt(15)+20;
					//computer randomly select a number between 20 and 35
				} else {
					System.out.println("How many candies do you want to have in this game? (Please give whole numbers)");
					numberOfCandies = sc.nextInt();
				}//Ask the user for the total number of candies that can be used in this game
				System.out.println("You will have " + numberOfCandies + " in this game");
				System.out.println("And everytime, you can take between 1 to 3 candies");
				//tell the player that everytime he or she can only pull out 1~3 candies
				leftOver = numberOfCandies;
				if (firstOrSecondChecker) { //if the user for his or her first pick
					System.out.println("How many do you want to get? Give a number");
					int input = sc.nextInt();
					while (input > numberOfPulls || input <= 0) { //catch input error (same in the later methods)
						System.out.println("Your take value is not valid, please retake");
						input = sc.nextInt();
					} 
					leftOver -= input;
				}
				//partially starting the game (if player chose to go first)
	}
	public static void startGames(int leftOver, int numberOfPulls, Scanner sc) {
		//start the game: with three input value
		while (leftOver>=0) {
			//when there are more or equal to 0 candies
			if (leftOver != 0) {
				//when there are still candies left to move
				if (leftOver<=3) {
					//situations where computer must win
					System.out.println("I will take "+leftOver+", you lost");
					break;
					//get out the loop and print the final result
				} 
				int pullOut = calculatePulls(leftOver, numberOfPulls);
				//use the method to calcuate the pulls
				if (pullOut==1){
					System.out.println("I will take "+ pullOut + " candy");
				} else {
					System.out.println("I will take " + pullOut + " candies");
				} //singular or plural check 
				leftOver -= pullOut;
				//change the leftOver value
				if (leftOver!=0) {
					System.out.println("There are " + leftOver + " candies left. How many do you want to get?");
					int take = sc.nextInt();
					while (take > numberOfPulls || take <= 0) {
						System.out.println("Your take value is not valid, please retake");
						take = sc.nextInt();
					} //invalid value check
					leftOver -= take;
					//change the value of leftOver
				}
			} else {
				System.out.println("You won the game! Congratulation!");
				break;
				//tell the player that he or she won the game
			} 
		}
	}
	public static int calculatePulls(int leftOver, int numberOfPulls) { //the algorithm is described at the end of the code
		int baseOfDecision = leftOver % (numberOfPulls+1);
		//numberOfPulls+1 represents a cycle is 4 candies
		int pullOut = 1;
		//if baseOfDecision is 1, then the program shall randomly choose a number between 1 and the numberOfCandies and wait for the next pull, since any change at this point will produce the same result. 
		if (baseOfDecision ==0){//if the user is smart, he or she will find out every cycle is four, thus getting the multiples of 4 would be optimal
			pullOut = new Random().nextInt(numberOfPulls)+1;
		} else {
			pullOut = baseOfDecision;
		}
		//move the user to a false spot
		return pullOut;
	}
	public static boolean playerMood(){
		//check whether the player want to play this game again, use the boolean playAgain to test it. 
		System.out.println("Do you want to play again? Type \"yes\" if you want to play again!");
		String yesOrNo = sc.next();
		if (yesOrNo.equalsIgnoreCase("yes")){
			playAgain = true;
			//the program will be in the while loop and run again since the playAgain is assigned as true
		} else if (yesOrNo.equalsIgnoreCase("no")) {
			playAgain = false;
			System.out.println("Thank you for playing this game!");
			//this program will terminate since the boolean playAgain is false
		} else {
			while (!(yesOrNo.equalsIgnoreCase("no"))&&!(yesOrNo.equalsIgnoreCase("yes"))){
				System.out.println("invalid input, please type \"yes\" or \"no\"");
				yesOrNo = sc.next();
				if (yesOrNo.equalsIgnoreCase("yes")){
					playAgain = true;
					//the program will be in the while loop and run again since the playAgain is assigned as true
				} else if (yesOrNo.equalsIgnoreCase("no")) {
					playAgain = false;
					System.out.println("Thank you for playing this game!");
					//this program will terminate since the boolean playAgain is false
				}
			}
		}
		return playAgain;
	}
	public static void main(String[] args) {
		//main method: execute the playing method and produce the final result
		while (playAgain){
			initialGames();
			startGames(leftOver,numberOfPulls,sc);
			playerMood();
		}
	}
}
/*The algorithm is based on the truth table at every spot
here is the truth table 
Number of candy left after the player choosed 
		1	2	3	4	5	6	7	8
ComputerT	T	T	F	T	T	T	F
Player  F	F	F	T	F	F	F	T
Therefore, when baseOfDecision (leftOver%(numberOfPulls+1)) is equal to 1~3, then choose the number of reminder will guarantee success
However, when it lands on 4, then the computer has to random the number (between 1~3), and wait for the player to accidentally lands on 4
Therefore, if I want to make the computer wins everyone of the game if the play choose no initially, the total number should be set where when total % 4 = 1, 2, or 3, where the first pull will be these numbers (reminder of the division)
*/