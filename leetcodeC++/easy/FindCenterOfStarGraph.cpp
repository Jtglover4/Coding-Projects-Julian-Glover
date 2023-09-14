class Solution {
public:
    int findCenter(vector<vector<int>>& edges) {
    
        unordered_map<int, int> m;
        
        for(int i = 0; i < edges.size(); i++){
            for(auto x : edges[i]){
                
                m[x]++;
                
            }            
        }
        
        unordered_map<int, int>::iterator it;
        
       for (it = m.begin(); it != m.end(); it++){
            if(it -> second == edges.size()){
                return it -> first;
            } 
       }
        
        return 0;
        
    }
};