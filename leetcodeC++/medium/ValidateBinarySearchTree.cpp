/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool ma = false;
    bool mi = false;
    bool isValidBST(TreeNode* root) {
        
        if(root -> val == INT_MAX){
            
            if(ma != true){
                ma = true;
            }else{
                return false;
            }
            
            if(root -> right != NULL){
                return false;
            }
            if(root -> left != NULL){
                if((root -> left -> val) < INT_MAX){
                    return isValidBST(root -> left);
                }else{
                    return false;
                }
            }
            
            return helper(root -> left, INT_MIN, root -> val);
            
        }else if(root -> val == INT_MIN){
            
            if(mi != true){
                mi = true;
            }else{
                return false;
            }
        
            if(root -> left != NULL){
                return false;
            }
            if(root -> right != NULL){
                if((root -> right -> val) > INT_MIN){

                    return isValidBST(root -> right);

                }else{
                    return false;
                }
            }
            return helper(root -> right, root -> val, INT_MAX);
        }
        
        return (helper(root, INT_MIN, INT_MAX));
        
          
    }
    bool helper(TreeNode* root, long int min, long int max){
        
        if(root == NULL){
            return true;
        }
            
        if(root -> val >= max){
            if((max == INT_MAX) && (root -> val == INT_MAX)){
                return isValidBST(root);
            }
            return false;
        }
        if(root -> val <= min){
            if((min == INT_MIN) && (root -> val == INT_MIN)){
                return isValidBST(root);
            }
            return false;
        }
        
           
        return (helper(root->left, min, root->val) && helper(root->right, root->val, max));
    }
};