import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;;
public class MergeIntervals {
    public int[][] merge(int[][] intervals){
        if(intervals.length<=1)return intervals;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        List<int[]>res=new LinkedList<>();
        for(int[] interval:intervals){
            if(res.isEmpty()&&res.getLast()[1]<interval[0]){
                res.add(interval);
            }
            else{
                res.getLast()[1]=Math.max(res.getLast()[1],interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String args){
        MergeIntervals mergeintervals=new MergeIntervals();
        int[][] intervals = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };
        int[][]ans=mergeintervals.merge(intervals);
        System.out.println("合并后的区间：");
        for (int[] an :ans ) {
            System.out.println("[" + an[0] + ", " + an[1] + "]");
        }
    }
}