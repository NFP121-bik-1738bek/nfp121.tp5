package question1;

import java.util.Collection;

public class EnsembleTest extends junit.framework.TestCase {

    public void testUnion() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> union = e1.union(e2);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }

    public void testAdd() {
        question1.Ensemble<Integer> e1;
        e1 = new question1.Ensemble<Integer>();
        e1.add(8);
        assertTrue(e1.contains(8));
        assertEquals(1, e1.size());

        question1.Ensemble<Integer> e2;
        e2 = new question1.Ensemble<Integer>();
        e2.add(9);
        assertTrue(e1.addAll(e2));
    }


    
    public void testUnion2() {
        question1.Ensemble<Integer> e1;
        question1.Ensemble<Integer> e2;
        
        e1 = new question1.Ensemble<Integer>();
        e2 = new question1.Ensemble<Integer>();
        
        e1.add(2);
        e1.add(9);
        e1.add(73);
        e1.add(19);
        
        e2.add(79);
        e2.add(13);
        e2.add(2);
        
        question1.Ensemble<Integer> union = e1.union(e2);
        
        assertEquals(6, union.size());
        int[] testColl = {2, 9, 13, 19, 73, 79};
        for(int num : testColl) {
            assertTrue(union.contains(num));
        }
    }
    
    public void testInter() {
        question1.Ensemble<Integer> e1;
        question1.Ensemble<Integer> e2;
        
        e1 = new question1.Ensemble<Integer>();
        e2 = new question1.Ensemble<Integer>();
        
        e1.add(3);
        e1.add(9);
        e1.add(73);
        e1.add(19);
        
        e2.add(73);
        e2.add(19);
        e2.add(2);
        e2.add(11);

        question1.Ensemble<Integer> inter = e1.inter(e2);
        assertEquals(2, inter.size());
        int[] testColl = {19, 73};
        for(int num : testColl) {
            assertTrue(inter.contains(num));
        }
    }
    
    public void testDiff() {
        question1.Ensemble<Integer> e1;
        question1.Ensemble<Integer> e2;
        
        e1 = new question1.Ensemble<Integer>();
        e2 = new question1.Ensemble<Integer>();

        e1.add(3);
        e1.add(9);
        e1.add(73);
        e1.add(19);
        
        e2.add(73);
        e2.add(19);
        e2.add(2);
        e2.add(11);
        
        question1.Ensemble<Integer> diff = e1.diff(e2);
        assertEquals(2, diff.size());
        int[] testColl = {3, 9};
        for(int num : testColl) {
            assertTrue(diff.contains(num));
        }
    }
    
    public void testDiffSym() {
        question1.Ensemble<Integer> e1;
        question1.Ensemble<Integer> e2;
        
        e1 = new question1.Ensemble<Integer>();
        e2 = new question1.Ensemble<Integer>();
        
        e1.add(3);
        e1.add(9);
        e1.add(73);
        e1.add(19);
        
        e2.add(73);
        e2.add(19);
        e2.add(2);
        e2.add(11);
        
        question1.Ensemble<Integer> diffSym = e1.diffSym(e2);
        assertEquals(4, diffSym.size());
        int[] testColl = {3, 9, 2, 11};
        for(int num : testColl) {
            assertTrue(diffSym.contains(num));
        }
    
    
    }
    
    
}
