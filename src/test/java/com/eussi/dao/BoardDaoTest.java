package com.eussi.dao;

import com.eussi.dao.BoardDao;
import com.eussi.domain.Board;
import com.eussi.test.dataset.util.XlsDataSetBeanFactory;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@SpringApplicationContext({"eussiwebsite-dao.xml"})
public class BoardDaoTest  extends UnitilsTestNG {

    @SpringBean("boardDao")
    private BoardDao boardDao;

    /**
     * 创建一个新的论坛版块
     *
     * @param
     */

    @Test
    @ExpectedDataSet("EussiWebsite.ExpectedBoards.xls")
    public void addBoard() throws Exception {
        //通过XlsDataSetBeanFactory数据集绑定工厂创建测试实体
        List<Board> boards = XlsDataSetBeanFactory.createBeans(BoardDaoTest.class, "EussiWebsite.SaveBoards.xls", "t_board", Board.class);
        for (Board board : boards) {
            boardDao.save(board);
        }
    }

    /**
     * 删除一个版块
     *
     * @param
     */

    @Test
    @DataSet(value = "EussiWebsite.Boards.xls")//准备数据
    @ExpectedDataSet(value = "EussiWebsite.ExpectedBoards.xls")
    public void removeBoard() {
        Board board = boardDao.get(7);
        boardDao.remove(board);
    }

    @Test
    @DataSet("EussiWebsite.Boards.xls")//准备数据
    public void getBoard() {
        Board board = boardDao.load(1);
        assertNotNull(board);
        assertEquals(board.getBoardName(), "育儿");
    }
}
