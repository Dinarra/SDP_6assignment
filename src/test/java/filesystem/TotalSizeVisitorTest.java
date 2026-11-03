package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSizeVisitorTest {

    @Test
    void sumsFilesAcrossNestedDirectories() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode inner = new DirectoryNode("inner");
        root.addChild(inner);
        root.addChild(new FileNode("a.txt", 100));
        inner.addChild(new FileNode("b.txt", 200));

        assertEquals(300, TotalSizeVisitor.of(root));
    }
}