package FileSystem;

import java.util.Arrays;
import java.util.List;

import FileSystem.FileSystemModels.Directory;
import FileSystem.FileSystemModels.File;
import FileSystem.FilteringSystem.AndFilter;
import FileSystem.FilteringSystem.interfaces.Filter;
import FileSystem.FilteringSystem.ExtensionFilter;
import FileSystem.FilteringSystem.SizeFilter;

/*
Design an in-memory File System capable of storing files inside multiple nested directories.
Implement a flexible Search Engine that takes dynamic query parameters (e.g., File Size, Extension, Name) and returns all matching files.
The system must support combining multiple query parameters (e.g., "Find files that are > 5MB AND have the '.txt' extension").

Functional Requirements:

1. The system must support files and directories. Directories can contain files and other directories (nested).
2. Users can filter by Name, Extension, and Size (Greater Than, Less Than, Equal To).
3. Users must be able to combine filters using logical operators (AND, OR).
4. Adding a new filter type (e.g., DateFilter) must require zero changes to the core search algorithm.
*/

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello");
        Directory root = new Directory("root");
        Directory documents = new Directory("documents");
        Directory pictures = new Directory("pictures");

        root.addElement(documents);
        root.addElement(pictures);

        // Files with same name in multiple folders
        documents.addElement(new File("report", "pdf", 500));
        documents.addElement(new File("notes", "txt", 50));
        pictures.addElement(new File("vacation", "jpg", 2000));
        pictures.addElement(new File("report", "txt", 100));
         pictures.addElement(new File("testFile", "txt", 100)); // Same name, different extension & folder

        // 2. Setup Search Engine
        FileSearchEngine engine = new FileSearchEngine();

        // 3. Create Query Parameters (Find files that are .txt AND larger than 60 bytes)
        Filter txtFilter = new ExtensionFilter("txt");
        Filter sizeFilter = new SizeFilter(Operator.GREATER_THAN, 60);
        
        Filter combinedQuery = new AndFilter(Arrays.asList(txtFilter, sizeFilter));

        // 4. Execute Search
        List<File> matchingFiles = engine.search(root, combinedQuery);

        // 5. Output Results
        System.out.println("Matching Files:");
        for (File file : matchingFiles) {
            System.out.println("- " + file.getName() + "." + file.getExtension() + " (" + file.getSize() + " bytes)");
        }
        // Output: - report.txt (100 bytes)
    }
}
