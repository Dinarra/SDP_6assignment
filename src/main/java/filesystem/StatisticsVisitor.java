package filesystem;

public class StatisticsVisitor implements Visitor {

    private int fileCount = 0;
    private int directoryCount = 0;
    private int currentDepth = -1;
    private int maxDepth = 0;

    @Override
    public void visit(FileNode file) {
        fileCount++;
        maxDepth = Math.max(maxDepth, currentDepth + 1);
    }

    @Override
    public void visit(DirectoryNode directory) {
        directoryCount++;
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);

        for (Element child : directory.getChildren()) {
            child.accept(this);
        }

        currentDepth--;
    }

    public int getFileCount() { return fileCount; }
    public int getDirectoryCount() { return directoryCount; }
    public int getMaxDepth() { return maxDepth; }
}