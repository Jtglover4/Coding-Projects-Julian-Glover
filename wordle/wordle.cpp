// For debugging
#include <iostream>
// For std::remove
#include <algorithm> 
#include <map>
#include <set>
#include "wordle.h"
#include "dict-eng.h"
using namespace std;


// Add prototypes of helper functions here
void help(size_t size, size_t index, const std::string& in, const std::string& floating, const std::set<std::string>& dict, std::set<std::string>& ans);


// Definition of primary wordle function
std::set<std::string> wordle(
    const std::string& in,
    const std::string& floating,
    const std::set<std::string>& dict)
{
    // Add your code here
    size_t x = in.size();
    string final;
    size_t index = 0;
    std::set<std::string> ans;

    help(x, index, in, floating, dict, ans);

    return ans;

}


// Define any helper functions here
void help(size_t size, size_t index, const string& in, const string& floating, const set<string>& dict, set<string>& ans){
    
    if((index == size) && (floating.size() == 0)){
        
        if(dict.find(in) != dict.end()){
            ans.insert(in);
            return;

        }else{

        return;

        }

    }

    string s = in;
    string current = floating;
    size_t numf = 0;

    for(size_t i = 0; i < s.size(); ++i){

        if(s[i] == '-'){
            numf++;
        }
    }

    if(s[index] == '-'){

        if(numf > current.size()){

            for (char j = 'a'; j <= 'z'; ++j){

                s[index] = j;

                if(current.find(j) != string::npos){
                    
                    current.erase(current.find(j), 1);
                    help(size, index+1, s, current, dict, ans);
                    current = floating;

                }else{

                    help(size, index+1, s, current, dict, ans);

                }

            }

        }else if(numf == current.size()){

            for(size_t x = 0; x < current.size(); ++x){

                s[index] = current[x];
                current.erase(x, 1);
                help(size, index+1, s, current, dict, ans);
                current = floating;

            }

        }

    }else{

        help(size, index+1, s, current, dict, ans);
        return;

    }


}
