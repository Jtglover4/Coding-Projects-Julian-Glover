class Solution {
public:
    int mySqrt(int x) {
     
        for(long int i = 0; i <= (x/2) + 1; i++){
            
            if(i*i == x){
                return i;
            }else if(i*i > x){
                return i-1;
            }
            
        }
        return 0;
    }
};