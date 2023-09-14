class Solution {
public:
    bool isValid(string s) {
        
        stack<char> help;
        
        for(int i = 0; i < s.size(); i++){
            
            if(help.empty()){

                help.push(s[i]);

            }else if((help.top() == '(' && s[i] == ')') || (help.top() == '{' && s[i] == '}' ) || (help.top() == '[' && s[i] == ']')){

                help.pop();

            }else{

                help.push(s[i]);
                
            }
            
        }
        
        if(help.empty()){
            return true;
        }else{
            return false;
        }

    }
};