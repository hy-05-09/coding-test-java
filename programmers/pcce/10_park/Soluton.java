/*
 * [Programmers][PCCE 기출] 10번 - 공원
 * Link: https://school.programmers.co.kr/learn/courses/30/lessons/340198
 *
 * Idea:
 * - DP로 "빈칸(-1)만으로 만들 수 있는 가장 큰 정사각형"의 한 변 max를 구함
 * - result[i][j] = (i,j)를 우하단으로 하는 최대 정사각형 한 변
 *   if park[i][j] == "-1":
 *     result[i][j] = (i==0||j==0) ? 1 : min(left, up, diag) + 1
 *   else result[i][j] = 0
 * - mats 중 mat <= max 인 것 중 최댓값을 answer로 반환, 없으면 -1
 *
 * Edge cases:
 * - 문자열 비교는 "-1".equals(...)
 * - 첫 행/첫 열은 경계 처리 필요
 *
 * Time: O(R*C)  (R,C <= 50)
 * Space: O(R*C)
 */


import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        int rows = park.length;
        int cols = park[0].length;
        int max = 0;
        int[][] result = new int[rows][cols];
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if ("-1".equals(park[i][j])){
                    if (i==0|j==0) result[i][j]=1;
                    else {
                        result[i][j] = 
                            Math.min(result[i][j-1],
                            Math.min(result[i-1][j-1], result[i-1][j]))+1;
                    }
                    if (result[i][j]>max) max=result[i][j];
                } else result[i][j]=0;
            }
        }
        
        for (int mat : mats){
            if (mat <= max && mat > answer) answer = mat;
        }
        
        return answer;
    }
    
}