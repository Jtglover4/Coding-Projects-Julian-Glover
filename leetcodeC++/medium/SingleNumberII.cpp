class Solution {
public:
    int singleNumber(vector<int>& nums) {
        
        unordered_map<int, int> m;
        
        for(auto i : nums){
            
            m[i]++;
            
        }
        
        unordered_map<int, int>::iterator it;
        
       for (it = m.begin(); it != m.end(); it++){
            if((it -> second) == 1){
                return (it -> first);
            }
       }
        return 0;
    }
};