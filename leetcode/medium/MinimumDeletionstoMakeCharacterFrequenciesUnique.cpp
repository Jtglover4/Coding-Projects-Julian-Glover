class Solution {
public:
    int minDeletions(string s) {
        
        unordered_map<int, int> m;
        
        int ans = 0;
        
        while(s[ans] != '\0'){
            
            m[s[ans]]++;
            ans++;
        }
    
    ans = 0;
    
    priority_queue<int> p;
    
    for (auto it = m.begin(); it != m.end(); ++it)
        {
            p.push(it->second);
        } 
    
    while(1){
        
        int freq = p.top();
        
        p.pop();
        
        if(p.empty()){
            
            return ans;
            
        }
        
        if(freq == p.top()){
            
            if(freq > 1){
                p.push(freq-1);
            }
            ans++;
            
        }
        
    }
    return ans;
    }
};