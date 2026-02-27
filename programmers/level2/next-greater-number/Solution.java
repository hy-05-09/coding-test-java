/*
 * [Programmers][level2] 뒤에 있는 큰 수 찾기
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/154539?language=java
 *
 * Idea:
 * - 각 원소 numbers[i]에 대해, 오른쪽 구간(i+1 ~ n-1)에서 처음으로 등장하는 "더 큰 수"를 찾는다.
 * - 기본 접근은 선형 탐색(브루트포스):
 *   - j를 i+1부터 증가시키며 numbers[j] > numbers[i]인 첫 값을 찾으면 answer[i]에 저장하고 종료
 *   - 끝까지 없으면 answer[i] = -1
 *
 * Small optimizations (caching / reuse):
 * - 연속으로 같은 값이 반복되면(numbers[i] == numbers[i-1]) 결과도 동일하므로 answer[i-1]을 재사용
 * - 지금까지 본 최대값(max)과 그 위치(maxIdx)를 저장해두고,
 *   numbers[i]가 max와 같으면(= 과거에 같은 최대값이 등장) answer[maxIdx]를 재사용
 *
 * Edge cases:
 * - 마지막 원소는 오른쪽에 값이 없으므로 answer[n-1] = -1
 *
 * Complexity:
 * - Time: O(n^2) worst-case  // 기본적으로 각 i마다 오른쪽을 끝까지 훑을 수 있음
 * - Space: O(1) (excluding output array)
 *
 * Note:
 * - 이 문제는 단조 스택(monotonic stack)으로 O(n)에 최적화 가능하지만,
 *   본 코드는 브루트포스 기반 + 일부 재사용 최적화로 작성됨.
 */
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int max=-1;
        int maxIdx = -1;
        for (int i = 0; i < numbers.length-1; i++){
            int cur = numbers[i];
            answer[i] = -1;
            if (i>=1 && numbers[i]==numbers[i-1]){
                answer[i]=answer[i-1];
            }
            else if (numbers[i] == max) answer[i]=answer[maxIdx];
            else{
                for (int j= i+1; j< numbers.length; j++){
                    if (cur < numbers[j]){
                        answer[i]=numbers[j];
                        break;
                    }
                }   
            }
            if (numbers[i]>max) {
                max=numbers[i];
                maxIdx = i;
            }
        }
        answer[numbers.length-1] = -1;
        return answer;
    }
}