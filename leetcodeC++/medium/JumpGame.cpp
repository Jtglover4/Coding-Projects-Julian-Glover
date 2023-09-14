class Solution {
public:
    bool canJump(vector<int>& nums) {
        
        return helper(nums, nums.size() - 1);
        
    }
    
    bool helper(vector<int>& nums, int end){
        
    int m = 0; 
        
    for (int i = 0; (i < end) && (i <= m); i++)
    {
        m = max(m, i + nums[i]);
    }
    if (m >= end) 
    {
        return true;
    }
    
    return false;
        
    }
};