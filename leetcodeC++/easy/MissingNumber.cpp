class Solution {
public:
    int missingNumber(vector<int>& nums) {
        
        int n = nums.size();
        
        priority_queue<int> pq;
        
        for(auto i : nums){
            
            pq.push(i);
            
        }
                
        while(!pq.empty()){
            
            if(n == pq.top()){
                pq.pop();
                n--;
            }else{
                return n;
            }
            
        }
        return 0;
    }
};