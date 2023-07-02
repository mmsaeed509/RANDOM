#include <iostream>
#include <algorithm>

using namespace std;

template<class T>
        class Node{

        public:
            T data;
            Node<T> *next;
        };

template<class T>
        class LinkedList{
        private:

            Node<T> *head, *tail;

        public:
            LinkedList(){
                head=NULL;
                tail=NULL;
            }

            LinkedList(T value, int initial_size){
                Node<T> *tmp=new Node<T>;
                tmp->data=value;
                tmp->next=NULL;
                head=tmp;
                tail=tmp;
                tmp=NULL;

                for(int i=0;i<initial_size-1;++i){

                    Node<T> *tmp=new Node<T>;
                    tmp->data=value;
                    tmp->next=NULL;
                    tail->next=tmp;
                    tail=tmp;

                }

            }


            LinkedList<T>& operator = (LinkedList<T> anotherLinkedList){

                if( this == &anotherLinkedList )
                    return *this;

                iterator itr =anotherLinkedList.begin( );
                this->push_back(*itr);

                do{
                    ++itr;
                    this->push_back(*itr);

                }while(itr != anotherLinkedList.end());

                return *this;

            }

            class iterator{

            public:

                Node<T> *NodeIte;
                Node<T> *headIte;

            public:

                iterator(){

                    NodeIte=nullptr;
                    headIte=nullptr;

                }

                iterator(Node<T> *PtrNode,Node<T> *h){

                    NodeIte=PtrNode;
                    headIte=h;

                }

                void operator++(){

                    if (NodeIte->next == NULL ){
                        cout << "\nOut of Range\n";

                        return;

                    }

                    NodeIte = NodeIte->next;

                }

                void operator -- (){

                    LinkedList<T>:: iterator i;
                    i.NodeIte=this->headIte;
                    i.headIte=this->headIte;


                    if (this->headIte == this->NodeIte ){

                        cout<<"\nOut of Range\n";

                    }else{

                        while (i.NodeIte->next != NULL ){
                            if(i.NodeIte->next == this->NodeIte){
                                this->NodeIte = i.NodeIte;
                                return;

                            }

                            ++i;

                        }

                    }

                }


                T& operator*() const {

                    return NodeIte->data;

                }

                bool operator==(const iterator& itr) const {

                    return NodeIte== itr.NodeIte;

                }

                bool operator!=(const iterator& itr) const {

                    return NodeIte != itr.NodeIte;

                }

            };

            iterator erase(iterator position) {

                Node<T> *toDelete = position.NodeIte->next;
                position.NodeIte->next = position.NodeIte->next->next;
                if (toDelete == tail) tail = position.NodeIte;
                delete toDelete;
                return position;

            }

            void push_back(T value){

                Node<T> *temp=new Node<T>;
                temp->data=value;
                temp->next=NULL;

                if(head==NULL){

                    head=temp;
                    tail=temp;
                    temp=NULL;

                }else{

                    tail->next=temp;
                    tail=temp;

                }

            }

            void insert(int pos, T value){

                Node<T> *p=new Node<T>;
                Node<T> *cur;
                Node<T> *temp=new Node<T>;
                cur=head;

                for(int i=1; i<pos; i++){

                    p=cur;
                    cur=cur->next;

                }

                temp->data=value;
                p->next=temp;
                temp->next=cur;

            }


            void display(){

                Node<T> *temp;
                temp=head;

                while(temp!=NULL){

                    cout<<temp->data<<" ";
                    temp=temp->next;
                }

                cout<<endl;

            }

            void insert_start(T value){

                Node<T> *temp=new Node<T>;
                temp->data=value;
                temp->next=head;
                head=temp;

            }

            void delete_first(){

                Node<T> *temp;
                temp=head;
                head=head->next;
                delete temp;

            }

            void delete_last(){

                Node<T> *current;
                Node<T> *previous=new Node<T>;
                current=head;

                while(current->next!=NULL){

                    previous=current;
                    current=current->next;

                }

                tail=previous;
                previous->next=NULL;
                delete current;

            }

            void delete_position(int pos){

                Node<T> *current;
                Node<T> *previous=new Node<T>;
                current=head;

                for(int i=1;i<pos;i++){

                    previous=current;
                    current=current->next;

                }

                previous->next=current->next;

            }

            bool search(T value){

                Node<T> *temp;
                temp=head;

                while(temp!=NULL){

                    if(temp->data == value) return 1;
                    temp=temp->next;

                }

                return 0;

            }

            iterator begin() {

                return iterator(head,head);

            }

            iterator end() {

                return iterator(tail,head);

            }

            int size(){

                int count = 0;
                Node<T> *temp;
                temp=head;

                while(temp!=NULL){

                    ++count;
                    temp=temp->next;

                }

                return count;

            }

            ~LinkedList(){

                do{

                    delete_last();

                }while(head != tail);

                delete head;
                delete tail;

            }

        };

int main(){

    LinkedList<int> link(5,2);
    link.push_back(5);
    link.push_back(7);
    link.push_back(8);
    cout<<link.size()<<endl;
    link.display();
    link.insert(2,5);
    link.display();
    link.insert_start(4);
    link.display();
    link.delete_first();
    link.display();
    link.delete_last();
    link.display();
    link.delete_position(2);
    link.display();
    cout<<link.search(5)<<endl;
    cout<<link.search(10);
    cout<<"\n\n\n\n";
    LinkedList<int>:: iterator it = link.begin();
    LinkedList<int>:: iterator it2 = link.begin();
    cout<<*it;
    ++it;
    cout<<*it;
    ++it;
    cout<<*it;
    ++it;
    cout<<*it;

    ++it;       ///out of range

    --it;
    cout<<*it;
    ++it;
    cout<<*it;

    link.erase(it2);

    cout<<endl;

    LinkedList<int> l2;
    l2 = link;
    l2.display();

    return 0;
}
