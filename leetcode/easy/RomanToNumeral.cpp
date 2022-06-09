class Solution {
public:
    int romanToInt(string s) {
        if((s.length() < 1) || (s.length() > 15)){
            cout << "error";
            return 0;
        }
        
        int x = 0;
        int sum = 0;
        
        while(s[x] != '\0'){
            
            if((s[x] == 'I') && (s[x+1] == 'V')){ //start with more specific cases first
                sum += 4;
                x += 2;
                continue; //lower run time and memory usage
            }else if((s[x] == 'I') && (s[x+1] == 'X')){
                sum += 9;
                x += 2;
                continue; //lower run time and memory usage
            }else if((s[x] == 'X') && (s[x+1] == 'L')){
                sum += 40;
                x+= 2;
                continue; //lower run time and memory usage
            }else if((s[x] == 'X') && (s[x+1] == 'C')){
                sum += 90;
                x+= 2;
                continue; //lower run time and memory usage
            }else if((s[x] == 'C') && (s[x+1] == 'D')){
                sum += 400;
                x+= 2;
                continue; //lower run time and memory usage
            }else if((s[x] == 'C') && (s[x+1] == 'M')){
                sum += 900;
                x+= 2;
                continue; //lower run time and memory usage
            }else if(s[x] == 'C'){ 
                sum += 100;
                x++;
            }else if(s[x] == 'M'){
                sum += 1000;
                x++;
            }else if(s[x] == 'V'){
                sum += 5;
                x++;
            }else if(s[x] == 'D'){
                sum += 500;
                x++;
            }else if(s[x] == 'L'){
                sum += 50;
                x++;
            }else if(s[x] == 'I'){
                sum += 1;
                x++;
            }else if(s[x] == 'X'){
                sum += 10;
                x++;
            }else{
                cout << "error";
                return 0;
            }
        }
        return sum;
    }
};