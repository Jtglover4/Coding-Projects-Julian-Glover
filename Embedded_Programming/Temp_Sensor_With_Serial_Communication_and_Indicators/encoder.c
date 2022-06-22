/********************************************
 *
 *  Name:
 *  Email:
 *  Section:
 *  Assignment: Final Project
 *
 ********************************************/

#include <avr/io.h>
#include <util/delay.h>
#include <stdio.h>
#include <avr/interrupt.h>
#include "lcd.h"
#include "encoder.h"

extern volatile unsigned char a; //encoder
extern volatile unsigned char b;
extern volatile unsigned char c; //encoder
extern volatile unsigned char new_state, old_state; 
extern volatile int tempcount;
extern volatile unsigned char changed;

void encode_init(void){ // initialize the encoder, make sure the correct bits are being read
	
	PORTD |= ((1 << PD2) | (1 << PD3));
	PCICR |= (1 << PCIE2);
	PCMSK2 |= ((1 << PCINT18) | (1 << PCINT19));
	
}

void encode_start(void){	//check the initial state of the encoder for which state it is in, and set the state
	c = PIND;
	
	if((c & (1 << 2)) != 0){
		a = 1;
	}else{
		a = 0;
	}
	
	if((c & (1 << 3)) != 0){
		b =1;
	}else{
		b = 0;
	}

    if (!b && !a)
	old_state = 0;
    else if (!b && a)
	old_state = 1;
    else if (b && !a)
	old_state = 2;
    else
	old_state = 3;

    new_state = old_state;
}

ISR(PCINT2_vect){ //Interupt, it will detect when the encoder has changed state and wil update the value
	
	c = PIND;
	
	//get current position
	if((c & (1<<2)) != 0){ 
		a = 1;
	}else{
		a = 0;
	}
	
	if((c & (1<<3)) != 0){
		b =1;
	}else{
		b = 0;
	}
	//update position
	if (old_state == 0) {

	    // Handle A and B inputs for state 0
		if (!b && a){
			new_state = 1;
			tempcount++;
		}else if (b && !a){
			new_state = 2;
			tempcount--;
		}
		

	}
	else if (old_state == 1) {

	    // Handle A and B inputs for state 1
		if (!b && !a){
			new_state = 0;
		
			tempcount--;
		}else if(b && a){
		new_state = 3;
		tempcount++;
		}
		

	}
	else if (old_state == 2) {

	    // Handle A and B inputs for state 2
		if (!b && !a){
			new_state = 0;
		tempcount++;
		}else if(b && a){
			new_state = 3;
			tempcount--;
		}
		

	}
	else {   // old_state = 3

	    // Handle A and B inputs for state 3
		if (!b && a){
		new_state = 1;
		tempcount--;
		}else if (b && !a){
		new_state = 2;
		tempcount++;
		}

	}
	
	if(tempcount > 90){
		tempcount = 90;
	}else if(tempcount < 50){ // mkaing sure the temp value stays in the correct range
		tempcount = 50;
	}
	
	if (new_state != old_state) {
	    changed = 1;
	    old_state = new_state;
	}
	
}