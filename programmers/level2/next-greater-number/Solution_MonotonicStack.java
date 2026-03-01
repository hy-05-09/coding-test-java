/*
 * [Programmers][level2] 뒤에 있는 큰 수 찾기 (Monotonic Stack)
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/154539?language=java
 *
 * Idea:
 * - 각 원소 numbers[i]에 대해, 오른쪽에서 "자기보다 큰 수" 중 가장 가까운 값을 찾아야 한다.
 * - 브루트포스로 하면 i마다 오른쪽을 선형 탐색하므로 O(n^2)까지 커질 수 있어(최대 n=1,000,000) 시간 초과가 발생한다.
 * - 이를 단조 스택(monotonic stack)으로 O(n)에 해결한다.
 *
 * Monotonic stack approach:
 * - 스택에는 "아직 뒷 큰수를 못 찾은 인덱스"를 저장한다. (값이 아니라 인덱스를 저장해야 중복 값도 구분 가능)
 * - 스택은 numbers[인덱스] 기준으로 '내림차순(감소)'이 유지되도록 관리한다.
 *
 * Process:
 * - i를 0부터 n-1까지 순회하며 현재 값 cur = numbers[i]를 본다.
 * - cur가 스택 top 인덱스의 값(numbers[top])보다 크면,
 *   cur은 top 인덱스의 "가장 가까운 뒷 큰수"가 된다.
 *   -> answer[top] = cur로 확정하고 pop한다.
 * - 위 조건이 깨질 때까지(= 더 이상 cur이 큰 수가 아닐 때까지) while로 연쇄 처리한다.
 * - 이후 i는 아직 뒷 큰수를 못 찾았으므로 스택에 push한다.
 *
 * Edge cases:
 * - 끝까지 순회해도 뒷 큰수를 찾지 못한 인덱스들은 정답이 -1이다.
 *   -> 순회 후 스택에 남은 인덱스들을 모두 -1 처리한다.
 *
 * Complexity:
 * - Time: O(n)
 *   - 각 인덱스는 스택에 최대 1번 push, 최대 1번 pop 되므로 전체 연산은 선형이다.
 * - Space: O(n) (excluding output array)
 */
import java.util.ArrayDeque;
import java.util.Deque;

class Solution_MonotonicStack {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++){
            int cur = numbers[i];
            while(!stack.isEmpty() && numbers[stack.peek()] < cur){
                answer[stack.pop()] = cur;
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}