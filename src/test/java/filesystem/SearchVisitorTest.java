package filesystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchVisitorTest {

    @Test
    void combinesNameAndMinimumSizeConditions() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("report.txt", 50));
        root.addChild(new FileNode("photo.jpg", 500));
        root.addChild(new FileNode("big-report.txt", 100));

        SearchVisitor search = new SearchVisitor(".txt", 100);
        root.accept(search);

        assertEquals(List.of("big-report.txt"), search.getMatches());
    }

    @Test
    void returnsEmptyListWhenNothingMatches() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("a.txt", 10));

        SearchVisitor search = new SearchVisitor("pdf", 0);
        root.accept(search);

        assertTrue(search.getMatches().isEmpty());
    }
    @Test
    void producesSortedRootRelativePaths() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode work = new DirectoryNode("work");
        root.addChild(docs);
        docs.addChild(work);
        work.addChild(new FileNode("c.txt", 1));
        docs.addChild(new FileNode("b.txt", 1));
        root.addChild(new FileNode("a.txt", 1));

        SearchVisitor search = new SearchVisitor(".txt", 0);
        root.accept(search);

        assertEquals(List.of("a.txt", "docs/b.txt", "docs/work/c.txt"), search.getMatches());
    }
}