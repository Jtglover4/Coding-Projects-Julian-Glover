/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    bool isPalindrome(ListNode* head) {
        
        vector<int> v;
        
        append(head, &v);
        
        int j = v.size()-1;
        
        for(int x = 0; x <= j; x++){
            
            if(v[x] != v[j]){
                return false;
            }
            j--;
        }
        
        return true;
        
        
    }
    void append(ListNode* head, vector<int>* vec){
        
        while(head != NULL){
            
            vec -> push_back(head->val);
            head = head -> next;
            
        }
        
    }
};