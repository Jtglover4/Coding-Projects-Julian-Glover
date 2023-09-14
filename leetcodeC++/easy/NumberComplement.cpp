class Solution {
public:
    int findComplement(int num) {
        
        string a = itos(num);
        
        for(int i = 0; i < a.size(); i++){
            
            if(a[i] == '1'){
                a[i] = '0';
            }else{
                a[i] = '1';
            }
            
        }
        return stoi(a);
        
    }
    string itos(int i){
        
        string ans = "";
        
        while(i > 0){
            
            ans = (char)((i%2)+48) + ans;
            i /= 2;
            
        }
        return ans;
        
    }
    int stoi(string s){
        
        int ans = 0;
        
        long int n = 1;
        
        for(int i = s.length()-1; i >=0; i--){
            
            ans += ((int)s[i]-48)*n;
            n*=2;
            
        }
        return ans;
        
    }
};