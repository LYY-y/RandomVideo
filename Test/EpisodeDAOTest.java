import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* EpisodeDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>二月 27, 2020</pre> 
* @version 1.0 
*/ 
public class EpisodeDAOTest { 

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: readEpisode(String filePath, int chapter)
    *
    */
    @Test
    public void testReadEpisode() throws Exception {
        int[][] episode=EpisodeDAO.readEpisode("F:\\Personal\\Desktop\\RandomVideo\\Episode.txt",5);
        for (int i=0; i<episode.length; i++){
            for (int j=0; j<episode[i].length; j++) {
                System.out.print(episode[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
    *
    * Method: updateEpisode(String filePath, int chapter, int episode)
    *
    */
    @Test
    public void testUpdateEpisode() throws Exception {
        EpisodeDAO.deleteEpisode("F:\\Personal\\Desktop\\RandomVideo\\Episode.txt",1 ,5);
    }

} 
