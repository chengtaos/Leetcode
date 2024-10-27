import java.util.*;

public class ShortestPathInMatrix {

    // 静态内部类 Point，表示矩阵中的一个位置
    static class Point {
        int x;
        int y;
        Point parent; // 用于回溯路径

        public Point(int x, int y, Point parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }

    // 定义方向数组，表示上、下、左、右四个方向
    static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 寻找最短路径的函数
    public void findShortestPath(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Point start = new Point(0, 0, null);
        Point end = new Point(rows - 1, cols - 1, null);

        // 创建队列用于BFS
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        // 开始BFS搜索
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 如果到达终点，停止搜索
            if (current.x == end.x && current.y == end.y) {
                end = current;
                break;
            }

            // 遍历四个方向
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                // 检查新位置是否在矩阵范围内
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                    // 检查是否可通行且未被访问过
                    if (!visited[newX][newY] && matrix[newX][newY] == '0') {
                        queue.offer(new Point(newX, newY, current));
                        visited[newX][newY] = true;
                    }
                }
            }
        }

        // 如果找到路径，回溯并打印
        printPath(end, matrix);
    }

    private void printPath(Point end, char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (end.parent != null) {
            List<Point> path = new ArrayList<>();
            Point current = end;
            while (current != null) {
                path.add(current);
                current = current.parent;
            }
            Collections.reverse(path);
            System.out.println("找到最短路径，长度为：" + (path.size() - 1));
            System.out.println("路径为：");
            for (Point p : path) {
                System.out.println("(" + p.x + ", " + p.y + ")");
                // 在矩阵上标记路径
                if (matrix[p.x][p.y] == '0') {
                    matrix[p.x][p.y] = '*';
                }
            }

            // 打印标记路径后的矩阵
            System.out.println("路径图：");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("不存在从起点到终点的路径！");
        }
    }

    public static void main(String[] args) {
        // 示例矩阵
        char[][] matrix = {
                {'0', '0', '0', '0', '0'},
                {'0', '1', '1', '0', '1'},
                {'0', '1', '0', '0', '0'},
                {'0', '1', '0', '0', '0'},
                {'0', '0', '0', '1', '0'}
        };
        ShortestPathInMatrix test = new ShortestPathInMatrix();
        test.findShortestPath(matrix);
    }
}
