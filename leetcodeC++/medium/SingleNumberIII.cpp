class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        
        unordered_map<int, int> m;
        vector<int> ans;
        
        for(auto i : nums){
            
            m[i]++;
            
        }
        
        for(auto it = m.begin(); it != m.end(); ++it){
            
            if(it -> second == 1){
                
                ans.push_back(it->first);
                
            }
            
        }
        return ans;
        
    }
};