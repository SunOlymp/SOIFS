import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder log = new StringBuilder();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        File gamesDir = new File("/Users/olymp/Games");
        File logDirectory = new File("/Users/olymp/Games/temp/temp.txt");

        if (!gamesDir.exists()) {
            gamesDir.mkdir();
            log.append(formattedDate).append("Папка Games успешно создана.");
        }


        createDir(gamesDir, "src", log, formattedDate);
        createDir(new File(gamesDir, "src"), "main", log, formattedDate);
        createDir(new File(gamesDir, "src"), "test", log, formattedDate);
        createNewFile(new File(gamesDir, "src/main"), "Main.java", log, formattedDate);
        createNewFile(new File(gamesDir, "src/main"), "Utils.java", log, formattedDate);


        createDir(gamesDir, "res", log, formattedDate);
        createDir(new File(gamesDir, "res"), "drawables", log, formattedDate);
        createDir(new File(gamesDir, "res"), "vectors", log, formattedDate);
        createDir(new File(gamesDir, "res"), "icons", log, formattedDate);

        createDir(gamesDir, "savegames", log, formattedDate);

        createDir(gamesDir, "temp", log, formattedDate);
        createNewFile(new File(gamesDir, "temp"), "temp.txt", log, formattedDate);

        logger(logDirectory, log);
    }

    private static void createDir(File parentFolder, String newDirectory, StringBuilder log, String timeDate) {
        File dir = new File(parentFolder, newDirectory);
        if (dir.mkdir()) {
            log.append(timeDate).append(" папка ").append(newDirectory).append(" успешно создана.\n");
        } else {
            log.append(timeDate).append(" папка ").append(newDirectory).append(" уже существует.\n");
        }
    }

    private static void createNewFile(File parentFolder, String newFile, StringBuilder log, String timeDate) {
        File file = new File(parentFolder, newFile);
        try {
            if (file.createNewFile()) {
                log.append(timeDate).append(" файл ").append(newFile).append(" успешно создан.\n");
            } else if (file.exists()) {
                log.append(timeDate).append(" файл ").append(newFile).append(" уже существует.\n");
            }
        } catch (IOException ex) {
            log.append(timeDate).append(" Произошла ошибка при создании файла ").append(newFile + ".\n");
            ex.printStackTrace();
        }
    }

    private static void logger(File directoryFile, StringBuilder log) throws Exception {

        FileOutputStream fos = new FileOutputStream(directoryFile, true);

        PrintWriter writer = new PrintWriter(fos);

        try {
            writer.println(log.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }


}
