class Solution {
public:
    void sortColors(vector<int>& nums) {
        
        priority_queue<int> p;
        
       for(auto i: nums){
            
            p.push(i);
            
        }
        
        int i = nums.size() - 1;
        
        while(!p.empty()){
            
            nums[i] = p.top();
            p.pop();
            i--;
            
        }
        
        
    }
};