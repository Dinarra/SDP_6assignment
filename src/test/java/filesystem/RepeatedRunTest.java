package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatedRunTest {

    @Test
    void totalSizeIsTheSameOnRepeatedRuns() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode inner = new DirectoryNode("inner");
        root.addChild(inner);
        root.addChild(new FileNode("a.txt", 10));
        inner.addChild(new FileNode("b.txt", 20));

        assertEquals(30, TotalSizeVisitor.of(root));
        assertEquals(30, TotalSizeVisitor.of(root));
    }

    @Test
    void statisticsAreTheSameOnRepeatedRuns() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("a.txt", 10));

        StatisticsVisitor first = new StatisticsVisitor();
        root.accept(first);
        StatisticsVisitor second = new StatisticsVisitor();
        root.accept(second);

        assertEquals(first.getFileCount(), second.getFileCount());
        assertEquals(first.getDirectoryCount(), second.getDirectoryCount());
        assertEquals(first.getMaxDepth(), second.getMaxDepth());
    }
}