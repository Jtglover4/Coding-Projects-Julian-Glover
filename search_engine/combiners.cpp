#include "searcheng.h"
#include "combiners.h"

// Complete the necessary code
WebPageSet AndWebPageSetCombiner :: combine(const WebPageSet& setA, const WebPageSet& setB){
    setC.clear();
    std::set<WebPage*>::const_iterator it;

    for(it = setA.begin(); it != setA.end(); ++it){

        if(setB.find(*it) != setB.end()){
            setC.insert(*it);
        }

    }
    return setC;
}

WebPageSet OrWebPageSetCombiner :: combine(const WebPageSet& setA, const WebPageSet& setB){
    setC.clear();

    std::set<WebPage*>::const_iterator it;

    for(it = setA.begin(); it != setA.end(); ++it){

        setC.insert(*it);        

    }
    for(it = setB.begin(); it != setB.end(); ++it){

        setC.insert(*it);        

    }
    return setC;
}
WebPageSet DiffWebPageSetCombiner :: combine(const WebPageSet& setA, const WebPageSet& setB){
    setC.clear();

    std::set<WebPage*>::const_iterator it;

    for(it = setA.begin(); it != setA.end(); ++it){

        if(setB.find(*it) == setB.end()){
            setC.insert(*it);
        }

    }
    return setC; 
}