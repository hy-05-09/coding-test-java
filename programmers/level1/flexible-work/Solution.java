/*
 * [Programmers][Level 1] - 유연근무제 
 * Tag: 2025 프로그래머스 코드챌린지 1차 예선
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/388351
 
 *
 * Idea:
 * - 각 직원별로 "출근 인정 마감 시각" = 희망 시각 + 10분을 계산
 * - 7일 중 주말(토/일)은 제외하고 평일 5일만 검사
 * - 평일 5일 모두 실제 출근 시각 <= 마감 시각이면 상품 대상(answer++)
 *
 * Key points:
 * - 시각은 HHMM 정수이므로 +10 했을 때 분이 60을 넘으면 시 보정이 필요함
 *   (예: 8:55 -> 9:05)
 * - startday(1~7) 기준으로 j(0~6)일차의 요일을 계산해 토(6), 일(7)을 스킵
 *
 * Edge cases:
 * - startday가 7(일)일 때, 요일 계산이 다음 주로 넘어가므로 모듈러 처리/분기 주의
 * - 직원은 하루 1회만 출근 로그가 주어지므로 단순 비교로 충분
 *
 * Time Complexity: O(n * 7)  (n <= 1000)
 * Space Complexity: O(1)
 */

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i=0; i<schedules.length; i++){
            int sTime = timeToInt(schedules[i]);
            int num = 0;
            for (int j=0; j<timelogs[i].length;j++){
                if (startday + j == 6 || startday + j == 7) continue;
                if (startday == 7 && j == 6) continue;
                if (timelogs[i][j] <= sTime)
                    num++;
            }
            if (num == 5) answer++;
        }
        
        return answer;
    }
    
    private int timeToInt(int schedules){
        int sTime = 0;
        if (schedules % 100 >= 50){
            sTime = (schedules / 100 + 1) * 100 + schedules % 10;
        } else sTime = schedules + 10;
        return sTime;
    }
}