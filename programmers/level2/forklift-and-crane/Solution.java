/*
 * [Programmers][level2] - 지게차와 크레인
 * Tag: 2025 프로그래머스 코드챌린지 1차 예선
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/388353
 * 
 *
 * Idea:
 * - 요청 문자열 길이로 출고 방식 구분
 *   1글자: 지게차(접근 가능한 컨테이너만 제거)
 *   2글자: 크레인(해당 알파벳 전부 제거, 내부는 임시표시로 관리)
 *
 * Representation:
 * - '0' : 제거된(빈) 칸
 * - '1' : 크레인 처리 시 내부 컨테이너를 임시로 표시(외부와 연결되면 이후 제거 가능하도록)
 *
 * Approach:
 * - 요청마다 takeResult()로 창고 상태 갱신
 * - takeContainer():
 *   - 가장자리면 항상 접근 가능
 *   - 내부는 '0'과 연결되는 '1'들을 반복 전파(while change)로 외부 연결 영역 확장
 *   - 마지막에 (row,col) 주변 4방향 중 '0'이 있으면 접근 가능으로 판단
 * - 모든 요청 처리 후, 남아있는 컨테이너 수 = 전체 - ('0' 또는 '1' 개수)
 *
 * Edge cases:
 * - row/col 경계 처리(가장자리 접근 가능)
 * - 문자열 수정은 StringBuilder로 한 글자 치환
 *
 * Time Complexity: O(|requests| * n * m * 반복전파)
 * Space Complexity: O(n)  // 문자열 배열 복사 및 StringBuilder
 */

class Solution {
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int cnt = 0;
        int num = storage[0].length();
        for (int i=0; i < requests.length; i++){
            char s = requests[i].charAt(0);
            int takeAll = takeAll(requests[i]);
            storage = takeResult(storage, s, takeAll);
        }
        for (int i=0; i<storage.length; i ++){
            for (int j=0; j<num; j++){
                if (storage[i].charAt(j) == '0'||
                   storage[i].charAt(j) == '1') cnt++;
            }
        }
        answer = num * storage.length - cnt;
        return answer;
    }
    private int takeAll(String requests){
        if (requests.length() == 1) return 0;
        else return 1;
    }
    private int takeContainer(String[] storage, int row, int col){
        if (row==0 || col == 0 || row == storage.length-1 ||
           col == storage[0].length()-1) return 1;
        int change = -1;
        while (change != 0) {
            change = 0;
            for (int i=0; i < storage.length; i++){
            for (int j=0; j < storage[0].length(); j++){
                if (storage[i].charAt(j) == '1'){
                    if (storage[i-1].charAt(j)=='0' || 
                        storage[i+1].charAt(j)=='0' ||
                        storage[i].charAt(j-1)=='0' ||
                        storage[i].charAt(j+1)=='0')  {
                        StringBuilder sb = new StringBuilder(storage[i]);
                        sb.setCharAt(j, '0');
                        storage[i]=sb.toString();
                        change++;
                }
            }
        }
        }
        }
        if (storage[row-1].charAt(col)=='0' || 
            storage[row+1].charAt(col)=='0' ||
            storage[row].charAt(col-1)=='0' ||
            storage[row].charAt(col+1)=='0')
            return 1;
        return 0;
    }
        
    private String[] takeResult(String[] storage, char s, int takeAll){
        int idx = storage[0].length();
        int row = storage.length;
        String[] result = storage.clone();
        
        if (takeAll == 0) {
            for (int i=0; i<storage.length; i++){
                for (int j=0; j < idx; j++){
                    if (storage[i].charAt(j) == s && 
                       takeContainer(storage, i, j) == 1){
                        StringBuilder sb = new StringBuilder(result[i]);
                        sb.setCharAt(j, '0');
                        result[i]=sb.toString();
                    }
                }
            }
        }
        else {
            for (int i=0; i<storage.length; i++){
                for (int j=0; j < idx; j++){
                    if (storage[i].charAt(j) == s){
                        StringBuilder sb = new StringBuilder(result[i]);
                        if (takeContainer(storage,i,j)==1){
                            sb.setCharAt(j, '0');
                        } else sb.setCharAt(j, '1');
                        result[i]=sb.toString();
                    }
                }
            }
        }
        return result;
    }
}