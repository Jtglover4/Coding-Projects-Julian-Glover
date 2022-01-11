#include "bigint.h"
#include <iostream>
#include <fstream>
#include <string>
using namespace std;


int main(int argc, char* argv[]) {
	if (argc != 2) {
		cout<<"\033[1;41m----Missing an argument----\033[0m"<<endl;
	  cout << "Usage: ./decipher <file>"<<endl;
	  cout << "Examples:" << endl;
	  cout << "\t./decipher secret/message1.txt \033[1;90m//to decipher message 1\033[0m" << endl;
	  cout << "\t./decipher secret/message2.txt \033[1;90m//to decipher message 2\033[0m"<<endl;
		return -1;
	}

	/************* You complete *************/
try{
  
  int base;
  string d;
  string n;
  
 ifstream ifile(argv[1]);
  
  ifile >> base;
  ifile >> d;
  ifile >> n;
  
//   d.base = base;
//   n.base = base;
  BigInt x(d, base);
  BigInt y(n, base);
  
  string secret;
  //BigInt result;
  
  int intresult;
  char answer;
  
  while(ifile >> secret){
    
    //ifile >> secret;
    BigInt thing(secret, base);
    //result = modPow(thing, x, y);
    thing.modulusExp(x, y);
    //intresult = result.to_int();
    intresult = thing.to_int();
    answer = (char)intresult;
    
    cout << answer;
  }
  cout << endl;
  
 }catch(const exception& e){
  
  cout << e.what() << endl;
  return -1;
 }
  
	return 0;
}
