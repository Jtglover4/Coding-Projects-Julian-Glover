class Solution {
public:
    string addBinary(string a, string b) {
        
        string ans = "";
        int carry = 0;
        
        if(a.size() > b.size()){
            
            for(int x = a.size() - b.size(); x > 0; x--){
                b = '0' + b;
            }
            
        }else if(b.size() > a.size()){
            
            for(int x = b.size() - a.size(); x > 0; x--){
                a = '0' + a;
            }
        }
        
        for(int i = (a.size() - 1); i >= 0; i--){
            
            if(a[i] == '1' && b[i] == '1'){

                if(carry){
                    ans = '1' + ans;
                }else{
                    ans = '0' + ans;
                }
                carry = 1;

            }else if((a[i] == '1' && b[i] == '0') || (a[i] == '0' && b[i] == '1')){
                
                if(carry){
                    ans = '0' + ans;
                }else{
                    ans = '1' + ans;
                }
                
            }else{
                if(carry){
                    ans = '1' + ans;
                    carry = 0;
                }else{
                    ans = '0' + ans;
                }
            }
        }
        
        if(carry){
            ans = '1' + ans;
        }

        return ans;
    }
};