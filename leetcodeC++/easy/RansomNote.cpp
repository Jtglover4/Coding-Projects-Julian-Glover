class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        
        unordered_map<char, int> mappy;
        
        for(int i = 0; i < magazine.size(); i++){
            
            mappy[magazine[i]]++;
            
        }
        
        for(int i = 0; i < ransomNote.size(); i++){
            
            if(mappy[ransomNote[i]] <= 0){
                return false;
            }
            mappy[ransomNote[i]]--;
        }
        return true;
        
    }
};