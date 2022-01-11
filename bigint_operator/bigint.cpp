#include "bigint.h"
#include <iostream>
#include <string>
#include <climits>
#include <cmath>
using namespace std;


//******************************************************************
//START OF THE CONSTRUCTORS SECTION.
//******************************************************************
// bool BigInt::absCompare(BigInt left, BigInt right)
// {
//   left.isPositive = true;
//   right.isPositive = true;
//   return left.compare(right) == 1;
// }
/*
// Create a default BigInt with base 10.
// This function is complete. You do not have to add anything.
*/
BigInt::BigInt(){
  base = 10;
  isPositive = true;
}

/*
// Create a BigInt with a specified base.
// This function is complete. You do not have to add anything.
*/
BigInt::BigInt(int setbase){
    if(setbase < 2 || setbase > 36){
        throw InvalidBaseException();
    }
    base = setbase;
    isPositive = true;
}

/*
// Destructure. You do not have to add anything.
// This function is complete
*/
BigInt::~BigInt(){}

/*
//  Create a BigInt from int (base 10).
//  - Convert the int (base 10) input into a specified base (setbase).
//  - Don't forget to set the base accordingly
//  For example:
//     BigInt k(6,2) <- int 6 base 10 to BigInt base 2
//          - Result:  BigInt k with value 110 (base 2)
//     BigInt k(154,16) <- 154 base 10 to BigInt base 16
//          - Result: BigInt k with value 9A (base 16)
*/
BigInt::BigInt(int input,int setbase){
    if(setbase < 2 || setbase > 36){
        throw InvalidBaseException();
    }
    /************* You complete *************/
    base = setbase;
    long long long_input = input;
      
    if(long_input < 0){
      
      long_input*=-1;
      isPositive = false;
      
    }else{
      
      isPositive = true;
      
    }
  if(long_input == 0){
    vec.push_back(0);
  }
      
  while(long_input != 0){
    
    int remainder = long_input % setbase;
    long_input = long_input / setbase;
        
      vec.push_back(remainder);
     
       
  }

}

/*
//  Create a BigInt from string.
//  Don't forget to set the base accordingly
//  The input string will contain
//      - numbers (0-9) and/or 
//      - uppercase letters (A-Z) [A=10, B=11, ... , Z=35]
//  The input string may contain
//      - a negative sign ('-') to signify that the number is negative.
//  Note: char '0' = int 48 (see ASCII table)
//  Note: char 'A' = int 65
*/
BigInt::BigInt(const string &s, int setbase){
  
    if(setbase < 2 || setbase > 36){
        throw InvalidBaseException();
    }
    /************* You complete *************/
  
  base = setbase;
  
   if(s[0] == '-'){
      
      isPositive = false;
      
    }else{
      
      isPositive = true;
   }
     
if(isPositive == true){ 
  for(int i = s.length()-1; i >= 0; i--){

      if(s[i] < 65){
        vec.push_back((int)(s[i]) - 48); 
      }else{
        vec.push_back((int)(s[i]) - 55);
      }

    }

  }else{
  
  for(int i = s.length()-1; i >= 1; i--){

      if(s[i] < 65){
        vec.push_back((int)(s[i]) - 48); 
      }else{
        vec.push_back((int)(s[i]) - 55);
      }

    }
  
  }
}


/*
//  Create a BigInt from another BigInt.
//  This is a copy constructor.
//  Simply call the assignment operator (=) below.
*/
BigInt::BigInt(const BigInt &b){

    /************* You complete *************/
  
  *this = b;

}

/*
//  Assignment operator. (i.e., BigInt a = b;)
//  Overriding a's data with BigInt b's data
//       - vec
//       - isPositive
//       - base
*/
BigInt & BigInt::operator = (const BigInt &b){

    /************* You complete *************/

  if(this == &b){ 
    
    return *this;
                 
   }
  /*
  int x = b.vec.size();
  
  for(int i = 0; i < x; i++){
    vec[i] = b.vec[i]; 
  }
  */
  
  vec = b.vec;
  base = b.base;
  
  isPositive = b.isPositive;  

    return *this;
}


//******************************************************************
//END OF THE CONSTRUCTORS SECTION
//******************************************************************

//******************************************************************
//START OF THE DISPLAYS SECTION
//******************************************************************

/*
// Convert to string and return the string
// Display it in its corresponding base
//  Note: char '0' = int 48 (see the ASCII table)
//  Note: char 'A' = int 65
*/
string BigInt::to_string(){

    /************* You complete *************/
  
  string output;
  
  if(isPositive == false){
    
    output += '-';
    
  }
  
    for(int i = vec.size()-1; i >= 0; i--){
      
      if(vec[i] < 10){
        
        output += (char)vec[i]+48;
        
      }else{
        
        output += (char)vec[i] + 55;
        
      }
    }
    return output;
  
  
  

  //return "";//for now
}

/*
//  Convert BigInt to integer base 10 and return that int
//    If BigInt >= INT_MAX, return INT_MAX.
//    If BigInt =< INT_MIN, return INT_MIN.
//  Note: 1. INT_MAX and INT_MIN are already defined for you
//           in the 'climits' library.
//        2. INT_MAX = 2147483647, INT_MIN = -2147483648
//           therefore, INT_MIN does not equal to (-INT_MAX)
*/
int BigInt::to_int() const{

    /************* You complete *************/
  unsigned long long int num = 0;
  
  for(int i = vec.size() - 1; i >= 0; i--){
    
    num += ((int)vec[i] * pow(base, i));
            
  }
  /*
  if(isPositive == false){
    
    num = num * -1;
    
  }
  */
  if(num >= INT_MAX && isPositive == true){
    
    return INT_MAX;
    
  }else if(num > INT_MAX && isPositive == false){
    
    return INT_MIN;
    
  }
  
  if(isPositive == false){
    
    num = num * -1;
    
  }

  return num;
  
  
  
 
  //return 0;//for now
}

//******************************************************************
//END OF THE DISPLAYS SECTION
//******************************************************************

//******************************************************************
//START OF THE EQUALITY CHECK OPERATORS SECTION
//******************************************************************

/*
//  Compare a and b.
//     If a = b, return 0.
//     If a < b, return -1.
//     If a > b, return 1.
//  See the HINT below
*/
int BigInt::compare(const BigInt &b) const{
    if(base != b.base){
        throw DiffBaseException();
    }
    /************* You complete *************/

    //HINT:
    //1. do NOT call the to_int() or to_string() function
    //2. do NOT convert bigInt to other datatypes first (e.g., long long, int)
    
    //First let's compare the signs
    //    - if the signs aren't the same,
    //              how do you know which one is larger?
       if(isPositive == true && b.isPositive == false){
         return 1;
       }else if(isPositive == false && b.isPositive == true){
         return -1;
       }
    //If they have the same sign, let's compare their vec sizes 
    //    - what if both are positive, but a's vec is larger (a or b is larger?)
    //    - other cases?
if(vec.size() != b.vec.size()){
  
    if(vec.size() > b.vec.size()){
      
      if(isPositive == false && b.isPositive == false){
        return -1;
      }else{
      
        return 1;
      }
        
    }else if(vec.size() < b.vec.size()){
      
      if(isPositive == false && b.isPositive == false){
        return 1;
      }else{
        return -1;
      }
     
      
    }
  
}
    
    //If they have the same vec size, let's compare their digits one by one
    // Starting from the most-significant digits. --> in what vector position?
    // Remember that the BigInt vectors are in reverse order!
    //    - what if both are positive, but the digit of a is greater than that of b (a or b is larger?)
    //    - other cases?
if(vec.size() == b.vec.size()){  
  
  for(int i = vec.size() - 1; i >= 0; i--){
    
    if(vec[i] > b.vec[i]){
      
       if(isPositive == false && b.isPositive == false){
         
        return -1;
         
       }else{
      
      return 1;
       }
      
    }else if(vec[i] < b.vec[i]){
       if(isPositive == false && b.isPositive == false){
        return 1;
       }else{
          
      return -1;
       }
      
    }
    
  }
  
}
 
    //If ALL digits are the same, then they MUST be equal!!
    return 0;
    
}


//Call the compare function above to check if a == b
bool operator == (const BigInt &a, const BigInt &b){
  
    /************* You complete *************/
    
  int z = a.compare(b);
  
  if(z == 0){
    
    return 1;
    
  }
  
  
  return false;//for now
}

//Call the compare function above to check if a != b
bool operator != (const BigInt &a, const BigInt &b){
  
    /************* You complete *************/

  
    int z = a.compare(b);

  if(z != 0){
    
    return 1;
    
  }
  
  
  
  return false;//for now
}

//Call the compare function above to check if a <= b
bool operator <= (const BigInt &a, const BigInt &b){
  
    /************* You complete *************/

  
    int z = a.compare(b);

  if(z == -1 || z == 0){
    
    return 1;
    
  }
  
  
  
  return false;//for now
}

//Call the compare function above to check if a >= b
bool operator >= (const BigInt &a, const BigInt &b){
  
    /************* You complete *************/

  
    int z = a.compare(b);

  if(z == 1 || z == 0){
    
    return 1;
    
  }
  
  
  
  return false;//for now
}

//Call the compare function above to check if a > b
bool operator > (const BigInt &a, const BigInt &b){
  
    /************* You complete *************/

    int z = a.compare(b);

  if(z == 1){
    
    return 1;
    
  }
  
  
  
  return false;//for now
}

//Call the compare function above to check if a < b
bool operator < (const BigInt &a, const BigInt &b){
  
    /************* You complete *************/
  
  int z = a.compare(b);

if(z == -1){
    
    return 1;
    
  }  
  
  
  
  return false;//for now
}

//******************************************************************
//END OF THE EQUALITY CHECK OPERATORS SECTION
//******************************************************************

//******************************************************************
//START OF THE ARITHMETIC OPERATORS SECTION
//******************************************************************

/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call += operator on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt operator + (const  BigInt &a, const BigInt & b){

  /************* You complete *************/
  BigInt c = a;
  c += b;

  return c;//for now
}

/*
//==================
// MEMBER function
//==================
//  Addition assignment operator.
//    i.e., a += b
*/
const BigInt & BigInt::operator += (const BigInt &b) {
    if(base != b.base){
        throw DiffBaseException();
    }
    /************* You complete *************/
  
//   if(isPositive == true && b.isPositive == false){
    
//     *this = *this - b;
//     return *this;
    
//   }else if(isPositive == false && b.isPositive == true){
    
//     *this = b - *this;
    
//     return *this;
    
//   }
  
  bool absCompare = true;
  
  {
    BigInt tempA = *this;
    BigInt tempB = b;
    tempA.isPositive = true;
    tempB.isPositive = true;
    
    if(tempA.compare(tempB) == -1){
        absCompare = false;    
    }
  } 
  
  
 bool resPositive = true;
  if(!isPositive && !b.isPositive)
  {
      resPositive = false;
  }
  else if(isPositive && !b.isPositive && !absCompare)
  {
      resPositive = false;
  }
  else if(!isPositive && b.isPositive && absCompare)
  {
      resPositive = false;
  }
  
  
   BigInt top = *this;
   BigInt bottom = b;
  
  
  if(!absCompare)
  {
    top = b;
    bottom = *this;
  }
  

   if(bottom.isPositive != top.isPositive)
  {
    for(int i =0;i< (int) bottom.vec.size();++i)
    {
      bottom.vec[i]*=-1;
    }
  }
  
  if(bottom.vec.size() > top. vec.size()){
    while(bottom.vec.size() > top.vec.size()){
      top.vec.push_back(0);
    }
}
  
int tempval;
int remain = 0;
  

//for(int i = tempB.size() - 1; i >= 0; i--){
  for(int i = 0; i < (int)top.vec.size(); i++){

    tempval = top.vec[i]+remain;
 
    if(i< (int) bottom.vec.size())
    {
      tempval += bottom.vec[i];
    }
    
    remain = 0;
    while(tempval<0)
    {
      tempval+=base;
      --remain;
    }
    
   remain += tempval/base;

   top.vec[i] = tempval % base;
  
}
  

if(remain > 0){
  top.vec.push_back(remain);
}
  
  this->vec = top.vec;
  this->isPositive = resPositive;
    
  
  while(this->vec[this->vec.size()-1] == 0 && this->vec.size()>1)
  {
    this->vec.pop_back();
  }

  if(this->vec.size() == 1 && this->vec[0] == 0){
    this->isPositive = true;
  }

  return *this;
}

/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call -= operator on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt operator - (const  BigInt &a, const BigInt & b){

  /************* You complete *************/
  BigInt c = a;
  c -= b;
  

  return c;//for now
}


/*
//==================
// MEMBER function
//==================
//  Subtraction assignment operator.
//    i.e., a -= b
//  Note: Think of base cases (e.g., subtract by itself)
*/
const BigInt & BigInt::operator -= (const BigInt &b){
    if(base != b.base){
        throw DiffBaseException();
    }
    /************* You complete *************/
  
  BigInt temp = b;
  temp.isPositive = !temp.isPositive;
  *this += temp;
  
/*
if(isPositive == true && b.isPositive == false){
    
    *this = *this + b;
    return *this;
    
  }else if(isPositive == false && b.isPositive == true){
    
    *this = b + *this;
    
    return *this;
  }
  
  vector<int> tempTop = b.vec;
  vector<int> tempBottom = vec;
  int jcompare = compare(b);
  
  if(jcompare == 1){
    if(isPositive){
      tempTop = vec;
      tempBottom = b.vec;
    } 
    isPositive=true;
  }
  else if (jcompare == -1)
  {
     if(!isPositive){
        tempTop = vec;
        tempBottom = b.vec;
    }
    isPositive=false;
  }
  else{
    vec = {0};
    isPositive = true;
    return *this;
  }
  
  
 
  if(tempBottom.size() > tempTop.size()){
    while(tempBottom.size() > tempTop.size()){
      tempTop.push_back(0);
    }
}

if(tempTop.size() > tempBottom.size()){
    while(tempTop.size() > tempBottom.size()){
      tempBottom.push_back(0);
    }
}
  
int tempval;
int remain = 0;
int h = tempBottom.size();

//for(int i = tempBottom.size() - 1; i >= 0; i--){
  for(int i = 0; i < h; i++){
  
   tempval = tempTop[i]-tempBottom[i]-remain;
    if(tempval<0){
      tempval+=base;
      remain = 1;
      }else{
      remain = 0;
      }
   //remain = (tempval+tempTop[i])/base;
   tempTop[i] = tempval;
  
}

  
  this -> vec = tempTop;  
  */
    

  return *this;

}

/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call *= operator on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt operator * (const  BigInt &a, const BigInt & b){

    /************* You complete *************/
  BigInt c = a;
  c *= b;

  return c;
    
}


/*
//==================
// MEMBER function
//==================
//  Multiplication assignment operator.
//    i.e., a *= b
//  Implement Long Multiplication
//  Note: What are the base cases?? (e.g., multiply by 0)
*/
const BigInt & BigInt::operator *= (const BigInt &b){
    if(base != b.base){
        throw DiffBaseException();
    }
    /************* You complete *************/
  
  BigInt tempA = *this;
  BigInt tempB = b;
   
  
if(tempA.isPositive == tempB.isPositive){
  
  isPositive = true;
  
}else{
  
  isPositive = false;
  
}
  
 BigInt result(0,base);
  
  for(int i = 0; i < (int)vec.size(); i++){
    vector<int> temp;
    int carry=0;
    for(int j=0;j < (int)b.vec.size();++j){
      int product = vec[i]*b.vec[j] + carry;
      carry = product/base;
      temp.push_back(product % base);
    }
    if(carry > 0){
      temp.push_back(carry);
    }
    int k = i;
    while(k > 0){
      temp.insert(temp.begin(), 0);
      k--;
    }
    BigInt temp0(0, base);
    temp0.vec = temp;
    result += temp0;
  }
  
  
   this->vec = result.vec; 
  
  if(this->vec.size() == 1 && this->vec[0] == 0){
    this->isPositive = true;
  }
  
    return *this;
}

/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call /= operator on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt operator / (const  BigInt &a, const BigInt & b){

    /************* You complete *************/
 
  BigInt c = a;
  c /= b;

  return c;

}


/*
//==================
// MEMBER function
//==================
//  Division assignment operator.
//    - Call 'divisionMain' to get the quotient;
*/
const BigInt & BigInt::operator /= (const BigInt &b){
    if(base != b.base){
        throw DiffBaseException();
    }
    if(b.vec.size() == 1 && b.vec[0] == 0){
        throw DivByZeroException();
    }
    /************* You complete *************/
  BigInt q(0,base);
  BigInt r(0,base);
  

    
  if(isPositive == b.isPositive){ 
      isPositive = true; 
  }else{ 
    isPositive = false;
  }
  
    this->divisionMain(b,q,r);
  
  this->vec = q.vec;
  
  while(this->vec[this->vec.size()-1] == 0 && this->vec.size()>1)
  {
    this->vec.pop_back();
  }
  
   if(this->vec.size()==1 && this->vec[0]==0)
    {
      this->isPositive = true;
    }

  
    return *this;
}

/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call %= operator on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt operator % (const  BigInt &a, const BigInt & b){

    /************* You complete *************/
  
  BigInt c = a;
  c %= b;

  return c;

}


/*
//==================
// MEMBER function
//==================
//  Modulus assignment operator.
//    - Call 'divisionMain' to get the remainder;
//    - Note: remainder takes the sign of the dividend.
*/
const BigInt & BigInt::operator %= (const BigInt &b){
    if(base != b.base){
        throw DiffBaseException();
    }
    if(b.vec.size() == 1 && b.vec[0] == 0){
        throw DivByZeroException();//divide by zero.
    }
    /************* You complete *************/

  BigInt q(0,base);
  BigInt r(0,base);
  
  this->divisionMain(b,q,r);
  
  this->vec = r.vec;
  
  if(this->vec.size()==1 && this->vec[0]==0)
    {
      this->isPositive = true;
    }
  
  
    return *this;
}

/*
//  Main function for the Division (/=) and Modulus (%=) operators.
//     - Compute (q)uotient and (r)emainder
//     - Implement Long Division
//  Note: 1. This should behave like integer division
//        2. What are the base cases?? (e.g., div by itself)
*/
void BigInt::divisionMain(const BigInt &b, BigInt &quotient, BigInt &remainder){

    /************* You complete *************/
  
  
    BigInt den = b;
    BigInt num = *this;
    den.isPositive = true;
    num.isPositive = true;
    quotient.base = this->base;
  
    if(den.compare(num) == 1)
    {
      quotient.vec = {0};
      remainder.vec = num.vec;
      //quotient.base = this->base;
      return;
    }
   if(den.compare(num) == 0)
    {
      quotient.vec = {1};
      remainder.vec = {0};
      //quotient.base = this->base;
      return;
    }
  
    if(den.vec.size()==1 && den.vec[0] ==1)
    {
      quotient.vec = num.vec;
      return;
    }
    
  
    BigInt q(0, base);
    q.vec.clear();
    BigInt temp(0,base);
    temp.vec.clear();
    for(int i=(int)num.vec.size()-1;i>=0;--i){   
      temp.vec.insert(temp.vec.begin(), num.vec[i]);
      long long  k =0;
      while( (den*BigInt(k+1, base)).compare(temp) != 1)
      {
       ++k; 
      }
      q.vec.insert(q.vec.begin(), k);
      temp -= (BigInt(k,base) * den);
      if(temp.vec.size()==1 && temp.vec[0]==0)
      {
        temp.vec.clear();
      }

      
    }

    if(temp.vec.size() == 0){

        temp.vec.push_back(0);

      }
  
    quotient = q;
   
    remainder = temp;
  
  

}



/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call the 'exponentiation' function on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt pow(const  BigInt &a, const BigInt & b){

    /************* You complete *************/
  
  BigInt c = a;
  c.exponentiation(b);

  return c;
  
}

/*
//==================
// MEMBER function
//==================
//  Exponentiation assignment function.
//     - i.e., a.exponentiation(b);
// Note: 1. implement Exponentiation by Squaring (see the writeup)
//       2. b should be treated as BigInt, not int or other data type.
*/
const BigInt & BigInt::exponentiation(const BigInt &b){
    if(base != b.base){
        throw DiffBaseException();
    }
    if(!b.isPositive){
        throw ExpByNegativeException();
    }
    /************* You complete *************/  

  BigInt tempA = *this;
  BigInt tempB = b;
  //BigInt tempM = *this;
  
  BigInt two;
  two.vec = {2};
  two.base = base;
  two.isPositive = true;
  
  //BigInt exponent = two;
  
  BigInt one;
  one.vec = {1};
  one.base = base;
  one.isPositive = true;
  
  BigInt zero;
  zero.vec = {0};
  zero.base = base;
  zero.isPositive = true;
  
    BigInt tempsol;
    tempsol.base = base;
    tempsol.isPositive = true;
    tempsol.vec = {1};
  
  
  if(tempA.vec.size() == 1 && tempA == one){
    
    this->vec = {1};
    return *this;
    
  }
  if(b.vec.size() == 1 && b.vec[0] == 0){
    
    this->vec = {1};
    isPositive = true;
    return *this;
    
  }else if(b.vec.size() == 1 && b == one){
    
    return *this;
    
  }
  
  if(isPositive == false && b % two == zero){
    
    isPositive = true;
    
  }
  
  
  /*
  BigInt remainder;
  int count = 0;
    while(exponent < b){
    
    exponent *= two;
    count++;
  }
  
  remainder = b - exponent;
  */
/*
  
  if(tempB % two == zero){
      
      tempA = pow(tempA, tempB/two) * pow(tempA, tempB/two);
      tempB /= two;
    
  }else{
    
    tempA = pow(tempA, tempB/two) * tempA;
    tempB -= one;
    
  }
  */
   // BigInt temp2 = tempM *tempM;

//   if(tempB % two == zero){

//         tempA = pow(temp2, tempB/two);
// //          tempA *= tempA;
// //           tempB /= two;
    
//     }else{

//       tempA = pow(temp2, (tempB - one)/two) * tempM;
// //        tempsol *= tempA;
// //          // tempA *= tempA;
// //           //tempsol %= m;
// //           tempB -= one;
//     }
while(tempB > zero){
// while(tempB != one){
  
  if(tempB % two == zero){

  tempA *= tempA;
  tempB /= two;
    
  }else{
    
    tempsol *= tempA;
   // tempA *= tempA;
    tempB -= one;
    
  }
//   tempB /= two;
  //tempB.base = b.base;
}
//   tempA *= ifoddvec;
//   tempA %= m;
  *this = tempsol;

  // if(count > 0){

  //   for(int i = 0; i < count; i++){

  //     tempA *= tempM;

  //   }

  // }

   // this->vec = tempA.vec;

      return *this;
  }

/*
//======================
// NON-MEMBER function
//======================
// 1. Create a copy of a
// 2. Call the 'modulusExp' function on the copy and b
// 3. Return the copy
// Note: Should take you exactly 3 lines of code
*/
BigInt modPow(const BigInt &a, const BigInt &b, const BigInt &m){

    /************* You complete *************/
  
  BigInt c = a;
  c.modulusExp(b, m);

  return c;
}


/*
//==================
// MEMBER function
//==================
//  Modulus Exponentiation assignment function.
//     - i.e., a.modulusExp(b)
// Note: 1. implement Modulus Exponentiation (see the writeup)
//       2. b should be treated as BigInt, not int or other data type.
// Hint: same implementation as exponentiation, but take modulus 
//          after every call to the *= operator.
*/
const BigInt & BigInt::modulusExp(const BigInt &b, const BigInt &m){
    if(base != b.base || base != m.base){
        throw DiffBaseException();
    }
    if(!b.isPositive){
        throw ExpByNegativeException();
    }
    /************* You complete *************/

  BigInt tempA = *this;
  //BigInt temp2 = tempA * tempA;
  //BigInt div = m;
  BigInt tempB = b;
  
   //BigInt tempsol;
//    tempsol.base = base;
//    tempsol.isPositive = true;
//    tempsol.vec = {1};
  //ifoddvec = one;
  
//   BigInt sol1 = tempsol;
//   BigInt sol2 = tempsol;
//   BigInt sol3 = tempsol;
//   BigInt sol4 = tempsol;
  
  BigInt two("2", base);
//   two.vec = {2};
//   two.base = base;
//   two.isPositive = true;
  
  BigInt one("1", base);
//   one.vec = {1};
//   one.base = base;
//   one.isPositive = true;
  
  //BigInt ifoddvec = one;
  
  BigInt zero("0", base);
//   zero.vec = {0};
//   zero.base = base;
//   zero.isPositive = true;
  
if(b == zero){
  
  *this = one;
  return *this;
  
}else if(b == one){
  
  *this = tempA % m;
  return *this;
  
}
  
  BigInt tempsol = one;

while(tempB > zero){
// while(tempB != one){
  
  if(tempB % two == zero){

  tempA *= tempA;
  tempA %= m;
  tempB /= two;
    
  }else{
    
    tempsol *= tempA;
   // tempA *= tempA;
    tempsol %= m;
    tempB -= one;
    
  }
//   tempB /= two;
  //tempB.base = b.base;
}
//   tempA *= ifoddvec;
//   tempA %= m;
  *this = tempsol;
  
    return *this;
}

//******************************************************************
//END OF THE ARITHMETIC OPERATORS SECTION
//******************************************************************
//is abs value of left greater than right
