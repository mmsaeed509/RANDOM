#include <bits/stdc++.h>
using namespace std;

// using
class word
{
public:
    string key;
    vector <int> index;

    friend ostream& operator <<(ostream & out, word key)
    {
        out<<key.key<<" ";
        for (int i=0; i<key.index.size(); i++)
        {
            if (i==key.index.size()-1)
            {
                out<<key.index[i];
                break;
            }
            out<<key.index[i]<<", ";
        }
        return out;
    }

    bool operator<( word b)
    {
        string c,d;
        for (int i=0; i< key.length() ; ++i)
        {
            c+=tolower(key[i]);
        }
        for (int i=0; i<b.key.length() ; ++i)
        {
            d+=tolower(b.key[i]);
        }
        return (c<d);
    }

    bool operator>( word b)
    {
        string c,d;
        for (int i=0; i< key.length() ; ++i)
        {
            c+=tolower(key[i]);
        }
        for (int i=0; i<b.key.length() ; ++i)
        {
            d+=tolower(b.key[i]);
        }
        return (c>d);
    }

};




//Node class.
template<class T>
class BSTNode
{
public:
    T key;
    BSTNode* right;//greater nodes on right.
    BSTNode* left;//smaller nodes on left.
public:
    //setting initial values to left and right pointers.
    BSTNode()
    {
        right = NULL;
        left = NULL;
    }
    //to be able to send data only as a parameter.
    BSTNode(T DATA, BSTNode* right = 0, BSTNode* left = 0)
    {
        key = DATA;
        this->right = right;
        this->left = left;
    }
    //Getters.
    BSTNode*getleft()
    {
        return left;
    }
    BSTNode*getright()
    {
        return right;
    }
    T getkey()
    {
        return key;
    }
};




// using
//class with all used functions .
template<class T>
class BSTFCI
{
public:
    //Base node in BST.
    BSTNode<T>* root;
public:
    BSTFCI()
    {
        root = NULL;
    }

    BSTFCI(T data)
    {
        root->key = data;
        root->left = root->right = NULL;
    }

    //Recursion Function - Inserting new node to the binary search tree.
    BSTNode<T>* Insert_Node(T DATA, BSTNode<T>* node)
    {
        //InCase there is no node create root node.
        if (node == NULL)
        {
            node = new BSTNode<T>;
            node->key = DATA;
            node->left = node->right = NULL;
        }

            //InCase given key > node key -- > Go right subtree.
        else if (DATA > node->key)
            node->right = Insert_Node(DATA, node->right); //Calling it self.

            //InCase given key < node key -- > Go left subtree.
        else if (DATA < node->key)
            node->left = Insert_Node(DATA, node->left);

        return node;
    }

    //Function to call insert function.
    void Insert(T DATA)
    {
        root = Insert_Node(DATA, root);
    }

    //Printing nodes left then root then right.
    void PrintTreeInOrder(BSTNode<T> * node)
    {
        if (node != NULL)
        {
            PrintTreeInOrder(node->left);
            cout << node->key << endl;
            PrintTreeInOrder(node->right);
        }
    }

    //Function to call PrintTreeInOrder function.
    void PrintTreeInOrder()
    {
        PrintTreeInOrder(root);
        cout << endl<<endl;
    }



    // using
    //function to calculate the height of each node .
    template<class T2>
    int height(BSTNode<T2>* node);

    //Function returns true if BST balanced.
    template<class T2>
    friend bool isBalanced( BSTNode<T2> *root);


// using
    void get_nodes ()
    {
        vector<word>a;

        string s;
        string b="";
        word obj;
        int n;
        cout<<"Enter no. lines: ";
        cin>>n;
        cin.ignore();
        cout<<"Enter your text: "<<endl;
        for (int i=0; i<n; i++)
        {
            getline(cin,s);
            for (int j=0; j<s.length(); j++)
            {
                bool x=0;
                if (s[j]==' '||s[j]=='.'||s[j]==',')
                {
                    x=1;
                    for (int k=0; k<a.size(); k++)
                    {
                        if (a[k].key==b)
                        {
                            a[k].index.push_back(i+1);
                            b="";
                            break;
                        }
                    }
                    if (b!="")
                    {
                        obj.key=b;
                        obj.index.clear();
                        obj.index.push_back(i+1);
                        a.push_back(obj);
                        b="";
                        continue;
                    }

                }

                if (!x)
                    b+=s[j];
                if (j==s.length()-1)
                {
                    for (int k=0; k<a.size(); k++)
                    {
                        if (a[k].key==b)
                        {
                            a[k].index.push_back(i+1);
                            b="";
                            break;
                        }
                    }
                    if (b!="")
                    {
                        obj.key=b;
                        obj.index.clear();
                        obj.index.push_back(i+1);
                        a.push_back(obj);
                        b="";
                        continue;
                    }

                }

            }
        }
        for (int i=0; i<a.size(); i++)
        {
            Insert(a[i]);
        }
    }
};

template<class T>
template<class T2>
int BSTFCI<T>::height(BSTNode<T2> *node) {
    return 0;
}

template<class T2>
bool isBalanced(BSTNode<T2> *root) {
    return 0;
}

int main()
{

    /**
    I am for truth,
    no matter who tells it.
    I am for justice,
    no matter who it is for or against.
    Malcolm X
    **/

    BSTFCI <word> * test4 = new BSTFCI <word>();
    test4->get_nodes();
    test4->PrintTreeInOrder();

    return 0;
}
