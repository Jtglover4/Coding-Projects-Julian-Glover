class Solution {
public:
    bool isPalindrome(string s) {
        
        string use = "";
        int i = 0;
        while(s[i] != '\0'){
            
            if(s[i] >= 65 && s[i] <= 90){
                
                use += (s[i] + 32);
                
            }else if(s[i] >= 97 && s[i] <= 122){
                
                use += s[i];
                
            }else if(s[i] >= 48 && s[i] <= 57){
                
                use += s[i];
                
            }
            
            i++;
            
        }
                
        int j = use.length() - 1;
        
        
        for(int x = 0; x <= j; x++){
            
            if(use[x] != use[j]){
                return false;
            }
            j--;
        }
        return true;
        
    }
};