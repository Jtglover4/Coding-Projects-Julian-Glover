class Solution {
public:
    bool detectCapitalUse(string word) {
        
        char states[] = {'a', 'f', 'n'};
        char state;
        
        for(int i = 0; i < word.length(); i++){
            
            if(i == 0 && word[i] >= 65 && word[i] <= 90){
                
                state = 'a';
                continue;
                
            }else if (i == 0 && (word[i] < 65 || word[i] > 90)){
                
                state = 'n';
                continue;
                
            }
                        
            if(state == 'a'){
                
                if(i == 1 && word[i] >= 97 && word[i] <= 122){
                    
                    state = 'f';
                    continue;
                    
                }else if(word[i] < 65 || word[i] > 90){
                    
                    return false;
                    
                }
                
            }else if(state == 'n'){
                
                if(!(word[i] >= 97 && word[i] <= 122)){
                    
                    return false;
                    
                }
                
            }else{
                
                if(i >= 1 && (word[i] < 97 || word[i] > 122)){
                    return false;
                }
                
            }
            
            
        }
        return true;
        
    }
};