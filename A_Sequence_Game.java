import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Sequence_Game {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
      int t=Integer.parseInt(br.readLine());
      while(t-->0){
          st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int arr[]=new int[n];
        st=new StringTokenizer(br.readLine());
        int target=Integer.parseInt(st.nextToken());
        for (int i = 0; i < n-1; i++) {
            int mn=Math.min(arr[i],arr[i+1]);
            int mx=Math.max(arr[i],arr[i+1]);
            if(target<=mx && target>=mn){
                System.out.println("yes");
                break;
            }

            
        }
        System.out.println("no");

      }
      
    }
}
