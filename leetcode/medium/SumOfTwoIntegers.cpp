class Solution {
public:
    int getSum(int a, int b) {
        
        vector<int> vec;
        
        bool pos = true;
        bool ss = true;
        int big;
        int small;
        int absa;
        int absb;
        
        if(a < 0){
            absa = a*-1;
        }else{
            absa = a;
        }
        
        if(b < 0){
            absb = b*-1;
        }else{
            absb = b;
        }
        
        if(absa > absb){
            
            big = absa;
            small = absb;
            
            if(a <0 && b > 0){
                pos = false;
                ss = false;
            }else if (a < 0 && b < 0){
                
                pos = false;                
            }else if(a > 0 && b < 0){
                
                ss = false;
                
            }else if(a <0 && b == 0){
                pos = false;
            }
            
        }else if(absb > absa){
            
            big = absb;
            small = absa;
            
            if(b < 0 && a > 0){
                
                ss = false;
                pos = false;
                
            }else if(a < 0 && b < 0){
                
                pos = false;
                
            }else if(b > 0 && a < 0){
                
                ss = false;
                
            }else if(b < 0 && a == 0){
                pos = false;
            }
            
        }else{
            
            big = absa; 
            small = absa;
            
            if(a < 0 || b < 0){
                return 0;
            }
            
        }
        
        for(int i = 0; i < big; i++){
            vec.push_back(1);
        }
                
        if(!ss){
            for(int x = 0; x < small; x++){
                vec.pop_back();
            }
        }else{
            for(int x = 0; x < small; x++){
                vec.push_back(1);
            }
        }
        
        if(pos){
            return vec.size();
        }else{
            return -(vec.size());
        }
        
    }
};