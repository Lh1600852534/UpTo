package lh.cn.edu.henu.upto;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import lh.cn.edu.henu.upto.chatFrame.PieData;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        Random random = new Random();
        String r = Integer.toHexString(random.nextInt(256));
        String g = Integer.toHexString(random.nextInt(256));
        String b = Integer.toHexString(random.nextInt(256));
        String rgb = "0X" + r + g + b;
        //System.out.println(rgb);

        int a = 0Xaa;
        int c = 0XAA;
        assertEquals(a,c);

        ArrayList<PieData> arrayList = new ArrayList<>();

        PieData pieData1 = new PieData("a",1);
        arrayList.add(pieData1);

        PieData pieData2 = arrayList.get(0);
        pieData2.setName("b");
        pieData2.setValue(2);

        Integer integer = new Integer(1);
        int i = 1;
        System.out.println(integer == i);

        System.out.println(arrayList.get(0).getName());
        System.out.println(pieData1.getName());
        System.out.println(pieData1 == pieData2);



    }
}