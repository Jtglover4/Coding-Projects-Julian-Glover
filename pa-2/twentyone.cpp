/*******************************************************************************
 * CS 103 Twenty-One (Blackjack) PA 2
******************************************************************************/

// Add other #includes if you need
#include <iostream>
#include <cstdlib>

using namespace std;

/* Prototypes */
void shuffle(int cards[]);
void printCard(int id);
int cardValue(int id);
void printHand(int hand[], int numCards);
int getBestScore(int hand[], int numCards);

const int NUM_CARDS = 52;

/**
 * Global arrays to be used as look-up tables, if desired.
 * It is up to you if and how you want to use these 
 */
const char suit[4] = {'H','S','D','C'};
const char* type[13] = 
  {"2","3","4","5","6","7",
   "8","9","10","J","Q","K","A"};
const int value[13] = {2,3,4,5,6,7,8,9,10,10,10,10,11};

/**
 * Should permute the deck of cards, effectively shuffling it.
 * You must use the Fisher-Yates / Durstenfeld shuffle algorithm
 *  described in the assignment writeup.
 */
void shuffle(int cards[])
{
  /******** You complete ****************/
  for(int i = 0; i < 52; i++){
    cards[i] = i;
  }
  for(int i = NUM_CARDS-1; i > 0; i--){
    int j = rand() % (i+1);
      
    int temp = cards[i];
    cards[i] = cards[j];
    cards[j] = temp;
  }
    
}

/**
 * Prints the card in a "pretty" format:   "type-suit"
 *  Examples:  K-C  (King of clubs), 2-H (2 of hearts)
 *  Valid Types are: 2,3,4,5,6,7,8,9,10,J,Q,K,A
 *  Valid Suits are: H, D, C, S
 */
void printCard(int id)
{
  /******** You complete ****************/
  char suits;
  const char* val;
  
     if(id <=12){
       suits = suit[0];
     }else if(id > 12 && id <= 25){
       suits = suit[1];
     }else if(id > 25 && id <= 38){
       suits = suit[2];
     }else{
       suits = suit[3];
     }
     
     if(id <= 12){
       int x = id;
       val = type[x];
     }else if(id > 12 && id <= 25){
       int x = id - 13;
       val = type[x];
     }else if(id > 25 && id <= 38){
       int x = id - 26;
       val = type[x];
     }else{
       int x = id - 39;
       val = type[x];
     }
  
      cout << val << "-" << suits << " ";
      
}
/**
 * Returns the numeric value of the card.
 *  Should return 11 for an ACE and can then
 *  be adjusted externally based on the sum of the score.
 */
int cardValue(int id)
{
  /******** You complete ****************/
  int cardval;
  
  if((id % 13) >= 8 && (id % 13) <= 11 ){
    cardval = 10;
  }else if((id % 13) < 8){
    cardval = (id % 13) + 2;
  }else{
    cardval = 11;
  }
  return cardval;

}

/**
 * Should print out each card in the hand separated by a space and
 * then print a newline at the end.  
 * Should use printCard() to print out each card.
 */
void printHand(int hand[], int numCards)
{
  /******** You complete ****************/
for(int i = 0; i < numCards; i++){
  printCard(hand[i]);
}


}

/**
 * Should return the total score of the provided hand.  
 * ACES should be treated as 11s to make the highest possible hand
 *  and only being reduced to 1s as needed to avoid busting.
 */
int getBestScore(int hand[], int numCards)
{
  /******** You complete ****************/

int score = 0;
int aces = 0;
for(int i = 0; i < numCards; i++){
  
  if(cardValue(hand[i]) == 11){
    aces++;
  }
  
  score = score + cardValue(hand[i]);
  
  if(score > 21 && aces > 0){
    score = score - 10;
    aces--;
   }
}
  
  return score;
  
}

/**
 * Main program logic for the game of 21
 */
int main(int argc, char* argv[])
{
  //---------------------------------------
  // Do not change this code -- Begin
  //---------------------------------------
   if(argc < 2){
     cout << "Error - Please provide the seed value." << endl;
     return 1;
        }
 int seed = atoi(argv[1]);

  srand(seed);

  int cards[52];
  int dhand[9];
  int phand[9];
  //---------------------------------------
  // Do not change this code -- End
  //---------------------------------------

  /******** You complete ****************/
  //printCard(40); 
  //cardValue(24);
  //shuffle(cards);
  //cout << cards[20];
  //printHand(cards, 4);
  //cout << endl;
  

  
  bool go = true;
  bool pa = true;
  
 
while(pa == true){
while(go == true){
  /*
  for(int i = 0; i <= 51; i++){
    cards[i] = i;
  }
  */
  
  shuffle(cards);
  int card = 0;
  int pcard = 0;
  int dcard = 0;
  
  phand[pcard] = cards[card];
  card++;
  pcard++;
  dhand[dcard] = cards[card];
  card++;
  dcard++;
  phand[pcard] = cards[card];
  card++;
  pcard++;
  dhand[dcard] = cards[card];
  card++;
  dcard++;
    
    cout << "Dealer: " << "?" << " ";
    printCard(dhand[1]);
    cout << endl;
    
    cout << "Player: ";
    printHand(phand, pcard);
    cout << endl;
   
    int pscore = getBestScore(phand, pcard);
    int dscore = getBestScore(dhand, dcard);
  
    if(pscore == 21 && dscore != 21){
        while(dscore < 17){
        dhand[dcard] = cards[card];
        card++;
        dcard++;
        dscore = getBestScore(dhand, dcard);
      }
      cout << "Dealer:";
      printHand(dhand, dcard);
      cout << endl;
    }
        if(pscore == 21 && dscore != 21){
          cout << "Win " << pscore << " " << dscore << endl;
          go = false;
          break;
        }else if(dscore == 21 && pscore != 21){
          cout << "Lose " << pscore << " " << dscore << endl;
          go = false;
          break;
        }else if(dscore == 21 && pscore == 21){
          cout << "Tie " << pscore << " " << dscore << endl;
          go = false;
          break;
        }
          
    

  
    char move;
  bool a = true;
  
  
  while(a == true){
    cout << "Type 'h' to hit and 's' to stay:" << endl;
    cin >> move;
    
    if(move == 's'){
      a = false;
    }
      
    if(move == 'h'){
      
      phand[pcard] = cards[card];
      pcard++;
      card++;
      
      cout << "Player: ";
      printHand(phand, pcard);
      cout << endl;
      
      pscore = getBestScore(phand, pcard);
      dscore = getBestScore(dhand, dcard);
      
      
        if(pscore > 21){
          cout << "Player busts";
          cout << endl;
          cout << "Lose " << pscore << " " << dscore;
          go = false;
          a = false;
        }else if(pscore == 21){
         a = false;
        }
    }
  }
 if(go == false){
   break;
 }
  bool b = true;
  while(b == true){
    while(dscore < 17 && dscore <= 21 ){
          dhand[dcard] = cards[card];
          card++;
          dcard++;
          dscore = getBestScore(dhand, dcard);
        }
        cout << "Dealer: ";
        printHand(dhand, dcard);
        cout << endl;
        if(dscore > pscore && dscore <= 21){
          cout << "Lose " << pscore << " " << dscore;
          b = false;
          go = false;
        }else if(pscore > dscore && pscore <= 21){
          cout << "Win " << pscore << " " << dscore;
          b = false;
          go = false;
        }else if(dscore > pscore && dscore > 21){
          cout << "Dealer busts" << endl;
          cout << "Win " << pscore << " " << dscore;
          b = false;
          go = false;
        }else if(pscore > dscore && pscore > 21){
          cout << "Player busts" << endl;
          cout << "Lose " << pscore << " " << dscore;
          b = false;
          go = false;
        }else if (pscore == dscore){
          cout << "Tie " << pscore << " " << dscore;
          b = false;
          go = false;
        }else if(pscore == 21){
          cout << "Win " << pscore << " " << dscore;
          b = false;
          go = false;
        }
  }
    //have a while loop until over 21 or presses s
    /*
     int pscore = getBestScore(phand, pcard);
     int dscore = getBestScore(dhand, dcard);
        if(pscore > 21){
        cout << "Player busts";
        cout << endl;
        cout << "Lose " << pscore << " " << dscore;
        break;
        }
    */
     
  
     //getBestScore(dhand, dcard);
    
      
      /*
     cout << endl;
     cout << endl;
     cout << "Play again? [y/n]" << endl;
     char in;
     cin >> in;
     if(in != 'y'){
       go = false;
     }
     */

  
}
     cout << endl;
     cout << endl;
     cout << "Play again? [y/n]" << endl;
     char in;
     cin >> in;
     if(in == 'y'){
       go = true;
     }else if(in == 'n'){
       pa = false;
       go = false;
     }else{
       pa = false;
       go = false;
     }
}
  return 0;
}
