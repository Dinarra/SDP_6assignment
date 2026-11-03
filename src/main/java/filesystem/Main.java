package filesystem;

public class Main {

    public static void main(String[] args) {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode photos = new DirectoryNode("photos");
        DirectoryNode work = new DirectoryNode("work");

        root.addChild(docs);
        root.addChild(photos);
        docs.addChild(work);

        docs.addChild(new FileNode("notes.txt", 120));
        work.addChild(new FileNode("disks.jpg", 200));
        work.addChild(new FileNode("files.txt", 50));
        photos.addChild(new FileNode("photos.png", 67));

        System.out.println("Total size: " + TotalSizeVisitor.of(root));

        SearchVisitor search = new SearchVisitor(".txt", 100);
        root.accept(search);
        System.out.println("Search results: " + search.getMatches());

        StatisticsVisitor stats = new StatisticsVisitor();
        root.accept(stats);
        System.out.println("Files: " + stats.getFileCount());
        System.out.println("Directories: " + stats.getDirectoryCount());
        System.out.println("Max depth: " + stats.getMaxDepth());
    }
}