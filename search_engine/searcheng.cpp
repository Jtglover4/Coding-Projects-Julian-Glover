#include <map>
#include <set>
#include <vector>
#include <iostream>
#include <fstream>
#include "searcheng.h"

using namespace std;

// Helper function that will extract the extension of a filename
std::string extract_extension(const std::string& filename);

std::string extract_extension(const std::string& filename)
{
    size_t idx = filename.rfind(".");
    if (idx == std::string::npos) {
        return std::string();
    }
    return filename.substr(idx + 1);
}


// To be updated as needed 
SearchEng::SearchEng()
{

}

// To be completed
SearchEng::~SearchEng()
{
    
    std::map<std::string, PageParser*> :: iterator it;
    it = parsers_.begin();

    while(it != parsers_.end()){
        delete it->second;
        ++it;
    }

    std::map<std::string, WebPage*> :: iterator it2;
    it2 = tofile.begin();
    while(it2 != tofile.end()){
        delete it2 -> second;
        ++it2;
    }
    
}

// Complete
void SearchEng::register_parser(const std::string& extension, PageParser* parser)
{
    if (parsers_.find(extension) != parsers_.end())
    {
        throw std::invalid_argument("parser for provided extension already exists");
    }
    parsers_.insert(make_pair(extension, parser));
}

// Complete
void SearchEng::read_pages_from_index(const std::string& index_file)
{
    ifstream ifile(index_file.c_str());
    if(ifile.fail()) {
        cerr << "Unable to open index file: " << index_file << endl;
    }

    // Parse all the files
    string filename;
    while(ifile >> filename) {
#ifdef DEBUG
        cout << "Reading " << filename << endl;
#endif
        read_page(filename);
    }
    ifile.close();
}

// To be completed
void SearchEng::read_page(const string& filename)
{

    string ext = extract_extension(filename);

    if(parsers_.find(ext) == parsers_.end()){
        throw std::logic_error("Error");
    }

    ifstream ifile(filename.c_str());

    if(ifile.fail()){
        throw std::invalid_argument("Can't open file");
    }else{

        parsers_[ext] -> parse(ifile, terms, outlinks);

        if(tofile.find(filename) == tofile.end()){
            wpage = new WebPage(filename);
            tofile.insert(std::make_pair(filename, wpage));
            wpage -> all_terms(terms);
        }else{
            wpage = tofile.find(filename)->second;
            wpage -> all_terms(terms);
        }

        StringSet::iterator it;

        for(it = terms.begin(); it != terms.end(); ++it){

            if(toset.find(*it) == toset.end()){
                WebPageSet Nset;
                Nset.insert(wpage);
                toset.insert(make_pair(*it, Nset));

            }else{
                toset.at(*it).insert(wpage);
            }

        }
        for(it = outlinks.begin(); it != outlinks.end(); ++it){

            if((tofile.find(*it) == tofile.end())){

                link = new WebPage(*it);
                tofile.insert(make_pair(*it, link));
                link -> add_incoming_link(wpage);
                wpage -> add_outgoing_link(link);

            }else{

                link = tofile.find(*it)->second;
                link -> add_incoming_link(wpage);
                wpage -> add_outgoing_link(link);

            }
        }
        
        }
}

// To be completed
WebPage* SearchEng::retrieve_page(const std::string& page_name) const
{
    if(tofile.find(page_name) != tofile.end()){
        return tofile.at(page_name);
    }else{
        return NULL;
    }
}

// To be completed
void SearchEng::display_page(std::ostream& ostr, const std::string& page_name) const
{
   if(tofile.find(page_name) == tofile.end()){
        throw std::invalid_argument("file doesn't exist");
    }
   
    ifstream ifile(page_name.c_str());

    if(ifile.fail()){
        throw std::invalid_argument("Can't open file");
    }

    string ext = extract_extension(page_name);

    if(parsers_.find(ext) == parsers_.end() && !ifile.fail()){
        throw std::logic_error("no registered parser");
    }

    ostr << (parsers_.find(ext)->second-> display_text(ifile)); 
}

// To be completed
WebPageSet SearchEng::search(const std::vector<std::string>& terms, WebPageSetCombiner* combiner) const
{
    WebPageSet tempset;
    
    for(unsigned int i = 0; i < terms.size(); i++){
        
        if(toset.find(terms[i]) != toset.end() && tempset.empty() == 1){

            
                tempset = toset.at(terms[i]);

        }else if(toset.find(terms[i]) != toset.end() && tempset.empty() != 1){

                tempset = combiner -> combine(tempset, toset.at(terms[i]));            

        }else if (toset.find(terms[i]) == toset.end()){

            WebPageSet temp1;
            WebPageSet combined;
            combined = combiner -> combine(tempset, temp1);
            return combined;

        }

    }
    return tempset;
}

// Add private helper function implementations here

