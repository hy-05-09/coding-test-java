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