#include <bits/stdc++.h>

using namespace std;
class Node{

    public:
           int data;
           Node *next;
};

class Stack{

    private:
            Node *top;

    public:
            Stack(){top=NULL;}
            void push(int x);
            int pop();
            void Display();
};

void Stack::push(int x){

    Node *t=new Node;

    if(t==NULL)
        cout<<"Stak is Full\n";
    else
    {
        t->data=x;
        t->next=top;
        top=t;
    }
}
int Stack::pop(){

    int x=-1;
    if(top==NULL)
        cout<<"Stack isEmpty\n";
    else
    {
        x=top->data;
        Node *t=top;
        top=top->next;
        delete t;
    }
    return x;

}
void Stack::Display(){

    Node *p=top;
    while(p!=NULL)
    {
        cout<<p->data<<" ";
        p=p->next;
    }
    cout<<endl;
}

    int evaluatePostfix(char* exp)
    {

        Stack* stack;
        stack->push(strlen(exp));
        int i;

        for (i = 0; exp[i]; ++i)
        {
            if(exp[i] == ' ')continue;

            else if (isdigit(exp[i]))
            {
                int num=0;

                while(isdigit(exp[i]))
                {
                    num = num * 10 + (int)(exp[i] - '0');
                    i++;
                }
                i--;

                stack->push(num);
            }

            else
            {
                int val1 = stack->pop();
                int val2 = stack->pop();

                switch (exp[i])
                {
                    case '+':stack->push(val2 + val1); break;
                    case '-':stack->push(val2 - val1); break;
                    case '*':stack->push(val2 * val1); break;
                    case '/':stack->push(val2 / val1); break;

                }
            }
        }
        return stack->pop();
    }

int main(){

    Stack stk;

    char exp[] = "7 5 * ";
    cout << evaluatePostfix(exp);

    return 0;

}