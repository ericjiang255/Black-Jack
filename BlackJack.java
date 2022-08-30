
/* Eric Jiang
   A simple game of black jack. 
   Requires the supporting file Deck.java
*/
import java.util.*;
public class BlackJack {
	public static void main(String[] args) { 
		Deck deck = new Deck();
		Scanner scan = new Scanner(System.in);
		Greetings(deck);
		DealerDealt(deck, 0);
		PlayerDealt(deck,0);
		Boolean ToF = CheckPlayerTotal(deck, 0);
		while (ToF == false) { 
			int hitOrStay= HoS(deck,scan);
			ToF= CheckPlayerTotal(deck,hitOrStay);
		}
	}
	
	public static void Greetings(Deck deck) { //greetings prompt
		deck.Defaults();
		deck.SetCard(deck.GetBank(),0);
		System.out.println("====================");
		System.out.println("Lets Play Black Jack");
		System.out.println("====================");
		System.out.println("");
	}
	public static void DealerDealt(Deck deck, int whichcace) { //prints the cards that the dealer gets
		switch(whichcace) {
			case 0: //initial dealing of two cards with one of the cards hidden. the two cards are sent to be calculated
				System.out.println("The Dealer:");
				String y = Card(deck);
				String x = Card(deck);
				if(y.equals("A")) {
					deck.DAddAceCount();
				} 
				if (x.equals("A")) {
					deck.DAddAceCount();
				}
				if(y.equals("10")) {
					System.out.println(" ___     ___");
					System.out.println("|   |   |   |");
					System.out.println("| 10|   |   |"  );
					System.out.println("|___|   |___|" );
				}else {
					System.out.println(" ___     ___");
					System.out.println("|   |   |   |");
					System.out.println("| "+y+" |   |   |"  );
					System.out.println("|___|   |___|" );
				}
				deck.DealerCalculate(x);
				deck.DealerCalculate(y);
				deck.setHidden(x);
				break;
			
			case 1: //the hidden card from case 0 is revealed
				System.out.println("Dealer reveals:");
				if(deck.getHidden().equals("10")) {
					System.out.println(" ___ ");
					System.out.println("|   |");
					System.out.println("| 10|");
					System.out.println("|___|");
				}else {
					System.out.println(" ___ ");
					System.out.println("|   |");
					System.out.println("| "+deck.getHidden()+" |");
					System.out.println("|___|" );
				}CheckDealerTotal(deck);
				break;
				
			case 2: //dealer gets additional cards and sends them to be calculated
				System.out.println("The Dealer was dealt:");
				String z = Card(deck);
				if (z.equals("A")) {
					deck.DAddAceCount();
				}
				if(z.equals("10")) {
					System.out.println(" ___ ");
					System.out.println("|   |");
					System.out.println("| 10|");
					System.out.println("|___|");
				}else {
					System.out.println(" ___ ");
					System.out.println("|   |");
					System.out.println("| "+z+" |");
					System.out.println("|___|" );
				}
				deck.DealerCalculate(z);
				CheckDealerTotal(deck);
				break;
				
		}
	}
	public static void PlayerDealt(Deck deck, int whichcase) { //prints out the card being dealt to player
		switch(whichcase) {
			case 0: //deals the initial 2 player cards and sends them to be calculated
				System.out.println("You:");
				String a = Card(deck);
				String b = Card(deck);
				if(a.equals("A")) {
					deck.PAddAceCount();
				} 
				if (b.equals("A")) {
					deck.PAddAceCount();
				}
				if(a.equals("10") && b.equals("10")) {
					System.out.println(" ___     ___");
					System.out.println("|   |   |   |");
					System.out.println("| 10|   | 10|"  );
					System.out.println("|___|   |___|" );
				} else if (a.equals("10")) {
					System.out.println(" ___     ___");
					System.out.println("|   |   |   |");
					System.out.println("| "+b+" |   | 10|"  );
					System.out.println("|___|   |___|" );
				} else if (b.equals("10")) {
					System.out.println(" ___     ___");
					System.out.println("|   |   |   |");
					System.out.println("| "+a+" |   | 10|"  );
					System.out.println("|___|   |___|" );
				} else {
					System.out.println(" ___     ___");
					System.out.println("|   |   |   |");
					System.out.println("| "+a+" |   | "+b+" |"  );
					System.out.println("|___|   |___|" );
				}
				deck.PlayerCalculate(a);
				deck.PlayerCalculate(b);
				break;
				
			case 1: //deals additional cards and sends them to be calculated
				System.out.println("You were hit with:");
				String c = Card(deck);
				if(c.equals("A")) {
					deck.PAddAceCount();
				}
				if(c.equals("10")) {
					System.out.println(" ___");
					System.out.println("|   |");
					System.out.println("| 10|");
					System.out.println("|___|");
				}else {
					System.out.println(" ___");
					System.out.println("|   |");
					System.out.println("| "+c+" |");
					System.out.println("|___|");
				}
				deck.PlayerCalculate(c);
				break;
		}
	}
	public static int HoS(Deck deck,Scanner scan) { //asks you you want another card
		System.out.println("Would you like to hit or stay? (Enter hit or stay)");
		String hs = scan.next();
		if (hs.equals("hit")) {
			PlayerDealt(deck,1);
			return 0;
		} 
		return 1;
	}
	public static String Card(Deck deck) { //gets card, deletes card from the deck, and sets new card for next time
		String Card = deck.GetCard();
		deck.SetBank(deck.GetBank(), deck.GetRand());
		deck.SetCard(deck.GetBank(), 0);
		return Card;
	}
	public static Boolean CheckPlayerTotal(Deck deck, int hitOrStay) { //checks whether you busted or not and if to continue the game or not
		int ptotal = deck.GetPlayerTotal();
		if (ptotal == 21) {
			System.out.println("You got 21!");
			DealerDealt(deck,1);
			return true;
		} else if (ptotal > 21) {
			System.out.println("Game Over. You busted.");
			return true;
		} else if (hitOrStay == 1) {
			DealerDealt(deck,1);
			return true;
		}
		return false; 
	}
	public static void CheckDealerTotal(Deck deck) { //checks dealers total and see who wins
		int totalp = deck.GetPlayerTotal();
		int totald = deck.GetDealerTotal();
		if (totalp == totald ) {
			System.out.println("You tied with dealer.");
		} else if(totald == 21) {
			System.out.println("You lost. The house wins!");
		} else if(totald > totalp && totald <21) {
			System.out.println("You lost. The house wins.");
		} else if(totald<totalp && totald<21) {
			DealerDealt(deck,2);
		} else {
			System.out.println("You won.");
		}
	}
	
	
}
