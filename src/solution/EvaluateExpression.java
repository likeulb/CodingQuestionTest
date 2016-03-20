package solution;

import java.util.Stack;

public class EvaluateExpression {
	public int evaluateExpression(String[] expression) {
        // write your code here
        if(expression==null||expression.length==0) return 0;
        Stack<Integer> data = new Stack<>();
        Stack<String> ope = new Stack<>();
        for(String s:expression){
            char cur = s.charAt(0);
            if(cur<'0'||cur>'9'){
                switch(cur){
                    case '(': ope.push(s);
                        break;
                    case '+':
                    case '-': 
                        while(!ope.isEmpty()&&ope.peek().charAt(0)!='('){
                            calculate(ope,data);
                        }
                        ope.push(s);
                        break;
                    case '*':
                    case '/':
                    	System.out.println(ope.isEmpty());
                        while(!ope.isEmpty()&&((ope.peek().charAt(0)=='*')||(ope.peek().charAt(0)=='/'))){
                            calculate(ope,data);
                        }
                        ope.push(s);
                        break;
                    case ')':
                        while(!ope.isEmpty()&&(ope.peek().charAt(0)!='(')){
                            calculate(ope,data);
                        }
                        ope.pop();
                }
            }
            else{
                data.push(Integer.parseInt(s));
            }
        }
        while(!ope.isEmpty()){
            calculate(ope,data);
        }
        return data.pop();
    }
    public void calculate(Stack<String> ope, Stack<Integer> data){
        char last = ope.pop().charAt(0);
        int sec=data.pop();
        int first=data.pop();
        switch(last){
            case '+': data.add(first+sec);
                break;
            case '-': data.add(first-sec);
                break;
            case '*': data.add(first*sec);
                break;
            case '/': data.add(first/sec);
                break;
        }
    }
    public static void main(String[] args){
    	EvaluateExpression test = new EvaluateExpression();
    	String[] s = {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};
    	test.evaluateExpression(s);
    }
}
