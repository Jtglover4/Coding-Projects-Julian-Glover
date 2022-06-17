class Solution {
public:
    int jump(vector<int>& nums) {
        int length = nums.size() - 1;
        int place = -1;
        int next = 0;
        int ans = 0;
        
        for (int i = 0; next < length; i++) {
            if (i > place){
                ans++; 
                place = next;
            }
            next = max(next, nums[i] + i);
        };
        return ans;
    }
};