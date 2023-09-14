class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
    
        vector<int> ans;
        
        for(int i = 0; i < nums.size(); i++){
            
            if(nums[i] == 0){
                
                ans.push_back(i);
                
            }
            
        }
        
        
        if(ans.size() == 1){
            
            return max(ans[0], (int)(nums.size()-1) - ans[0]);
            
        }else if(ans.size() == 0){
            
            return nums.size();
            
        }
        
        int m = ans[0];
        
        for(int x = 1; x < ans.size(); x++){
            
            if((ans[x] - ans[x-1]) > m){
                m = ans[x] - ans[x-1] - 1;
            }
            
        }
        
        if((nums.size()-1) - ans[ans.size() - 1] > m){
            return ((nums.size()-1) - ans[ans.size() - 1]);
        }
                
        return m;
        
        
    }
};