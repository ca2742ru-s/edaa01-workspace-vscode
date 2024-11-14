package testqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;
import java.util.Iterator;

class TestAppendFifoQueue {
    private FifoQueue<Integer> firstQueue;
    private FifoQueue<Integer> secondQueue;

    @BeforeEach
    void setUp(){
        firstQueue = new FifoQueue<Integer>();
        secondQueue = new FifoQueue<Integer>();
    }

    @AfterEach
    void tearDown(){
        firstQueue = null;
        secondQueue = null;
    }

    @Test
    void concatenateEmpty(){
        //Assert that queues are empty
        assertEquals(0, firstQueue.size(), "firstQueue is not empty");
        assertEquals(null, firstQueue.peek(), "firstQueue is not empty (last element not null)");
        assertEquals(0, secondQueue.size(), "secondQueue is not empty");
        assertEquals(null, secondQueue.peek(), "secondQueue is not empty (last element not null)");
        
        
        //Append queues
        firstQueue.append(secondQueue);

        //Assert that concatenated queue is empty
        assertEquals(0, firstQueue.size(), "concatenated is not empty after concatenation"); 
        assertEquals(null, firstQueue.peek(), "concatenated queue is not empty (last element not null)");  
    }

    @Test
    void concatenateEmptyWithNonEmpty(){
        //Create non-empty queue
        for(int i = 1; i < 6; i++){
            secondQueue.offer(i);
        }

        //Assert firstQueue empty and secondQueue not empty
        assertEquals(0, firstQueue.size(), "firstQueue is not empty");
        assertEquals(null, firstQueue.peek(), "firstQueue is not empty (last element not null)");
        assertNotEquals(0, secondQueue.size(), "secondQueue is empty");
        assertNotEquals(null, secondQueue.peek(), "secondQueue is empty (last is null)");
        //Save size for comparison
        int size = secondQueue.size();

        //Iterator to compare inserted elements after concatenation
        Iterator<Integer> itr = secondQueue.iterator();
        //Append firstQueue with secondQueue
        firstQueue.append(secondQueue);

        //Assert firstQueue correct size and secondQueue is empty after concatenation
        assertEquals(size, firstQueue.size());
        assertEquals(0, secondQueue.size(), "secondQueue is not empty after concatenation");
        
        //Assert correct order of elements
        for (Integer e : firstQueue) {
            assertEquals(e, itr.next(), "Wrong order of elements after concatenation");
        }
    }

    @Test
    void concatenateNonEmptyWithEmpty(){
        //Create non-empty queue
        for(int i = 1; i < 6; i++){
            firstQueue.offer(i);
        }

        //Assert firstQueue not empty and secondQueue empty
        assertNotEquals(0, firstQueue.size(), "firstQueue is empty");
        assertNotEquals(null, firstQueue.peek(), "firstQueue is empty (last is null)");
        assertEquals(0, secondQueue.size(), "secondQueue is not empty");
        assertEquals(null, secondQueue.peek(), "secondQueue is not empty (last is not null)");
        //Save size for comparison after concatenation
        int size = firstQueue.size();

        //Iterator to compare order of elements after concatenation
        Iterator<Integer> itr = firstQueue.iterator();
        firstQueue.append(secondQueue);

        //Assert correct size after concatenation
        assertEquals(size, firstQueue.size());

        //Assert correct order of elements
        for (Integer e : firstQueue) {
            assertEquals(e, itr.next(), "Wrong order of elements after concatenation");
        }
    }

    @Test
    void concatenateTwoNoneEmpty(){
        //Create two non-empty queues
        for(int i = 1; i < 6; i++){
            firstQueue.offer(i);
            secondQueue.offer(i+5);
        }
        //Assert non-empty sizes of queues
        assertNotEquals(0, firstQueue, "firstQueue is empty");
        assertNotEquals(0, secondQueue, "secondQueue is empty");
        assertNotEquals(null, firstQueue.peek(), "firstQueue is not empty (last is not null)");
        assertNotEquals(null, secondQueue.peek(), "secondQueue is not empty (last is not null)");

        //Save sizes for comparison after concatenation
        int sizeFirst = firstQueue.size();
        int sizeSecond = secondQueue.size();

        Iterator<Integer> firstItr = firstQueue.iterator();
        Iterator<Integer> secondItr = secondQueue.iterator();
        

        firstQueue.append(secondQueue);
        

        //Assert firstQueue having correct size and secondQueue being empty after concatenation
        assertEquals(firstQueue.size(), sizeFirst + sizeSecond, "Size not correct after concatenation");
        assertEquals(0, secondQueue.size(), "secondQueue is not empty after concatenation");

        //Assert correct order of elements
        for (Integer e : firstQueue) {
            if(firstItr.hasNext()){
                assertEquals(e, firstItr.next(), "Elements are not in correct order");
            }
            else
                assertEquals(e, secondItr.next(), "Elements are not in correct order");
            
        }

    }

    @Test
    void concatenateSame(){
        //Create non-empty queues
        for(int i = 1; i < 6; i++){
            firstQueue.offer(i);
            secondQueue.offer(i+5);
        }
        //Assert illegalArgumentException is thrown when concatenating the same queue
        assertThrows(IllegalArgumentException.class, () -> firstQueue.append(firstQueue));
        assertThrows(IllegalArgumentException.class, () -> secondQueue.append(secondQueue));
    }

}
