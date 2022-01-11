#include "delist.h"
#include <iostream>
#include <string>

using namespace std;

DEList::DEList(){
  
  lsize = 0;
  head = NULL;
  tail = NULL;
  
}

bool DEList::empty(){
  
  if(head == NULL){
    
    return 1;
    
  }else{
    
    return 0;
    
  }
  
}

int DEList::front(){
  
  if(empty()){
    return -1;
  }else{
  
  return head->val;
    
  }
    
}

int DEList::back(){
  
  if(empty()){
    return -1;
  }else{  
    
  return tail->val;
    
  }
  
}

int DEList::size(){
  
  return lsize;
  
}


DEList::~DEList(){
  
  if(tail != NULL){
    tail = NULL;
  }
  
  while(head != NULL){
      DEItem* temp = head;
      head = head->next;
      delete temp;
      lsize--;
  }
}

void DEList::push_back(int new_val){
  
  DEItem* temp = new DEItem;
  temp->val = new_val;
  temp->next = NULL;
  temp->prev = NULL;
  
  if(lsize == 0){
    
    head = temp;
    tail = temp;
    
  }else{
  
  temp->prev = tail;
  tail->next = temp;
  tail = temp;
    
  }
  
  lsize++;
  
}

void DEList::push_front(int new_val){
  
  DEItem* temp = new DEItem;
  temp->next = NULL;
  temp->prev = NULL;
  temp->val = new_val;
  
  if(lsize == 0){
    
    tail = temp;
    head = temp;
    
  }else{
  
  temp->next = head;
  head->prev = temp;
  head = temp;
    
  }
  
  lsize++;
  
}

void DEList::pop_front(){
  
  if(lsize != 0){
    
    DEItem* temp = head;
    head = head->next;
    
 
      if(head == NULL){

          tail = NULL;

      }else{

        head->prev = NULL;

        }        
   
    
    delete temp;
    lsize--;
    
  }
  
}

void DEList::pop_back(){
  
   if(lsize != 0){
    
    DEItem* temp = tail;
    tail = tail->prev;
    
    if(tail == NULL){
      
        head = NULL;
      
    }else{
      
      tail->next = NULL;
      
      }    
    
    delete temp;
    lsize--;
    
  }
  
}