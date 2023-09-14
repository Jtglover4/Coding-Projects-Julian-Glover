class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        
        int left = 0; 
        int right = (int)nums.size() - 1; 
        int middle; 
        int answer;
        
        while(left <= right)
        {
            middle = (left + right) / 2;
            
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle] < target){
                
                left = middle + 1;
                answer = middle + 1;
                
            }else{
                answer = middle;
                right = middle - 1;
            }
        }
    return answer;
    }
};