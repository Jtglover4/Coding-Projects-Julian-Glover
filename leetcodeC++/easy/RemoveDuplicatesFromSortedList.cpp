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
    ListNode* deleteDuplicates(ListNode* head) {
        
        if(head == NULL){
            return head;
        }
        
        ListNode* temp;
        
        ListNode* thead = head;
        
        while(thead -> next != NULL){
            
            temp = thead -> next;
            
            if(thead -> val == temp -> val){
                
                while(thead-> val == temp -> val){
                    
                    if(temp -> next != NULL){
                        thead -> next = temp -> next;
                        temp = thead -> next;
                    }else{
                        thead -> next = NULL;
                        break;
                    }
                    
                }
            }
            if(thead -> next != NULL){
                thead = thead->next;
            }
        }
        
        return head;
        
    }
};