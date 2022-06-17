class Solution {
public:
    int lengthOfLastWord(string s) {
        
        bool end = true;
        string ans;
        
        for(int i = 0; i < s.size(); i++){
            
            if(s[i] == '\0'){
                if(end){
                    return (int)ans.size();
                }
            }
            
            if((s[i] >= 'A' && s[i] <= 'Z') || (s[i] >= 'a' && s[i] <= 'z')){
                
                if(end){
                    ans = "";
                }
                
                ans+= s[i];
                end = false;
                
            }else if(s[i] == ' '){
                end = true;
            }
            
            
        }
        return ans.size();
    }
};