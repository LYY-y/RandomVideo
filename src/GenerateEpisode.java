/**顶层
 * 1.给出集的数量，随机出这些集->实现：从文件中取出n个数->generateEpisode
 * 2.输入某一个集，确认已看过不会再出现->实现：从文件中删除指定的数->deleteEpisode
 *
 * 模块（类）：
 * 1.储存（未看过的）->读取储存文件数据->episodeDao
 * 2.产生随机数（未看过）generateEpisode->读取行，以行
 * 3.记录已看过deleteEpisode
 * */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class GenerateEpisode {
    /**随机生成集数*/
    public List generateEpisode(String filePath, int chapter, int num){
        List<TreeSet> allEpisodesList = EpisodeDAO.readEpisode(filePath,chapter);
        int chaptersMax = allEpisodesList.size();
        int episodeMin = 0;
        int episodeMax = 0;
        List<TreeSet> list=new ArrayList<TreeSet>(chaptersMax);
        for (int i=0; i<chaptersMax; i++){
            list.add(i,new TreeSet());
        }
        int count = 0;
        while (count <= num ) {
            int chapterTemp=this.craetRandom(0,chaptersMax);
            if (allEpisodesList.get(chapterTemp).size() == 0){
                continue;
            }
            episodeMin = (int) allEpisodesList.get(chapterTemp).first();
            episodeMax = (int) allEpisodesList.get(chapterTemp).last();
            int episodeTemp=this.craetRandom(episodeMin,episodeMax);
            if (!list.get(chapterTemp).contains(episodeTemp)) {
                list.get(chapterTemp).add(episodeTemp);
                count++;
            }else {
                continue;
            }
        }
        return list;
    }

    public int craetRandom(int begin, int last){
        int randoms = 0;
        randoms = (int) (Math.random() * (last - begin) + begin);
        return randoms;
    }

    public void printRandom(List<TreeSet> list){
        for (int i=0; i<list.size(); i++) {
            Iterator iterator = list.get(i).iterator();
            while (iterator.hasNext()){
                System.out.print("第" + (i+1) + "季 第" + (iterator.next()) + "集\n");
            }
        }
    }



    /**更新集数*/
    public boolean updateEpisodes(String filePath, int chapter, int episode, String mode){
        if ("del".equals(mode)) {
            return EpisodeDAO.deleteEpisode(filePath, chapter, episode);
        }else if ("add".equals(mode)){
            return EpisodeDAO.addEpisode(filePath, chapter, episode);
        }
        return false;
    }

    public static void main(String[] args){
        String filePath = "F:\\Personal\\Desktop\\RandomVideo\\Episode.txt";
        GenerateEpisode generateEpisode = new GenerateEpisode();
        generateEpisode.updateEpisodes(filePath,4,1,"del");
        generateEpisode.updateEpisodes(filePath, 5, 6, "add");
        generateEpisode.printRandom(generateEpisode.generateEpisode(filePath,5,9));

    }
}
