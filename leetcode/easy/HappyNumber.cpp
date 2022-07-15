class Solution {
public:
    bool isHappy(int n) {
        
        return helper(n);
        
    }
    int helper(int num){
        
        int ans = 0;
        
        while(num > 0){
            
            ans += (num%10)*(num%10);
            num = num/10;
            
        }
        
        if((int)log10(ans) == 0){
            
            if(ans == 1 || ans == 7){
                return true;
            }
            return false;
            
        }
            
        return helper(ans);
        
        
    }
};