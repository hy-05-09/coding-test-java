/*
 * [Programmers][연습문제] 연속된 부분 수열의 합
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/178870?language=java
 * Idea:
 * - 비내림차순 + 모든 원소 양수 → 투 포인터(슬라이딩 윈도우)로 합을 k에 맞춘다.
 * - 현재 구간 합(sum)이
 *   - k보다 작으면 구간 확장(왼쪽 포인터 start를 더 앞쪽으로 이동하며 sum에 더함)
 *   - k보다 크면 구간 축소(오른쪽 포인터 end를 줄이며 sum에서 뺌)
 *
 * Tie-breaking:
 * - 합이 k인 구간을 찾으면(현재 start~end),
 *   같은 값이 반복되는 경우 더 앞쪽/짧은 구간을 만들기 위해 start/end를 함께 당겨 정리(중복 구간 제거)
 *
 * Complexity:
 * - Time: O(n)  // 포인터가 배열을 최대 1~2번 정도만 훑음
 * - Space: O(1)
 */

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int length = sequence.length;
        int start=length-1, end=length-1;
        int sum=sequence[end];
        
        while(start>0 && end > 0){
            if (sum==k) {
                while (start>0 && sequence[start-1]==sequence[end]){
                    start--;
                    end--;
                }
                break;
            }
            if(sum<k){
                sum+=sequence[--start];
            }
            else if(sum>k){
                if (start==end){
                    start--; 
                    end--;
                    sum = sequence[end];
                }
                else sum-=sequence[end--];
            }
        }
        
        answer[0]=start;
        answer[1]=end;
        
        return answer;
    }
}