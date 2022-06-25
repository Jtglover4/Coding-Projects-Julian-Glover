class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        
        vector<int> ans;
         int carry = 0;
            
            for(int i = digits.size() - 1; i >= 0; i--){

                    
                    digits[i] += 1;
                    
                    if(digits[i] <= 9){
                        
                        carry = 0;
                        break;
                        
                    }else{
                        
                        digits[i] = 0;
                        carry = 1;
                        
                    }
                    

                }
        
            if(carry){
                
                ans.push_back(1);
                
            }
            
            for(auto i : digits){
                
                ans.push_back(i);
                
            }
        
        return ans;
            
        }
    
};