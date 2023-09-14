class Solution {
public:
    string intToRoman(int num) {
        
        string ans = "";
        
        int m = num/1000;
        
        while(m > 0){
            ans += 'M';
            m--; 
        }
        
        num = num % 1000;
        
        if(num >= 900){
            ans += "CM";
            num = num % 900;
        }else if(num >= 500){
            
                ans += 'D';
            
            num -= 500;
            
        }
        
        if(num >= 400){
            ans += "CD";
            num = num % 400;
        }else if(num >= 100){
            
            int c = num / 100;
            while(c > 0){
                ans += 'C';
                c--;
            }
            
            num = num %100;
            
        }
        
        if(num >= 90){
            ans += "XC";
            num = num % 90;
        }else if(num >= 50){
            
            ans += 'L';
            
            num -= 50;
            
        }
        
        if(num >= 40){
            ans += "XL";
            num = num % 40;
        }else if(num >= 10){
            
            int x = num / 10;
            while(x > 0){
                ans += 'X';
                x--;
            }
            
            num = num % 10;
            
        }
        
        if(num == 9){
            ans += "IX";
            return ans;
        }else if(num >= 5){
            
            ans += 'V';
            
            num -= 5;
            
        }
        
        if(num == 4){
            ans+= "IV";
        }else{
            
            while(num > 0){
                ans += 'I';
                num--;
            }
            
        }
        
        return ans;
        
    }
};