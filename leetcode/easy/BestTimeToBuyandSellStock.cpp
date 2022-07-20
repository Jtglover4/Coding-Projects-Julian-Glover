class Solution {
public:
    int maxProfit(vector<int>& prices) {
        
        int ans = 0;
        
        int lowest = prices[0];
        
        for(int i = 1; i < prices.size(); i++){
            
            if(prices[i] < lowest){
                lowest = prices[i];
            }else if(prices[i] - lowest > ans){
                ans = prices[i] - lowest;
            }
        }
        return ans;
    }
};