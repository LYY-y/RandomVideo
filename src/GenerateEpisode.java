/**顶层
 * 1.给出集的数量，随机出这些集->实现：从文件中取出n个数->generateEpisode
 * 2.输入某一个集，确认已看过不会再出现->实现：从文件中删除指定的数->deleteEpisode
 *
 * 模块（类）：
 * 1.储存（未看过的）->读取储存文件数据->episodeDao
 * 2.产生随机数（未看过）generateEpisode->读取行，以行
 * 3.记录已看过deleteEpisode
 * */

/**随机生成集数*/
public class GenerateEpisode {

    public int[] GenerateEpisode(int n){
        int[] results = null;
        int[][] episodes = EpisodeDAO.readEpisode("F:\\Personal\\Desktop\\RandomVideo\\Episode.txt",5);
        int chapterTemp=this.craetRandom(0,episodes.length);
        int episodeTemp=this.craetRandom(0,episodes[chapterTemp][episodes[chapterTemp].length-1]);
        return results;
    }

    public int craetRandom(int begin, int last){
        int randoms = 0;
        randoms = (int) (Math.random() * (last - begin) + begin);
        return randoms;
    }


    public void printRandom(int[] episodes){
        for (int i=0; i<episodes.length; i=i+2) {
            System.out.print("第" + episodes[i] + "季 第" + episodes[i+1] + "集");
        }
    }

    /**二分法查找数组是否存在目标数*/
    public int isContains(int[] episodesLine, int target){
        int left = 0;
        int right = episodesLine.length-1;
        while (left < right){
            int mid = (left+right)>>>1;
            if (episodesLine[mid] < target){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,5,9,10,15};
        System.out.println(new GenerateEpisode().isContains(arr,1));
    }
}
