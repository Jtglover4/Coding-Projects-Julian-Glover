class Solution {
public:
    bool isPalindrome(int x) {
        
        for(int i = 0; i < (to_string(x).length() / 2); i++){

            if (to_string(x)[i] != to_string(x)[to_string(x).length() - i - 1]){
                return 0;
            }
        }
        return 1;
    }
};