import java.util.Arrays;

public class ArrayListGenerics<E> {
    private int size;
    private static final int defaultCapacity = 10;
    Object[] dataElements;
    private final Object[] defaultObject = {};

    public ArrayListGenerics(int initialCapacity){
        if (initialCapacity >= 0){
            this.dataElements = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayListGenerics(){
        this.dataElements = defaultObject;
    }

    public int size(){
        return this.size;
    }

    public <E> void add(E el, Object[] data, int s){
        if (s == data.length){
            data = grow();
        }
        data[s] = el;
        this.size = s + 1;
    }

    public <E> void add(E el){
        add(el, this.dataElements, this.size);
    }

    private Object[] grow(int minCapacity){
        return dataElements = Arrays.copyOf(dataElements, minCapacity);
    }
    public Object[] grow(){
        return grow(size+1);
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public E get(int index){
        if(index < 0 || index > this.size-1){
            throw new IndexOutOfBoundsException();
        } else {
            return (E) this.dataElements[index];
        }
    }

    public void removeLast(){
        this.dataElements[this.size - 1] = null;
        this.size = size - 1;
    }

}
