package FileSystem.FilteringSystem;

import FileSystem.FilteringSystem.interfaces.Filter;

import java.util.List;

import FileSystem.FileSystemModels.File;

public class AndFilter implements Filter {
    private List<Filter> filters;

    public AndFilter(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean isValid(File file) {
        for (Filter filter : filters) {
            if (!filter.isValid(file)) {
                return false; // Fast fail
            }
        }
        return true;
    }
}
