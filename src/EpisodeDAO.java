import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**存储未观看集数，每一行为一季，行的元素个数为集数
 * 以ArrayList代表季，ArratList的元素TreeSet代表集数*/
public class EpisodeDAO {
    /**查找指定问件，指定读取的行数（即chapter）*/
    public static List readEpisode(String filePath, int chapter){
        String[] episodesArr;
        String episodes;
        RandomAccessFile randomAccessFile = initFile(filePath);

        /**初始化列表，list的长度为chapter*/
        List<TreeSet> list=new ArrayList<TreeSet>(chapter);
        for (int i=0; i<chapter; i++){
            list.add(i,new TreeSet());
        }

        /**逐行读取文件内容，以空格为分割，按季增添入list的set中，若为空字符串则跳过*/
        try {
            for (int i=0; i<chapter; i++) {
                episodes=randomAccessFile.readLine();
                episodesArr=episodes.split("\\s+");
                for (int j = 0; j < episodesArr.length; j++) {
                    if ("".equals(episodesArr[j])){
                        continue;
                    }
                    list.get(i).add(Integer.parseInt(episodesArr[j]));
                }
            }
            randomAccessFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**获取指定文件，删除指定的季数、集数*/
    public static boolean deleteEpisode(String filePath, int chapter, int episode){
        String line;
        boolean isDelete = false;
        RandomAccessFile randomAccessFile = initFile(filePath);

        try {
            /**将指针移到chapter的前，并用point定位*/
            int n=0;
            while (n < chapter-1){
                randomAccessFile.readLine();
                n++;
            }
            long point = randomAccessFile.getFilePointer();

            /**若point的下一行包含该集数，则将返回true，否则默认返回isDelete为false*/
            if (randomAccessFile.readLine().contains(episode+"")) {
                isDelete = true;
            }

            /**回到原来的指针point，对下一行的内容进行删除（替换为' '）*/
            randomAccessFile.seek(point);
            line=randomAccessFile.readLine().replace((char) (episode+'0'),  ' ');

            /**返回point指针，并写入文件*/
            randomAccessFile.seek(point);
            randomAccessFile.write(line.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isDelete;
    }

    /**获取指定文件，增添指定的季数、集数*/
    public static boolean addEpisode(String filePath, int chapter, int episode){
        boolean isAdd = true;
        RandomAccessFile randomAccessFile = initFile(filePath);
        try {
            /**将指针移到chapter的前，并用point定位*/
            int n=0;
            while (n < chapter-1) {
                randomAccessFile.readLine();
                n++;
            }
            long point = randomAccessFile.getFilePointer();

            randomAccessFile.seek(point);
            StringBuilder stringBuffer = new StringBuilder();
            byte[] bytes = new byte[100];
            int len;
            /**从该文件读取最多bytes.length个字节的数据到字节数组，以下使stringBuffer记录从piont到文件结尾的所有内容*/
            while ((len = randomAccessFile.read(bytes)) != -1){
                stringBuffer.append(new String(bytes,0,len));
            }
            randomAccessFile.seek(point);
            randomAccessFile.write((episode+" ").getBytes());
            byte[] b=stringBuffer.toString().getBytes();
            /**以下重新写入point到文件最后的部分*/
            randomAccessFile.write(stringBuffer.toString().getBytes());

        }catch (IOException e){
            isAdd = false;
            System.out.println(e.getMessage());
            return isAdd;
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isAdd;
    }

    /**初始化文件，指定文件格式为rw（可读写）*/
    public static RandomAccessFile initFile(String filePath){
        File file = new File(filePath);
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file,"rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            return randomAccessFile;
        }
    }
}
