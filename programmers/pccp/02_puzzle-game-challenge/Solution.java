/*
 * [Programmers]PCCP 기출문제 2번 / 퍼즐 게임 챌린지
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/340212?language=java
*
 * Approach: Parametric Search (Binary Search on level)
 *
 * Key observation:
 * - 숙련도 level이 커질수록(diff - level이 줄어들수록) 각 퍼즐에서 틀리는 횟수가 감소하므로
 *   전체 소요 시간(time)은 단조 감소한다.
 *   → "제한 시간(limit) 안에 가능한 최소 level"을 이분 탐색으로 찾는다.
 *
 * Time calculation for a fixed level:
 * - i번째 퍼즐의 난이도 = diffs[i], 현재 퍼즐 시간 = times[i], 이전 퍼즐 시간 = times[i-1]
 * - diffs[i] <= level:
 *     - 틀리지 않으므로 times[i]만 소요
 * - diffs[i] > level:
 *     - mistakes = diffs[i] - level 번 틀림
 *     - 한 번 틀릴 때마다 (times[i] + times[i-1]) 소요
 *     - 마지막으로 정답 처리에 times[i] 추가
 *     - 총: (times[i-1] + times[i]) * mistakes + times[i]
 * - 곱셈이 발생하므로 long 캐스팅으로 오버플로를 방지한다.
 *
 * Binary search details:
 * - search range: min=1 ~ max=max(diffs)
 * - mid(level)로 전체 time을 계산해
 *   - time > limit  → 숙련도 부족 → min = level + 1
 *   - time <= limit → 가능 → 더 낮은 level을 탐색 → max = level
 * - max==min이 되면 그 값이 최소 숙련도.
 *
 * Complexity:
 * - Time: O(n log maxDiff)
 * - Space: O(1)
 *
 * Note:
 * - 본 코드에서는 i=0에서 else 분기가 발생하지 않도록(diffs[0]=1, min=1) 문제 조건에 기대고 있다.
 *   (따라서 times[i-1] 접근이 안전해짐)
 */
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int min = 1;
        int max = diffs[0];
        int level = 0;
        for (int d: diffs)
            if (d > max) max = d;
        while (true){
            if (max==min) return min;
            level = (min+max)/2;
            long time = 0;
            for (int i = 0; i < times.length; i++){
                if (level >= diffs[i])
                    time += times[i];
                else {
                    time += ((long)(times[i-1]+times[i])*(diffs[i]-level));
                    time += times[i];
                }
            }
            if (time > limit)
                min = level+1;
            else max = level;
        }
    }
}