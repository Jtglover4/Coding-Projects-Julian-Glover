class Solution {
public:
    vector<int> countBits(int n) {
        
        vector<int> vec(n+1);
        int ans;
        int temp;
        
        for(int i = 0; i <= n; i++){
            ans = 0;
            temp = i;
            while(temp > 0){
                ans += (temp%2);
                temp = temp/2;
            }
            vec[i] = ans;
        }
        
        return vec;
        
    }
};