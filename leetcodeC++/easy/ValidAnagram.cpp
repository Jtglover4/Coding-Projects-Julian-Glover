class Solution {
public:
    bool isAnagram(string s, string t) {
        
        unordered_map<char, int> m;
        
        if(s.length() != t.length()){
            return false;
        }
        
        for(auto i : s){
            m[i]++;
        }
        
        for(auto x : t){
            
            if(m[x] <= 0){
                return false;
            }
            
            m[x]--;
            
        }
        
        return true;
        
    }
};