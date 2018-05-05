import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tatiana Sedneva 2018
 *Idea:
 *Divide a dataset into two heaps.
 *One contains biggest  half of numbers sorting by descending order,
 *another with small number sorting ascending order.
 *So at the peek of two heaps we are going two have two middle numbers of dataset.
 *So, if size of peaks are equal (dataset contains an even number of elements), get two peaks and count median.
 *If size of one heap is bigger then another (dataset contains an odd number of elements), median is a peak if biggest heap.
 */
public class Main {

    public static PriorityQueue maxHeap = new PriorityQueue();

    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (Integer o1, Integer o2) -> -1 * o1.compareTo(o2)
    );

    public static void heapsAdd(int n) {
        if (maxHeap.size() == 0) {
            maxHeap.add(n);
        } else {
            if (n < (Integer) maxHeap.peek()) {
                minHeap.add(n);
            } else {
                maxHeap.add(n);
            }
        }
        normalize(maxHeap, minHeap);
    }

    public static void normalize(PriorityQueue maxHeap, PriorityQueue minHeap) {
        if (Math.abs(maxHeap.size() - minHeap.size()) >= 2) {
            if (maxHeap.size() > minHeap.size()) {
                int a = (Integer) maxHeap.poll();
                minHeap.add(a);
            } else {
                int a = (Integer) minHeap.poll();
                maxHeap.add(a);
            }
        }
    }

    public static double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((Integer) maxHeap.peek() + (Integer) minHeap.peek()) / 2.0;
        } else {
            if (maxHeap.size() > minHeap.size()) {
                return (Integer) maxHeap.peek();
            } else {
                return (Integer) minHeap.peek();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            heapsAdd(a);
            double mediana = getMedian();
            System.out.println(mediana);
        }
        sc.close();
    }
}
