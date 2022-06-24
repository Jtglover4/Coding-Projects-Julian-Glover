class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        
        string res = strs[0];
        
        for(int i = 1; i < strs.size(); i++){
            
            res = helper(res, strs[i]);
            
        }
        
        return res;
        
    }
    
    string helper(string s1, string s2){
        
        string ans = "";
        int i = 0;
        
        if((s1 == "") || (s2 == "")){
            return ans;
        }
        
        while(s1[i] == s2[i] && s1[i] != '\0'){
            
            ans+= s1[i];
            i++;
        }
        
        return ans;
        
    }
};