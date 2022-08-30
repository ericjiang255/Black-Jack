import java.util.*;
public class Deck {
	private String[] Bank;
	private String Card;
	private int Num;
	private int PlayerAceCount;
	private int pTotal;
	private String hidden;
	private int DealerAceCount;
	private int dTotal;
	//Sets the defaults of the game, 52 cards
	public void Defaults() { 
		String[] bank= {"A","A","A","A","2","2","2","2","3","3","3","3","4","4","4","4","5","5","5","5","6","6","6","6","7","7","7","7","8","8","8","8","9","9","9","9","10","10","10","10","J","J","J","J","Q","Q","Q","Q","K","K","K","K"};
		Bank = bank;
		PlayerAceCount=0;
		DealerAceCount=0;
	}
	//sets the random card
	public void SetCard(String[] Bank, int Num) {
		Random rand= new Random();
		int x = rand.nextInt(Bank.length-1);
		this.Num = x;
		this.Card = Bank[x];
	}
	public String GetCard() {
		return Card;
	}
	//Get the random number where the card was drawn from
	public int GetRand() {
		return Num;
	}
	//Removing the dealt card from the Deck
	public void SetBank(String[] Bank, int Num) {
		String[] NewBank = new String[Bank.length - 1];
		for (int i = 0, j = 0; i < Bank.length; i++) {
		    if (i != Num) {
		        NewBank[j++] = Bank[i];
		    }
		}
		this.Bank= NewBank;
	}
	public String[] GetBank() {
		return Bank;
	}
	
	
	
	//Stores hidden card;
	public void setHidden(String x) {
		this.hidden=x;
	}
	public String getHidden () {
		return hidden; 
	}
	//Calculates how many aces dealer has
	public void DAddAceCount() {
		this.DealerAceCount= DealerAceCount+1;
	}
	//Calculates dealer total
	public void DealerCalculate(String x) {
		if (x=="A") {
			this.dTotal = dTotal+ 11;
			if (dTotal > 21) {
				this.dTotal= dTotal- 10;
			}
		} else if (x=="J"|| x== "Q"|| x=="K") {
			this.dTotal = dTotal+ 10;
		} else{
			try{
				this.dTotal= dTotal+ Integer.parseInt(x);
			}
			catch (NumberFormatException ex){
				ex.printStackTrace();
			}
		}
		
		if (dTotal > 21 && DealerAceCount>0) {  //if player has an ace and they are over the limit then the ace turns into a 1
			this.dTotal= dTotal- 10;
			this.DealerAceCount = DealerAceCount -1;
		}
	}
	public int GetDealerTotal() {
		return dTotal;
	}
	
	
	
	//Calculates how many aces pLayer has
	public void PAddAceCount() {
		this.PlayerAceCount= PlayerAceCount+1;
	}
	//Calculates player total
	public void PlayerCalculate(String x) {
		if (x=="A") {
			this.pTotal = pTotal+ 11;
		} else if (x=="J"|| x== "Q"|| x=="K") {
			this.pTotal = pTotal+ 10;
		} else{
			try{
				this.pTotal= pTotal+ Integer.parseInt(x);
			}
			catch (NumberFormatException ex){
				ex.printStackTrace();
			}
		}
		if (pTotal > 21 && PlayerAceCount>0) {//if player has an ace and they are over the limit then the ace turns into a 1
			this.pTotal= pTotal- 10;
			this.PlayerAceCount = PlayerAceCount -1;
		}
	}
	public int GetPlayerTotal() {
		return pTotal;
	}
	
}
