package ideolve.task.almost;

public class Main {
    public static void main(String[] args) {
        BookIndexer bookIndexer = new BookIndexer();
        bookIndexer.readExcludeWords("C:/Users/Sarthak/IdeaProjects/StudentManagementApp/src/ideolve/task/almost/exclude-words.txt");
        bookIndexer.readBookPages();
        bookIndexer.generateIndex();
        bookIndexer.writeIndexToFile("C:/Users/Sarthak/IdeaProjects/StudentManagementApp/src/ideolve/task/almost/index.txt");
        System.out.println("Index generated and written to 'index.txt'.");
    }
}
