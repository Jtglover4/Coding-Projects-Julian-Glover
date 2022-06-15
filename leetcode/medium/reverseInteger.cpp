class Solution {
public:
    int reverse(int x) {
        
        int remain;
        long int res = 0;
        
        while(x != 0) {
            remain = x % 10;
            res = res * 10 + remain;
            if((res > 2147483647) || (res < -2147483648)){
                return 0;
            }
            x /= 10;
          }
        
        return res;
    }
};