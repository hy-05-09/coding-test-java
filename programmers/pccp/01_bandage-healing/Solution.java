/*
 * [Programmers]PCCP 기출문제 1번 - 붕대 감기
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/250137?language=java
 *
 * Idea:
 * - 공격 시간이 오름차순, 시간 범위(<= 1000)가 작음 → 1초 단위 시뮬레이션.
 * - 매 초마다 "공격 여부"를 확인해
 *   - 공격이면: 회복 불가, 피해 적용, 연속 성공 시간(스트릭) 0으로 초기화
 *   - 비공격이면: 1초 회복(x) 적용 + 연속 성공 시간 증가
 *       - 연속 성공 시간이 t에 도달하면 추가 회복(y) 적용 후 스트릭 0으로 리셋
 * - 체력은 매번 최대 체력(maxHealth)을 넘지 않게 clamp.
 * - 공격 후 체력이 0 이하가 되면 즉시 -1 반환(사망하면 이후 회복 불가).
 *
 * Implementation details:
 * - 마지막 공격 시간(lastTime)까지만 루프를 돌면 모든 공격 직후 상태를 얻을 수 있음.
 * - attacks 배열을 순회하는 포인터(attackIdx)를 두고, 현재 시간 i가 다음 공격 시간과 같을 때만 피해 처리.
 *
 * Complexity:
 * - Time: O(lastTime) ≤ O(1000)  // 초 단위로 한 번 순회
 * - Space: O(1)
 */

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int time = attacks[attacks.length-1][0];
        int consecutive = 0;
        int attackIdx = 0;
        for (int i=1; i<=time; i++){
            if (attackIdx<= attacks.length && i==attacks[attackIdx][0]){
                health -= attacks[attackIdx][1];
                attackIdx ++;
                consecutive = 0;
                if (health <= 0)
                    return -1;
            }
            else {
                consecutive ++;
                if (consecutive == bandage[0]) 
                    health = Math.min(maxHealth, health+bandage[2]);
                health = Math.min(maxHealth, health+bandage[1]);
            }
        }
        
        return health;
    }
}