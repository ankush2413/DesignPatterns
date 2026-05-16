package FileSystem.FilteringSystem;

import FileSystem.FilteringSystem.interfaces.Filter;
import FileSystem.FileSystemModels.File;

public class ExtensionFilter implements Filter{
    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean isValid(File file) {
        return file.getExtension().equalsIgnoreCase(this.extension);
    }
}
