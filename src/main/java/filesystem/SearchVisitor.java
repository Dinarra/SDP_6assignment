package filesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class SearchVisitor implements Visitor {

    private final String nameFragment;
    private final long minSize;
    private final List<String> matches = new ArrayList<>();
    private final List<String> pathSegments = new ArrayList<>();
    private boolean rootVisited = false;

    public SearchVisitor(String nameFragment, long minSize) {
        this.nameFragment = nameFragment;
        this.minSize = minSize;
    }

    @Override
    public void visit(FileNode file) {
        // TODO 1: if the name contains nameFragment AND size >= minSize,
        // build the path (pathSegments + file name, joined with "/") and add it to matches
    }

    @Override
    public void visit(DirectoryNode directory) {
        boolean isRoot = !rootVisited;
        rootVisited = true;

        if (!isRoot) {
            pathSegments.add(directory.getName());
        }

        for (Element child : directory.getChildren()) {
            child.accept(this);
        }

        if (!isRoot) {
            pathSegments.remove(pathSegments.size() - 1);
        }
    }

    public List<String> getMatches() {
        List<String> sorted = new ArrayList<>(matches);
        Collections.sort(sorted);
        return List.copyOf(sorted);
    }
}