package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

public class Node<T> implements List<T> {

    private T element;
    private List<T> tail;

    public Node(T element,List<T> tail) {
        this.element = element;
        this.tail = tail;
    }

    @Override
    public T head() {
        return this.element;
    }

    @Override
    public @NotNull List<T> tail() {
        return this.tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public @NotNull List<T> reverse() {

        List<T> reverse = List.nil();
        List<T> aux = this;
        while (!aux.isEmpty()){
            reverse = List.cons(aux.head(), reverse);
            aux = aux.tail();
        }
        return reverse;

    }


}
