package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsVisitorTest {

    @Test
    void countsFilesDirectoriesAndMaxDepthInNestedTree() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode a = new DirectoryNode("a");
        DirectoryNode b = new DirectoryNode("b");
        root.addChild(a);
        a.addChild(b);
        root.addChild(new FileNode("f1.txt", 10));
        a.addChild(new FileNode("f2.txt", 20));
        b.addChild(new FileNode("f3.txt", 30));

        StatisticsVisitor stats = new StatisticsVisitor();
        root.accept(stats);

        assertEquals(3, stats.getFileCount());
        assertEquals(3, stats.getDirectoryCount());
        assertEquals(3, stats.getMaxDepth());
    }

    @Test
    void handlesEmptyRootDirectory() {
        DirectoryNode root = new DirectoryNode("root");

        StatisticsVisitor stats = new StatisticsVisitor();
        root.accept(stats);

        assertEquals(0, stats.getFileCount());
        assertEquals(1, stats.getDirectoryCount());
        assertEquals(0, stats.getMaxDepth());
    }
}