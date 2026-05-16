package FileSystem.FilteringSystem;

import FileSystem.FilteringSystem.interfaces.Filter;
import FileSystem.FileSystemModels.File;
import FileSystem.Operator;

public class SizeFilter implements Filter{
    private int targetSize;
    private Operator operator;

    public SizeFilter(Operator operator, int targetSize) {
        this.operator = operator;
        this.targetSize = targetSize;
    }

    @Override
    public boolean isValid(File file) {
        switch (operator) {
            case GREATER_THAN: return file.getSize() > targetSize;
            case LESS_THAN: return file.getSize() < targetSize;
            case EQUALS: return file.getSize() == targetSize;
            default: return false;
        }
    }
}
