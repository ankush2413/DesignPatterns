package FileSystem.FileSystemModels;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemElement {
    
    private List<FileSystemElement> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    public List<FileSystemElement> getChildren() {
        return children;
    }

    @Override
    public int getSize() {
        return children.stream().mapToInt(FileSystemElement::getSize).sum();
    }

    @Override
    public boolean isDirectory() { return true; }
}
