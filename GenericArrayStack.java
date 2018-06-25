public class GenericArrayStack<E> implements Stack<E> {
   
    // ADD YOUR INSTANCE VARIABLES HERE

    private E[] elems;
    private int top;

    // Constructor
    @SuppressWarnings("unchecked")
    public GenericArrayStack( int capacity ) {
        
        elems = (E[]) new Object[capacity];
        top = 0;
    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {

        return top == 0;
    }

    @SuppressWarnings("unchecked")
    public void push( E elem ) {

        if(top == elems.length){

            E[] temp = (E[]) new Object[elems.length+1];

            for(int i = 0; i < top; i++){

                temp[i] = elems[i]; 

            }

            elems = temp.clone();

        }
        else{

            this.elems[top] = elem;
            top++;

        }

    }
    public E pop() {

        E temp;

        temp = elems[top-1];
        elems[top-1] = null;
        top--;

        return temp;
    }

    public E peek() {

        return elems[top-1];
    }
}
