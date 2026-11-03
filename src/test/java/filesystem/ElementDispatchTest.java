package filesystem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementDispatchTest {

    private static class RecordingVisitor implements Visitor {
        final List<String> calls = new ArrayList<>();

        @Override
        public void visit(FileNode file) {
            calls.add("file");
        }

        @Override
        public void visit(DirectoryNode directory) {
            calls.add("directory");
        }
    }

    @Test
    void fileReachesFileVisitMethod() {
        RecordingVisitor visitor = new RecordingVisitor();
        new FileNode("a.txt", 5).accept(visitor);

        assertEquals(List.of("file"), visitor.calls);
    }

    @Test
    void directoryReachesDirectoryVisitMethod() {
        RecordingVisitor visitor = new RecordingVisitor();
        new DirectoryNode("root").accept(visitor);

        assertEquals(List.of("directory"), visitor.calls);
    }
}