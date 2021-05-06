package day0506;

public class PROG_스킬트리_210506 {
	
	public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i=0;i<skill_trees.length;i++){
            String now = skill_trees[i];
            
            int sameCount = 0;
            for(int j=0;j<now.length();j++){
                for(int k=0;k<skill.length();k++){
                    if(now.charAt(j) == skill.charAt(k)) sameCount++;
                }
            }
            
            int idx = 0;
            for(int j=0;j<now.length();j++){
                if(idx > skill.length()-1) break;
                
                if(now.charAt(j) == skill.charAt(idx)) idx++;
            }
            
            if(idx == sameCount) answer++;
        }
        
        return answer;
    }

}
