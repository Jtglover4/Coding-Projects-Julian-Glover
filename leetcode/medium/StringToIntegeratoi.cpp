class Solution {
public:
    int myAtoi(string s) {
        
        bool pos = true;
        bool start = false;
        int position = 0;
        long int ans = 0;
        bool sign = false;
        
        for(int i = 0; i <= s.size(); i++){
            
            if(((s[i] == '-') || (s[i] == '+')) && (start == false)){
                
                if(sign){
                    return 0;
                }
                
                if(s[i] == '-'){
                    pos = false;
                }
                
                sign = true;
                
            }else if((s[i] >= '0') && (s[i] <= '9')){
                    start = true;
                
                    if((pos && (ans*10 + (s[i] - 48)) > INT_MAX) || (!pos && (-1*(ans*10 + (s[i] - 48))) < INT_MIN)){
                        
                        if(pos){
                            return INT_MAX;
                        }else{
                            return INT_MIN;
                        }
                    }
                    
                    ans = ans*10 + (s[i] - 48);
                
            }else{
                
                if(start == true){
                
                    if(!pos){
                        ans *= -1;
                    }
                    break;
                }else if(s[i] != ' '){
                    return 0;
                }else{
                    if(sign){
                        return 0;
                    }
                }
                
            }
            
        }
        return ans;
    }
};