/*
 * [Programmers][PCCE 기출] 9번 - 지폐 접기
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/340199
 *
 * Idea:
 * - wallet/bill의 (작은변, 큰변)을 정렬해 비교
 * - bill이 wallet에 들어갈 때까지 큰 변을 2로 나누며 접기
 * - 접을 때마다 (작은변, 큰변) 순서가 깨질 수 있어 재정렬
 *
 * Edge cases:
 * - 90도 회전 가능하므로 항상 (small, big)로 비교
 * - 나누기 2는 정수 나눗셈(홀수면 버림)
 *
 * Time Complexity: O(answer)  // 접는 횟수만큼 반복 (최대 로그 수준)
 * Space Complexity: O(log(max(bill)))
 */

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int wSmall = Math.min(wallet[0],wallet[1]);
        int wBig = Math.max(wallet[0],wallet[1]);
        int bSmall = Math.min(bill[0],bill[1]);
        int bBig = Math.max(bill[0],bill[1]);
        for (int i=0;;i++) {
            if (bSmall > wSmall || bBig > wBig){
                answer ++;
                bBig /= 2;
                if (bSmall > bBig){
                    int x = bSmall;
                    bSmall = bBig;
                    bBig = x;
                }
            }  else break;  
        }
        
        
        return answer;
    }
    
    
}