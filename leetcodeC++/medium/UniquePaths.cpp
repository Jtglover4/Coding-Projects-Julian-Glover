class Solution {
public:
    int uniquePaths(int m, int n) {
        
//         if(m == 1 || n == 1){ ///////////////////////////////////////////////////////////////////////////////
            
//             return 1;
            
//         }
           
//            return uniquePaths(m-1, n) + uniquePaths(m, n-1); RECURSIVE APPROACH, RUN TIME TOO LONG //////////
        
        int num[m][n];
 
    
        for (int i = 0; i < m; i++){
            num[i][0] = 1;
        }
        
        for (int j = 0; j < n; j++){
            num[0][j] = 1;
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++){

                num[i][j] = num[i - 1][j] + num[i][j - 1]; 
            }
        }
        
    return num[m - 1][n - 1];
        
    }
};