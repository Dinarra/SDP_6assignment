package filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        if (file.getName().contains(nameFragment) && file.getSizeInBytes() >= minSize) {
            List<String> parts = new ArrayList<>(pathSegments);
            parts.add(file.getName());
            matches.add(String.join("/", parts));
        }
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