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
    ListNode* heady;
    ListNode* reverseList(ListNode* head) {
        
        if (head == NULL)
            return NULL;
        
        heady = head;
        
        helper(heady);
        
        return heady;
        
    }
    
    ListNode* helper(ListNode* node){
    
        if (node->next == NULL) {
            heady = node;
            return node;
        }
        ListNode* temp = helper(node->next);
        temp->next = node;
        node->next = NULL;
        return node;
    }
};