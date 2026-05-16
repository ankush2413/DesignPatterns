package FileSystem.FileSystemModels;

public abstract class FileSystemElement {
    protected String name;
    
    public FileSystemElement(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
    public abstract boolean isDirectory();
    public abstract int getSize(); // Directories calculate size of contents
}
