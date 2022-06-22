class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
    
        map<int, int> mappy;
        vector<int> ans;
        int num = 0;
        
        for(auto i : nums){
           mappy[target - i]++;
        }
        
        for(auto i : nums){
           
            if((i == target - i) && (mappy[i] % 2 == 1)){
                mappy[i]--;
                break;
            }
            
        }
        
        for(int x = 0; x < nums.size(); x++){
            
            if(mappy[nums[x]] > 0){
                ans.push_back(x);
                mappy[nums[x]]--;
            }
                            
        }
        
        sort(ans.begin(), ans.end());
        
        return ans;
    }
    
};