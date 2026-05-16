package FileSystem.FileSystemModels;

public class File extends FileSystemElement {
    
    private final String extension;
    private final int size;
    private byte[] content; // Placeholder for actual file content

    public File(String name, String extension, int size) {
        super(name);
        this.extension = extension;
        this.size = size;
    }

    public String getExtension() { return extension; }
    
    @Override
    public int getSize() { return size; }
    
    @Override
    public boolean isDirectory() { return false; }

}
