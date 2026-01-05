/*
 * [Programmers] PCCP 기출문제 1번 - 동영상 재생기
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/340213
 *
 * Idea:
 * - 시간을 초 단위로 변환해 시뮬레이션
 * - prev/next 처리 후, 현재 위치가 오프닝 구간이면 op_end로 스킵
 *
 * Edge cases:
 * - cur < 10초면 prev -> 0
 * - len - cur < 10초면 next -> len
 * - 시작 위치가 오프닝 구간인 경우도 먼저 스킵
 *
 * Complexity: O(commands.length)
 */

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int len = toSec(video_len);
        int cur = toSec(pos);
        int opStart = toSec(op_start);
        int opEnd = toSec(op_end);
        
        
        cur = skipOpening(cur, opStart, opEnd);
        
        for (String cmd : commands){
            if ("prev".equals(cmd)){
                if (cur <= 10) cur = 0;
                else cur -= 10;
            }
            else if ("next".equals(cmd)){
                if (cur >= len - 10) cur = len;
                else cur += 10;
            }
            cur = skipOpening(cur, opStart, opEnd);
        }
    
        String answer = "";
        answer = toTime(cur);
        return answer;
    }
    
    private int skipOpening(int cur, int opStart, int opEnd){
        if (cur >= opStart && cur <= opEnd ){
            return opEnd;
        } else return cur;
    }
    
    
    private int toSec(String time){
        int mm = Integer.parseInt(time.substring(0,2));
        int ss = Integer.parseInt(time.substring(3,5));
        return mm * 60 + ss;
    }

    private String toTime(int sec){
        int mm = sec / 60;
        int ss = sec % 60;
        return String.format("%02d:%02d", mm, ss);
    }
}