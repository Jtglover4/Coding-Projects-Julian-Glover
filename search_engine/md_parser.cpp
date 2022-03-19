#include <iostream>
#include <fstream>
#include <stdexcept>
#include <cctype>
#include "md_parser.h"
#include "util.h"

using namespace std;

typedef enum { NORMALTEXT, LINKTEXT, ISLINK, LINKURL } PARSE_STATE_T;

// To be completed
void MDParser::parse(std::istream& istr, std::set<std::string>& allSearchableTerms, std::set<std::string>& allOutgoingLinks)
{

    // Remove any contents of the sets before starting to parse.
    allSearchableTerms.clear();
    allOutgoingLinks.clear();

    // Complete your code below
    char c = istr.get();
    PARSE_STATE_T current = NORMALTEXT;
    string term = "";
    string link = "";

    while(!istr.fail()){
        
        if(current == NORMALTEXT){
            
            if (!isalnum(c)){

                if(c == '['){
                    current = LINKTEXT;
                }
                if(term != ""){
                    term = conv_to_lower(term);
                    allSearchableTerms.insert(term);
                    term = "";
                }
                
                
            }else{
                
                term += c;

            }

        }else if(current == LINKTEXT){

            if(isalnum(c)){

                term += c;

            }else{
                if(c == ']'){
                    current = ISLINK;
                }
                if(term != ""){
                    term = conv_to_lower(term);
                    allSearchableTerms.insert(term);
                    term = "";
                }
            }

        }else if(current == ISLINK){

            if(c == '['){
                current = LINKTEXT;
            }else if(c == '('){
                current = LINKURL;
            }else{
                current = NORMALTEXT;
                term += c;
            }

        }else{

                if(c == ')'){
                    current = NORMALTEXT;

                    if(link != ""){
                        link = conv_to_lower(link);
                        allOutgoingLinks.insert(link);
                        link = "";
                    }

                }else{
                    link += c;
                }
             
            }
            
            c = istr.get();
        }
        if(term != ""){
            term = conv_to_lower(term);
            allSearchableTerms.insert(term);
        }
        term = "";

}

// To be completed
std::string MDParser::display_text(std::istream& istr)
{
    std::string temp;

    char j = istr.get();

    while(!istr.fail()){
        if(j != '('){
            temp += j;
        }else{
            while(j != ')'){
                if(istr.peek() != EOF){
                    j = istr.get();
                }

            }
            if(istr.peek() != EOF){
                j = istr.get();
                temp += j;
            }

        }

        // if(istr.peek() != EOF){
        // temp += j;
        //  }
        j = istr.get();
    }

    return temp;

}


