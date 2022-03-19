#include "cmdhandler.h"
#include "util.h"
using namespace std;

// Complete
QuitHandler::QuitHandler()
{

}

// Complete
QuitHandler::QuitHandler(Handler* next)
    : Handler(next)
{

}

// Complete
bool QuitHandler::canHandle(const std::string& cmd) const
{
    return cmd == "QUIT";

}

// Complete
Handler::HANDLER_STATUS_T QuitHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{
    return HANDLER_QUIT;
}

// Complete
PrintHandler::PrintHandler()
{

}

// Complete
PrintHandler::PrintHandler(Handler* next)
    : Handler(next)
{

}

// Complete
bool PrintHandler::canHandle(const std::string& cmd) const
{
    return cmd == "PRINT";

}

// Complete
Handler::HANDLER_STATUS_T PrintHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{
    string name;

    if (!(instr >> name)) {
        return HANDLER_ERROR;
    }
    try {
        eng->display_page(ostr, name);
    }
    catch (std::exception& e) {
        return HANDLER_ERROR;
    }
    return HANDLER_OK;
}

// Add code for other handler class implementations below
IncomingHandler::IncomingHandler()
{

}

OutgoingHandler::OutgoingHandler()
{

}

AndHandler::AndHandler()
{

}
OrHandler::OrHandler()
{

}
DiffHandler::DiffHandler()
{

}
IncomingHandler::IncomingHandler(Handler* next)
    : Handler(next)
{

}
OutgoingHandler::OutgoingHandler(Handler* next)
    : Handler(next)
{

}
AndHandler::AndHandler(Handler* next)
    : Handler(next)
{

}
OrHandler::OrHandler(Handler* next)
    : Handler(next)
{

}
DiffHandler::DiffHandler(Handler* next)
    : Handler(next)
{

}
bool AndHandler::canHandle(const std::string& cmd) const
{
    
    return cmd == "AND";

}
bool OrHandler::canHandle(const std::string& cmd) const
{
    return cmd == "OR";

}
bool DiffHandler::canHandle(const std::string& cmd) const
{
    return cmd == "DIFF";

}
bool IncomingHandler::canHandle(const std::string& cmd) const
{
    return cmd == "INCOMING";

}
bool OutgoingHandler::canHandle(const std::string& cmd) const
{
    return cmd == "OUTGOING";

}

Handler::HANDLER_STATUS_T OutgoingHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{

    string name;

    instr >> name;

    if(name == ""){
        return HANDLER_ERROR;
    }else{

        if((eng -> retrieve_page(name)) == NULL){

            return HANDLER_ERROR;

        }else{

            display_hits(((eng -> retrieve_page(name)) -> outgoing_links()), ostr);

            return HANDLER_OK;
        }

    }

}
Handler::HANDLER_STATUS_T IncomingHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{

    string name;

    instr >> name;

    if(name == ""){
        return HANDLER_ERROR;
    }else{

        if((eng -> retrieve_page(name)) == NULL){

            return HANDLER_ERROR;

        }else{

            display_hits(((eng -> retrieve_page(name)) -> incoming_links()), ostr);

        return HANDLER_OK;
        }
        
    }

}
Handler::HANDLER_STATUS_T AndHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{
    AndWebPageSetCombiner combine;
   vector<string> vec;
   string temp;

    while(instr >> temp){
        vec.push_back(temp);
    }

    if(vec.size() > 0){
        display_hits((eng -> search(vec, &combine)), ostr);
    }

    return HANDLER_OK;

}

Handler::HANDLER_STATUS_T OrHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{
    OrWebPageSetCombiner combine;
   vector<string> vec;
   string temp;

    while(instr >> temp){
        vec.push_back(temp);
    }

    if(vec.size() > 0){
        display_hits((eng -> search(vec, &combine)), ostr);
    }

    return HANDLER_OK;
}

Handler::HANDLER_STATUS_T DiffHandler::process(SearchEng* eng, std::istream& instr, std::ostream& ostr)
{
    DiffWebPageSetCombiner combine;
   vector<string> vec;
   string temp;

    while(instr >> temp){
        vec.push_back(temp);
    }

    if(vec.size() > 0){
        display_hits((eng -> search(vec, &combine)), ostr);
    }

    return HANDLER_OK;
}