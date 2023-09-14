class Solution {
public:
    bool isPerfectSquare(int num) {
        
//         if(num == 1){ //// slower runtime
//             return true;
//         }
        
//         for(long int i = 1; i <= num/2; i++){
            
//             if(i*i == num){
//                 return true;
//             }
            
//         }
//         return false; //////slower runtime
        
        int beg=1;
        int end =num;
        long int middle;
        
        while(beg <= end){
            
            middle = beg+(end-beg)/2;
            
            if(middle*middle==num){
                return true;
            }else if(middle*middle<num){
                beg=middle+1;
            }else{
                end =middle-1;
            }
        }
        return false;
    }
};