#include "equal-paths.h"
using namespace std;


// You may add any prototypes of helper functions here

int length(Node* root);

bool equalPaths(Node * root)
{
    // Add your code below
    if(root == NULL){
        return 1;
    }else{
        int l = length(root -> left);
        int r = length(root -> right);

        if(l - r == 0){
            return 1;
        }else if(((l - r) >= -1) && ((l-r) <= 1) && (root -> left == NULL || root -> right == NULL)){
            return 1;
        }else{
            return 0;
        }
    }

}

int length(Node * root){

    if(root == NULL){
        return 0;
    }

    int x;

    if(length(root -> right) > length(root -> left)){
        x = 1 + length(root -> right);
        return x;
    }else{
        x = 1 + length(root -> left);
        return x;
    }


}

