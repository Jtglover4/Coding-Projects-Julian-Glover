class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        
        vector<vector<int>> ans;
        
            for(int i = 1; i <= numRows; i++){
                
                vector<int> vec;
                
                int num = 1;
                
                for(int x = 1; x <= i; x++){
                    
                    vec.push_back(num);
                    
                    num = num * (i-x)/x;
                    
                }
                
                ans.push_back(vec);


            }    
            
         return ans;   
        }
        
    
};