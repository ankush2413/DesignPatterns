package FileSystem.FilteringSystem.interfaces;

import FileSystem.FileSystemModels.File;

public interface Filter {
    boolean isValid(File file);
}
