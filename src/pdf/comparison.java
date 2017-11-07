/*
 * comparison.java
 *
 * Created on September 5, 2008, 11:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

/**
 *
 * @author mar
 */
public class comparison {
    
    /** Creates a new instance of comparison */
    public comparison() {
    }
    public void cmp(int[] a)
    {
        int Biggest=0;
     for (int i = 0; i < a.length; i++) {
            Biggest=i;
            for (int j = i+1; j < a.length; j++) {
                if(a[j]>a[Biggest])
                    Biggest=j;
                //output+=para1[Biggest].text;
                swap(i,Biggest,a);
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]+" ");
        }
        
    }

    private void swap(int i, int i0,int [] a) {
        int temp=a[i];
        a[i]=a[i0];
        a[i0]=temp;
    }
   
    

 public static void main(String[] args) {
        int [] a={2,3,5,4,8,6,7,9};
        comparison c=new comparison();
        c.cmp(a);
    }
}
