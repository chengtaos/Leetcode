//leetcode 56 合并区间
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start(i), end(i)] 。
//合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> merge_intervals(vector<vector<int>> intervals) {
    if (intervals.empty()) return {};  // 检查是否为空
    sort(intervals.begin(), intervals.end());  // 先排序

    vector<vector<int>> res;
    for (const auto& interval : intervals) {
        int left = interval[0], right = interval[1];
        // 插入条件修改：当 res 为空或当前区间与 res 最后一个区间不重叠时
        if (res.empty() || res.back()[1] < left) {
            res.push_back({left, right});
        } else {
            // 更新当前的右边界
            res.back()[1] = max(res.back()[1], right);
        }
    }
    return res;
}

int main() {
    vector<vector<int>> intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    vector<vector<int>> res = merge_intervals(intervals);
    for (const auto& interval : res) {
        cout << "[" << interval[0] << ", " << interval[1] << "] ";
    }
    cout << endl;
    return 0;
}
