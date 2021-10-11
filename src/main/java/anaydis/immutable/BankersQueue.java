package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

public class BankersQueue<T> implements Queue<T> {

    private List<T> in;
    private List<T> out;

    public BankersQueue(List<T> in, List<T> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public @NotNull Queue<T> enqueue(@NotNull T value) {
        return new BankersQueue<>(List.cons(value,this.in), this.out);
    }

    @Override
    public @NotNull Result<T> dequeue() {
        Result<T> result;
        if (out.isEmpty() && !in.isEmpty()){
            List<T> outAux = in.reverse();
            List<T> inAux = List.nil();
            return result = new Result<>(outAux.head(),new BankersQueue<>(inAux,outAux.tail()));
        }
        else {
            return result = new Result<>(out.head(),new BankersQueue<>(this.in,this.out.tail()));
        }
    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
