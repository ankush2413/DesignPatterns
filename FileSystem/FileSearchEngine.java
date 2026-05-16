package FileSystem;

import java.util.ArrayList;
import java.util.List;

import FileSystem.FileSystemModels.Directory;
import FileSystem.FileSystemModels.FileSystemElement;
import FileSystem.FileSystemModels.File;
import FileSystem.FileSystemModels.Directory;
import FileSystem.FilteringSystem.interfaces.Filter;

public class FileSearchEngine {
    public List<File> search(Directory root, Filter filter) {
        List<File> result = new ArrayList<>();
        searchHelper(root, filter, result);
        return result;
    }

    // Standard Depth First Search (DFS) for hierarchical trees
    private void searchHelper(Directory directory, Filter filter, List<File> result) {
        for (FileSystemElement element : directory.getChildren()) {
            if (element.isDirectory()) {
                // If it's a folder, recurse into it
                searchHelper((Directory) element, filter, result);
            } else {
                // If it's a file, apply the query parameters (filters)
                File file = (File) element;
                if (filter.isValid(file)) {
                    result.add(file);
                }
            }
        }
    }
}
