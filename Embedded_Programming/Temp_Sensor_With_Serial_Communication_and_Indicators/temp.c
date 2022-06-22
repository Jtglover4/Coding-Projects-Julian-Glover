/********************************************
 *
 *  Name:
 *  Email:
 *  Section:
 *  Assignment: Temp reader
 *
 ********************************************/

#include <avr/io.h>
#include <util/delay.h>
#include <stdio.h>
#include <avr/interrupt.h>
#include <avr/eeprom.h>

#include "encoder.h"
#include "lcd.h"
#include "ds18b20.h"
#include "servo.h"
#define FOSC 16000000
#define BAUD 9600
#define MYUBRR (FOSC/16/BAUD -1)

void clear(void){
	
	lcd_moveto(0,0);
	lcd_stringout("                ");
	lcd_moveto(1,0);
	lcd_stringout("                ");
	
}
void timer0_init(void)
{
    TCCR0A |= (0b11 << WGM00);  // Fast PWM mode, modulus = 256
	TIMSK0 |= (1 << OCIE0A);
    OCR0A = 107;                // Initial pulse duty cycle of 50%

}


void time1_init(void){
	TCCR1B |= (1 << WGM12);
	TIMSK1 |= (1 << OCIE1A);
	OCR1A = 31250;
}

volatile unsigned char a, b; //encoder
volatile unsigned char c; //encoder
int z; 
volatile int num = 0; //speaker
volatile int num2 = 0; //speaker
volatile unsigned char new_state, old_state; //encoder
volatile unsigned char changed = 0;  // Flag for state change encoder
volatile int tempcount; //encoder
enum states { local, remote};
char state;
char buf1[17];
char rxchar(void); // function for recieving data
void txchar(char ch); //function for sending data
char buf2[17]; 
volatile int start = 0; //knowing if the message has started
volatile int end = 0; //knowing when it has ended
volatile int indexo = 0; //for state machine
volatile char buffy[17]; // adding in message
volatile char f;
int ans; 
char finalbuf[17]; //final value of temp
char outbuf[17];   //what is outputted

char r;
int x;
int y;

int main(void){
	
	lcd_init();
	
	lcd_moveto(0,0);
	lcd_stringout("Final Project  ");
	lcd_moveto(1,0);					//splashscreen
	lcd_stringout("Julian Glover  ");
	_delay_ms(1000);
	clear();
		
		changed = 0; //flag for change in encoder
	
		c = PIND;
		DDRB |= (1<<3); //outputs for LEDs, buzzer, etc.
		DDRB |= (1<<4);
		DDRB |= (1<<5);
		
		DDRC |= (1 << 5); 
		PORTC |= (1 << 1); //pull ups for buttons
		PORTC |= (1 << 2); //pull ups for buttons
		
		DDRC |= (1 << 4);
		PORTC &= ~(1 << 4);
		
		encode_init();
		
		
		UBRR0 = MYUBRR;
		UCSR0B |= (1 << TXEN0 | 1 << RXEN0 | 1 << RXCIE0); //buffer and transmitter setup
		UCSR0C = (3 << UCSZ00);
		
		timer0_init();
		timer2_init();
		time1_init();
		sei(); //interupts are on
		state = local; //initialize the state
		
		if(eeprom_read_byte((void *) 200) >= 50 && eeprom_read_byte((void *) 200) <= 90){ //making sure the encoder value stays
			tempcount = eeprom_read_byte((void *) 200);
		}else{
			tempcount = 70;
		}
		
	encode_start();
	
	lcd_moveto(0, 1);
	lcd_stringout("Temp");
	lcd_moveto(1,1);
	lcd_stringout("Remote");
	
	snprintf(buf1, 7, "%d", tempcount);
	lcd_moveto(0,13);
	lcd_stringout(buf1);
	
	unsigned char t[2];
    
    if (ds_init() == 0) {    // Initialize the DS18B20
         // Sensor not responding
		 lcd_moveto(0,0);
		 lcd_stringout("     Error     ");
    }

    ds_convert();  
	
	while(1){
		
		if(state == local){ //indicator the state of remote or local
			
			lcd_moveto(1,0);
			lcd_stringout(" ");
			lcd_moveto(0,0);
			lcd_stringout(">"); 
			
			if((PINC & (1 << 2)) == 0){
				state = remote;
			}
			
		}else if(state == remote){
			
			lcd_moveto(1,0);
			lcd_stringout(">");
			lcd_moveto(0,0);
			lcd_stringout(" ");
			
			if((PINC & (1 << 1)) == 0){
				state = local;
			}
			
		}
		
		
        if (ds_temp(t)) {    // True if conversion complete
            /*
              Process the values returned in t[0]
              and t[1] to find the temperature.
            */
			x = (t[1] << 8) + t[0];
			x = ((x*9)/5);
			y = x % 16;
			y = y*10;
			y /= 16;
			x = (x >> 4);
			x += 32;
			
			snprintf(outbuf, 17, "@+%d#", x);
			int k = 0;
			
			while(k < 5){ //transmitting the temp value
				txchar(outbuf[k]);
				k++;
			}
        
            ds_convert();   // Start next conversion
			
        }
		
		eeprom_update_byte((void *) 200, tempcount); //storing encoder value
			
			z = x*10 + y;
		
			if(tempcount*10 >= z){ //changing buzzer/LEDs based on temp compared to encoder value
				
				num2 = 0;
				TCCR1B &= ~(1 << CS12);
				PORTB |= (1 << 5);
				PORTB &= ~(1 << 4);
				lcd_moveto(1,12);
				lcd_stringout("COOL");
				
			}else if((tempcount*10 < z) && ((tempcount*10 + 30) > z)){
				
				
				num2 = 0;
				PORTB &= ~(1 << 4);
				PORTB &= ~(1 << 5);
				TCCR1B |= (1 << CS12);
				lcd_moveto(1,12);
				lcd_stringout("WARM");
				
			}else if((tempcount*10 + 30) < z){
				
				if(num2 == 0){
					TCCR0B |= (0b100 << CS20);
					num2++;
				}
				TCCR1B &= ~(1 << CS12);
				PORTB &= ~(1 << 5);
				PORTB |= (1<<4);
				lcd_moveto(1,12);
				lcd_stringout("HOT ");

			}
			
			char buf[17];
			snprintf(buf, 17, "%d.%d", x, y);
			lcd_moveto(0,6);
			lcd_stringout(buf);

			
			
		if(state == local){ ///////////////////////////////////////////////////setting value for PWM dial
			
			OCR2A = ((100-x)/3)+12; //using a divider of 3 to keep it rounded
			
		}else if(state == remote){ 
			
			finalbuf[0] = buffy[1];
			finalbuf[1] = buffy[2];
			finalbuf[2] = buffy[3];
			
			if(end){
				
				sscanf(finalbuf, "%d", &ans);
				OCR2A = ((100-ans)/3)+12; // using a divider of 3 to keep rounded
				snprintf(buf2, 17, "%d", ans);
				lcd_moveto(1, 8);
				lcd_stringout(buf2);

			}
			
		}////////////////////////////////////////////////////////////////////////
		
			
		if (changed) { // Did state change?
		
			changed = 0;        // Reset changed flag

			snprintf(buf1, 7, "%d ", tempcount);
			lcd_moveto(0,13);
			lcd_stringout(buf1);

        }
		
	}
	
	return 0;
	
}

ISR(TIMER1_COMPA_vect){ //blinking the LED if the state is WARM
	
	if((PINB & (1 << 4)) != 0){
		PORTB &= ~(1 << 4);
	}else{
		PORTB |= (1 << 4);
	}
	
}

ISR(TIMER0_COMPA_vect){ //setting off the buzzer for 0.5 seconds
	
	if((PINC & (1 << 5)) != 0){
		PORTC &= ~(1 << 5);
		num++;
	}else{
		PORTC |= (1 << 5);
		num++;
	}
	
	if(num == 292){
		TCCR0B &= ~(0b100 << CS20);
		num = 0;
	}
	
}
ISR(USART_RX_vect){ ///////////////////////////////////////////////state machine to recieve the data and output it correctly
		
	f = UDR0;
	
	if(start == 0){
		
		if(f == '@'){ //start of message
			
			start = 1;
			indexo = 1;
			end = 0;
		}
		
	}else if(start == 1){
		
		if(indexo == 1){
			if(f == '+' || f == '-'){
				buffy[0] = f;
				indexo++;
			}else{
				start = 0;
			}
		}else if(indexo == 2){
			if(f >= '0' && f <= '9'){
				buffy[1] = f;
				indexo++;
			}else{
				start = 0;
			}
		}else if(indexo == 3){
			
			if(f >= '0' && f <= '9'){
				buffy[2] = f;
				indexo++;
			}else{
				start = 0;
			}
			
		}else if(indexo == 4){
			
			if(f == '#'){ //end of message
				end = 1;
				indexo++;
				buffy[3] = '\0';
			}
			start = 0;
			
		}
	
	}
	
}

void txchar(char ch){ //transmit data
	
	while((UCSR0A & (1 << UDRE0)) == 0){}
	UDR0 = ch;
	
}

char rxchar(){ //recieve data
	
	while( !(UCSR0A & (1 << RXC0))){}
	return UDR0;
	
} ///////////////////////////////////////////////////////////