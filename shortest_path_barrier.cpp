//给定一个二维矩阵，其中包含起点、终点和障碍物。要求找到从起点到终点的最短路径，并打印该路径。
#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include<algorithm>

using namespace std;

// 定义移动的四个方向：上、下、左、右
const vector<pair<int, int>> directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

// BFS 找到最短路径
vector<pair<int, int>> findShortestPath(const vector<vector<int>>& matrix, pair<int, int> start, pair<int, int> end) {
    int rows = matrix.size();
    int cols = matrix[0].size();
    vector<vector<bool>> visited(rows, vector<bool>(cols, false));
    vector<vector<pair<int, int>>> prev(rows, vector<pair<int, int>>(cols, {-1, -1}));  // 存储前驱节点

    queue<pair<int, int>> q;
    q.push(start);
    visited[start.first][start.second] = true;

    // 开始 BFS
    while (!q.empty()) {
        auto [x, y] = q.front();
        q.pop();

        // 如果到达终点，构建路径
        if (make_pair(x, y) == end) {
            vector<pair<int, int>> path;
            for (pair<int, int> at = end; at != make_pair(-1, -1); at = prev[at.first][at.second])
                path.push_back(at);
            reverse(path.begin(), path.end());
            return path;
        }

        // 遍历四个方向
        for (const auto& [dx, dy] : directions) {
            int nx = x + dx, ny = y + dy;

            // 检查新位置是否合法、不是障碍物且未访问过
            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && matrix[nx][ny] == 0 && !visited[nx][ny]) {
                q.push({nx, ny});
                visited[nx][ny] = true;
                prev[nx][ny] = {x, y};  // 记录前驱节点
            }
        }
    }
    return {};  // 如果找不到路径，返回空路径
}

// 打印路径
void printPath(const vector<pair<int, int>>& path) {
    if (path.empty()) {
        cout << "No path found" << endl;
        return;
    }
    cout << "Shortest path:" << endl;
    for (const auto& [x, y] : path) {
        cout << "(" << x << ", " << y << ") ";
    }
    cout << endl;
}

int main() {
    // 示例矩阵：0 表示路径，1 表示障碍物
    vector<vector<int>> matrix = {
        {0, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0, 1, 0},
        {0, 1, 0, 1, 0, 0, 0},
        {0, 0, 1, 1, 0, 0, 0},
        {1, 0, 1, 0, 0, 1, 0}
    };

    pair<int, int> start = {0, 0};  // 起点坐标
    pair<int, int> end = {4, 6};    // 终点坐标

    vector<pair<int, int>> path = findShortestPath(matrix, start, end);
    printPath(path);

    return 0;
}