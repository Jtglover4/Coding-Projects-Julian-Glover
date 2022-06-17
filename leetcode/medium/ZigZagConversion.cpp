class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows == 1){
            return s;
        }
        
        string ans[numRows];
        int down = true;
        int r = 0;
        string final;
        for(int i = 0; i < s.size(); i++){
            ans[r].push_back(s[i]);
            if(r == (numRows - 1)){
                down = false;
            }else if (r == 0){
                down = true;
            }
            
            if(!down){
                r--;
            }else{
                r++;
            }
            
        }
        for(int x = 0; x < numRows; x++){
            final += ans[x];
        }        
        
        return final;
        
    }
};