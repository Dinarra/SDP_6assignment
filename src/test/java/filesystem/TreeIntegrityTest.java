package filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeIntegrityTest {

    @Test
    void rejectsNegativeFileSize() {
        assertThrows(IllegalArgumentException.class, () -> new FileNode("a.txt", -1));
    }

    @Test
    void rejectsDuplicateSiblingNames() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("same.txt", 1));

        assertThrows(IllegalArgumentException.class,
                () -> root.addChild(new FileNode("same.txt", 2)));
    }

    @Test
    void rejectsSecondParent() {
        DirectoryNode first = new DirectoryNode("first");
        DirectoryNode second = new DirectoryNode("second");
        DirectoryNode shared = new DirectoryNode("shared");
        first.addChild(shared);

        assertThrows(IllegalArgumentException.class, () -> second.addChild(shared));
    }

    @Test
    void rejectsCycles() {
        DirectoryNode a = new DirectoryNode("a");
        DirectoryNode b = new DirectoryNode("b");
        a.addChild(b);

        assertThrows(IllegalArgumentException.class, () -> b.addChild(a));
    }

    @Test
    void acceptsValidNesting() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode child = new DirectoryNode("child");
        DirectoryNode grandChild = new DirectoryNode("grandChild");

        assertDoesNotThrow(() -> {
            root.addChild(child);
            child.addChild(grandChild);
            grandChild.addChild(new FileNode("deep.txt", 1));
        });
    }
}