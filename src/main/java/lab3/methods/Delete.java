package lab3.methods;

public class Delete extends AbstractMethodsOwnStringBuilder {

    private final int start;
    private final int end;
    private String deletedStr;

    public Delete(StringBuilder stringBuilder, int start, int end) {
        super(stringBuilder);
        if (start > end) {
            throw new IndexOutOfBoundsException("start > end");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        super.execute();
        deletedStr = stringBuilder.substring(start, end);
        if (start - end != 1) {
            stringBuilder.delete(start, end);
        } else {
            stringBuilder.deleteCharAt(start);
        }
        super.setStatusIsExecute();
    }

    @Override
    public void undo() {
        super.undo();
        stringBuilder.insert(start, deletedStr);
        super.setStatusIsUnExecute();
    }
}
