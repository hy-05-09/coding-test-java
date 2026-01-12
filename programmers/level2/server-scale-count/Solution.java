/*
 * [Programmers][level2] - 서버 증설 횟수
 * Tag: 2025 프로그래머스 코드챌린지 2차 예선
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/389479
 * 
 * Idea:
 * - currentServer: 현재 운영 중인 (증설된) 서버 수 + 기본 서버 1대 포함
 * - serverLimit[t]: t시에 "반납(만료)될 서버 수"를 누적해두는 배열
 * - 각 시간 i에서:
 *   1) serverLimit[i]만큼 currentServer를 줄이며 만료 처리
 *   2) players[i]를 감당할 수 있을 때까지 서버를 1대씩 증설
 *      (증설할 때마다 totalAddedServer++, 만료 시점 i+k에 serverLimit[i+k]++)
 *
 * Key:
 * - 조건: players[i] >= m * currentServer 이면 부족하므로 서버 추가 필요
 * - i+k 접근이 있으므로 serverLimit 크기는 24+k로 확보
 *
 * Time Complexity: O(24 + answer)  // 24시간 순회 + 증설 횟수만큼(최대 24000 수준)
 * Space Complexity: O(24 + k)
 */

class Solution {
    public int solution(int[] players, int m, int k) {
        int currentServer = 1;
        int totalAddedServer = 0;
        int[] serverLimit = new int[24+k];
        
        for (int i=0; i < players.length; i++){
            while (serverLimit[i] != 0){
                currentServer--;
                serverLimit[i]--;
            }
            while (players[i] >= m*currentServer){
                currentServer++;
                totalAddedServer++;
                serverLimit[i+k]++;
            }
        }
        
        return totalAddedServer;
    }
}