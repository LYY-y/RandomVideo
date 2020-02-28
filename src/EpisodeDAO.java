import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**存储未观看集数，每一行为一季，行的元素个数为集数*/
public class EpisodeDAO {

    public static int[][] readEpisode(String filePath, int chapter){
        int[][] episode=new int[chapter][];
        String[] episodesArr;
        String episodes;
        try {
            File file = new File(filePath);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            for (int i=0; i<chapter; i++) {
                episodes=randomAccessFile.readLine();
                episodesArr=episodes.split(" ");
                episode[i]=new int[episodesArr.length];
                for (int j = 0; j < episodesArr.length; j++) {
                    episode[i][j] = Integer.parseInt(episodesArr[j]);
                }
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return episode;
    }

    public static void deleteEpisode(String filePath, int chapter, int episode){
        File file = new File(filePath);
        String line = null;
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file,"rw");
            int n=0;
            while (n < chapter-1){
                randomAccessFile.readLine();
                n++;
            }
            long point = randomAccessFile.getFilePointer();

            line = randomAccessFile.readLine().replace(episode+"", " ");

            System.out.println(line);
            randomAccessFile.seek(point);
            randomAccessFile.write(line.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
