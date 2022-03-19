#ifndef COMBINERS_H
#define COMBINERS_H

#include "searcheng.h"

/*** Define Derived WebPageSetCombiners (for AND, OR, DIFF) classes below ***/
class AndWebPageSetCombiner : public WebPageSetCombiner{
    
    public:
   
        WebPageSet combine(const WebPageSet& setA, const WebPageSet& setB);
  

    private:
    WebPageSet setC;
};

class OrWebPageSetCombiner : public WebPageSetCombiner{


    public:
        WebPageSet combine(const WebPageSet& setA, const WebPageSet& setB);
   

    private:
    WebPageSet setC;
};

class DiffWebPageSetCombiner : public WebPageSetCombiner{


    public:
        WebPageSet combine(const WebPageSet& setA, const WebPageSet& setB);
   

    private:
    WebPageSet setC;
};


#endif