import org.junit.Ignore;
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
    * Method: updateEpisode(String filePath, int chapter, int episode)
    *
    */
    @Test
    public void testDeleteEpisode() throws Exception {
        EpisodeDAO.deleteEpisode("F:\\Personal\\Desktop\\RandomVideo\\Episode.txt",1 ,5);
    }

    @Test
    public void testAddEpisode(){
        EpisodeDAO.addEpisode("F:\\Personal\\Desktop\\RandomVideo\\Episode.txt",5 ,5);
    }
} 
